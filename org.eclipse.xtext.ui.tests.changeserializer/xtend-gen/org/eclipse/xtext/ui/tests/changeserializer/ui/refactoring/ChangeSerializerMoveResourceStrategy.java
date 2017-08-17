package org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ide.refactoring.RefactoringIssueAcceptor;
import org.eclipse.xtext.ide.refactoring.ResourceURIChange;
import org.eclipse.xtext.ide.refactoring.XtextMoveArguments;
import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ChangeSerializerMoveResourceStrategy implements XtextMoveResourceStrategy {
  @Inject
  private IResourceServiceProvider.Registry resourceServiceProviderRegistry;
  
  public boolean isXtextResource(final URI uri) {
    IResourceServiceProvider _resourceServiceProvider = this.resourceServiceProviderRegistry.getResourceServiceProvider(uri);
    return (_resourceServiceProvider != null);
  }
  
  @Override
  public void applyMove(final XtextMoveArguments arguments, final RefactoringIssueAcceptor issues) {
    List<ResourceURIChange> _changes = arguments.getChanges();
    for (final ResourceURIChange change : _changes) {
      boolean _isXtextResource = this.isXtextResource(change.getNewURI());
      if (_isXtextResource) {
        final Resource resource = arguments.getResourceSet().getResource(change.getNewURI(), true);
        final EObject rootElement = IterableExtensions.<EObject>head(resource.getContents());
        if ((rootElement instanceof PackageDeclaration)) {
          final String newPackage = IterableExtensions.join(IterableExtensions.<String>drop(change.getNewURI().trimSegments(1).segmentsList(), 2), ".");
          ((PackageDeclaration)rootElement).setName(newPackage);
        }
      }
    }
  }
}
