/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.refactoring.participant

import com.google.inject.Inject
import java.util.List
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange
import org.eclipse.xtext.ide.refactoring.MoveResourceContext
import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor
import org.eclipse.xtext.ide.refactoring.ResourceURIChange
import org.eclipse.xtext.ide.serializer.IChangeSerializer
import org.eclipse.xtext.ui.resource.IResourceSetProvider
import org.eclipse.xtext.ui.resource.LiveScopeResourceSetInitializer
import org.eclipse.ltk.core.refactoring.resource.RenameResourceChange

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextMoveResourceProcessor {

	@Inject IResourceSetProvider resourceSetProvider
	@Inject LiveScopeResourceSetInitializer liveScopeResourceSetInitializer
	@Inject IChangeSerializer changeSerializer
	@Inject MoveResourceContext.Factory contextFactory
	@Inject XtextMoveResourceStrategyRegistry strategyRegistry
	@Inject ChangeConverter changeConverter

	def createChange(String name, 
					List<ResourceURIChange> fileUriChanges,
					List<ResourceURIChange> folderUriChanges,
					IProject project,
					RefactoringIssueAcceptor issues, 
					Set<? extends Object> excludedElements, 
					IProgressMonitor pm) throws CoreException, OperationCanceledException {
		if (folderUriChanges.empty && fileUriChanges.empty)
			return null
		val resourceSet = resourceSetProvider.get(project)
		liveScopeResourceSetInitializer.initialize(resourceSet)
		val moveContext = contextFactory.create(fileUriChanges, folderUriChanges, issues, changeSerializer, resourceSet)
		applyMoveStrategies(moveContext)
		changeConverter.initialize(name, [
			(!(it instanceof MoveResourceChange || it instanceof RenameResourceChange) 
				|| !excludedElements.contains(modifiedElement))
		], issues)
		changeSerializer.endRecordChanges(changeConverter)
		return changeConverter.change
	}

	protected def void applyMoveStrategies(MoveResourceContext context) {
		strategyRegistry.strategies.forEach[applyMove(context)]
		context.executeModifications
	}
}