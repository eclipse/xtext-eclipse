/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.refactoring.participant

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */

import com.google.inject.Inject
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant
import org.eclipse.ltk.core.refactoring.participants.MoveArguments
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.ide.serializer.IChangeSerializer
import org.eclipse.xtext.ui.refactoring.impl.RefactoringResourceSetProvider
import org.eclipse.xtext.ui.refactoring.impl.StatusWrapper
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange
import java.util.Set

@Data
class XtextMoveArguments {
	ResourceSet resourceSet
	// TODO pass error reporting facility
	List<ResourceMove> moves
}

@Data
class ResourceMove {
	URI oldURI
	URI newURI
}

interface XtextMoveParticipantStrategy {
	def void applyMove(XtextMoveArguments arguments)
}

class XtextMoveParticipantStrategyRegistry extends AbstractParticipantStrategyRegistry<XtextMoveParticipantStrategy> {
	override protected getExtensionPointID() {
		'org.eclipse.xtext.ui.moveParticipantStrategy'
	}
}

class XtextMoveParticipant extends MoveParticipant implements ISharableParticipant {
	
	@Inject IChangeSerializer changeSerializer
	@Inject ChangeConverter changeConverter
	@Inject RefactoringResourceSetProvider resourceSetProvider
	@Inject StatusWrapper statusWrapper
	@Inject XtextMoveParticipantStrategyRegistry strategyRegistry
	
	List<ResourceMove> moves = newArrayList()
	Set<IFile> modifiedElements = newHashSet
	
	IProject project // TODO: multi-project move
	
	override checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		return statusWrapper.refactoringStatus
	}
	
	override createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
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
		changeConverter.initialize(name, statusWrapper, [
			!(it instanceof MoveResourceChange) || !modifiedElements.contains(modifiedElement)
		])
		changeSerializer.endRecordChanges(changeConverter)
		return changeConverter.change
	}
	
	protected def applyMove(XtextMoveArguments moveArguments) {
		strategyRegistry.strategies.forEach[ it.applyMove(moveArguments) ]
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
	
	def URI getURI(IFile file) {
		URI.createPlatformResourceURI(file.fullPath.toString, true) 
	}
}

