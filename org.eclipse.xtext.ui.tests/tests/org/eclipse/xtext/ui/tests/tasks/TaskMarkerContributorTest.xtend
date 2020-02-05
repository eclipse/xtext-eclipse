/*******************************************************************************
 * Copyright (c) 2014, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.tasks

import com.google.inject.Inject
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IMarker
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.junit4.AbstractXtextTests
import org.eclipse.xtext.junit4.internal.LineDelimiters
import org.eclipse.xtext.ui.tasks.TaskMarkerContributor
import org.eclipse.xtext.ui.tasks.TaskMarkerTypeProvider
import org.eclipse.xtext.ui.tests.internal.TestsActivator
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.*

/**
 * @author Stefan Oehme - Initial contribution and API
 */
class TaskMarkerContributorTest extends AbstractXtextTests {

	@Inject
	TaskMarkerContributor markerContributor

	@Before
	def void setup() {
		injector = TestsActivator.getInstance.getInjector(
			TestsActivator.ORG_ECLIPSE_XTEXT_UI_TESTS_EDITOR_CONTENTASSIST_DOMAINMODELTESTLANGUAGE)
		injectMembers(this)
	}

	@Test
	def void testMarkerCreation() {
		val file = createFile("foo/foo.domainModelTest",
			LineDelimiters.toUnix('''
				/*
				 * TODO foo
				 * FIXME bar
				 */
			'''))
		val resource = file.resource
		markerContributor.updateMarkers(file, resource, new NullProgressMonitor())
		val markers = file.findMarkers(TaskMarkerTypeProvider.XTEXT_TASK_TYPE, true, IResource.DEPTH_ZERO).sortBy[getAttribute(IMarker.LINE_NUMBER).toString]
		assertEquals(2, markers.size)
		assertEquals("TODO foo", markers.head.getAttribute(IMarker.MESSAGE))
		assertEquals(2, markers.head.getAttribute(IMarker.LINE_NUMBER))
		assertEquals(6, markers.head.getAttribute(IMarker.CHAR_START))
		assertEquals(14, markers.head.getAttribute(IMarker.CHAR_END))
		assertEquals(IMarker.PRIORITY_NORMAL, markers.head.getAttribute(IMarker.PRIORITY))
		assertEquals("line 2", markers.head.getAttribute(IMarker.LOCATION))
		assertEquals("FIXME bar", markers.get(1).getAttribute(IMarker.MESSAGE))
		assertEquals(3, markers.get(1).getAttribute(IMarker.LINE_NUMBER))
		assertEquals(18, markers.get(1).getAttribute(IMarker.CHAR_START))
		assertEquals(27, markers.get(1).getAttribute(IMarker.CHAR_END))
		assertEquals(IMarker.PRIORITY_HIGH, markers.get(1).getAttribute(IMarker.PRIORITY))
		assertEquals("line 3", markers.get(1).getAttribute(IMarker.LOCATION))
	}

	def getResource(IFile file) {
		getResource(fileToString(file), URI.createFileURI(file.fullPath.toString).toString)
	}

	@After
	def void cleanUp() {
		cleanWorkspace
	}
}
