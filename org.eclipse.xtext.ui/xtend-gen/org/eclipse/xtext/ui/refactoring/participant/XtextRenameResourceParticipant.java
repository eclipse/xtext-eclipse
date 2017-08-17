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
import java.util.function.Consumer;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ui.refactoring.participant.LtkIssueAcceptor;
import org.eclipse.xtext.ui.refactoring.participant.ResourceURIConverter;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveResourceProcessor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
@SuppressWarnings("all")
public class XtextRenameResourceParticipant extends RenameParticipant implements ISharableParticipant {
  @Inject
  private LtkIssueAcceptor issues;
  
  @Inject
  @Extension
  private ResourceURIConverter _resourceURIConverter;
  
  @Inject
  private XtextMoveResourceProcessor processor;
  
  private List<ResourceURIChange> folderUriChanges = CollectionLiterals.<ResourceURIChange>newArrayList();
  
  private List<ResourceURIChange> fileUriChanges = CollectionLiterals.<ResourceURIChange>newArrayList();
  
  private Set<IResource> renamedResources = CollectionLiterals.<IResource>newHashSet();
  
  private IProject project;
  
  private Change change;
  
  @Override
  public RefactoringStatus checkConditions(final IProgressMonitor pm, final CheckConditionsContext context) throws OperationCanceledException {
    try {
      this.change = this.processor.createChange(this.getName(), this.fileUriChanges, this.folderUriChanges, this.project, this.issues, this.renamedResources, pm);
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
    return "Xtext rename resource participant";
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
    if ((arguments instanceof RenameArguments)) {
      if ((element instanceof IResource)) {
        if ((this.project == null)) {
          this.project = ((IResource)element).getProject();
        }
        final IPath oldPath = ((IResource)element).getFullPath();
        final IPath newPath = oldPath.removeLastSegments(1).append(((RenameArguments)arguments).getNewName());
        this.addResource(((IResource)element), oldPath, newPath, ((RenameArguments)arguments));
      }
    }
  }
  
  protected void addResource(final IResource resource, final IPath oldPath, final IPath newPath, final RenameArguments arguments) {
    try {
      boolean _isPrefixOf = oldPath.isPrefixOf(resource.getFullPath());
      if (_isPrefixOf) {
        final URI oldURI = this._resourceURIConverter.toURI(resource);
        final URI newURI = this._resourceURIConverter.toURI(newPath.append(resource.getFullPath().removeFirstSegments(oldPath.segmentCount())));
        final ResourceURIChange uriChange = new ResourceURIChange(oldURI, newURI);
        this.renamedResources.add(resource);
        if ((resource instanceof IFile)) {
          this.fileUriChanges.add(uriChange);
        } else {
          if ((resource instanceof IContainer)) {
            this.folderUriChanges.add(uriChange);
            final Consumer<IResource> _function = (IResource member) -> {
              this.addResource(member, oldPath, newPath, arguments);
            };
            ((List<IResource>)Conversions.doWrapArray(((IContainer)resource).members())).forEach(_function);
          }
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
