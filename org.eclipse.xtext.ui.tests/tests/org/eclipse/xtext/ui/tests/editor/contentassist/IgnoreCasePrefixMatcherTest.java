/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher;
import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher.IgnoreCase;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class IgnoreCasePrefixMatcherTest extends AbstractPrefixMatcherTest<PrefixMatcher.IgnoreCase> {

	@Override
	protected IgnoreCase createMatcher() {
		return new PrefixMatcher.IgnoreCase();
	}
	
	@Test public void testCamelCaseMismatch() {
		assertFalse(matcher.isCandidateMatchingPrefix("ExactMatch", "ExMa"));
		assertFalse(matcher.isCandidateMatchingPrefix("exactMatch", "exM"));
		assertFalse(matcher.isCandidateMatchingPrefix("eXactMatch", "eXM"));
	}
}
