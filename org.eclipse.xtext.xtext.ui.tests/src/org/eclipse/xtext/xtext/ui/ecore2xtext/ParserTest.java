/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.ecore2xtext;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Test;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class ParserTest extends AbstractXtextTests {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		with(Ecore2XtextTestStandaloneSetup.class);
		Ecore2xtextPackage.eINSTANCE.getNsURI(); // register EPackage
	}
	
	@Test public void testSimpleExample() throws Exception {
		EObject model = getModel("Root INT {classes { Concrete0 foo, Concrete1 bar } }");
		assertTrue(model instanceof Root);
		assertEquals("INT", model.eResource().getURIFragment(model));
		assertTrue("INT".equals(((Root)model).getName()));
		EList<Abstract> classes = ((Root) model).getClasses();
		assertEquals(2, classes.size());
		assertTrue(classes.get(0) instanceof Concrete0);
		assertEquals("foo", classes.get(0).getName());
		assertTrue(classes.get(1) instanceof Concrete1);
		assertEquals("bar", classes.get(1).getName());
	}
	
	@Override
	protected boolean shouldTestSerializer(XtextResource resource) {
		return false;
	}
}
