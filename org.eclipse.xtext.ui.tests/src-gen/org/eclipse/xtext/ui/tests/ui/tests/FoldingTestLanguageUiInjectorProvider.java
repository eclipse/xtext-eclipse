/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.eclipse.xtext.ui.tests.internal.TestsActivator;

public class FoldingTestLanguageUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return TestsActivator.getInstance().getInjector("org.eclipse.xtext.ui.tests.FoldingTestLanguage");
	}

}
