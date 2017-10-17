/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.model.edit;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.PerformChangeOperation;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.ide.serializer.IChangeSerializer;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.refactoring2.ChangeConverter;
import org.eclipse.xtext.ui.refactoring2.LtkIssueAcceptor;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.LiveScopeResourceSetInitializer;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noreference This class is not intended to be referenced by clients.
 * 
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class BatchModification {

	public interface IBatchableModification {
		void apply(EObject object, IChangeSerializer serializer);

		URI getEObjectURI();

		boolean isUpdateCrossReferences();

		boolean isUpdateRelatedFiles();
	}

	protected class ResolvedModification {
		private final IBatchableModification modification;
		private final EObject object;

		public ResolvedModification(EObject object, IBatchableModification modification) {
			super();
			this.object = object;
			this.modification = modification;
		}
	}

	private final static Logger LOG = Logger.getLogger(BatchModification.class);
	@Inject
	private ChangeConverter.Factory changeConverterFactory;
	private IXtextDocument document;
	@Inject
	private LtkIssueAcceptor issueAcceptor;
	@Inject
	private LiveScopeResourceSetInitializer liveScopeResourceSetInitializer;
	private IProject project;
	@Inject
	private IResourceSetProvider resourceSetProvider;
	@Inject
	private Provider<IChangeSerializer> serializerProvider;

	public void apply(Iterable<IBatchableModification> modifications, IProgressMonitor monitor) {
		if (Iterables.isEmpty(modifications)) {
			return;
		}
		try {
			new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
					applyInWorkspace(modifications, monitor);
				}
			}.run(monitor);
		} catch (InvocationTargetException | InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	protected void applyInWorkspace(Iterable<IBatchableModification> modifications, IProgressMonitor monitor) throws CoreException {
		IProject proj = getResolvedProject();
		if (proj == null) {
			LOG.error("No project configured.");
			return;
		}
		ResourceSet resourceSet = resourceSetProvider.get(proj);
		liveScopeResourceSetInitializer.initialize(resourceSet);
		List<ResolvedModification> resolved = Lists.newArrayList();
		for (IBatchableModification modification : modifications) {
			EObject obj = resourceSet.getEObject(modification.getEObjectURI(), true);
			if (obj == null || obj.eIsProxy()) {
				LOG.error("Invlid EObject URI " + modification.getEObjectURI());
				continue;
			}
			resolved.add(new ResolvedModification(obj, modification));
		}
		IChangeSerializer serializer = serializerProvider.get();
		serializer.setProgressMonitor(monitor);
		for (ResolvedModification mod : resolved) {
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			mod.modification.apply(mod.object, serializer);
		}
		boolean first = true;
		for (ResolvedModification mod : resolved) {
			if (first) {
				serializer.setUpdateCrossReferences(mod.modification.isUpdateCrossReferences());
				serializer.setUpdateRelatedFiles(mod.modification.isUpdateRelatedFiles());
			} else {
				if (serializer.isUpdateCrossReferences() != mod.modification.isUpdateCrossReferences()) {
					LOG.error("two modifications can't be batched.");
					return;
				}
				if (serializer.isUpdateRelatedFiles() != mod.modification.isUpdateRelatedFiles()) {
					LOG.error("two modifications can't be batched.");
					return;
				}
			}
		}
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		ChangeConverter converter = changeConverterFactory.create("Resolving Issues", null, issueAcceptor);
		serializer.applyModifications(converter);
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		Change change = converter.getChange();
		change.initializeValidationData(monitor);
		new PerformChangeOperation(change).run(monitor);
	}

	public IXtextDocument getDocument() {
		return document;
	}

	public IProject getProject() {
		return project;
	}

	public IProject getResolvedProject() {
		if (project != null) {
			return project;
		}
		if (document != null) {
			IResource resource = document.getAdapter(IResource.class);
			if (resource != null) {
				return resource.getProject();
			}
		}
		return null;
	}

	public void setDocument(IXtextDocument document) {
		this.document = document;
	}

	public void setProject(IProject project) {
		this.project = project;
	}
}
