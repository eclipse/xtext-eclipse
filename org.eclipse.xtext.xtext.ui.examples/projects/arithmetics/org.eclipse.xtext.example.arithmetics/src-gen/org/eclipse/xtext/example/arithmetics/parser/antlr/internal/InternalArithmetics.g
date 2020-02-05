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
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package org.eclipse.xtext.example.arithmetics.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.xtext.example.arithmetics.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.example.arithmetics.services.ArithmeticsGrammarAccess;

}

@parser::members {

 	private ArithmeticsGrammarAccess grammarAccess;

    public InternalArithmeticsParser(TokenStream input, ArithmeticsGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "Module";
   	}

   	@Override
   	protected ArithmeticsGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleModule
entryRuleModule returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getModuleRule()); }
	iv_ruleModule=ruleModule
	{ $current=$iv_ruleModule.current; }
	EOF;

// Rule Module
ruleModule returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='module'
		{
			newLeafNode(otherlv_0, grammarAccess.getModuleAccess().getModuleKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getModuleAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getModuleRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getModuleAccess().getImportsImportParserRuleCall_2_0());
				}
				lv_imports_2_0=ruleImport
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getModuleRule());
					}
					add(
						$current,
						"imports",
						lv_imports_2_0,
						"org.eclipse.xtext.example.arithmetics.Arithmetics.Import");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getModuleAccess().getStatementsStatementParserRuleCall_3_0());
				}
				lv_statements_3_0=ruleStatement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getModuleRule());
					}
					add(
						$current,
						"statements",
						lv_statements_3_0,
						"org.eclipse.xtext.example.arithmetics.Arithmetics.Statement");
					afterParserOrEnumRuleCall();
				}
			)
		)*
	)
;

// Entry rule entryRuleImport
entryRuleImport returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getImportRule()); }
	iv_ruleImport=ruleImport
	{ $current=$iv_ruleImport.current; }
	EOF;

// Rule Import
ruleImport returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='import'
		{
			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getImportRule());
					}
				}
				otherlv_1=RULE_ID
				{
					newLeafNode(otherlv_1, grammarAccess.getImportAccess().getModuleModuleCrossReference_1_0());
				}
			)
		)
	)
;

// Entry rule entryRuleStatement
entryRuleStatement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStatementRule()); }
	iv_ruleStatement=ruleStatement
	{ $current=$iv_ruleStatement.current; }
	EOF;

// Rule Statement
ruleStatement returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getStatementAccess().getDefinitionParserRuleCall_0());
		}
		this_Definition_0=ruleDefinition
		{
			$current = $this_Definition_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getStatementAccess().getEvaluationParserRuleCall_1());
		}
		this_Evaluation_1=ruleEvaluation
		{
			$current = $this_Evaluation_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleDefinition
entryRuleDefinition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDefinitionRule()); }
	iv_ruleDefinition=ruleDefinition
	{ $current=$iv_ruleDefinition.current; }
	EOF;

// Rule Definition
ruleDefinition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='def'
		{
			newLeafNode(otherlv_0, grammarAccess.getDefinitionAccess().getDefKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getDefinitionAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getDefinitionRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			otherlv_2='('
			{
				newLeafNode(otherlv_2, grammarAccess.getDefinitionAccess().getLeftParenthesisKeyword_2_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getDefinitionAccess().getArgsDeclaredParameterParserRuleCall_2_1_0());
					}
					lv_args_3_0=ruleDeclaredParameter
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getDefinitionRule());
						}
						add(
							$current,
							"args",
							lv_args_3_0,
							"org.eclipse.xtext.example.arithmetics.Arithmetics.DeclaredParameter");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_4=','
				{
					newLeafNode(otherlv_4, grammarAccess.getDefinitionAccess().getCommaKeyword_2_2_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getDefinitionAccess().getArgsDeclaredParameterParserRuleCall_2_2_1_0());
						}
						lv_args_5_0=ruleDeclaredParameter
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getDefinitionRule());
							}
							add(
								$current,
								"args",
								lv_args_5_0,
								"org.eclipse.xtext.example.arithmetics.Arithmetics.DeclaredParameter");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
			otherlv_6=')'
			{
				newLeafNode(otherlv_6, grammarAccess.getDefinitionAccess().getRightParenthesisKeyword_2_3());
			}
		)?
		otherlv_7=':'
		{
			newLeafNode(otherlv_7, grammarAccess.getDefinitionAccess().getColonKeyword_3());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getDefinitionAccess().getExprExpressionParserRuleCall_4_0());
				}
				lv_expr_8_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getDefinitionRule());
					}
					set(
						$current,
						"expr",
						lv_expr_8_0,
						"org.eclipse.xtext.example.arithmetics.Arithmetics.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_9=';'
		{
			newLeafNode(otherlv_9, grammarAccess.getDefinitionAccess().getSemicolonKeyword_5());
		}
	)
