/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.noJdt;

import static org.eclipse.xtext.builder.impl.BuilderUtil.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Sebastian Zarnekow
 */
public class SingleProjectTest extends AbstractBuilderTest {

	private IProject project;

	@Before
	public void createProject() throws Exception {
		project = createEmptyProject("sample");
	}
	
	@After
	public void forgetProject() throws Exception {
		project = null;
	}
	
	private void waitForBuild() throws Exception {
		workspace.build();
	}
	
	@Test public void testValidSimpleModel() throws Exception {
		IFile file = workspace.createFile("sample/file" + F_EXT, "Hello World (from World)!");
		waitForBuild();
		assertEquals(0, countMarkers(file));
	}

	@Test public void testSimpleModelWithSyntaxError() throws Exception {
		IFile file = workspace.createFile("sample/sample" + F_EXT, "Hello World");
		waitForBuild();
		assertEquals(1, countMarkers(file));
	}

	@Test public void testTwoFilesInSameProject() throws Exception {
		IFile file1 = workspace.createFile("sample/a" + F_EXT, "Hello A!");
		IFile file2 = workspace.createFile("sample/b" + F_EXT, "Hello B (from A)!");
		waitForBuild();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
		assertTrue(indexContainsElement(file1.getFullPath().toString(), "A"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(), "B"));
		assertEquals(2, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectRemoveNature() throws Exception {
		workspace.createFile("sample/a" + F_EXT, "Hello A!");
		workspace.createFile("sample/b" + F_EXT, "Hello B (from A)!");
		waitForBuild();
		assertEquals(2, countResourcesInIndex());
		workspace.removeNature(project, XtextProjectHelper.NATURE_ID);
		waitForBuild();
		assertEquals(0, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectWithLinkingError() throws Exception {
		workspace.createFile("sample/a" + F_EXT, "Hello A!");
		IFile file = workspace.createFile("sample/a" + F_EXT, "Hello B (from C)!");
		waitForBuild();
		assertEquals(1, countMarkers(file));
	}

	@Test public void testBug342875() throws Exception {
		IFile file = workspace.createFile("sample/first" + F_EXT, "Hello A");
		ResourceAttributes resourceAttributes = file.getResourceAttributes();
		resourceAttributes.setReadOnly(true);
		file.setResourceAttributes(resourceAttributes);
		try {
			waitForBuild();
			assertTrue(file.isReadOnly());
			assertEquals(1, countMarkers(file));
		} finally {
			resourceAttributes.setReadOnly(false);
			file.setResourceAttributes(resourceAttributes);
		}
	}
}
