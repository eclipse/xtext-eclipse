/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.refactoring.participant;

import com.google.inject.Inject;
import java.util.List;
import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ui.refactoring.participant.LtkIssueAcceptor;
import org.eclipse.xtext.ui.refactoring.participant.ResourceURIConverter;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveResourceProcessor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
@SuppressWarnings("all")
public class XtextMoveResourceParticipant extends MoveParticipant implements ISharableParticipant {
  @Inject
  private LtkIssueAcceptor issues;
  
  @Inject
  @Extension
  private ResourceURIConverter _resourceURIConverter;
  
  @Inject
  private XtextMoveResourceProcessor processor;
  
  private List<ResourceURIChange> fileUriChanges = CollectionLiterals.<ResourceURIChange>newArrayList();
  
  private Set<IFile> movedFiles = CollectionLiterals.<IFile>newHashSet();
  
  private IProject project;
  
  private Change change;
  
  @Override
  public RefactoringStatus checkConditions(final IProgressMonitor pm, final CheckConditionsContext context) throws OperationCanceledException {
    try {
      this.change = this.processor.createChange(this.getName(), this.fileUriChanges, CollectionLiterals.<ResourceURIChange>emptyList(), this.project, this.issues, this.movedFiles, pm);
      return this.issues.getRefactoringStatus();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public Change createChange(final IProgressMonitor pm) throws CoreException, OperationCanceledException {
    return this.change;
  }
  
  @Override
  public String getName() {
    return "Xtext move resource participant";
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
        final Object destination = ((MoveArguments)arguments).getDestination();
        if ((destination instanceof IFolder)) {
          if ((this.project == null)) {
            this.project = ((IFile)element).getProject();
          }
          final IFile destinationFile = ((IFolder)destination).getFile(((IFile)element).getName());
          URI _uRI = this._resourceURIConverter.toURI(((IResource)element));
          URI _uRI_1 = this._resourceURIConverter.toURI(destinationFile);
          ResourceURIChange _resourceURIChange = new ResourceURIChange(_uRI, _uRI_1);
          this.fileUriChanges.add(_resourceURIChange);
          this.movedFiles.add(((IFile)element));
        }
      }
    }
  }
}
