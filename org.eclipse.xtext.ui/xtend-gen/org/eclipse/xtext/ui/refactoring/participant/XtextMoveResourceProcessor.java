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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange;
import org.eclipse.ltk.core.refactoring.resource.RenameResourceChange;
import org.eclipse.xtext.ide.refactoring.MoveResourceContext;
import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy;
import org.eclipse.xtext.ide.serializer.IChangeSerializer;
import org.eclipse.xtext.ui.refactoring.participant.ChangeConverter;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveResourceStrategyRegistry;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.LiveScopeResourceSetInitializer;

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
@SuppressWarnings("all")
public class XtextMoveResourceProcessor {
  @Inject
  private IResourceSetProvider resourceSetProvider;
  
  @Inject
  private LiveScopeResourceSetInitializer liveScopeResourceSetInitializer;
  
  @Inject
  private IChangeSerializer changeSerializer;
  
  @Inject
  private MoveResourceContext.Factory contextFactory;
  
  @Inject
  private XtextMoveResourceStrategyRegistry strategyRegistry;
  
  @Inject
  private ChangeConverter changeConverter;
  
  public Change createChange(final String name, final List<ResourceURIChange> fileUriChanges, final List<ResourceURIChange> folderUriChanges, final IProject project, final RefactoringIssueAcceptor issues, final Set<?> excludedElements, final IProgressMonitor pm) throws CoreException, OperationCanceledException {
    if ((folderUriChanges.isEmpty() && fileUriChanges.isEmpty())) {
      return null;
    }
    final ResourceSet resourceSet = this.resourceSetProvider.get(project);
    this.liveScopeResourceSetInitializer.initialize(resourceSet);
    final MoveResourceContext moveContext = this.contextFactory.create(fileUriChanges, folderUriChanges, issues, this.changeSerializer, resourceSet);
    this.applyMoveStrategies(moveContext);
    final Predicate<Change> _function = (Change it) -> {
      return ((!((it instanceof MoveResourceChange) || (it instanceof RenameResourceChange))) || (!excludedElements.contains(it.getModifiedElement())));
    };
    this.changeConverter.initialize(name, _function, issues);
    this.changeSerializer.endRecordChanges(this.changeConverter);
    return this.changeConverter.getChange();
  }
  
  protected void applyMoveStrategies(final MoveResourceContext context) {
    final Consumer<XtextMoveResourceStrategy> _function = (XtextMoveResourceStrategy it) -> {
      it.applyMove(context);
    };
    this.strategyRegistry.getStrategies().forEach(_function);
    context.executeModifications();
  }
}
