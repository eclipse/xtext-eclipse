/*******************************************************************************
 * Copyright (c) 2012, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dennis Huebner - Initial contribution and API
 */
public class Bug386476Test extends AbstractParticipatingBuilderTest {
	private IJavaProject javaProject;

	@Before
	public void createProject() throws Exception {
		javaProject = workspace.createJavaProject("BuilderDisabled");
		workspace.addNature(javaProject.getProject(), XtextProjectHelper.NATURE_ID);
		workspace.build();
	}

	@Test
	public void testBuildIsNotInvokedWhenBuilderIsDisabled() throws Exception {
		IProject project = javaProject.getProject();
		IFile file = createSomeBuilderRelatedFile(project);
		workspace.build();
		startLogging();

		// With Xtext Builder activated builder is invoked
		stimulateBuildSchedulerTrigger(project);
		assertEquals("Xtext builder is triggered.", 1, getInvocationCount());

		// Disable/remove Xtext builder
		workspace.removeBuilder(project, XtextProjectHelper.BUILDER_ID);
		reset();

		// With Xtext Builder activated builder is not invoked
		stimulateBuildSchedulerTrigger(project);
		assertEquals("Xtext builder is triggered but should not, cause was disabled", 0, getInvocationCount());
		stopLogging();
		file.delete(true, false, workspace.monitor());
	}

	private void stimulateBuildSchedulerTrigger(IProject project) throws CoreException {
		project.close(workspace.monitor());
		workspace.build();
		project.open(workspace.monitor());
		workspace.build();
	}

	private IFile createSomeBuilderRelatedFile(IProject project) throws CoreException {
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, workspace.monitor());
		workspace.build();
		return file;
	}
}
