/*******************************************************************************
 * Copyright (c) 2013, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.domainmodel.tests

import com.google.inject.Inject
import org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.eclipse.xtext.xbase.validation.IssueCodes.*
import static org.eclipse.xtext.xtype.XtypePackage.Literals.*

@RunWith(XtextRunner)
@InjectWith(DomainmodelInjectorProvider)
/**
 * @author Jan Koehnlein - copied and adapted from Xtend
 */
class ValidationTests {

	@Inject extension ParseHelper<DomainModel> parseHelper

	@Inject extension ValidationTestHelper validationTestHelper

	@Test def testImportUnused() {
		val model = parse('''
			import java.util.List
			entity X {}
		''');
		assertWarning(model, XIMPORT_DECLARATION, IMPORT_UNUSED);
	}

	@Test def testImportUnused_1() {
		val model = parse('''
			import java.util.List
			entity X {
				sb: java.util.List<String>
			}
		''');
		assertWarning(model, XIMPORT_DECLARATION, IMPORT_UNUSED);
	}

	@Test def testImportUnused_2() {
		val model = parse('''
			import java.util.List
			entity X {
				sb : List<String>
				op foo() : List<String> {
					sb
				}
			}
		''');
		assertNoIssues(model);
	}

	@Test def testImportUnused_3() {
		val model = parse('''
			import java.util.Map$Entry
			entity X {
				sb: Entry<String, String>
				op foo() : Entry<String, String> {
					sb
				}
			}
		''');
		assertNoIssues(model);
	}

	@Test def testImportUnused_4() {
		val model = parse('''
			import java.util.Map
			entity X { 
				sb: Map$Entry<String, String> 
				op foo() : Map$Entry<String, String> {
					sb
				}
			}
		''');
		assertNoIssues(model);
	}

	@Test def testImportUnused_5() {
		val model = parse('''
			import java.util.Map$Entry
			entity X {
				sb: Map$Entry<String, String>
				op foo(): Map$Entry<String, String> {
					sb
				}
			}
		''');
		assertNoIssues(model);
	}

	@Test def testImportUnused_6() {
		val model = parse('''
			import java.awt.Label
			/** {@link Label} */ 
			entity X{}
		''');
		assertNoIssues(model);
	}

	@Test def testImportUnused_7() {
		val model = parse('''
			import java.awt.Label
			/** @see Label */
			entity X{}
		''');
		assertNoIssues(model);
	}

	@Test def testImportDuplicate() {
		val model = parse('''
			import java.util.List
			import java.util.List
			entity X {
				sb: List<String>
				op foo() : List<String> {
					sb
				}
			}
		''');
		assertWarning(model, XIMPORT_DECLARATION, IMPORT_UNUSED);
	}

	@Test def testImportCollision() {
		val model = parse('''
			import java.util.List
			import java.awt.List
			entity X {
				sb: List
				op foo() : List {
					sb
				}
			}
		''');
		assertError(model, XIMPORT_DECLARATION, IMPORT_COLLISION);
	}

	@Test def testImportWildcard() {
		val model = parse('''
			import java.util.*
			import java.util.List
			entity X {
				sb: List<String>
				op foo() : List<String> {
					sb
				}
			}
		''');
		assertWarning(model, XIMPORT_DECLARATION, IMPORT_WILDCARD_DEPRECATED);
	}

	@Test def testImportConflictWithTypeInSameFile() {
		val model = parse('''
			import java.util.List 
			entity List {
				
			}
		''');
		assertError(model, XIMPORT_DECLARATION, IMPORT_CONFLICT);
	}

	@Test def testImportNoConflictWithTypeInSameFile() {
		val model = parse('''
			import java.util.List
			entity List2 {
			}
		''');
		assertNoErrors(model);
	}

	def protected parse(CharSequence modelAsText) {
		parseHelper.parse(modelAsText)
	}
}