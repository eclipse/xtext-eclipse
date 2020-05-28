/*******************************************************************************
 * Copyright (c) 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.formatter.FormatterTestHelper
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(ArithmeticsInjectorProvider)
class ArithmeticsFormatterTest {

	@Inject extension FormatterTestHelper

	@Ignore("See https://github.com/eclipse/xtext-core/issues/1506")
	@Test def testFormatter() {
		assertFormatted[
			toBeFormatted = '''
			module test
			5 * 3 + 4;
			'''
			expectation = '''
			module test
			5 * 3 + 4;
			'''
		]
	}
}