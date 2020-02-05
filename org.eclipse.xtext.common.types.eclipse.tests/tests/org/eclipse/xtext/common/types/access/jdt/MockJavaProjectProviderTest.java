/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.jdt;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class MockJavaProjectProviderTest extends Assert {
	
	@BeforeClass public static void createMockJavaProject() throws Exception {
		MockJavaProjectProvider.setUp();
	}

	@Test public void testClasspathResolved() throws CoreException {
		IJavaProject javaProject = new MockJavaProjectProvider().getJavaProject(null);
		javaProject.getResolvedClasspath(false);
//		assertTrue(expandAndLookFor(javaProject, 0, ParameterizedTypes.class.getSimpleName() + ".class"));
		
	}
	
//	public boolean expandAndLookFor(IJavaElement javaElement, int tab, String input) throws CoreException {
//		IJavaElement[] children = null;
//		// force opening of element by getting its children
//		if (javaElement instanceof IParent) {
//			IParent parent = (IParent)javaElement;
//			children = parent.getChildren();
//		}
//		StringBuffer buffer = new StringBuffer(16);
//		((JavaElement)javaElement).toStringInfo(tab, buffer);
////		System.out.println(buffer);
//		boolean result = buffer.indexOf(input) >= 0;
//		if (!result) {
//			if (children != null) {
//				for (int i = 0, length = children.length; i < length; i++) {
//					result |= expandAndLookFor(children[i], tab + 1, input);
//				}
//			}
//		}
//		return result;
//	}
	
}
