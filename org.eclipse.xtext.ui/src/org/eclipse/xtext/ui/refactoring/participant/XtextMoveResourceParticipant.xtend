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
import org.eclipse.ltk.core.refactoring.Change

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextMoveResourceParticipant extends MoveParticipant implements ISharableParticipant {

	@Inject LtkIssueAcceptor issues
	@Inject extension ResourceURIConverter
	@Inject XtextMoveResourceProcessor processor
	
	List<ResourceURIChange> fileUriChanges = newArrayList()
	Set<IFile> movedFiles = newHashSet
	IProject project // TODO: multi-project move

	Change change 
	
	override checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		change = processor.createChange(name, fileUriChanges, emptyList, project, issues, movedFiles, pm)
		return issues.refactoringStatus
	}

	override createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return change
	}

	override getName() {
		'Xtext move resource participant'
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
					fileUriChanges += new ResourceURIChange(element.toURI, destinationFile.toURI)
					movedFiles += element
				}
			}
		}
	}
}
