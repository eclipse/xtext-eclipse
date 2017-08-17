/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.refactoring.participant

import com.google.inject.Inject
import java.util.Set
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.emf.common.util.URI
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange
import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments
import org.eclipse.xtext.ide.serializer.IChangeSerializer
import org.eclipse.xtext.resource.IResourceServiceProvider

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextMoveResourceProcessor {

	@Inject IChangeSerializer changeSerializer
	@Inject ChangeConverter changeConverter
	@Inject XtextMoveResourceStrategyRegistry strategyRegistry
	@Inject IResourceServiceProvider.Registry resourceServiceProviderRegistry 

	def createChange(String name, 
					XtextMoveArguments moveArguments, 
					RefactoringIssueAcceptor issues, 
					Set<? extends Object> excludedElements, 
					IProgressMonitor pm) throws CoreException, OperationCanceledException {
		for (move : moveArguments.changes) {
			if(move.oldURI.isXtextResource) {
				val resource = moveArguments.resourceSet.getResource(move.oldURI, true)
				changeSerializer.beginRecordChanges(resource)
			}
		}
		for (move : moveArguments.changes) {
			if(move.oldURI.isXtextResource) {
				val resource = moveArguments.resourceSet.getResource(move.oldURI, true)
				resource.setURI(move.newURI)
			}
			// TODO: should we add it if the newURI is an Xtext URI?
		}
		applyMoveStrategies(moveArguments, issues)
		changeConverter.initialize(name, [
			!(it instanceof MoveResourceChange) || !excludedElements.contains(modifiedElement)
		], issues)
		changeSerializer.endRecordChanges(changeConverter)
		return changeConverter.change
	}

	protected def void applyMoveStrategies(XtextMoveArguments moveArguments, RefactoringIssueAcceptor issues) {
		strategyRegistry.strategies.forEach[applyMove(moveArguments, issues)]
	}
		
	def isXtextResource(URI uri) {
		resourceServiceProviderRegistry.getResourceServiceProvider(uri) !== null
	}	
}