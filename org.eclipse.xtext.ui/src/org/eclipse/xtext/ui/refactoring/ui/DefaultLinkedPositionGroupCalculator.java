/*******************************************************************************
 * Copyright (c) 2011, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.refactoring.ui;

import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Lists.*;
import static org.eclipse.xtext.util.Strings.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.link.LinkedPositionGroup;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.findReferences.IReferenceFinder;
import org.eclipse.xtext.findReferences.TargetURIConverter;
import org.eclipse.xtext.resource.IGlobalServiceProvider;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.impl.DefaultReferenceDescription;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.refactoring.ElementRenameArguments;
import org.eclipse.xtext.ui.refactoring.IDependentElementsCalculator;
import org.eclipse.xtext.ui.refactoring.ILinkedPositionGroupCalculator;
import org.eclipse.xtext.ui.refactoring.IRefactoringUpdateAcceptor;
import org.eclipse.xtext.ui.refactoring.IReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy.Provider.NoSuchStrategyException;
import org.eclipse.xtext.ui.refactoring.IRenamedElementTracker;
import org.eclipse.xtext.ui.refactoring.impl.CachingResourceSetProvider;
import org.eclipse.xtext.ui.refactoring.impl.IRefactoringDocument;
import org.eclipse.xtext.ui.refactoring.impl.ProjectUtil;
import org.eclipse.xtext.ui.refactoring.impl.RefactoringResourceSetProvider;
import org.eclipse.xtext.ui.refactoring.impl.StatusWrapper;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Calculates the linked positions for simultaneous editing when a refactoring is triggered in linked mode.
 * 
 * @author Jan Koehnlein - Initial contribution and API
 */
public class DefaultLinkedPositionGroupCalculator implements ILinkedPositionGroupCalculator {

	private static final Logger LOG = Logger.getLogger(DefaultLinkedPositionGroupCalculator.class);

	@Inject
	private ProjectUtil projectUtil;

	@Inject
	private RefactoringResourceSetProvider resourceSetProvider;

	@Inject
	private IGlobalServiceProvider globalServiceProvider;

	@Inject
	private IRenamedElementTracker renamedElementTracker;

	@Inject
	private IResourceServiceProvider.Registry resourceServiceProviderRegistry;

	@Inject
	private IReferenceFinder referenceFinder;

	@Inject
	private IReferenceUpdater referenceUpdater;
	
	@Inject
	private TargetURIConverter targetURIConverter;

	@Inject
	private Provider<LocalResourceRefactoringUpdateAcceptor> updateAcceptorProvider;

	@Override
	public Provider<LinkedPositionGroup> getLinkedPositionGroup(
			IRenameElementContext renameElementContext,
			IProgressMonitor monitor) {
		final SubMonitor progress = SubMonitor.convert(monitor, 100);
		final XtextEditor editor = (XtextEditor) renameElementContext.getTriggeringEditor();
		IProject project = projectUtil.getProject(renameElementContext.getContextResourceURI());
		if (project == null)
			throw new IllegalStateException("Could not determine project for context resource "
					+ renameElementContext.getContextResourceURI());
		
		RefactoringResourceSetProvider resourceSetProvider = new CachingResourceSetProvider(DefaultLinkedPositionGroupCalculator.this.resourceSetProvider);
		
		ResourceSet resourceSet = resourceSetProvider.get(project);
		EObject targetElement = resourceSet.getEObject(renameElementContext.getTargetElementURI(), true);
		if (targetElement == null)
			throw new IllegalStateException("Target element could not be loaded");
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		IRenameStrategy.Provider strategyProvider = globalServiceProvider.findService(targetElement,
				IRenameStrategy.Provider.class);
		IRenameStrategy renameStrategy = null;
		try {
			renameStrategy = strategyProvider.get(targetElement, renameElementContext);
		} catch(NoSuchStrategyException exc) {
			// handle in next line
		}
		if(renameStrategy == null) 
			throw new IllegalArgumentException("Cannot find a rename strategy for "
					+ notNull(renameElementContext.getTargetElementURI()));
		String newName = renameStrategy.getOriginalName();
		IResourceServiceProvider resourceServiceProvider = resourceServiceProviderRegistry.getResourceServiceProvider(renameElementContext.getTargetElementURI());
		IDependentElementsCalculator dependentElementsCalculator =  resourceServiceProvider.get(IDependentElementsCalculator.class);
		Iterable<URI> dependentElementURIs = dependentElementsCalculator.getDependentElementURIs(targetElement,
				progress.newChild(10));
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		LocalResourceRefactoringUpdateAcceptor updateAcceptor = updateAcceptorProvider.get();
		updateAcceptor.setLocalResourceURI(renameElementContext.getContextResourceURI());
		renameStrategy.createDeclarationUpdates(newName, resourceSet, updateAcceptor);
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		Map<URI, URI> original2newEObjectURI = renamedElementTracker.renameAndTrack(
				concat(Collections.singleton(renameElementContext.getTargetElementURI()), dependentElementURIs),
				newName, resourceSet, renameStrategy, progress.newChild(10));
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		ElementRenameArguments elementRenameArguments = new ElementRenameArguments(
				renameElementContext.getTargetElementURI(), newName, renameStrategy, original2newEObjectURI, resourceSetProvider);
		final List<IReferenceDescription> referenceDescriptions = newArrayList();
		IReferenceFinder.Acceptor referenceAcceptor = new IReferenceFinder.Acceptor() {
			@Override
			public void accept(IReferenceDescription referenceDescription) {
				referenceDescriptions.add(referenceDescription);
			}
			@Override
			public void accept(EObject source, URI sourceURI, EReference eReference, int index, EObject targetOrProxy,
					URI targetURI) {
				referenceDescriptions.add(new DefaultReferenceDescription(EcoreUtil2.getFragmentPathURI(source), targetURI, eReference, index, null));
			}
		};
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		referenceFinder.findReferences(
				targetURIConverter.fromIterable(elementRenameArguments.getRenamedElementURIs()),
				resourceSet.getResource(renameElementContext.getContextResourceURI(), true),
				referenceAcceptor, progress.newChild(10));
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		referenceUpdater.createReferenceUpdates(elementRenameArguments, referenceDescriptions, updateAcceptor,
				progress.newChild(60));
		final List<ReplaceEdit> textEdits = updateAcceptor.getTextEdits();
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		final IRenameStrategy renameStrategy2 = renameStrategy;
		return new Provider<LinkedPositionGroup>() {

			@Override
			public LinkedPositionGroup get() {
				LinkedPositionGroup linkedGroup = createLinkedGroupFromReplaceEdits(textEdits, editor,
						renameStrategy2.getOriginalName(), progress.newChild(10));
				return linkedGroup;
			}
		};
	}

