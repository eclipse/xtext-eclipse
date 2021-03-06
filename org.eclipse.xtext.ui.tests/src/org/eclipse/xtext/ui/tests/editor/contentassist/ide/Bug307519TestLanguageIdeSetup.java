/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.ide;

import org.eclipse.xtext.ui.tests.editor.contentassist.Bug307519TestLanguageRuntimeModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.Bug307519TestLanguageStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class Bug307519TestLanguageIdeSetup extends Bug307519TestLanguageStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new Bug307519TestLanguageRuntimeModule(), new Bug307519TestLanguageIdeModule()));
	}
}
