/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug348427Scenario2_4Test extends AbstractBug348427Test {

	@Override
	protected String getScenario() {
		return "2.4";
	}
	
	@Override
	@Test public void testExpectNextKeyword_1() throws Exception {
		newBuilder().append("Name : a child1 end ").assertText("next", "Name");
	}
	
	@Override
	@Test public void testExpectNextKeyword_2() throws Exception {
		newBuilder().append("Name : b child2 end ").assertText("next", "Name");
	}
	
	@Override
	@Test public void testExpectNextKeyword_3() throws Exception {
		newBuilder().append("Name : bool keyword a child3 end ").assertText("next", "Name");
	}

}
