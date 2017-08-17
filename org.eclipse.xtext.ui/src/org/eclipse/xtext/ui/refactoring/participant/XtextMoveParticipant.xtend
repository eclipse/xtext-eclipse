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
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.emf.common.util.URI
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant
import org.eclipse.ltk.core.refactoring.participants.MoveArguments
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments
import org.eclipse.xtext.ide.serializer.IChangeSerializer
import org.eclipse.xtext.ui.refactoring.impl.RefactoringResourceSetProvider
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange
import java.util.Set
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.ide.refactoring.XtextMoveStrategy
import org.eclipse.xtext.ide.refactoring.ResourceMove
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextMoveParticipantStrategyRegistry extends AbstractParticipantStrategyRegistry<XtextMoveStrategy> {
	override protected getExtensionPointID() {
		'org.eclipse.xtext.ui.moveParticipantStrategy'
	}
}

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextMoveParticipant extends MoveParticipant implements ISharableParticipant {
	
	@Inject IChangeSerializer changeSerializer
	@Inject ChangeConverter changeConverter
	@Inject RefactoringResourceSetProvider resourceSetProvider
	@Inject LtkIssueAcceptor issues
	@Inject XtextMoveParticipantStrategyRegistry strategyRegistry
	@Inject IResourceServiceProvider.Registry resourceServiceProviderRegistry 
	
	List<ResourceMove> moves = newArrayList()
	Set<IFile> modifiedElements = newHashSet
	
	IProject project // TODO: multi-project move
	
	override checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		return issues.refactoringStatus
	}
	
	override createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		if(moves.empty)
			return null
		val resourceSet = resourceSetProvider.get(project)
		val moveArguments = new XtextMoveArguments(resourceSet, moves)
		for(move: moves) {
			val resource = resourceSet.getResource(move.oldURI, true)
			changeSerializer.beginRecordChanges(resource)
		}
		for(move: moves) {
			val resource = resourceSet.getResource(move.oldURI, true)
			resource.setURI(move.newURI)
		}
		applyMove(moveArguments) 
		changeConverter.initialize(name, 
			[ !(it instanceof MoveResourceChange) || !modifiedElements.contains(modifiedElement) ], 
			issues)
		changeSerializer.endRecordChanges(changeConverter)
		return changeConverter.change
	}
	
	protected def void applyMove(XtextMoveArguments moveArguments) {
		strategyRegistry.strategies.forEach[ applyMove(moveArguments, issues) ]
	}
	
	override getName() {
		'Xtext move participant'
	}
	
	override protected initialize(Object element) {
		addElement(element, arguments)
		true
	}
	
	override addElement(Object element, RefactoringArguments arguments) {
		if (arguments instanceof MoveArguments) {
			if (element instanceof IFile) {
				val oldURI = element.URI
				if (oldURI.isXtextResource) {					
					val destination = arguments.destination
					if(destination instanceof IFolder) {
						if(project === null)
							project = element.project
						val destinationFile = destination.getFile(element.name)
						moves += new ResourceMove(element.URI, destinationFile.URI)
						modifiedElements += element
					}
				}
			}
		}
	}
	
	def isXtextResource(URI uri) {
		resourceServiceProviderRegistry.getResourceServiceProvider(uri) !== null
	}
	
	def URI getURI(IFile file) {
		URI.createPlatformResourceURI(file.fullPath.toString, true) 
	}
}

