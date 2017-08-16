package org.eclipse.xtext.ui.tests.changeserializer.scoping

import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration
import org.eclipse.xtext.scoping.impl.ImportNormalizer

class ChangeSerializerImportScopeProvider extends ImportedNamespaceAwareLocalScopeProvider {
	
	override protected internalGetImportedNamespaceResolvers(EObject context, boolean ignoreCase) {
		val resolvers = super.internalGetImportedNamespaceResolvers(context, ignoreCase)
		if(context instanceof PackageDeclaration) 
			resolvers += new ImportNormalizer(qualifiedNameConverter.toQualifiedName(context.name), true, false)
		return resolvers
	}
}