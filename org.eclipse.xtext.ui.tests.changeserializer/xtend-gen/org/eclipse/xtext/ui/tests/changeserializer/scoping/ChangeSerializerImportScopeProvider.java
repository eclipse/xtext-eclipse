package org.eclipse.xtext.ui.tests.changeserializer.scoping;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration;

@SuppressWarnings("all")
public class ChangeSerializerImportScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
  @Override
  protected List<ImportNormalizer> internalGetImportedNamespaceResolvers(final EObject context, final boolean ignoreCase) {
    final List<ImportNormalizer> resolvers = super.internalGetImportedNamespaceResolvers(context, ignoreCase);
    if ((context instanceof PackageDeclaration)) {
      QualifiedName _qualifiedName = this.getQualifiedNameConverter().toQualifiedName(((PackageDeclaration)context).getName());
      ImportNormalizer _importNormalizer = new ImportNormalizer(_qualifiedName, true, false);
      resolvers.add(_importNormalizer);
    }
    return resolvers;
  }
}
