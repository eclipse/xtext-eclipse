/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.ui.tests.editor.contentassist.CrossReferenceProposalTestLanguageRuntimeModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.CrossReferenceProposalTestLanguageStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class CrossReferenceProposalTestLanguageIdeSetup extends CrossReferenceProposalTestLanguageStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new CrossReferenceProposalTestLanguageRuntimeModule(), new CrossReferenceProposalTestLanguageIdeModule()));
	}
}
