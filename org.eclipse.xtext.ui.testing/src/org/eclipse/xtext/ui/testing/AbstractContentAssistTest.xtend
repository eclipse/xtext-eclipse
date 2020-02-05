/*******************************************************************************
 * Copyright (c) 2013, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.testing

import com.google.inject.Inject
import com.google.inject.Injector
import com.google.inject.Provider
import java.io.InputStream
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory
import org.eclipse.xtext.resource.FileExtensionProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.ui.testing.util.ResourceLoadHelper
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

import static org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.*

/**
 * @since 2.12
 */
abstract class AbstractContentAssistTest implements ResourceLoadHelper, IJavaProjectProvider {
	
	@Inject Provider<XtextResourceSet> resourceSetProvider
	
	@Inject FileExtensionProvider fileExtensionProvider

	@Inject Injector injector
	
	static IJavaProject javaProject
	
	@BeforeClass @BeforeAll
	def static void setUp() {
		javaProject = createJavaProject("contentAssistTest")
	}	
	
	@AfterClass @AfterAll
	def static void tearDown() {
		javaProject.project.delete(true, new NullProgressMonitor)
	}
	
	@SuppressWarnings("unchecked")
	def protected expect(String[]... arrays) {
		val expectation = newArrayList()
		for(array: arrays) {
			expectation.addAll(array)
		}
		return expectation
	}
	
	def protected ContentAssistProcessorTestBuilder newBuilder() throws Exception {
		new ContentAssistProcessorTestBuilder(injector, this)
	}
	
	override getResourceFor(InputStream stream) {
		val set = resourceSetProvider.get()
		initializeTypeProvider(set)
		val result = set.createResource(URI::createURI("Test." + fileExtensionProvider.primaryFileExtension))
		result.load(stream, null)
		result as XtextResource
	}
	
	def protected initializeTypeProvider(XtextResourceSet set) {
		val typeProviderFactory = new JdtTypeProviderFactory(this)
		typeProviderFactory.findOrCreateTypeProvider(set);
		set.setClasspathURIContext(getJavaProject(set));
	}

	override getJavaProject(ResourceSet resourceSet) {
		javaProject
	}
	
}
