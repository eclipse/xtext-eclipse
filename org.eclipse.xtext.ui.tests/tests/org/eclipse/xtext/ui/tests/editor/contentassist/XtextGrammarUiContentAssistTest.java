/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testlanguages.xtextgrammar.ui.tests.XtextGrammarTestLanguageUiInjectorProvider;
import org.eclipse.xtext.ui.testing.AbstractContentAssistTest;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@InjectWith(XtextGrammarTestLanguageUiInjectorProvider.class)
@RunWith(XtextRunner.class)
public class XtextGrammarUiContentAssistTest extends AbstractContentAssistTest {

	@Test public void testCompleteRuleCall() throws Exception {
		newBuilder().appendNl("grammar foo").appendNl("generate foo \"foo\"").appendNl(
				"R1 : (attr+=R2)*;").appendNl("R2 : (attr=INT)? prop=R3;").append("R3: attr+=").assertText("R1", "R2",
				"R3", "\"Value\"", "(", "[", "+=" // current node is always a suggestion
		);
	}

	/**
	 * regression test for:
	 * 
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=260825
	 */
	@Test public void testCompleteParserRule_01() throws Exception {
		newBuilder().appendNl("grammar foo").appendNl("generate foo \"foo\"").appendNl(
				"MyRule : 'foo' name=ID; ").assertText("Name", "terminal", "enum");
	}

	@Test public void testCompleteParserRule_02() throws Exception {
		newBuilder().appendNl("grammar foo").appendNl("generate foo \"foo\"").appendNl("")
				.appendNl("MyRule : 'foo' name=ID; ").assertTextAtCursorPosition("MyRule", "Name", "terminal", "enum",
						"as", "generate", "import");
	}

	@Test public void testCompleteParserRule_03() throws Exception {
		newBuilder().appendNl("grammar foo").appendNl("generate foo \"foo\"").appendNl("")
				.appendNl(" MyRule : 'foo' name=ID; ").assertTextAtCursorPosition(" MyRule", "Name", "terminal",
						"enum", "as", "generate", "import");
	}

	@Test public void testCompleteReturnsKeyword_01() throws Exception {
		newBuilder().appendNl("grammar foo").append("MyRule r").assertText("returns");
	}

	public void _testCompleteGenerateKeyword() throws Exception {
		newBuilder().appendNl("grammar foo").appendNl("generate foo \"foo\"").appendNl("")
				.appendNl("MyRule : 'foo' name=ID; ").assertTextAtCursorPosition("generate", 3, "generate");
	}

	@Test public void testCompleteImportAndGenerateRule() throws Exception {
		newBuilder().appendNl("grammar foo").appendNl("generate foo \"foo\"").appendNl("")
				.appendNl("R1 : (attr+=R2)*;").appendNl("R2 : (attr=INT)? prop=R3;").assertTextAtCursorPosition("R1",
						"Name", "as", "generate", "import", "terminal", "enum");
	}

	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=269593
	 */
	@Test public void testCompleteRuleCallWithSpace() throws Exception {
		newBuilder().appendNl("grammar foo").appendNl("generate foo \"foo\"").appendNl(
				"R1 : (attr+=R2)*;").appendNl("R2 : (attr=INT)? prop=R3;").append("R3: attr+= ").assertText("R1", "R2",
				"R3", "\"Value\"", "(", "[");
	}

	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=269649
	 */
	@Test public void testCompletionOnGenerateKeyword() throws Exception {
		newBuilder().appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
				.appendNl("generate meta \"url\"").appendNl("Rule: name=ID;").assertTextAtCursorPosition("generate", 3,
						"generate", ":" // as 'gen' is a parser rule, 'hidden' and 'returns' would conflict with rulename
				);
	}

	/**
	 * regression test for:
	 * 
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=260825 https://bugs.eclipse.org/bugs/show_bug.cgi?id=262313
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=267582
	 */
	@Test public void testCompleteAssignmentWithBacktracking() throws Exception {
		newBuilder().appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
				.appendNl("generate foo \"foo\"").append("MyRule : 'foo' name").assertText("\"Value\"", "(", "*", "+",
						"+=", ";", "=", "?", "?=", "{", "|").appendNl(";").append("terminal Other_Id").assertText(":");
	}

}
