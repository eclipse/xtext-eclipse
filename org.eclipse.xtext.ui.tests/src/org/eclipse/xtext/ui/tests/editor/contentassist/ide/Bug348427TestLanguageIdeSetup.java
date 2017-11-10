/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.ui.tests.editor.contentassist.Bug348427TestLanguageRuntimeModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.Bug348427TestLanguageStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class Bug348427TestLanguageIdeSetup extends Bug348427TestLanguageStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new Bug348427TestLanguageRuntimeModule(), new Bug348427TestLanguageIdeModule()));
	}
}