;

// Entry rule entryRuleDeclaredParameter
entryRuleDeclaredParameter returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDeclaredParameterRule()); }
	iv_ruleDeclaredParameter=ruleDeclaredParameter
	{ $current=$iv_ruleDeclaredParameter.current; }
	EOF;

// Rule DeclaredParameter
ruleDeclaredParameter returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_name_0_0=RULE_ID
			{
				newLeafNode(lv_name_0_0, grammarAccess.getDeclaredParameterAccess().getNameIDTerminalRuleCall_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getDeclaredParameterRule());
				}
				setWithLastConsumed(
					$current,
					"name",
					lv_name_0_0,
					"org.eclipse.xtext.common.Terminals.ID");
			}
		)
	)
;

// Entry rule entryRuleEvaluation
entryRuleEvaluation returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEvaluationRule()); }
	iv_ruleEvaluation=ruleEvaluation
	{ $current=$iv_ruleEvaluation.current; }
	EOF;

// Rule Evaluation
ruleEvaluation returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getEvaluationAccess().getExpressionExpressionParserRuleCall_0_0());
				}
				lv_expression_0_0=ruleExpression
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEvaluationRule());
					}
					set(
						$current,
						"expression",
						lv_expression_0_0,
						"org.eclipse.xtext.example.arithmetics.Arithmetics.Expression");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1=';'
		{
			newLeafNode(otherlv_1, grammarAccess.getEvaluationAccess().getSemicolonKeyword_1());
		}
	)
;

// Entry rule entryRuleExpression
entryRuleExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpressionRule()); }
	iv_ruleExpression=ruleExpression
	{ $current=$iv_ruleExpression.current; }
	EOF;

// Rule Expression
ruleExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getExpressionAccess().getAdditionParserRuleCall());
	}
	this_Addition_0=ruleAddition
	{
		$current = $this_Addition_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleAddition
entryRuleAddition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAdditionRule()); }
	iv_ruleAddition=ruleAddition
	{ $current=$iv_ruleAddition.current; }
	EOF;

// Rule Addition
ruleAddition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0());
		}
		this_Multiplication_0=ruleMultiplication
		{
			$current = $this_Multiplication_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				(
					(
						{
							$current = forceCreateModelElementAndSet(
								grammarAccess.getAdditionAccess().getPlusLeftAction_1_0_0_0(),
								$current);
						}
					)
					otherlv_2='+'
					{
						newLeafNode(otherlv_2, grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1());
					}
				)
				    |
				(
					(
						{
							$current = forceCreateModelElementAndSet(
								grammarAccess.getAdditionAccess().getMinusLeftAction_1_0_1_0(),
								$current);
						}
					)
					otherlv_4='-'
					{
						newLeafNode(otherlv_4, grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1());
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_1_0());
					}
					lv_right_5_0=ruleMultiplication
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getAdditionRule());
						}
						set(
							$current,
							"right",
							lv_right_5_0,
							"org.eclipse.xtext.example.arithmetics.Arithmetics.Multiplication");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleMultiplication
entryRuleMultiplication returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getMultiplicationRule()); }
	iv_ruleMultiplication=ruleMultiplication
	{ $current=$iv_ruleMultiplication.current; }
	EOF;

