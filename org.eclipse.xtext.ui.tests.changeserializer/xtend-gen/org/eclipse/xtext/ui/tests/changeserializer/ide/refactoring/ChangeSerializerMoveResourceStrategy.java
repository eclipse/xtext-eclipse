package org.eclipse.xtext.ui.tests.changeserializer.ide.refactoring;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ide.refactoring.MoveResourceContext;
import org.eclipse.xtext.ide.refactoring.ResourceModification;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ChangeSerializerMoveResourceStrategy implements XtextMoveResourceStrategy {
  @Override
  public void applyMove(@Extension final MoveResourceContext context) {
    List<ResourceURIChange> _fileChanges = context.getFileChanges();
    for (final ResourceURIChange change : _fileChanges) {
      boolean _isXtextResource = context.isXtextResource(change.getNewURI());
      if (_isXtextResource) {
        final ResourceModification _function = (Resource resource) -> {
          resource.setURI(change.getNewURI());
          final EObject rootElement = IterableExtensions.<EObject>head(resource.getContents());
          if ((rootElement instanceof PackageDeclaration)) {
            final String newPackage = IterableExtensions.join(IterableExtensions.<String>drop(change.getNewURI().trimSegments(1).segmentsList(), 2), ".");
            ((PackageDeclaration)rootElement).setName(newPackage);
          }
        };
        context.addModification(change.getOldURI(), _function);
      }
    }
  }
}
