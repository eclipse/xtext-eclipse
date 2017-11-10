/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.ui.tests.editor.contentassist.Bug288760TestLanguageRuntimeModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.Bug288760TestLanguageStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class Bug288760TestLanguageIdeSetup extends Bug288760TestLanguageStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new Bug288760TestLanguageRuntimeModule(), new Bug288760TestLanguageIdeModule()));
	}
}
