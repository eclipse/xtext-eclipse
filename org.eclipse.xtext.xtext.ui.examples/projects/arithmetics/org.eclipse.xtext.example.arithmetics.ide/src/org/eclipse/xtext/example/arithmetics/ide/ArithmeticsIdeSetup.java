/*******************************************************************************
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.example.arithmetics.ArithmeticsRuntimeModule;
import org.eclipse.xtext.example.arithmetics.ArithmeticsStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class ArithmeticsIdeSetup extends ArithmeticsStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new ArithmeticsRuntimeModule(), new ArithmeticsIdeModule()));
	}
	
}
