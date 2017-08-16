package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring

import org.eclipse.ltk.core.refactoring.participants.MoveParticipant
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.core.runtime.CoreException
import org.eclipse.ltk.core.refactoring.RefactoringStatus

class ChangeSerializerMoveRefactoringParticipant extends MoveParticipant {
	
	override checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		return new RefactoringStatus
	}
	
	override createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return null
	}
	
	override getName() {
		'ChangeSerializer move participant'
	}
	
	override protected initialize(Object element) {
		true
	}
	
}