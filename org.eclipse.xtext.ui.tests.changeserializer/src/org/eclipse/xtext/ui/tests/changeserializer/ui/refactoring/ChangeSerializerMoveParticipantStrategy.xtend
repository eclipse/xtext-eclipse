package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring

import org.eclipse.xtext.ui.refactoring.participant.XtextMoveArguments
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveParticipantStrategy
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration

class ChangeSerializerMoveParticipantStrategy implements XtextMoveParticipantStrategy {

	override applyMove(XtextMoveArguments arguments) {
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
