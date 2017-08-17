/*
 * generated by Xtext unspecified
 */
package org.eclipse.xtext.ui.tests.changeserializer

import com.google.inject.Binder
import com.google.inject.name.Names
import org.eclipse.xtext.ide.serializer.hooks.IReferenceUpdater
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.resource.impl.LiveShadowedResourceDescriptions
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import org.eclipse.xtext.ui.tests.changeserializer.ide.refactoring.ChangeSerializerReferenceUpdater
import org.eclipse.xtext.ui.tests.changeserializer.scoping.ChangeSerializerImportScopeProvider

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class ChangeSerializerRuntimeModule extends AbstractChangeSerializerRuntimeModule {

	override void configureIResourceDescriptionsLiveScope(Binder binder) {
		binder.bind(IResourceDescriptions).annotatedWith(Names.named(ResourceDescriptionsProvider.LIVE_SCOPE)).to(
			LiveShadowedResourceDescriptions)
	}

	override configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(
			ChangeSerializerImportScopeProvider);
	}

	def Class<? extends IReferenceUpdater> bindReferenceUpdater() {
		ChangeSerializerReferenceUpdater
	}
}
