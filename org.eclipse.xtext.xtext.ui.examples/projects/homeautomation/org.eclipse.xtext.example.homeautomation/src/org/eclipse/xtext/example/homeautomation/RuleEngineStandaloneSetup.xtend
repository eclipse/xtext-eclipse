/*******************************************************************************
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.homeautomation

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class RuleEngineStandaloneSetup extends RuleEngineStandaloneSetupGenerated {

	def static void doSetup() {
		new RuleEngineStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
