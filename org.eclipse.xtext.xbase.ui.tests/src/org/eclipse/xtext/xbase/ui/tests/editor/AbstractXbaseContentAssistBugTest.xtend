/*******************************************************************************
 * Copyright (c) 2013, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.tests.editor

import java.io.InputStream
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.CoreException
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder
import org.eclipse.xtext.ui.testing.util.TargetPlatformUtil
import org.eclipse.xtext.xbase.ui.tests.AbstractXbaseUITestCase
import org.junit.AfterClass
import org.junit.BeforeClass

import static org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.*

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
abstract class AbstractXbaseContentAssistBugTest extends AbstractXbaseUITestCase implements IJavaProjectProvider {

	IProject demandCreateProject;
	
	static IProject staticProject;
	
	@BeforeClass
	def static void createTestProject() throws Exception {
		TargetPlatformUtil.setTargetPlatform(AbstractXbaseContentAssistBugTest)
		staticProject = AbstractXbaseUITestCase::createPluginProject(AbstractXbaseContentAssistBugTest.name);
	}
	
	@AfterClass
	def static void deleteTestProject() throws Exception {
		deleteProject(staticProject);
	}
	
	override void tearDown() throws Exception {
		if (demandCreateProject !== null)
			deleteProject(demandCreateProject);
		super.tearDown();
	}
	
	override boolean doCleanWorkspace() {
		return false;
	}

	override getJavaProject(ResourceSet resourceSet) {
		val projectName = getProjectName();
		var javaProject = findJavaProject(projectName);
		if (javaProject === null || !javaProject.exists()) {
			try {
				demandCreateProject = AbstractXbaseUITestCase::createPluginProject(projectName);
				javaProject = findJavaProject(projectName);
			} catch (CoreException e) {
				fail("cannot create java project due to: " + e.getMessage() + " / " + e);
			}
		}
		return javaProject;
	}

	def protected String getProjectName() {
		return getClass().getSimpleName() + "Project";
	}
	
	override getResourceFor(InputStream stream) {
		val result = super.getResourceFor(stream);
		initializeTypeProvider(result);
		return result;
	}
	
	def protected void initializeTypeProvider(XtextResource result) {
		val resourceSet = result.getResourceSet() as XtextResourceSet
		val typeProviderFactory = new JdtTypeProviderFactory(this);
		typeProviderFactory.findOrCreateTypeProvider(resourceSet);
		resourceSet.setClasspathURIContext(getJavaProject(resourceSet));
	}
	
	def protected newBuilder() throws Exception {
		return new ContentAssistProcessorTestBuilder(getInjector(), this);
	}
	
}
