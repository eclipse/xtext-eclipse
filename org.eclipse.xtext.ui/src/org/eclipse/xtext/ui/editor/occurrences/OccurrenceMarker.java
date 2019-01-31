/*******************************************************************************
 * Copyright (c) 2011, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.occurrences;

import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Sets.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelExtension;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.xtext.ui.editor.XtextEditor;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class OccurrenceMarker {
	
	private ISelectionChangedListener selectionListener;

	private boolean isMarkOccurrences;

	private XtextEditor editor;

	@Inject
	private MarkOccurrenceJob markOccurrenceJob;

	public void connect(XtextEditor editor, boolean isMarkOccurrences) {
		this.editor = editor;
		((IPostSelectionProvider) editor.getSelectionProvider())
				.addPostSelectionChangedListener(getSelectionChangedListener());
		this.isMarkOccurrences = isMarkOccurrences;
	}

	public void disconnect(XtextEditor editor) {
		((IPostSelectionProvider) editor.getSelectionProvider())
				.removePostSelectionChangedListener(getSelectionChangedListener());
		this.editor = null;
	}

	public void setMarkOccurrences(boolean isMarkOccurrences) {
		this.isMarkOccurrences = isMarkOccurrences;
		doMarkOccurrences(editor.getSelectionProvider().getSelection());
	}

	protected void doMarkOccurrences(final ISelection selection) {
		if (selection instanceof ITextSelection) {
			XtextEditor editor = this.editor;
			if (editor != null) {
				markOccurrenceJob.cancel();
				markOccurrenceJob.initialize(editor, (ITextSelection) selection, isMarkOccurrences);
				if (!markOccurrenceJob.isSystem())
					markOccurrenceJob.setSystem(true);
				markOccurrenceJob.setPriority(Job.DECORATE);
				markOccurrenceJob.schedule();
			}
		}
	}

	protected ISelectionChangedListener getSelectionChangedListener() {
		if (selectionListener == null)
			selectionListener = createSelectionChangedListener();
		return selectionListener;
	}

	protected ISelectionChangedListener createSelectionChangedListener() {
		return new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (isMarkOccurrences)
					doMarkOccurrences(event.getSelection());
			}
		};
	}
	
	/**
	 * Public for testing purpose.
	 * 
	 * @since 2.17
	 */
	@VisibleForTesting
	public void joinMarkOccurrenceJob() {
		try {
			doMarkOccurrences(editor.getSelectionProvider().getSelection());
			this.markOccurrenceJob.join();
			Display display = Display.getCurrent();
			if (display != null) {
				while(display.readAndDispatch()) {
					synchronized (this) {
						wait(1);
					}		
				}
			}
		} catch (InterruptedException e) {
			// ignore
			e.printStackTrace();
		}	
	}

	public static class MarkOccurrenceJob extends Job {

		@Inject
		private IOccurrenceComputer occurrenceComputer;

		private XtextEditor initialEditor;
		private volatile boolean initialIsMarkOccurrences;
		private ITextSelection initialSelection;

		public MarkOccurrenceJob() {
			super(Messages.OccurrenceMarker_MarkOccurrenceJob_title);
		}

		public void initialize(XtextEditor editor, ITextSelection selection, boolean isMarkOccurrences) {
			this.initialEditor = editor;
			this.initialIsMarkOccurrences = isMarkOccurrences;
			this.initialSelection = selection;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			final XtextEditor editor = initialEditor;
			final boolean isMarkOccurrences = initialIsMarkOccurrences;
			final ITextSelection selection = initialSelection;
			final SubMonitor progress = SubMonitor.convert(monitor, 2);
			if (!progress.isCanceled()) {
				final Map<Annotation, Position> annotations = (isMarkOccurrences) ? occurrenceComputer.createAnnotationMap(editor, selection,
						progress.newChild(1)) : Collections.<Annotation, Position>emptyMap();
				if (!progress.isCanceled()) {
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							if (!progress.isCanceled()) {
								final IAnnotationModel annotationModel = getAnnotationModel(editor);
								if (annotationModel instanceof IAnnotationModelExtension)
									((IAnnotationModelExtension) annotationModel).replaceAnnotations(
											getExistingOccurrenceAnnotations(annotationModel), annotations);
								else if(annotationModel != null)
									throw new IllegalStateException(
											"AnnotationModel does not implement IAnnotationModelExtension");  //$NON-NLS-1$
							}
						}
					});
					return Status.OK_STATUS;
				}
			}
			return Status.CANCEL_STATUS;
		}

		protected IAnnotationModel getAnnotationModel(XtextEditor editor) {
			if(editor != null) {
				IEditorInput editorInput = editor.getEditorInput();
				if(editorInput != null)  {
					IDocumentProvider documentProvider = editor.getDocumentProvider();
					if(documentProvider != null) {
						return documentProvider.getAnnotationModel(editorInput);
					}
				}
			}
			return null;
		}
		
		protected Annotation[] getExistingOccurrenceAnnotations(IAnnotationModel annotationModel) {
			Set<Annotation> removeSet = newHashSet();
			for (Iterator<Annotation> annotationIter = annotationModel.getAnnotationIterator(); annotationIter
					.hasNext();) {
				Annotation annotation = annotationIter.next();
				if (occurrenceComputer.hasAnnotationType(annotation.getType())) {
					removeSet.add(annotation);
				}
			}
			return toArray(removeSet, Annotation.class);
		}
	}
}
