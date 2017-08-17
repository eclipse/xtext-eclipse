package org.eclipse.xtext.ui.tests.changeserializer.ide.refactoring

import org.eclipse.xtext.ide.refactoring.MoveResourceContext
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration

class ChangeSerializerMoveResourceStrategy implements XtextMoveResourceStrategy {

	override applyMove(extension MoveResourceContext context) {
		for (change : context.fileChanges) {
			if(change.newURI.isXtextResource) {
				context.addModification(change.oldURI, [ resource |
					resource.URI = change.newURI
					val rootElement = resource.contents.head
					if (rootElement instanceof PackageDeclaration) {
						val newPackage = change.newURI.trimSegments(1).segmentsList.drop(2).join('.')
						rootElement.name = newPackage
					}
				])
			}
		}
	}
}
