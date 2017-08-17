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
import org.eclipse.xtext.ide.refactoring.XtextMoveFolderArguments
import org.eclipse.xtext.ui.refactoring.impl.RefactoringResourceSetProvider

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
class XtextRenameResourceParticipant extends RenameParticipant implements ISharableParticipant {

	@Inject RefactoringResourceSetProvider resourceSetProvider
	@Inject LtkIssueAcceptor issues
	@Inject extension ResourceURIUtil
	@Inject XtextMoveResourceProcessor processor

	List<ResourceURIChange> folderUriChanges = newArrayList()
	List<ResourceURIChange> uriChanges = newArrayList()
	Set<IResource> modifiedResources = newHashSet

	IProject project

	override checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		return issues.refactoringStatus
	}

	override createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		if(uriChanges.empty)
			return null
		val resourceSet = resourceSetProvider.get(project)
		val moveFolderArguments = new XtextMoveFolderArguments(resourceSet, uriChanges, folderUriChanges)
		return processor.createChange(name, moveFolderArguments, issues, modifiedResources, pm)
	}

	override getName() {
		'Xtext move participant'
	}

	override protected initialize(Object element) {
		addElement(element, arguments)
		true
	}

	override addElement(Object element, RefactoringArguments arguments) {
		if (arguments instanceof RenameArguments) {
			if (element instanceof IContainer) {
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
			if (resource instanceof IFile) {
				modifiedResources += resource
				uriChanges += uriChange
			} else if (resource instanceof IContainer) {
				modifiedResources += resource
				folderUriChanges += uriChange
				resource.members.forEach [ member |
					addResource(member, oldPath, newPath, arguments)
				]
			}
		}
	}
}
