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
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant
import org.eclipse.ltk.core.refactoring.participants.MoveArguments
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments
import org.eclipse.xtext.ide.refactoring.ResourceURIChange
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments
import org.eclipse.xtext.ui.refactoring.impl.RefactoringResourceSetProvider

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextMoveResourceParticipant extends MoveParticipant implements ISharableParticipant {

	@Inject RefactoringResourceSetProvider resourceSetProvider
	@Inject LtkIssueAcceptor issues
	@Inject extension ResourceURIUtil
	@Inject XtextMoveResourceProcessor processor

	List<ResourceURIChange> uriChanges = newArrayList()
	Set<IFile> modifiedElements = newHashSet

	IProject project // TODO: multi-project move

	override checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		return issues.refactoringStatus
	}

	override createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		if (uriChanges.empty)
			return null
		val resourceSet = resourceSetProvider.get(project)
		val moveArguments = new XtextMoveArguments(resourceSet, uriChanges)
		return processor.createChange(name, moveArguments, issues, modifiedElements, pm)
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
				if (destination instanceof IFolder) {
					if (project === null)
						project = element.project
					val destinationFile = destination.getFile(element.name)
					uriChanges += new ResourceURIChange(element.toURI, destinationFile.toURI)
					modifiedElements += element
				}
			}
		}
	}
}
