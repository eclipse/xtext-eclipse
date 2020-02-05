/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.syntaxcoloring

import com.google.inject.Inject
import org.eclipse.jface.text.TypedRegion
import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor
import org.eclipse.xtext.junit4.AbstractXtextTests
import org.eclipse.xtext.junit4.internal.LineDelimiters
import org.eclipse.xtext.testlanguages.noJdt.NoJdtTestLanguageStandaloneSetup
import org.eclipse.xtext.util.CancelIndicator
import org.junit.Before
import org.junit.Test

import static org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration.*

/**
 * @author Stefan Oehme - Initial contribution and API
 */
class TaskHighlightingTest extends AbstractXtextTests implements IHighlightedPositionAcceptor {

	@Inject
	DefaultSemanticHighlightingCalculator highlighter
	
	val expectedRegions = newHashSet

	@Before
	def void setup() {
		with(NoJdtTestLanguageStandaloneSetup)
		injectMembers(this)
		expectedRegions.clear
	}
	
	@Test
	def void test() {
		val resource = getResourceFromString(
		LineDelimiters.toUnix('''
			//TODO foo
			/*
			 * FIXME bar
			 * Fixme no match
			 * FOO also no match
			 */
			Hello notATODO!
		'''))
		expect(2, 4, TASK_ID)
		expect(17, 5, TASK_ID)
		getHighlighter().provideHighlightingFor(resource, this, CancelIndicator.NullImpl)
	}
	
	protected def getHighlighter() {
		return highlighter
	}
	
	protected def expect(int offset, int length, String type) {
		expectedRegions.add(new TypedRegion(offset, length, type));
	}

	override addPosition(int offset, int length, String... id) {
		assertEquals(1, id.length);
		val region = new TypedRegion(offset, length, id.get(0));
		assertFalse(region.toString(), expectedRegions.isEmpty());
		assertTrue("expected: " + expectedRegions.toString() + " but was: " + region, expectedRegions.remove(region));
	}
	
}
