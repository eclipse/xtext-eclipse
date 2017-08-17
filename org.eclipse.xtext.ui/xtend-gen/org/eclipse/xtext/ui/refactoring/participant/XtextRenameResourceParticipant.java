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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ide.refactoring.XtextMoveFolderArguments;
import org.eclipse.xtext.ui.refactoring.participant.LtkIssueAcceptor;
import org.eclipse.xtext.ui.refactoring.participant.ResourceURIUtil;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveResourceProcessor;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.LiveScopeResourceSetInitializer;
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
  private IResourceSetProvider resourceSetProvider;
  
  @Inject
  private LiveScopeResourceSetInitializer liveScopeResourceSetInitializer;
  
  @Inject
  private LtkIssueAcceptor issues;
  
  @Inject
  @Extension
  private ResourceURIUtil _resourceURIUtil;
  
  @Inject
  private XtextMoveResourceProcessor processor;
  
  private List<ResourceURIChange> folderUriChanges = CollectionLiterals.<ResourceURIChange>newArrayList();
  
  private List<ResourceURIChange> uriChanges = CollectionLiterals.<ResourceURIChange>newArrayList();
  
  private Set<IResource> modifiedResources = CollectionLiterals.<IResource>newHashSet();
  
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
    this.liveScopeResourceSetInitializer.initialize(resourceSet);
    final XtextMoveFolderArguments moveFolderArguments = new XtextMoveFolderArguments(resourceSet, this.uriChanges, this.folderUriChanges);
    return this.processor.createChange(this.getName(), moveFolderArguments, this.issues, this.modifiedResources, pm);
  }
  
  @Override
  public String getName() {
    return "Xtext rename participant";
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
      if ((element instanceof IContainer)) {
        if ((this.project == null)) {
          this.project = ((IContainer)element).getProject();
        }
        final IPath oldPath = ((IContainer)element).getFullPath();
        final IPath newPath = oldPath.removeLastSegments(1).append(((RenameArguments)arguments).getNewName());
        this.addResource(((IResource)element), oldPath, newPath, ((RenameArguments)arguments));
      }
    }
  }
  
  protected void addResource(final IResource resource, final IPath oldPath, final IPath newPath, final RenameArguments arguments) {
    try {
      boolean _isPrefixOf = oldPath.isPrefixOf(resource.getFullPath());
      if (_isPrefixOf) {
        final URI oldURI = this._resourceURIUtil.toURI(resource);
        final URI newURI = this._resourceURIUtil.toURI(newPath.append(resource.getFullPath().removeFirstSegments(oldPath.segmentCount())));
        final ResourceURIChange uriChange = new ResourceURIChange(oldURI, newURI);
        if ((resource instanceof IFile)) {
          this.modifiedResources.add(((IFile)resource));
          this.uriChanges.add(uriChange);
        } else {
          if ((resource instanceof IContainer)) {
            this.modifiedResources.add(((IContainer)resource));
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
