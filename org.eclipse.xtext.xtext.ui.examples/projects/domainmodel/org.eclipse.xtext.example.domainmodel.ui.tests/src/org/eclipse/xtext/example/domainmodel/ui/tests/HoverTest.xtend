/*******************************************************************************
 * Copyright (c) 2018, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.domainmodel.ui.tests

import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.testing.AbstractHoverTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.createJavaProject

/**
 * @author miklossy - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(DomainmodelUiInjectorProvider)
class HoverTest extends AbstractHoverTest {

	@Before def void setup() {
		/*
		 * Xbase-based languages require java project
		 */
		 projectName.createJavaProject
	}

	@Test def hover_over_import_statement() {
		'''
			import java.util.List
			
			entity Foo {
				bar : List<String>
			}
		'''.hasHoverOver("java.util.List", '''An ordered collection (also known as a <i>sequence</i>''')
	}

	@Test def hover_over_link_in_javadoc() {
		'''
			/**
			 * {@link java.util.List}
			 */
			 entity Foo {}
		'''.hasHoverOver("java.util.List", '''An ordered collection (also known as a <i>sequence</i>''')
	}

	@Test def hover_over_java_typed_property() {
		'''
			entity Foo {
				name : Object
			}
		'''.hasHoverOver("name", '''Property name : Object''')
	}

	@Test def hover_over_java_type() {
		'''
			entity Foo {
				name : Object
			}
		'''.hasHoverOver("Object", '''Class <code>Object</code> is the root of the class hierarchy.''')
	}

	@Test def hover_over_entity_typed_property() {
		'''
			entity Foo {
				b : Bar
			}
			
			entity Bar {}
		'''.hasHoverOver("b", '''Property b : Bar''')
	}

	@Test def hover_over_entity_type() {
		'''
			entity Foo {
				b : Bar
			}
			
			/**
			 * Documentation of the entity Bar.
			 */
			entity Bar {}
		'''.hasHoverOver("Bar", '''Documentation of the entity Bar.''')
	}

	@Test def hover_over_operation() {
		'''
			entity Foo {
				b : String
				op : getB() {
					b
				}
			}
		'''.hasHoverOver("getB", '''getB() : String''')
	}
}