	protected LinkedPositionGroup createLinkedGroupFromReplaceEdits(List<ReplaceEdit> edits, XtextEditor xtextEditor,
			final String originalName, SubMonitor progress) {
		if (edits == null)
			return null;
		final IXtextDocument document = xtextEditor.getDocument();
		LinkedPositionGroup group = new LinkedPositionGroup();
		Iterable<LinkedPosition> linkedPositions = filter(
				Iterables.transform(edits, new Function<ReplaceEdit, LinkedPosition>() {
					@Override
					public LinkedPosition apply(ReplaceEdit edit) {
						try {
							String textToReplace = document.get(edit.getOffset(), edit.getLength());
							int indexOf = textToReplace.indexOf(originalName);
							if (indexOf != -1) {
								int calculatedOffset = edit.getOffset() + indexOf;
								return new LinkedPosition(document, calculatedOffset, originalName.length());
							}
						} catch (BadLocationException exc) {
							LOG.error("Skipping invalid text edit " + notNull(edit), exc);
						}
						return null;
					}
				}), Predicates.notNull());
		progress.worked(10);
		final int invocationOffset = xtextEditor.getInternalSourceViewer().getSelectedRange().x;
		int i = 0;
		for (LinkedPosition position : sortPositions(linkedPositions, invocationOffset)) {
			try {
				position.setSequenceNumber(i);
				i++;
				group.addPosition(position);
			} catch (BadLocationException e) {
				LOG.error(e.getMessage(), e);
				return null;
			}
		}
		return group;
	}

	protected Iterable<LinkedPosition> sortPositions(Iterable<LinkedPosition> linkedPositions,
			final int invocationOffset) {
		Comparator<LinkedPosition> comparator = new Comparator<LinkedPosition>() {

			@Override
			public int compare(LinkedPosition left, LinkedPosition right) {
				return rank(left) - rank(right);
			}

			private int rank(LinkedPosition o1) {
				int relativeRank = o1.getOffset() + o1.length - invocationOffset;
				if (relativeRank < 0)
					return Integer.MAX_VALUE + relativeRank;
				else
					return relativeRank;
			}
		};
		return ImmutableSortedSet.copyOf(comparator, linkedPositions);
	}

	public static class LocalResourceRefactoringUpdateAcceptor implements IRefactoringUpdateAcceptor {

		@Inject
		private IRefactoringDocument.Provider refactoringDocumentProvider;

		@Inject
		private StatusWrapper status;

		private List<ReplaceEdit> textEdits = newArrayList();
		private URI localResourceURI;

		public void setLocalResourceURI(URI localResourceURI) {
			this.localResourceURI = localResourceURI;
		}

		public List<ReplaceEdit> getTextEdits() {
			return textEdits;
		}

		@Override
		public StatusWrapper getRefactoringStatus() {
			return status;
		}

		@Override
		public IRefactoringDocument getDocument(URI resourceURI) {
			return refactoringDocumentProvider.get(resourceURI, status);
		}

		@Override
		public Change createCompositeChange(String name, IProgressMonitor monitor) {
			return null;
		}

		@Override
		public void accept(URI resourceURI, Change change) {
			// ignore
		}

		@Override
		public void accept(URI resourceURI, TextEdit textEdit) {
			if (localResourceURI.equals(resourceURI) && textEdit instanceof ReplaceEdit) {
				textEdits.add((ReplaceEdit) textEdit);
			}
		}
	}

}