// Rule Multiplication
ruleMultiplication returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getMultiplicationAccess().getPrimaryExpressionParserRuleCall_0());
		}
		this_PrimaryExpression_0=rulePrimaryExpression
		{
			$current = $this_PrimaryExpression_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				(
					(
						{
							$current = forceCreateModelElementAndSet(
								grammarAccess.getMultiplicationAccess().getMultiLeftAction_1_0_0_0(),
								$current);
						}
					)
					otherlv_2='*'
					{
						newLeafNode(otherlv_2, grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1());
					}
				)
				    |
				(
					(
						{
							$current = forceCreateModelElementAndSet(
								grammarAccess.getMultiplicationAccess().getDivLeftAction_1_0_1_0(),
								$current);
						}
					)
					otherlv_4='/'
					{
						newLeafNode(otherlv_4, grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1());
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getMultiplicationAccess().getRightPrimaryExpressionParserRuleCall_1_1_0());
					}
					lv_right_5_0=rulePrimaryExpression
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getMultiplicationRule());
						}
						set(
							$current,
							"right",
							lv_right_5_0,
							"org.eclipse.xtext.example.arithmetics.Arithmetics.PrimaryExpression");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRulePrimaryExpression
entryRulePrimaryExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPrimaryExpressionRule()); }
	iv_rulePrimaryExpression=rulePrimaryExpression
	{ $current=$iv_rulePrimaryExpression.current; }
	EOF;

// Rule PrimaryExpression
rulePrimaryExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			otherlv_0='('
			{
				newLeafNode(otherlv_0, grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_0_0());
			}
			{
				newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getExpressionParserRuleCall_0_1());
			}
			this_Expression_1=ruleExpression
			{
				$current = $this_Expression_1.current;
				afterParserOrEnumRuleCall();
			}
			otherlv_2=')'
			{
				newLeafNode(otherlv_2, grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_0_2());
			}
		)
		    |
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getPrimaryExpressionAccess().getNumberLiteralAction_1_0(),
						$current);
				}
			)
			(
				(
					lv_value_4_0=RULE_NUMBER
					{
						newLeafNode(lv_value_4_0, grammarAccess.getPrimaryExpressionAccess().getValueNUMBERTerminalRuleCall_1_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getPrimaryExpressionRule());
						}
						setWithLastConsumed(
							$current,
							"value",
							lv_value_4_0,
							"org.eclipse.xtext.example.arithmetics.Arithmetics.NUMBER");
					}
				)
			)
		)
		    |
		(
			(
				{
					$current = forceCreateModelElement(
						grammarAccess.getPrimaryExpressionAccess().getFunctionCallAction_2_0(),
						$current);
				}
			)
			(
				(
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getPrimaryExpressionRule());
						}
					}
					otherlv_6=RULE_ID
					{
						newLeafNode(otherlv_6, grammarAccess.getPrimaryExpressionAccess().getFuncAbstractDefinitionCrossReference_2_1_0());
					}
				)
			)
			(
				otherlv_7='('
				{
					newLeafNode(otherlv_7, grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_2_2_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getArgsExpressionParserRuleCall_2_2_1_0());
						}
						lv_args_8_0=ruleExpression
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
							}
							add(
								$current,
								"args",
								lv_args_8_0,
								"org.eclipse.xtext.example.arithmetics.Arithmetics.Expression");
							afterParserOrEnumRuleCall();
						}
					)
				)
				(
					otherlv_9=','
					{
						newLeafNode(otherlv_9, grammarAccess.getPrimaryExpressionAccess().getCommaKeyword_2_2_2_0());
					}
					(
						(
							{
								newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getArgsExpressionParserRuleCall_2_2_2_1_0());
							}
							lv_args_10_0=ruleExpression
							{
								if ($current==null) {
									$current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
								}
								add(
									$current,
									"args",
									lv_args_10_0,
									"org.eclipse.xtext.example.arithmetics.Arithmetics.Expression");
								afterParserOrEnumRuleCall();
							}
						)
					)
				)*
				otherlv_11=')'
				{
					newLeafNode(otherlv_11, grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_2_2_3());
				}
			)?
		)
	)
;

RULE_NUMBER : ('0'..'9')* ('.' ('0'..'9')+)?;

RULE_INT : 'this one has been deactivated';

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
