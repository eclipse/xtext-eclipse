/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.eclipse.xtext.ui.editor.contentassist.FQNPrefixMatcher;
import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher;
import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher.IgnoreCase;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class FQNPrefixMatcherTest extends AbstractPrefixMatcherTest<FQNPrefixMatcher>{

	@Override
	protected FQNPrefixMatcher createMatcher() {
		IgnoreCase ignoreCase = new PrefixMatcher.IgnoreCase();
		FQNPrefixMatcher result = new FQNPrefixMatcher();
		result.setDelegate(ignoreCase);
		result.setLastSegmentFinder(new FQNPrefixMatcher.LastSegmentFinder() {
			@Override
			public String getLastSegment(String fqn, char delimiter) {
				int i = fqn.lastIndexOf(delimiter);
				if (i >= 0) {
					if (i != fqn.length() - 1)
						return fqn.substring(i + 1);
					return "";
				}
				return fqn;
			}
		});
		return result;
	}

	@Test public void testCamelCaseMismatch() {
		assertFalse(matcher.isCandidateMatchingPrefix("ExactMatch", "ExMa"));
		assertFalse(matcher.isCandidateMatchingPrefix("exactMatch", "exM"));
		assertFalse(matcher.isCandidateMatchingPrefix("eXactMatch", "eXM"));
	}
	
	@Test public void testCamelCaseMismatchLastSegment() {
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "ExMa"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "exM"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "eXM"));
	}
	
	@Test public void testExactMatchLastSegment() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.", ""));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exact", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXact", "eXact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.Exact", "Exact"));
	}
	
	@Test public void testMisMatchLastSegment() {
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.Exact", "Mismatch"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.Exact", "ExactMismatch"));
	}
	
	@Test public void testExactPrefixLastSegment() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.Match", ""));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "Exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "eXact"));
	}
	
	@Test public void testLowerCaseMatchLastSegment() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.Exact", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.EXACT", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exact", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXact", "exact"));
	}
	
	@Test public void testLowerCasePrefixLastSegment() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.EXACTMatch", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "exact"));
	}
	
	@Test public void testUpperCasePrefixLastSegment() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "EXACT"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.EXACTMatch", "EXACT"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "EXACT"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "EXACT"));
	}
	
	@Test public void testCamelCaseMismatchSegmentsMatch() {
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "na.sp.ExMa"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "na.sp.exM"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "na.sp.eXM"));
	}
	
	@Test public void testExactMatchLastSegmentSegmentsMatch() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.", "na.sp."));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.", "na.s"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exact", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXact", "na.sp.eXact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.Exact", "na.sp.Exact"));
	}
	
	@Test public void testMisMatchLastSegmentSegmentsMatch() {
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.Exact", "na.sp.Mismatch"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.Exact", "na.sp.ExactMismatch"));
	}
	
	@Test public void testExactPrefixLastSegmentSegmentsMatch() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.Match", ""));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "na.sp.Exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "na.sp.eXact"));
	}
	
	@Test public void testLowerCaseMatchLastSegmentSegmentsMatch() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.Exact", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.EXACT", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exact", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXact", "na.sp.exact"));
	}
	
	@Test public void testLowerCasePrefixLastSegmentSegmentsMatch() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.EXACTMatch", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "na.sp.exact"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "na.sp.exact"));
	}
	
	@Test public void testUpperCasePrefixLastSegmentSegmentsMatch() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "na.sp.EXACT"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.EXACTMatch", "na.sp.EXACT"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.exactMatch", "na.sp.EXACT"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.eXactMatch", "na.sp.EXACT"));
	}
	
	@Test public void testSegmentsMatch() {
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "name.space.ExactMatch"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "name.space"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "name.spa"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "nam"));
		assertTrue(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "na.spa.E"));
	}
	
	@Test public void testSegmentsMisMatch() {
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "name.space.tomuch.ExactMatch"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "name.ExactMatch"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "name.sp.t.ExactMatch"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "na.sp.t.ExactMatch"));
		assertFalse(matcher.isCandidateMatchingPrefix("name.space.ExactMatch", "ne.sp.ExactMatch"));
	}
	
}
