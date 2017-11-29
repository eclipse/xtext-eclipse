// Generated from org/antlr/codebuff/Codebuff.g4 by ANTLR 4.5.3
package org.antlr.codebuff;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CodebuffParser}.
 */
public interface CodebuffListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleDomainModel}.
	 * @param ctx the parse tree
	 */
	void enterRuleDomainModel(CodebuffParser.RuleDomainModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleDomainModel}.
	 * @param ctx the parse tree
	 */
	void exitRuleDomainModel(CodebuffParser.RuleDomainModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleAbstractElement}.
	 * @param ctx the parse tree
	 */
	void enterRuleAbstractElement(CodebuffParser.RuleAbstractElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleAbstractElement}.
	 * @param ctx the parse tree
	 */
	void exitRuleAbstractElement(CodebuffParser.RuleAbstractElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#rulePackageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRulePackageDeclaration(CodebuffParser.RulePackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#rulePackageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRulePackageDeclaration(CodebuffParser.RulePackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleEntity}.
	 * @param ctx the parse tree
	 */
	void enterRuleEntity(CodebuffParser.RuleEntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleEntity}.
	 * @param ctx the parse tree
	 */
	void exitRuleEntity(CodebuffParser.RuleEntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleFeature}.
	 * @param ctx the parse tree
	 */
	void enterRuleFeature(CodebuffParser.RuleFeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleFeature}.
	 * @param ctx the parse tree
	 */
	void exitRuleFeature(CodebuffParser.RuleFeatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleProperty}.
	 * @param ctx the parse tree
	 */
	void enterRuleProperty(CodebuffParser.RulePropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleProperty}.
	 * @param ctx the parse tree
	 */
	void exitRuleProperty(CodebuffParser.RulePropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOperation}.
	 * @param ctx the parse tree
	 */
	void enterRuleOperation(CodebuffParser.RuleOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOperation}.
	 * @param ctx the parse tree
	 */
	void exitRuleOperation(CodebuffParser.RuleOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXExpression(CodebuffParser.RuleXExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXExpression(CodebuffParser.RuleXExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXAssignment}.
	 * @param ctx the parse tree
	 */
	void enterRuleXAssignment(CodebuffParser.RuleXAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXAssignment}.
	 * @param ctx the parse tree
	 */
	void exitRuleXAssignment(CodebuffParser.RuleXAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpSingleAssign}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpSingleAssign(CodebuffParser.RuleOpSingleAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpSingleAssign}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpSingleAssign(CodebuffParser.RuleOpSingleAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpMultiAssign}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpMultiAssign(CodebuffParser.RuleOpMultiAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpMultiAssign}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpMultiAssign(CodebuffParser.RuleOpMultiAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXOrExpression(CodebuffParser.RuleXOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXOrExpression(CodebuffParser.RuleXOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpOr}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpOr(CodebuffParser.RuleOpOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpOr}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpOr(CodebuffParser.RuleOpOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXAndExpression(CodebuffParser.RuleXAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXAndExpression(CodebuffParser.RuleXAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpAnd}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpAnd(CodebuffParser.RuleOpAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpAnd}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpAnd(CodebuffParser.RuleOpAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXEqualityExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXEqualityExpression(CodebuffParser.RuleXEqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXEqualityExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXEqualityExpression(CodebuffParser.RuleXEqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpEquality}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpEquality(CodebuffParser.RuleOpEqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpEquality}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpEquality(CodebuffParser.RuleOpEqualityContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXRelationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXRelationalExpression(CodebuffParser.RuleXRelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXRelationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXRelationalExpression(CodebuffParser.RuleXRelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpCompare}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpCompare(CodebuffParser.RuleOpCompareContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpCompare}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpCompare(CodebuffParser.RuleOpCompareContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXOtherOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXOtherOperatorExpression(CodebuffParser.RuleXOtherOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXOtherOperatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXOtherOperatorExpression(CodebuffParser.RuleXOtherOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpOther}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpOther(CodebuffParser.RuleOpOtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpOther}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpOther(CodebuffParser.RuleOpOtherContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXAdditiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXAdditiveExpression(CodebuffParser.RuleXAdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXAdditiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXAdditiveExpression(CodebuffParser.RuleXAdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpAdd}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpAdd(CodebuffParser.RuleOpAddContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpAdd}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpAdd(CodebuffParser.RuleOpAddContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXMultiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXMultiplicativeExpression(CodebuffParser.RuleXMultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXMultiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXMultiplicativeExpression(CodebuffParser.RuleXMultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpMulti}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpMulti(CodebuffParser.RuleOpMultiContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpMulti}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpMulti(CodebuffParser.RuleOpMultiContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void enterRuleXUnaryOperation(CodebuffParser.RuleXUnaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void exitRuleXUnaryOperation(CodebuffParser.RuleXUnaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpUnary}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpUnary(CodebuffParser.RuleOpUnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpUnary}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpUnary(CodebuffParser.RuleOpUnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXCastedExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXCastedExpression(CodebuffParser.RuleXCastedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXCastedExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXCastedExpression(CodebuffParser.RuleXCastedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXPostfixOperation}.
	 * @param ctx the parse tree
	 */
	void enterRuleXPostfixOperation(CodebuffParser.RuleXPostfixOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXPostfixOperation}.
	 * @param ctx the parse tree
	 */
	void exitRuleXPostfixOperation(CodebuffParser.RuleXPostfixOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleOpPostfix}.
	 * @param ctx the parse tree
	 */
	void enterRuleOpPostfix(CodebuffParser.RuleOpPostfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleOpPostfix}.
	 * @param ctx the parse tree
	 */
	void exitRuleOpPostfix(CodebuffParser.RuleOpPostfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXMemberFeatureCall}.
	 * @param ctx the parse tree
	 */
	void enterRuleXMemberFeatureCall(CodebuffParser.RuleXMemberFeatureCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXMemberFeatureCall}.
	 * @param ctx the parse tree
	 */
	void exitRuleXMemberFeatureCall(CodebuffParser.RuleXMemberFeatureCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXPrimaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXPrimaryExpression(CodebuffParser.RuleXPrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXPrimaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXPrimaryExpression(CodebuffParser.RuleXPrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXLiteral(CodebuffParser.RuleXLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXLiteral(CodebuffParser.RuleXLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXCollectionLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXCollectionLiteral(CodebuffParser.RuleXCollectionLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXCollectionLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXCollectionLiteral(CodebuffParser.RuleXCollectionLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXSetLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXSetLiteral(CodebuffParser.RuleXSetLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXSetLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXSetLiteral(CodebuffParser.RuleXSetLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXListLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXListLiteral(CodebuffParser.RuleXListLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXListLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXListLiteral(CodebuffParser.RuleXListLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXClosure}.
	 * @param ctx the parse tree
	 */
	void enterRuleXClosure(CodebuffParser.RuleXClosureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXClosure}.
	 * @param ctx the parse tree
	 */
	void exitRuleXClosure(CodebuffParser.RuleXClosureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXExpressionInClosure}.
	 * @param ctx the parse tree
	 */
	void enterRuleXExpressionInClosure(CodebuffParser.RuleXExpressionInClosureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXExpressionInClosure}.
	 * @param ctx the parse tree
	 */
	void exitRuleXExpressionInClosure(CodebuffParser.RuleXExpressionInClosureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXShortClosure}.
	 * @param ctx the parse tree
	 */
	void enterRuleXShortClosure(CodebuffParser.RuleXShortClosureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXShortClosure}.
	 * @param ctx the parse tree
	 */
	void exitRuleXShortClosure(CodebuffParser.RuleXShortClosureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXParenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXParenthesizedExpression(CodebuffParser.RuleXParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXParenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXParenthesizedExpression(CodebuffParser.RuleXParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXIfExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXIfExpression(CodebuffParser.RuleXIfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXIfExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXIfExpression(CodebuffParser.RuleXIfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXSwitchExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXSwitchExpression(CodebuffParser.RuleXSwitchExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXSwitchExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXSwitchExpression(CodebuffParser.RuleXSwitchExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXCasePart}.
	 * @param ctx the parse tree
	 */
	void enterRuleXCasePart(CodebuffParser.RuleXCasePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXCasePart}.
	 * @param ctx the parse tree
	 */
	void exitRuleXCasePart(CodebuffParser.RuleXCasePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXForLoopExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXForLoopExpression(CodebuffParser.RuleXForLoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXForLoopExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXForLoopExpression(CodebuffParser.RuleXForLoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXBasicForLoopExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXBasicForLoopExpression(CodebuffParser.RuleXBasicForLoopExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXBasicForLoopExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXBasicForLoopExpression(CodebuffParser.RuleXBasicForLoopExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXWhileExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXWhileExpression(CodebuffParser.RuleXWhileExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXWhileExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXWhileExpression(CodebuffParser.RuleXWhileExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXDoWhileExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXDoWhileExpression(CodebuffParser.RuleXDoWhileExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXDoWhileExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXDoWhileExpression(CodebuffParser.RuleXDoWhileExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXBlockExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXBlockExpression(CodebuffParser.RuleXBlockExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXBlockExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXBlockExpression(CodebuffParser.RuleXBlockExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXExpressionOrVarDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRuleXExpressionOrVarDeclaration(CodebuffParser.RuleXExpressionOrVarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXExpressionOrVarDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRuleXExpressionOrVarDeclaration(CodebuffParser.RuleXExpressionOrVarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRuleXVariableDeclaration(CodebuffParser.RuleXVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRuleXVariableDeclaration(CodebuffParser.RuleXVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmFormalParameter}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmFormalParameter(CodebuffParser.RuleJvmFormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmFormalParameter}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmFormalParameter(CodebuffParser.RuleJvmFormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleFullJvmFormalParameter}.
	 * @param ctx the parse tree
	 */
	void enterRuleFullJvmFormalParameter(CodebuffParser.RuleFullJvmFormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleFullJvmFormalParameter}.
	 * @param ctx the parse tree
	 */
	void exitRuleFullJvmFormalParameter(CodebuffParser.RuleFullJvmFormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXFeatureCall}.
	 * @param ctx the parse tree
	 */
	void enterRuleXFeatureCall(CodebuffParser.RuleXFeatureCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXFeatureCall}.
	 * @param ctx the parse tree
	 */
	void exitRuleXFeatureCall(CodebuffParser.RuleXFeatureCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleFeatureCallID}.
	 * @param ctx the parse tree
	 */
	void enterRuleFeatureCallID(CodebuffParser.RuleFeatureCallIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleFeatureCallID}.
	 * @param ctx the parse tree
	 */
	void exitRuleFeatureCallID(CodebuffParser.RuleFeatureCallIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleIdOrSuper}.
	 * @param ctx the parse tree
	 */
	void enterRuleIdOrSuper(CodebuffParser.RuleIdOrSuperContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleIdOrSuper}.
	 * @param ctx the parse tree
	 */
	void exitRuleIdOrSuper(CodebuffParser.RuleIdOrSuperContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXConstructorCall}.
	 * @param ctx the parse tree
	 */
	void enterRuleXConstructorCall(CodebuffParser.RuleXConstructorCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXConstructorCall}.
	 * @param ctx the parse tree
	 */
	void exitRuleXConstructorCall(CodebuffParser.RuleXConstructorCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXBooleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXBooleanLiteral(CodebuffParser.RuleXBooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXBooleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXBooleanLiteral(CodebuffParser.RuleXBooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXNullLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXNullLiteral(CodebuffParser.RuleXNullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXNullLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXNullLiteral(CodebuffParser.RuleXNullLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXNumberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXNumberLiteral(CodebuffParser.RuleXNumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXNumberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXNumberLiteral(CodebuffParser.RuleXNumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXStringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXStringLiteral(CodebuffParser.RuleXStringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXStringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXStringLiteral(CodebuffParser.RuleXStringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXTypeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRuleXTypeLiteral(CodebuffParser.RuleXTypeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXTypeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRuleXTypeLiteral(CodebuffParser.RuleXTypeLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXThrowExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXThrowExpression(CodebuffParser.RuleXThrowExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXThrowExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXThrowExpression(CodebuffParser.RuleXThrowExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXReturnExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXReturnExpression(CodebuffParser.RuleXReturnExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXReturnExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXReturnExpression(CodebuffParser.RuleXReturnExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXTryCatchFinallyExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXTryCatchFinallyExpression(CodebuffParser.RuleXTryCatchFinallyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXTryCatchFinallyExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXTryCatchFinallyExpression(CodebuffParser.RuleXTryCatchFinallyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXSynchronizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterRuleXSynchronizedExpression(CodebuffParser.RuleXSynchronizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXSynchronizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitRuleXSynchronizedExpression(CodebuffParser.RuleXSynchronizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXCatchClause}.
	 * @param ctx the parse tree
	 */
	void enterRuleXCatchClause(CodebuffParser.RuleXCatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXCatchClause}.
	 * @param ctx the parse tree
	 */
	void exitRuleXCatchClause(CodebuffParser.RuleXCatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleQualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterRuleQualifiedName(CodebuffParser.RuleQualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleQualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitRuleQualifiedName(CodebuffParser.RuleQualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleNumber}.
	 * @param ctx the parse tree
	 */
	void enterRuleNumber(CodebuffParser.RuleNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleNumber}.
	 * @param ctx the parse tree
	 */
	void exitRuleNumber(CodebuffParser.RuleNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmTypeReference}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmTypeReference(CodebuffParser.RuleJvmTypeReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmTypeReference}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmTypeReference(CodebuffParser.RuleJvmTypeReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleArrayBrackets}.
	 * @param ctx the parse tree
	 */
	void enterRuleArrayBrackets(CodebuffParser.RuleArrayBracketsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleArrayBrackets}.
	 * @param ctx the parse tree
	 */
	void exitRuleArrayBrackets(CodebuffParser.RuleArrayBracketsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXFunctionTypeRef}.
	 * @param ctx the parse tree
	 */
	void enterRuleXFunctionTypeRef(CodebuffParser.RuleXFunctionTypeRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXFunctionTypeRef}.
	 * @param ctx the parse tree
	 */
	void exitRuleXFunctionTypeRef(CodebuffParser.RuleXFunctionTypeRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmParameterizedTypeReference}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmParameterizedTypeReference(CodebuffParser.RuleJvmParameterizedTypeReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmParameterizedTypeReference}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmParameterizedTypeReference(CodebuffParser.RuleJvmParameterizedTypeReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmArgumentTypeReference}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmArgumentTypeReference(CodebuffParser.RuleJvmArgumentTypeReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmArgumentTypeReference}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmArgumentTypeReference(CodebuffParser.RuleJvmArgumentTypeReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmWildcardTypeReference}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmWildcardTypeReference(CodebuffParser.RuleJvmWildcardTypeReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmWildcardTypeReference}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmWildcardTypeReference(CodebuffParser.RuleJvmWildcardTypeReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmUpperBound}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmUpperBound(CodebuffParser.RuleJvmUpperBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmUpperBound}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmUpperBound(CodebuffParser.RuleJvmUpperBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmUpperBoundAnded}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmUpperBoundAnded(CodebuffParser.RuleJvmUpperBoundAndedContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmUpperBoundAnded}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmUpperBoundAnded(CodebuffParser.RuleJvmUpperBoundAndedContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmLowerBound}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmLowerBound(CodebuffParser.RuleJvmLowerBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmLowerBound}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmLowerBound(CodebuffParser.RuleJvmLowerBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleJvmLowerBoundAnded}.
	 * @param ctx the parse tree
	 */
	void enterRuleJvmLowerBoundAnded(CodebuffParser.RuleJvmLowerBoundAndedContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleJvmLowerBoundAnded}.
	 * @param ctx the parse tree
	 */
	void exitRuleJvmLowerBoundAnded(CodebuffParser.RuleJvmLowerBoundAndedContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleQualifiedNameWithWildcard}.
	 * @param ctx the parse tree
	 */
	void enterRuleQualifiedNameWithWildcard(CodebuffParser.RuleQualifiedNameWithWildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleQualifiedNameWithWildcard}.
	 * @param ctx the parse tree
	 */
	void exitRuleQualifiedNameWithWildcard(CodebuffParser.RuleQualifiedNameWithWildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleValidID}.
	 * @param ctx the parse tree
	 */
	void enterRuleValidID(CodebuffParser.RuleValidIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleValidID}.
	 * @param ctx the parse tree
	 */
	void exitRuleValidID(CodebuffParser.RuleValidIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXImportSection}.
	 * @param ctx the parse tree
	 */
	void enterRuleXImportSection(CodebuffParser.RuleXImportSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXImportSection}.
	 * @param ctx the parse tree
	 */
	void exitRuleXImportSection(CodebuffParser.RuleXImportSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleXImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRuleXImportDeclaration(CodebuffParser.RuleXImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleXImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRuleXImportDeclaration(CodebuffParser.RuleXImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CodebuffParser#ruleQualifiedNameInStaticImport}.
	 * @param ctx the parse tree
	 */
	void enterRuleQualifiedNameInStaticImport(CodebuffParser.RuleQualifiedNameInStaticImportContext ctx);
	/**
	 * Exit a parse tree produced by {@link CodebuffParser#ruleQualifiedNameInStaticImport}.
	 * @param ctx the parse tree
	 */
	void exitRuleQualifiedNameInStaticImport(CodebuffParser.RuleQualifiedNameInStaticImportContext ctx);
}