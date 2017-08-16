package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ui.refactoring.participant.ResourceMove;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveArguments;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveParticipantStrategy;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ChangeSerializerMoveParticipantStrategy implements XtextMoveParticipantStrategy {
  @Override
  public void applyMove(final XtextMoveArguments arguments) {
    List<ResourceMove> _moves = arguments.getMoves();
    for (final ResourceMove move : _moves) {
      {
        final Resource resource = arguments.getResourceSet().getResource(move.getNewURI(), true);
        final EObject rootElement = IterableExtensions.<EObject>head(resource.getContents());
        if ((rootElement instanceof PackageDeclaration)) {
          final String newPackage = IterableExtensions.join(IterableExtensions.<String>drop(move.getNewURI().trimSegments(1).segmentsList(), 2), ".");
          ((PackageDeclaration)rootElement).setName(newPackage);
        }
      }
    }
  }
}
