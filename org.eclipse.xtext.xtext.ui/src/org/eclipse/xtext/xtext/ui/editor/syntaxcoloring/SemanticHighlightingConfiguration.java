/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.editor.syntaxcoloring;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class SemanticHighlightingConfiguration extends DefaultHighlightingConfiguration {

	public static final String RULE_DECLARATION_ID = "RuleDeclaration"; //$NON-NLS-1$
	public static final String TYPE_REFERENCE_ID = "TypeReference"; //$NON-NLS-1$
	public static final String DATA_TYPE_RULE_ID = "DataTypeIndicator"; //$NON-NLS-1$
	public static final String UNUSED_VALUE_ID = "UnusedValue"; //$NON-NLS-1$
	public static final String NEVER_CALLED_RULE_ID = "NeverCalledRule"; //$NON-NLS-1$
	public static final String SPECIAL_ATTRIBUTE_ID = "SpecialFeature"; //$NON-NLS-1$
	
	@Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		super.configure(acceptor);
		acceptor.acceptDefaultHighlighting(RULE_DECLARATION_ID, Messages.SemanticHighlightingConfiguration_5, defaultTextStyle());
		acceptor.acceptDefaultHighlighting(TYPE_REFERENCE_ID, Messages.SemanticHighlightingConfiguration_6, typeReference());
		acceptor.acceptDefaultHighlighting(DATA_TYPE_RULE_ID, Messages.SemanticHighlightingConfiguration_7, dataTypeRule());
		acceptor.acceptDefaultHighlighting(UNUSED_VALUE_ID, Messages.SemanticHighlightingConfiguration_8, unusedValue());
		acceptor.acceptDefaultHighlighting(NEVER_CALLED_RULE_ID, Messages.SemanticHighlightingConfiguration_9, unusedRule());
		acceptor.acceptDefaultHighlighting(SPECIAL_ATTRIBUTE_ID, Messages.SemanticHighlightingConfiguration_10, specialAttribute());
	}

	public TextStyle typeReference() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setStyle(SWT.ITALIC);
		return textStyle;
	}
	
	public TextStyle dataTypeRule() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(0, 0, 192));
		return textStyle;
	}
	
	public TextStyle unusedValue() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(128, 128, 128));
		return textStyle;
	}
	
	public TextStyle unusedRule() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(128, 128, 128));
		return textStyle;
	}
	
	public TextStyle specialAttribute(){
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(171, 48, 0));
		return textStyle;
	}
	

	
}
