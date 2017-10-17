package org.eclipse.xtext.ui.editor.quickfix;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextEditorInfo;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocumentUtil;
import org.eclipse.xtext.ui.editor.model.edit.BatchModification.IBatchableModification;
import org.eclipse.xtext.ui.util.IssueUtil;
import org.eclipse.xtext.validation.Issue;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * @author Heiko Behrens - Initial contribution and API
 */
public class MarkerResolutionGenerator extends AbstractIssueResolutionProviderAdapter implements IMarkerResolutionGenerator2 {

	@Inject
	private XtextEditorInfo editorInfo;
	
	private String editorId = null;
	
	@Inject
	private IssueUtil issueUtil;
	
	@Inject
	private ILanguageResourceHelper languageResourceHelper;

	@Inject 
	private IWorkbench workbench;

	@Inject
	private WorkbenchMarkerResolutionAdapter.Factory adapterFactory;

	public IssueUtil getIssueUtil() {
		return issueUtil;
	}

	public void setIssueUtil(IssueUtil issueUtil) {
		this.issueUtil = issueUtil;
	}

	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}

	public String getEditorId() {
		if(editorId == null){
			editorId = editorInfo.getEditorId();
		}
		return editorId;
	}

	@Override
	public boolean hasResolutions(IMarker marker) {
		return getResolutionProvider().hasResolutionFor(getIssueUtil().getCode(marker));
	}

	@Override
	public IMarkerResolution[] getResolutions(IMarker marker) {
		final IMarkerResolution[] emptyResult = new IMarkerResolution[0];
		try {
			if(!marker.isSubtypeOf(MarkerTypes.ANY_VALIDATION))
				return emptyResult;
		} catch (CoreException e) {
			return emptyResult;
		}
		if(!languageResourceHelper.isLanguageResource(marker.getResource())) {
			return emptyResult;
		}
		Issue issue = getIssueUtil().createIssue(marker);
		if (issue == null)
			return emptyResult;
		List<IssueResolution> resolutions = getResolutionProvider().getResolutions(issue);
		boolean isMultiFix = resolutions.stream().allMatch(e -> e.getModification() instanceof IBatchableModification);
		if (isMultiFix) {
			// TODO report a warning if there is a mixup between context and no context modifications
			return getAdaptedWorkbenchResolutions(Lists.newArrayList(resolutions), marker);
		}
		if (findEditor(marker.getResource()) != null) {
			XtextEditor editor = getEditor(marker.getResource());
			if (editor == null)
				return emptyResult;

			IAnnotationModel annotationModel = editor.getDocumentProvider().getAnnotationModel(editor.getEditorInput());
			if (annotationModel != null && !isMarkerStillValid(marker, annotationModel))
				return emptyResult;

			return getAdaptedResolutions(Lists.newArrayList(getResolutions(issue, editor.getDocument())));
		}
		return getAdaptedResolutions(Lists.newArrayList(getResolutionProvider().getResolutions(issue)));
	}

	public boolean isMarkerStillValid(final IMarker marker, final IAnnotationModel annotationModel) {
		Iterator<Annotation> iterator = annotationModel.getAnnotationIterator();
		return Iterators.any(iterator, new Predicate<Annotation>() {

			@Override
			public boolean apply(Annotation annotation) {
				if (annotation.isMarkedDeleted())
					return false;
				return referringToSameIssue(annotation, marker);
			}

			private boolean referringToSameIssue(Annotation annotation, IMarker marker) {
				if(getIssueUtil().refersToSameIssue(marker, annotation)) {
					return true;
				}
				return false;
			}
		});
	}

	public IXtextDocument getXtextDocument(IResource resource) {
		IXtextDocument result = XtextDocumentUtil.get(resource);
		if(result == null) {
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			try {
				IFile file = ResourceUtil.getFile(resource);
				IEditorInput input = new FileEditorInput(file);
				page.openEditor(input, getEditorId());
			} catch (PartInitException e) {
				return null;
			}
		}
		return XtextDocumentUtil.get(resource);
	}
	
	public XtextEditor getEditor(IResource resource) {
		XtextEditor result = findEditor(resource);
		if(result == null) {
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			try {
				IFile file = ResourceUtil.getFile(resource);
				IEditorInput input = new FileEditorInput(file);
				result = (XtextEditor) page.openEditor(input, getEditorId());
			} catch (PartInitException e) {
				return null;
			}
		}
			
		return result;
	}
	
	public XtextEditor findEditor(IResource resource) {
		if(resource instanceof IFile) {
			IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
			IEditorPart editor = activePage.findEditor(new FileEditorInput((IFile) resource));
			if(editor instanceof XtextEditor)
				return (XtextEditor)editor;
		}
		return null;

	}

	public class ResolutionAdapter implements IMarkerResolution2 {

		private final IssueResolution resolution;

		public ResolutionAdapter(IssueResolution resolution) {
			this.resolution = resolution;
		}

		@Override
		public String getLabel() {
			return resolution.getLabel();
		}

		@Override
		public void run(IMarker marker) {
			resolution.apply();
		}

		@Override
		public String getDescription() {
			return resolution.getDescription();
		}

		@Override
		public Image getImage() {
			return MarkerResolutionGenerator.this.getImage(resolution);
		}

	}

	protected IMarkerResolution[] getAdaptedResolutions(List<IssueResolution> resolutions) {
		IMarkerResolution[] result = new IMarkerResolution[resolutions.size()];
		for(int i=0; i<resolutions.size(); i++)
			result[i] = new ResolutionAdapter(resolutions.get(i));

		return result;
	}

	protected IMarkerResolution[] getAdaptedWorkbenchResolutions(List<IssueResolution> resolutions, IMarker marker) {
		IMarkerResolution[] result = new IMarkerResolution[resolutions.size()];
		for (int i = 0; i < resolutions.size(); i++) {
			result[i] = adapterFactory.create(marker, resolutions.get(i));
		}
		return result;
	}

}
