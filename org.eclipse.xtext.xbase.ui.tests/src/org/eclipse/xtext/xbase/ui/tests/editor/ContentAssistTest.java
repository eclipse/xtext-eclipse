/*******************************************************************************
 * Copyright (c) 2011, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.tests.editor;

import static org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.ui.testing.util.TargetPlatformUtil;
import org.eclipse.xtext.xbase.ui.internal.XbaseActivator;
import org.eclipse.xtext.xbase.ui.testing.AbstractXbaseContentAssistTest;
import org.eclipse.xtext.xbase.ui.tests.AbstractXbaseUITestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ContentAssistTest extends AbstractXbaseContentAssistTest {

	protected static final String PROJECT_NAME = "ContentAssistTestProject";

	private IProject demandCreateProject;
	
	private static IProject staticProject;
	
	@BeforeClass
	public static void createTestProject() throws Exception {
		TargetPlatformUtil.setTargetPlatform(ContentAssistTest.class);
		staticProject = AbstractXbaseUITestCase.createPluginProject(PROJECT_NAME);
		doInitFeatures(JavaCore.create(staticProject));
	}
	
	@AfterClass
	public static void deleteTestProject() throws Exception {
		deleteProject(staticProject);
	}
	
	@Override
	protected Injector getInjector() {
		return XbaseActivator.getInstance().getInjector("org.eclipse.xtext.xbase.Xbase");
	}
	
	@Override
	public void tearDown() throws Exception {
		if (demandCreateProject != null)
			deleteProject(demandCreateProject);
		super.tearDown();
	}

	@Override
	protected boolean doCleanWorkspace() {
		return false;
	}
	
	@Override
	public IJavaProject getJavaProject(ResourceSet resourceSet) {
		IJavaProject javaProject = findJavaProject(PROJECT_NAME);
		if (javaProject == null || !javaProject.exists()) {
			try {
				demandCreateProject = AbstractXbaseUITestCase.createPluginProject(PROJECT_NAME);
				javaProject = findJavaProject(PROJECT_NAME);
			} catch (CoreException e) {
				fail("cannot create java project due to: " + e.getMessage() + " / " + e);
			}
		}
		return javaProject;
	}
	
}
