/*******************************************************************************
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.example.arithmetics.parser.antlr.internal.InternalArithmeticsParser;
import org.eclipse.xtext.example.arithmetics.services.ArithmeticsGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class ArithmeticsParser extends AbstractAntlrParser {

	@Inject
	private ArithmeticsGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalArithmeticsParser createParser(XtextTokenStream stream) {
		return new InternalArithmeticsParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Module";
	}

	public ArithmeticsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ArithmeticsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
