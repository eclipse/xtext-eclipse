/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.jdt;

import java.util.List;

import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.access.impl.AbstractTypeProviderTest;
import org.eclipse.xtext.common.types.testSetups.TestEnum;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public abstract class AbstractJdtTypeProviderTest extends AbstractTypeProviderTest {
	
	@BeforeClass public static void createMockJavaProject() throws Exception {
		MockJavaProjectProvider.setUp();
	}
	
	@Test public void testEnum_07() throws Exception {
		doTestEnum_07("string");
	}
	
	protected void doTestEnum_07(String paramName) throws Exception {
		String typeName = TestEnum.class.getName();
		JvmEnumerationType type = (JvmEnumerationType) getTypeProvider().findTypeByName(typeName);
		boolean constructorSeen = false;
		for(JvmMember member: type.getMembers()) {
			if (member instanceof JvmConstructor) {
				constructorSeen = true;
				List<JvmFormalParameter> parameters = ((JvmConstructor) member).getParameters();
				assertEquals(1, parameters.size());
				JvmFormalParameter singleParam = parameters.get(0);
				assertEquals(paramName, singleParam.getName());
			}
		}
		assertTrue("constructorSeen", constructorSeen);
	}
}
