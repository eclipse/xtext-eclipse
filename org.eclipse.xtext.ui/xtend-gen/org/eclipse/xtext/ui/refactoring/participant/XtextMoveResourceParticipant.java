/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.refactoring.participant;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments;
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy;
import org.eclipse.xtext.ide.serializer.IChangeSerializer;
import org.eclipse.xtext.ui.refactoring.impl.RefactoringResourceSetProvider;
import org.eclipse.xtext.ui.refactoring.participant.ChangeConverter;
import org.eclipse.xtext.ui.refactoring.participant.LtkIssueAcceptor;
import org.eclipse.xtext.ui.refactoring.participant.ResourceURIUtil;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveParticipantStrategyRegistry;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
@SuppressWarnings("all")
public class XtextMoveResourceParticipant extends MoveParticipant implements ISharableParticipant {
  @Inject
  private IChangeSerializer changeSerializer;
  
  @Inject
  private ChangeConverter changeConverter;
  
  @Inject
  private RefactoringResourceSetProvider resourceSetProvider;
  
  @Inject
  private LtkIssueAcceptor issues;
  
  @Inject
  private XtextMoveParticipantStrategyRegistry strategyRegistry;
  
  @Inject
  @Extension
  private ResourceURIUtil _resourceURIUtil;
  
  private List<ResourceURIChange> uriChanges = CollectionLiterals.<ResourceURIChange>newArrayList();
  
  private Set<IFile> modifiedElements = CollectionLiterals.<IFile>newHashSet();
  
  private IProject project;
  
  @Override
  public RefactoringStatus checkConditions(final IProgressMonitor pm, final CheckConditionsContext context) throws OperationCanceledException {
    return this.issues.getRefactoringStatus();
  }
  
  @Override
  public Change createChange(final IProgressMonitor pm) throws CoreException, OperationCanceledException {
    boolean _isEmpty = this.uriChanges.isEmpty();
    if (_isEmpty) {
      return null;
    }
    final ResourceSet resourceSet = this.resourceSetProvider.get(this.project);
    final XtextMoveArguments moveArguments = new XtextMoveArguments(resourceSet, this.uriChanges);
    for (final ResourceURIChange move : this.uriChanges) {
      {
        final Resource resource = resourceSet.getResource(move.getOldURI(), true);
        this.changeSerializer.beginRecordChanges(resource);
      }
    }
    for (final ResourceURIChange move_1 : this.uriChanges) {
      {
        final Resource resource = resourceSet.getResource(move_1.getOldURI(), true);
        resource.setURI(move_1.getNewURI());
      }
    }
    this.applyMove(moveArguments);
    final Predicate<Change> _function = (Change it) -> {
      return ((!(it instanceof MoveResourceChange)) || (!this.modifiedElements.contains(it.getModifiedElement())));
    };
    this.changeConverter.initialize(this.getName(), _function, 
      this.issues);
    this.changeSerializer.endRecordChanges(this.changeConverter);
    return this.changeConverter.getChange();
  }
  
  protected void applyMove(final XtextMoveArguments moveArguments) {
    final Consumer<XtextMoveResourceStrategy> _function = (XtextMoveResourceStrategy it) -> {
      it.applyMove(moveArguments, this.issues);
    };
    this.strategyRegistry.getStrategies().forEach(_function);
  }
  
  @Override
  public String getName() {
    return "Xtext move participant";
  }
  
  @Override
  protected boolean initialize(final Object element) {
    boolean _xblockexpression = false;
    {
      this.addElement(element, this.getArguments());
      _xblockexpression = true;
    }
    return _xblockexpression;
  }
  
  @Override
  public void addElement(final Object element, final RefactoringArguments arguments) {
    if ((arguments instanceof MoveArguments)) {
      if ((element instanceof IFile)) {
        final URI oldURI = this._resourceURIUtil.toURI(((IResource)element));
        boolean _isXtextResource = this._resourceURIUtil.isXtextResource(oldURI);
        if (_isXtextResource) {
          final Object destination = ((MoveArguments)arguments).getDestination();
          if ((destination instanceof IFolder)) {
            if ((this.project == null)) {
              this.project = ((IFile)element).getProject();
            }
            final IFile destinationFile = ((IFolder)destination).getFile(((IFile)element).getName());
            URI _uRI = this._resourceURIUtil.toURI(((IResource)element));
            URI _uRI_1 = this._resourceURIUtil.toURI(destinationFile);
            ResourceURIChange _resourceURIChange = new ResourceURIChange(_uRI, _uRI_1);
            this.uriChanges.add(_resourceURIChange);
            this.modifiedElements.add(((IFile)element));
          }
        }
      }
    }
  }
}
