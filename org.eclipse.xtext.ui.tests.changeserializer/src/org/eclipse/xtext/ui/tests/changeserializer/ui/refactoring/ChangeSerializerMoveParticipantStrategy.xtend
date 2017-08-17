package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring

import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments
import org.eclipse.xtext.ide.refactoring.XtextMoveStrategy
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration

class ChangeSerializerMoveParticipantStrategy implements XtextMoveStrategy {

	override applyMove(XtextMoveArguments arguments, RefactoringIssueAcceptor issues) {
		for (move : arguments.moves) {
			val resource = arguments.resourceSet.getResource(move.newURI, true)
			val rootElement = resource.contents.head
			if (rootElement instanceof PackageDeclaration) {
				val newPackage = move.newURI.trimSegments(1).segmentsList.drop(2).join('.')
				rootElement.name = newPackage
			}
		}
	}
}
