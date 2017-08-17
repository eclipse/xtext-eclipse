package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring

import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration

class ChangeSerializerMoveResourceStrategy implements XtextMoveResourceStrategy {

	override applyMove(XtextMoveArguments arguments, RefactoringIssueAcceptor issues) {
		for (change : arguments.changes) {
			val resource = arguments.resourceSet.getResource(change.newURI, true)
			val rootElement = resource.contents.head
			if (rootElement instanceof PackageDeclaration) {
				val newPackage = change.newURI.trimSegments(1).segmentsList.drop(2).join('.')
				rootElement.name = newPackage
			}
		}
	}
}
