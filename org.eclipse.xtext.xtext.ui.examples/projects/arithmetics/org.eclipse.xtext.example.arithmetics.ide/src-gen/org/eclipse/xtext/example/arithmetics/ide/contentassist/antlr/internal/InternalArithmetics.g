/*******************************************************************************
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
grammar InternalArithmetics;

options {
	superClass=AbstractInternalContentAssistParser;
}

@lexer::header {
package org.eclipse.xtext.example.arithmetics.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.xtext.example.arithmetics.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.eclipse.xtext.example.arithmetics.services.ArithmeticsGrammarAccess;

}
@parser::members {
	private ArithmeticsGrammarAccess grammarAccess;

	public void setGrammarAccess(ArithmeticsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleModule
entryRuleModule
:
{ before(grammarAccess.getModuleRule()); }
	 ruleModule
{ after(grammarAccess.getModuleRule()); } 
	 EOF 
;

// Rule Module
ruleModule 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getModuleAccess().getGroup()); }
		(rule__Module__Group__0)
		{ after(grammarAccess.getModuleAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleImport
entryRuleImport
:
{ before(grammarAccess.getImportRule()); }
	 ruleImport
{ after(grammarAccess.getImportRule()); } 
	 EOF 
;

// Rule Import
ruleImport 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getImportAccess().getGroup()); }
		(rule__Import__Group__0)
		{ after(grammarAccess.getImportAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStatement
entryRuleStatement
:
{ before(grammarAccess.getStatementRule()); }
	 ruleStatement
{ after(grammarAccess.getStatementRule()); } 
	 EOF 
;

// Rule Statement
ruleStatement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStatementAccess().getAlternatives()); }
		(rule__Statement__Alternatives)
		{ after(grammarAccess.getStatementAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDefinition
entryRuleDefinition
:
{ before(grammarAccess.getDefinitionRule()); }
	 ruleDefinition
{ after(grammarAccess.getDefinitionRule()); } 
	 EOF 
;

// Rule Definition
ruleDefinition 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDefinitionAccess().getGroup()); }
		(rule__Definition__Group__0)
		{ after(grammarAccess.getDefinitionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDeclaredParameter
entryRuleDeclaredParameter
:
{ before(grammarAccess.getDeclaredParameterRule()); }
	 ruleDeclaredParameter
{ after(grammarAccess.getDeclaredParameterRule()); } 
	 EOF 
;

// Rule DeclaredParameter
ruleDeclaredParameter 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDeclaredParameterAccess().getNameAssignment()); }
		(rule__DeclaredParameter__NameAssignment)
		{ after(grammarAccess.getDeclaredParameterAccess().getNameAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEvaluation
entryRuleEvaluation
:
{ before(grammarAccess.getEvaluationRule()); }
	 ruleEvaluation
{ after(grammarAccess.getEvaluationRule()); } 
	 EOF 
;

// Rule Evaluation
ruleEvaluation 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEvaluationAccess().getGroup()); }
		(rule__Evaluation__Group__0)
		{ after(grammarAccess.getEvaluationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleExpression
entryRuleExpression
:
{ before(grammarAccess.getExpressionRule()); }
	 ruleExpression
{ after(grammarAccess.getExpressionRule()); } 
	 EOF 
;

// Rule Expression
ruleExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getExpressionAccess().getAdditionParserRuleCall()); }
		ruleAddition
		{ after(grammarAccess.getExpressionAccess().getAdditionParserRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAddition
entryRuleAddition
:
{ before(grammarAccess.getAdditionRule()); }
	 ruleAddition
{ after(grammarAccess.getAdditionRule()); } 
	 EOF 
;

// Rule Addition
ruleAddition 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAdditionAccess().getGroup()); }
		(rule__Addition__Group__0)
		{ after(grammarAccess.getAdditionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleMultiplication
entryRuleMultiplication
:
{ before(grammarAccess.getMultiplicationRule()); }
	 ruleMultiplication
{ after(grammarAccess.getMultiplicationRule()); } 
	 EOF 
;

// Rule Multiplication
ruleMultiplication 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getMultiplicationAccess().getGroup()); }
		(rule__Multiplication__Group__0)
		{ after(grammarAccess.getMultiplicationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePrimaryExpression
entryRulePrimaryExpression
:
{ before(grammarAccess.getPrimaryExpressionRule()); }
	 rulePrimaryExpression
{ after(grammarAccess.getPrimaryExpressionRule()); } 
	 EOF 
;

// Rule PrimaryExpression
rulePrimaryExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getAlternatives()); }
		(rule__PrimaryExpression__Alternatives)
		{ after(grammarAccess.getPrimaryExpressionAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Statement__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStatementAccess().getDefinitionParserRuleCall_0()); }
		ruleDefinition
		{ after(grammarAccess.getStatementAccess().getDefinitionParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getStatementAccess().getEvaluationParserRuleCall_1()); }
		ruleEvaluation
		{ after(grammarAccess.getStatementAccess().getEvaluationParserRuleCall_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Alternatives_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdditionAccess().getGroup_1_0_0()); }
		(rule__Addition__Group_1_0_0__0)
		{ after(grammarAccess.getAdditionAccess().getGroup_1_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getAdditionAccess().getGroup_1_0_1()); }
		(rule__Addition__Group_1_0_1__0)
		{ after(grammarAccess.getAdditionAccess().getGroup_1_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Alternatives_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMultiplicationAccess().getGroup_1_0_0()); }
		(rule__Multiplication__Group_1_0_0__0)
		{ after(grammarAccess.getMultiplicationAccess().getGroup_1_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getMultiplicationAccess().getGroup_1_0_1()); }
		(rule__Multiplication__Group_1_0_1__0)
		{ after(grammarAccess.getMultiplicationAccess().getGroup_1_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getGroup_0()); }
		(rule__PrimaryExpression__Group_0__0)
		{ after(grammarAccess.getPrimaryExpressionAccess().getGroup_0()); }
	)
	|
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getGroup_1()); }
		(rule__PrimaryExpression__Group_1__0)
		{ after(grammarAccess.getPrimaryExpressionAccess().getGroup_1()); }
	)
	|
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getGroup_2()); }
		(rule__PrimaryExpression__Group_2__0)
		{ after(grammarAccess.getPrimaryExpressionAccess().getGroup_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Module__Group__0__Impl
	rule__Module__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getModuleAccess().getModuleKeyword_0()); }
	'module'
	{ after(grammarAccess.getModuleAccess().getModuleKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Module__Group__1__Impl
	rule__Module__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getModuleAccess().getNameAssignment_1()); }
	(rule__Module__NameAssignment_1)
	{ after(grammarAccess.getModuleAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Module__Group__2__Impl
	rule__Module__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getModuleAccess().getImportsAssignment_2()); }
	(rule__Module__ImportsAssignment_2)*
	{ after(grammarAccess.getModuleAccess().getImportsAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Module__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getModuleAccess().getStatementsAssignment_3()); }
	(rule__Module__StatementsAssignment_3)*
	{ after(grammarAccess.getModuleAccess().getStatementsAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Import__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Import__Group__0__Impl
	rule__Import__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportAccess().getImportKeyword_0()); }
	'import'
	{ after(grammarAccess.getImportAccess().getImportKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Import__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportAccess().getModuleAssignment_1()); }
	(rule__Import__ModuleAssignment_1)
	{ after(grammarAccess.getImportAccess().getModuleAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Definition__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group__0__Impl
	rule__Definition__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getDefKeyword_0()); }
	'def'
	{ after(grammarAccess.getDefinitionAccess().getDefKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group__1__Impl
	rule__Definition__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getNameAssignment_1()); }
	(rule__Definition__NameAssignment_1)
	{ after(grammarAccess.getDefinitionAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group__2__Impl
	rule__Definition__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getGroup_2()); }
	(rule__Definition__Group_2__0)?
	{ after(grammarAccess.getDefinitionAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group__3__Impl
	rule__Definition__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getColonKeyword_3()); }
	':'
	{ after(grammarAccess.getDefinitionAccess().getColonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group__4__Impl
	rule__Definition__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getExprAssignment_4()); }
	(rule__Definition__ExprAssignment_4)
	{ after(grammarAccess.getDefinitionAccess().getExprAssignment_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getSemicolonKeyword_5()); }
	';'
	{ after(grammarAccess.getDefinitionAccess().getSemicolonKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Definition__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group_2__0__Impl
	rule__Definition__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getLeftParenthesisKeyword_2_0()); }
	'('
	{ after(grammarAccess.getDefinitionAccess().getLeftParenthesisKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group_2__1__Impl
	rule__Definition__Group_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getArgsAssignment_2_1()); }
	(rule__Definition__ArgsAssignment_2_1)
	{ after(grammarAccess.getDefinitionAccess().getArgsAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group_2__2__Impl
	rule__Definition__Group_2__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getGroup_2_2()); }
	(rule__Definition__Group_2_2__0)*
	{ after(grammarAccess.getDefinitionAccess().getGroup_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group_2__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getRightParenthesisKeyword_2_3()); }
	')'
	{ after(grammarAccess.getDefinitionAccess().getRightParenthesisKeyword_2_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Definition__Group_2_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group_2_2__0__Impl
	rule__Definition__Group_2_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getCommaKeyword_2_2_0()); }
	','
	{ after(grammarAccess.getDefinitionAccess().getCommaKeyword_2_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Definition__Group_2_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__Group_2_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDefinitionAccess().getArgsAssignment_2_2_1()); }
	(rule__Definition__ArgsAssignment_2_2_1)
	{ after(grammarAccess.getDefinitionAccess().getArgsAssignment_2_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Evaluation__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Evaluation__Group__0__Impl
	rule__Evaluation__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Evaluation__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEvaluationAccess().getExpressionAssignment_0()); }
	(rule__Evaluation__ExpressionAssignment_0)
	{ after(grammarAccess.getEvaluationAccess().getExpressionAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Evaluation__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Evaluation__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Evaluation__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEvaluationAccess().getSemicolonKeyword_1()); }
	';'
	{ after(grammarAccess.getEvaluationAccess().getSemicolonKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Addition__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group__0__Impl
	rule__Addition__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); }
	ruleMultiplication
	{ after(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getGroup_1()); }
	(rule__Addition__Group_1__0)*
	{ after(grammarAccess.getAdditionAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Addition__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group_1__0__Impl
	rule__Addition__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getAlternatives_1_0()); }
	(rule__Addition__Alternatives_1_0)
	{ after(grammarAccess.getAdditionAccess().getAlternatives_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getRightAssignment_1_1()); }
	(rule__Addition__RightAssignment_1_1)
	{ after(grammarAccess.getAdditionAccess().getRightAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Addition__Group_1_0_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group_1_0_0__0__Impl
	rule__Addition__Group_1_0_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1_0_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0()); }
	()
	{ after(grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1_0_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group_1_0_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1_0_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1()); }
	'+'
	{ after(grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Addition__Group_1_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group_1_0_1__0__Impl
	rule__Addition__Group_1_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0()); }
	()
	{ after(grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Addition__Group_1_0_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__Group_1_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1()); }
	'-'
	{ after(grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Multiplication__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group__0__Impl
	rule__Multiplication__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getPrimaryExpressionParserRuleCall_0()); }
	rulePrimaryExpression
	{ after(grammarAccess.getMultiplicationAccess().getPrimaryExpressionParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getGroup_1()); }
	(rule__Multiplication__Group_1__0)*
	{ after(grammarAccess.getMultiplicationAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Multiplication__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group_1__0__Impl
	rule__Multiplication__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getAlternatives_1_0()); }
	(rule__Multiplication__Alternatives_1_0)
	{ after(grammarAccess.getMultiplicationAccess().getAlternatives_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1()); }
	(rule__Multiplication__RightAssignment_1_1)
	{ after(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Multiplication__Group_1_0_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group_1_0_0__0__Impl
	rule__Multiplication__Group_1_0_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1_0_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getMultiLeftAction_1_0_0_0()); }
	()
	{ after(grammarAccess.getMultiplicationAccess().getMultiLeftAction_1_0_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1_0_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group_1_0_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1_0_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1()); }
	'*'
	{ after(grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Multiplication__Group_1_0_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group_1_0_1__0__Impl
	rule__Multiplication__Group_1_0_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1_0_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getDivLeftAction_1_0_1_0()); }
	()
	{ after(grammarAccess.getMultiplicationAccess().getDivLeftAction_1_0_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1_0_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Multiplication__Group_1_0_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__Group_1_0_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1()); }
	'/'
	{ after(grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PrimaryExpression__Group_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_0__0__Impl
	rule__PrimaryExpression__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_0_0()); }
	'('
	{ after(grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_0__1__Impl
	rule__PrimaryExpression__Group_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getExpressionParserRuleCall_0_1()); }
	ruleExpression
	{ after(grammarAccess.getPrimaryExpressionAccess().getExpressionParserRuleCall_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_0__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_0__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_0__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_0_2()); }
	')'
	{ after(grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_0_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PrimaryExpression__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_1__0__Impl
	rule__PrimaryExpression__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getNumberLiteralAction_1_0()); }
	()
	{ after(grammarAccess.getPrimaryExpressionAccess().getNumberLiteralAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getValueAssignment_1_1()); }
	(rule__PrimaryExpression__ValueAssignment_1_1)
	{ after(grammarAccess.getPrimaryExpressionAccess().getValueAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PrimaryExpression__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2__0__Impl
	rule__PrimaryExpression__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getFunctionCallAction_2_0()); }
	()
	{ after(grammarAccess.getPrimaryExpressionAccess().getFunctionCallAction_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2__1__Impl
	rule__PrimaryExpression__Group_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getFuncAssignment_2_1()); }
	(rule__PrimaryExpression__FuncAssignment_2_1)
	{ after(grammarAccess.getPrimaryExpressionAccess().getFuncAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getGroup_2_2()); }
	(rule__PrimaryExpression__Group_2_2__0)?
	{ after(grammarAccess.getPrimaryExpressionAccess().getGroup_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PrimaryExpression__Group_2_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2_2__0__Impl
	rule__PrimaryExpression__Group_2_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_2_2_0()); }
	'('
	{ after(grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_2_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2_2__1__Impl
	rule__PrimaryExpression__Group_2_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getArgsAssignment_2_2_1()); }
	(rule__PrimaryExpression__ArgsAssignment_2_2_1)
	{ after(grammarAccess.getPrimaryExpressionAccess().getArgsAssignment_2_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2_2__2__Impl
	rule__PrimaryExpression__Group_2_2__3
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getGroup_2_2_2()); }
	(rule__PrimaryExpression__Group_2_2_2__0)*
	{ after(grammarAccess.getPrimaryExpressionAccess().getGroup_2_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2_2__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_2_2_3()); }
	')'
	{ after(grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_2_2_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PrimaryExpression__Group_2_2_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2_2_2__0__Impl
	rule__PrimaryExpression__Group_2_2_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getCommaKeyword_2_2_2_0()); }
	','
	{ after(grammarAccess.getPrimaryExpressionAccess().getCommaKeyword_2_2_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PrimaryExpression__Group_2_2_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__Group_2_2_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPrimaryExpressionAccess().getArgsAssignment_2_2_2_1()); }
	(rule__PrimaryExpression__ArgsAssignment_2_2_2_1)
	{ after(grammarAccess.getPrimaryExpressionAccess().getArgsAssignment_2_2_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Module__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getModuleAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getModuleAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__ImportsAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getModuleAccess().getImportsImportParserRuleCall_2_0()); }
		ruleImport
		{ after(grammarAccess.getModuleAccess().getImportsImportParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Module__StatementsAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getModuleAccess().getStatementsStatementParserRuleCall_3_0()); }
		ruleStatement
		{ after(grammarAccess.getModuleAccess().getStatementsStatementParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__ModuleAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getImportAccess().getModuleModuleCrossReference_1_0()); }
		(
			{ before(grammarAccess.getImportAccess().getModuleModuleIDTerminalRuleCall_1_0_1()); }
			RULE_ID
			{ after(grammarAccess.getImportAccess().getModuleModuleIDTerminalRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getImportAccess().getModuleModuleCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDefinitionAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getDefinitionAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__ArgsAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDefinitionAccess().getArgsDeclaredParameterParserRuleCall_2_1_0()); }
		ruleDeclaredParameter
		{ after(grammarAccess.getDefinitionAccess().getArgsDeclaredParameterParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__ArgsAssignment_2_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDefinitionAccess().getArgsDeclaredParameterParserRuleCall_2_2_1_0()); }
		ruleDeclaredParameter
		{ after(grammarAccess.getDefinitionAccess().getArgsDeclaredParameterParserRuleCall_2_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Definition__ExprAssignment_4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDefinitionAccess().getExprExpressionParserRuleCall_4_0()); }
		ruleExpression
		{ after(grammarAccess.getDefinitionAccess().getExprExpressionParserRuleCall_4_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__DeclaredParameter__NameAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getDeclaredParameterAccess().getNameIDTerminalRuleCall_0()); }
		RULE_ID
		{ after(grammarAccess.getDeclaredParameterAccess().getNameIDTerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Evaluation__ExpressionAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEvaluationAccess().getExpressionExpressionParserRuleCall_0_0()); }
		ruleExpression
		{ after(grammarAccess.getEvaluationAccess().getExpressionExpressionParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Addition__RightAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_1_0()); }
		ruleMultiplication
		{ after(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Multiplication__RightAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMultiplicationAccess().getRightPrimaryExpressionParserRuleCall_1_1_0()); }
		rulePrimaryExpression
		{ after(grammarAccess.getMultiplicationAccess().getRightPrimaryExpressionParserRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__ValueAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getValueNUMBERTerminalRuleCall_1_1_0()); }
		RULE_NUMBER
		{ after(grammarAccess.getPrimaryExpressionAccess().getValueNUMBERTerminalRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__FuncAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getFuncAbstractDefinitionCrossReference_2_1_0()); }
		(
			{ before(grammarAccess.getPrimaryExpressionAccess().getFuncAbstractDefinitionIDTerminalRuleCall_2_1_0_1()); }
			RULE_ID
			{ after(grammarAccess.getPrimaryExpressionAccess().getFuncAbstractDefinitionIDTerminalRuleCall_2_1_0_1()); }
		)
		{ after(grammarAccess.getPrimaryExpressionAccess().getFuncAbstractDefinitionCrossReference_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__ArgsAssignment_2_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getArgsExpressionParserRuleCall_2_2_1_0()); }
		ruleExpression
		{ after(grammarAccess.getPrimaryExpressionAccess().getArgsExpressionParserRuleCall_2_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PrimaryExpression__ArgsAssignment_2_2_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPrimaryExpressionAccess().getArgsExpressionParserRuleCall_2_2_2_1_0()); }
		ruleExpression
		{ after(grammarAccess.getPrimaryExpressionAccess().getArgsExpressionParserRuleCall_2_2_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_NUMBER : ('0'..'9')* ('.' ('0'..'9')+)?;

RULE_INT : 'this one has been deactivated';

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
