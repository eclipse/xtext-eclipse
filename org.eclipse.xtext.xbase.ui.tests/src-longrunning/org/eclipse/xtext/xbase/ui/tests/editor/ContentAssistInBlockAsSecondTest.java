/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.tests.editor;

import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ContentAssistInBlockAsSecondTest extends ContentAssistInBlockTest {

	@Override
	protected String getPrefix() {
		return "{ doesNotExist()";
	}
	
	@Override
	protected String getSuffix() {
		return "\n}";
	}
	
	@Override
	@Test public void testEmptyInput() throws Exception {
		newBuilder().assertText(expect(getKeywordsAndStatics(), VARIABLE_DECL, CAST_INSTANCEOF));
	}
	
}
