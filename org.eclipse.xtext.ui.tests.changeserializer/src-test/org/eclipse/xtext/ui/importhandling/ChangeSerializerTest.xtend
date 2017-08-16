/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.importhandling

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.ide.serializer.impl.ChangeSerializer
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.InMemoryURIHandler
import org.eclipse.xtext.ui.tests.changeserializer.tests.ChangeSerializerInjectorProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration
import org.junit.Ignore

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(ChangeSerializerInjectorProvider)
class ChangeSerializerTest {

	@Inject Provider<ChangeSerializer> serializerProvider
	@Inject extension ChangeSerializerTestHelper

	@Test @Ignore
	def void testRenameGlobal1() {
		val fs = new InMemoryURIHandler()
		fs += "inmemory:/file1.chgser" -> '''
			package pkg1
			
			element Foo {
			}
		'''
		fs += "inmemory:/file2.chgser" -> '''
			package pkg2
			
			import pkg1.Foo
			
			element Bar {
				ref pkg1.Foo
			}
		'''

		val rs = fs.createResourceSet
		val model = rs.contents("inmemory:/file1.chgser", PackageDeclaration)

		val serializer = serializerProvider.get()
		serializer.beginRecordChanges(model.eResource)
		model.name = "newpackage"
		Assert.assertEquals(1, model.eResource.resourceSet.resources.size)
		serializer.endRecordChangesToTextDocuments === '''
			FIXME
		'''
	}

}
