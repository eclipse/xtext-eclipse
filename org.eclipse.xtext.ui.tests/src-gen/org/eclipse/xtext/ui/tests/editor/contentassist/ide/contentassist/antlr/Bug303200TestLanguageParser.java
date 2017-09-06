/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.ide.contentassist.antlr;

import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.tests.editor.contentassist.ide.contentassist.antlr.internal.InternalBug303200TestLanguageParser;
import org.eclipse.xtext.ui.tests.editor.contentassist.services.Bug303200TestLanguageGrammarAccess;

public class Bug303200TestLanguageParser extends AbstractContentAssistParser {

	@Inject
	private Bug303200TestLanguageGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalBug303200TestLanguageParser createParser() {
		InternalBug303200TestLanguageParser result = new InternalBug303200TestLanguageParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getProgramDirectiveAccess().getAlternatives(), "rule__ProgramDirective__Alternatives");
					put(grammarAccess.getAttributeAccess().getAlternatives(), "rule__Attribute__Alternatives");
					put(grammarAccess.getStatementAccess().getAlternatives(), "rule__Statement__Alternatives");
					put(grammarAccess.getStatementAccess().getAlternatives_1_2(), "rule__Statement__Alternatives_1_2");
					put(grammarAccess.getPostfixExpressionAccess().getAlternatives_1(), "rule__PostfixExpression__Alternatives_1");
					put(grammarAccess.getPropertyOperatorAccess().getAlternatives(), "rule__PropertyOperator__Alternatives");
					put(grammarAccess.getPrimaryExpressionAccess().getAlternatives(), "rule__PrimaryExpression__Alternatives");
					put(grammarAccess.getProgramAccess().getGroup(), "rule__Program__Group__0");
					put(grammarAccess.getProgramAccess().getGroup_1(), "rule__Program__Group_1__0");
					put(grammarAccess.getFunctionDefinitionAccess().getGroup(), "rule__FunctionDefinition__Group__0");
					put(grammarAccess.getAttributeAccess().getGroup_1(), "rule__Attribute__Group_1__0");
					put(grammarAccess.getParametersAccess().getGroup(), "rule__Parameters__Group__0");
					put(grammarAccess.getParametersAccess().getGroup_3(), "rule__Parameters__Group_3__0");
					put(grammarAccess.getParametersAccess().getGroup_3_2(), "rule__Parameters__Group_3_2__0");
					put(grammarAccess.getBlockAccess().getGroup(), "rule__Block__Group__0");
					put(grammarAccess.getBlockAccess().getGroup_2(), "rule__Block__Group_2__0");
					put(grammarAccess.getStatementAccess().getGroup_1(), "rule__Statement__Group_1__0");
					put(grammarAccess.getPostfixExpressionAccess().getGroup(), "rule__PostfixExpression__Group__0");
					put(grammarAccess.getPostfixExpressionAccess().getGroup_1_0(), "rule__PostfixExpression__Group_1_0__0");
					put(grammarAccess.getPostfixExpressionAccess().getGroup_1_1(), "rule__PostfixExpression__Group_1_1__0");
					put(grammarAccess.getPostfixExpressionAccess().getGroup_1_1_4(), "rule__PostfixExpression__Group_1_1_4__0");
					put(grammarAccess.getListExpressionAccess().getGroup(), "rule__ListExpression__Group__0");
					put(grammarAccess.getListExpressionAccess().getGroup_1(), "rule__ListExpression__Group_1__0");
					put(grammarAccess.getPropertyOperatorAccess().getGroup_0(), "rule__PropertyOperator__Group_0__0");
					put(grammarAccess.getPropertyOperatorAccess().getGroup_1(), "rule__PropertyOperator__Group_1__0");
					put(grammarAccess.getPrimaryExpressionAccess().getGroup_0(), "rule__PrimaryExpression__Group_0__0");
					put(grammarAccess.getPrimaryExpressionAccess().getGroup_1(), "rule__PrimaryExpression__Group_1__0");
					put(grammarAccess.getProgramAccess().getDirectivesAssignment_1_1(), "rule__Program__DirectivesAssignment_1_1");
					put(grammarAccess.getFunctionDefinitionAccess().getAttributesAssignment_0(), "rule__FunctionDefinition__AttributesAssignment_0");
					put(grammarAccess.getFunctionDefinitionAccess().getNameAssignment_3(), "rule__FunctionDefinition__NameAssignment_3");
					put(grammarAccess.getFunctionDefinitionAccess().getParamsAssignment_5(), "rule__FunctionDefinition__ParamsAssignment_5");
					put(grammarAccess.getFunctionDefinitionAccess().getBodyAssignment_7(), "rule__FunctionDefinition__BodyAssignment_7");
					put(grammarAccess.getAttributeAccess().getIdentAssignment_0(), "rule__Attribute__IdentAssignment_0");
					put(grammarAccess.getAttributeAccess().getExpressionAssignment_1_3(), "rule__Attribute__ExpressionAssignment_1_3");
					put(grammarAccess.getParametersAccess().getParamsAssignment_3_0(), "rule__Parameters__ParamsAssignment_3_0");
					put(grammarAccess.getParametersAccess().getParamsAssignment_3_2_2(), "rule__Parameters__ParamsAssignment_3_2_2");
					put(grammarAccess.getBlockAccess().getDirectivesAssignment_2_1(), "rule__Block__DirectivesAssignment_2_1");
					put(grammarAccess.getStatementAccess().getExpressionAssignment_1_1(), "rule__Statement__ExpressionAssignment_1_1");
					put(grammarAccess.getPostfixExpressionAccess().getPropertyAssignment_1_0_2(), "rule__PostfixExpression__PropertyAssignment_1_0_2");
					put(grammarAccess.getPostfixExpressionAccess().getArgumentsAssignment_1_1_4_0(), "rule__PostfixExpression__ArgumentsAssignment_1_1_4_0");
					put(grammarAccess.getListExpressionAccess().getExpressionsAssignment_0(), "rule__ListExpression__ExpressionsAssignment_0");
					put(grammarAccess.getListExpressionAccess().getExpressionsAssignment_1_3(), "rule__ListExpression__ExpressionsAssignment_1_3");
					put(grammarAccess.getPropertyOperatorAccess().getNameAssignment_0_2(), "rule__PropertyOperator__NameAssignment_0_2");
					put(grammarAccess.getPropertyOperatorAccess().getExpressionsAssignment_1_2(), "rule__PropertyOperator__ExpressionsAssignment_1_2");
					put(grammarAccess.getPrimaryExpressionAccess().getNameAssignment_0_1(), "rule__PrimaryExpression__NameAssignment_0_1");
					put(grammarAccess.getPrimaryExpressionAccess().getParamsAssignment_1_3(), "rule__PrimaryExpression__ParamsAssignment_1_3");
					put(grammarAccess.getPrimaryExpressionAccess().getBodyAssignment_1_5(), "rule__PrimaryExpression__BodyAssignment_1_5");
				}
			};
		}
		return nameMappings.get(element);
	}
			
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS" };
	}

	public Bug303200TestLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(Bug303200TestLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
