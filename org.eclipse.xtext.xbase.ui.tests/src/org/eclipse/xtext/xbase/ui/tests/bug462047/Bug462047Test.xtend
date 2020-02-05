/*******************************************************************************
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.tests.bug462047

import org.apache.log4j.Level
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.logging.LoggingTester
import org.eclipse.xtext.ui.testing.AbstractEditorTest
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil
import org.eclipse.xtext.ui.testing.util.TargetPlatformUtil
import org.eclipse.xtext.xbase.resource.BatchLinkableResource
import org.eclipse.xtext.xbase.testlanguages.bug462047.ui.tests.Bug462047LangUiInjectorProvider
import org.eclipse.xtext.xbase.ui.tests.AbstractXbaseUITestCase
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(Bug462047LangUiInjectorProvider)
class Bug462047Test extends AbstractEditorTest {
	
	@BeforeClass
	def static void setupTargetPlatform() {
		TargetPlatformUtil.setTargetPlatform(Bug462047Test);
	}
	
	@Before
	def void createProjects() {
		AbstractXbaseUITestCase.createPluginProject('bug462047')
	}
	
	@Test
	def void testNoErrors() {
		IResourcesSetupUtil.createFile('bug462047/src/a.bug462047lang', 'element CORE ref CORE.b')
		val file = IResourcesSetupUtil.createFile('bug462047/src/b.bug462047lang', 'element b ref CORE.c')
		IResourcesSetupUtil.createFile('bug462047/src/c.bug462047lang', 'element c')
		IResourcesSetupUtil.waitForBuild
		IResourcesSetupUtil.assertNoErrorsInWorkspace
		LoggingTester.captureLogging(Level.ERROR, BatchLinkableResource) [
			val editor = openEditor(file)
			val document = editor.document
			document.readOnly [ XtextResource res |
				EcoreUtil.resolveAll(res)
				val resourceSet = res.resourceSet
				assertNull(resourceSet.getResource(URI.createURI('java:/Objects/CORE.CORE'), false))
				return null
			]
		].assertNoLogEntries
	}
}