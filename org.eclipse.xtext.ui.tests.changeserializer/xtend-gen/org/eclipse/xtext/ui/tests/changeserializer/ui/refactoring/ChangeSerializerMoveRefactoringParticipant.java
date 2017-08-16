package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant;

@SuppressWarnings("all")
public class ChangeSerializerMoveRefactoringParticipant extends MoveParticipant {
  @Override
  public RefactoringStatus checkConditions(final IProgressMonitor pm, final CheckConditionsContext context) throws OperationCanceledException {
    return new RefactoringStatus();
  }
  
  @Override
  public Change createChange(final IProgressMonitor pm) throws CoreException, OperationCanceledException {
    return null;
  }
  
  @Override
  public String getName() {
    return "ChangeSerializer move participant";
  }
  
  @Override
  protected boolean initialize(final Object element) {
    return true;
  }
}
