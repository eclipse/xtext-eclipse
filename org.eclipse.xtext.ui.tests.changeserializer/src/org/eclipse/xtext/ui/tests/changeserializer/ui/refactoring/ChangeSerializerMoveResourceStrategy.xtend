package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring

import com.google.inject.Inject
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration

class ChangeSerializerMoveResourceStrategy implements XtextMoveResourceStrategy {

	@Inject IResourceServiceProvider.Registry resourceServiceProviderRegistry 

	def isXtextResource(URI uri) {
		resourceServiceProviderRegistry.getResourceServiceProvider(uri) !== null
	}

	override applyMove(XtextMoveArguments arguments, RefactoringIssueAcceptor issues) {
		for (change : arguments.changes) {
			if(change.newURI.isXtextResource) {
				val resource = arguments.resourceSet.getResource(change.newURI, true)
				val rootElement = resource.contents.head
				if (rootElement instanceof PackageDeclaration) {
					val newPackage = change.newURI.trimSegments(1).segmentsList.drop(2).join('.')
					rootElement.name = newPackage
				}
			}
		}
	}
}
