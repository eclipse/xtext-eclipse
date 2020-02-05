/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist.antlr;

import java.util.Collection;
import java.util.Set;

import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.LookAheadContentAssistTestLanguageRuntimeModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.LookAheadContentAssistTestLanguageStandaloneSetup;
import org.eclipse.xtext.ui.tests.editor.contentassist.ide.contentassist.antlr.LookAheadContentAssistTestLanguageParser;
import org.eclipse.xtext.ui.tests.editor.contentassist.services.LookAheadContentAssistTestLanguageGrammarAccess;
import org.eclipse.xtext.ui.tests.editor.contentassist.ui.LookAheadContentAssistTestLanguageUiModule;
import org.eclipse.xtext.ui.tests.internal.TestsActivator;
import org.eclipse.xtext.util.Modules2;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug282031ParserTest extends AbstractXtextTests {

	protected LookAheadContentAssistTestLanguageGrammarAccess grammarAccess;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		with(new LookAheadContentAssistTestLanguageStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(
						Modules2.mixin(new LookAheadContentAssistTestLanguageRuntimeModule(),
						new LookAheadContentAssistTestLanguageUiModule(TestsActivator.getInstance()),
						new SharedStateModule()));
			}
		});
		grammarAccess = get(LookAheadContentAssistTestLanguageGrammarAccess.class);
	}

	@Override
	public void tearDown() throws Exception {
		grammarAccess = null;
		super.tearDown();
	}
	
	@Test public void testBug() {
		String input ="( value1 ";
		Set<AbstractElement> expected = Sets.<AbstractElement>newHashSet(
				grammarAccess.getPairAccess().getEqualsSignKeyword_1(),
				grammarAccess.getModelAccess().getAttributeAssignment_1_0_1()
		);
		assertFollowers(input, expected);
	}

	protected Collection<FollowElement> getFollowSet(String input) {
		AbstractContentAssistParser parser = get(LookAheadContentAssistTestLanguageParser.class);
		return parser.getFollowElements(input, false);
	}
	
	private void assertFollowers(String input, Set<AbstractElement> expected) {
		Collection<FollowElement> followSet = getFollowSet(input);
//		Collection<FollowElement> followList = com.google.common.collect.Lists.newArrayList(getFollowSet(input));
		assertEquals(expected.size(), followSet.size());
		Set<AbstractElement> grammarElements = computeSearchElements(followSet);
		assertEquals(grammarElements.toString(), expected, grammarElements);
	}

	private Set<AbstractElement> computeSearchElements(Collection<FollowElement> followSet) {
		return  Sets.newHashSet(
				Iterables.transform(followSet, new Function<FollowElement, AbstractElement>(){
					@Override
					public AbstractElement apply(FollowElement from) {
						return from.getGrammarElement();
					}
				}));
	}
}
