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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange;
import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments;
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy;
import org.eclipse.xtext.ide.serializer.IChangeSerializer;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.refactoring.participant.ChangeConverter;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveResourceStrategyRegistry;

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
@SuppressWarnings("all")
public class XtextMoveResourceProcessor {
  @Inject
  private IChangeSerializer changeSerializer;
  
  @Inject
  private ChangeConverter changeConverter;
  
  @Inject
  private XtextMoveResourceStrategyRegistry strategyRegistry;
  
  @Inject
  private IResourceServiceProvider.Registry resourceServiceProviderRegistry;
  
  public Change createChange(final String name, final XtextMoveArguments moveArguments, final RefactoringIssueAcceptor issues, final Set<?> excludedElements, final IProgressMonitor pm) throws CoreException, OperationCanceledException {
    List<ResourceURIChange> _changes = moveArguments.getChanges();
    for (final ResourceURIChange move : _changes) {
      boolean _isXtextResource = this.isXtextResource(move.getOldURI());
      if (_isXtextResource) {
        final Resource resource = moveArguments.getResourceSet().getResource(move.getOldURI(), true);
        this.changeSerializer.beginRecordChanges(resource);
      }
    }
    List<ResourceURIChange> _changes_1 = moveArguments.getChanges();
    for (final ResourceURIChange move_1 : _changes_1) {
      boolean _isXtextResource_1 = this.isXtextResource(move_1.getOldURI());
      if (_isXtextResource_1) {
        final Resource resource_1 = moveArguments.getResourceSet().getResource(move_1.getOldURI(), true);
        resource_1.setURI(move_1.getNewURI());
      }
    }
    this.applyMoveStrategies(moveArguments, issues);
    final Predicate<Change> _function = (Change it) -> {
      return ((!(it instanceof MoveResourceChange)) || (!excludedElements.contains(it.getModifiedElement())));
    };
    this.changeConverter.initialize(name, _function, issues);
    this.changeSerializer.endRecordChanges(this.changeConverter);
    return this.changeConverter.getChange();
  }
  
  protected void applyMoveStrategies(final XtextMoveArguments moveArguments, final RefactoringIssueAcceptor issues) {
    final Consumer<XtextMoveResourceStrategy> _function = (XtextMoveResourceStrategy it) -> {
      it.applyMove(moveArguments, issues);
    };
    this.strategyRegistry.getStrategies().forEach(_function);
  }
  
  public boolean isXtextResource(final URI uri) {
    IResourceServiceProvider _resourceServiceProvider = this.resourceServiceProviderRegistry.getResourceServiceProvider(uri);
    return (_resourceServiceProvider != null);
  }
}
