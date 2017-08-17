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
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext
import org.eclipse.ltk.core.refactoring.participants.ISharableParticipant
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments
import org.eclipse.ltk.core.refactoring.participants.RenameArguments
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant
import org.eclipse.xtext.ide.refactoring.ResourceURIChange
import org.eclipse.ltk.core.refactoring.Change

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextRenameResourceParticipant extends RenameParticipant implements ISharableParticipant {

	@Inject LtkIssueAcceptor issues
	@Inject extension ResourceURIConverter
	@Inject XtextMoveResourceProcessor processor

	List<ResourceURIChange> folderUriChanges = newArrayList()
	List<ResourceURIChange> fileUriChanges = newArrayList()
	Set<IResource> renamedResources = newHashSet

	IProject project
	
	Change change
	
	override checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		change = processor.createChange(name, fileUriChanges, folderUriChanges, project, issues, renamedResources, pm)
		return issues.refactoringStatus
	}

	override createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return change 
	}

	override getName() {
		'Xtext rename resource participant'
	}

	override protected initialize(Object element) {
		addElement(element, arguments)
		true
	}

	override addElement(Object element, RefactoringArguments arguments) {
		if (arguments instanceof RenameArguments) {
			if (element instanceof IResource) {
				if (project === null)
					project = element.project
				val oldPath = element.fullPath
				val newPath = oldPath.removeLastSegments(1).append(arguments.newName)
				addResource(element, oldPath, newPath, arguments)
			}
		}
	}

	def protected void addResource(IResource resource, IPath oldPath, IPath newPath, RenameArguments arguments) {
		if (oldPath.isPrefixOf(resource.fullPath)) {
			val oldURI = resource.toURI
			val newURI = newPath.append(resource.fullPath.removeFirstSegments(oldPath.segmentCount)).toURI
			val uriChange = new ResourceURIChange(oldURI, newURI)
			renamedResources += resource
			if (resource instanceof IFile) {
				fileUriChanges += uriChange
			} else if (resource instanceof IContainer) {
				folderUriChanges += uriChange
				resource.members.forEach [ member |
					addResource(member, oldPath, newPath, arguments)
				]
			}
		}
	}
}
