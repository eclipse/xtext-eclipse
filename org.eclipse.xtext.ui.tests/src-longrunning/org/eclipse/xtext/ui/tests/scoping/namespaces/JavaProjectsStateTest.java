/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.scoping.namespaces;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.ui.util.JavaProjectClasspathChangeAnalyzer;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.containers.JavaProjectsState;
import org.eclipse.xtext.ui.containers.JavaProjectsStateHelper;
import org.eclipse.xtext.ui.containers.WorkspaceProjectsStateHelper;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.ui.resource.IStorage2UriMapperJdtExtensions;
import org.eclipse.xtext.ui.resource.Storage2UriMapperImpl;
import org.eclipse.xtext.ui.shared.JdtHelper;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class JavaProjectsStateTest extends AbstractJavaProjectsStateTest {

	private URI simpleUri1;
	private URI simpleUri2;
	private URI simpleUri3;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		simpleUri1 = createFileAndRegisterResource(project1, "file1");
		simpleUri2 = createFileAndRegisterResource(project1, "file2");
		simpleUri3 = createFileAndRegisterResource(project2, "file3");
	}
	
	@Override
	protected JavaProjectsState createProjectsState(IStorage2UriMapper mapper) {
		JavaProjectsState result = new JavaProjectsState();
		result.setMapper(mapper);
		result.setJdtHelper(new JdtHelper());
		result.setJavaProjectClasspathChangeAnalyzer(new JavaProjectClasspathChangeAnalyzer());
		JavaProjectsStateHelper javaProjectsStateHelper = new JavaProjectsStateHelper();
		javaProjectsStateHelper.setMapper(mapper);
		javaProjectsStateHelper.setUriMapperExtensions((IStorage2UriMapperJdtExtensions) ((Storage2UriMapperImpl)mapper).getContribution());
		javaProjectsStateHelper.setWorkspace(ResourcesPlugin.getWorkspace());
		result.setJavaProjectsHelper(javaProjectsStateHelper);
		WorkspaceProjectsStateHelper workspaceStateHelper = new WorkspaceProjectsStateHelper();
		workspaceStateHelper.setMapper(mapper);
		workspaceStateHelper.setWorkspace(ResourcesPlugin.getWorkspace());
		result.setProjectsHelper(workspaceStateHelper);
		return result;
	}
	
	@Test public void testGetContainerHandle_01() {
		String uri1ContainerHandle = projectsState.getContainerHandle(uri1);
		String uri2ContainerHandle = projectsState.getContainerHandle(uri2);
		String uri3ContainerHandle = projectsState.getContainerHandle(uri3);
		assertEquals(srcRoot.getHandleIdentifier(), uri1ContainerHandle);
		assertEquals(srcRoot.getHandleIdentifier(), uri2ContainerHandle);
		assertEquals(project2.getName(), uri3ContainerHandle);
	}
	
	@Test public void testGetContainerHandle_02() throws CoreException {
		IResourcesSetupUtil.removeNature(project1, XtextProjectHelper.NATURE_ID);
		IResourcesSetupUtil.removeNature(project2, XtextProjectHelper.NATURE_ID);
		String uri1ContainerHandle = projectsState.getContainerHandle(uri1);
		String uri2ContainerHandle = projectsState.getContainerHandle(uri2);
		String uri3ContainerHandle = projectsState.getContainerHandle(uri3);
		assertEquals(srcRoot.getHandleIdentifier(), uri1ContainerHandle);
		assertEquals(srcRoot.getHandleIdentifier(), uri2ContainerHandle);
		assertEquals(project2.getName(), uri3ContainerHandle);
	}
	
	@Test public void testGetContainerHandle_Bug316519_03() {
		IFile file = getFile(project1, "doesNotExist");
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		String handleIdent = projectsState.getContainerHandle(uri);
		assertEquals(project1.getName(), handleIdent);
	}
	
	@Test public void testGetContainerHandle_Bug316519_04() throws CoreException {
		IFile file = getFile(project1, "doesNotExist");
		IResourcesSetupUtil.removeNature(file.getProject(), XtextProjectHelper.NATURE_ID);
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		String handleIdent = projectsState.getContainerHandle(uri);
		assertEquals(project1.getName(), handleIdent);
	}
	
	@Test public void testGetContainedURIs_01() {
		Collection<URI> containedURIs = projectsState.getContainedURIs(srcRoot.getHandleIdentifier());
		assertEquals(2, containedURIs.size());
		assertTrue(containedURIs.contains(uri1));
		assertTrue(containedURIs.contains(uri2));
		assertFalse(containedURIs.contains(uri3));
	}
	
	@Test public void testGetContainedURIs_02() {
		Collection<URI> containedURIs = projectsState.getContainedURIs(project1.getName());
		assertEquals(containedURIs.toString(), 4, containedURIs.size());
		assertTrue(containedURIs.contains(uri1));
		assertTrue(containedURIs.contains(simpleUri1));
		assertTrue(containedURIs.contains(uri2));
		assertTrue(containedURIs.contains(simpleUri2));
		assertFalse(containedURIs.contains(uri3));
		assertFalse(containedURIs.contains(simpleUri3));
	}
	
	@Test public void testGetContainedURIs_03() throws CoreException, InvocationTargetException, InterruptedException {
		Collection<URI> containedURIs = projectsState.getContainedURIs(project1.getName());
		assertEquals(containedURIs.toString(), 4, containedURIs.size());
		URI uri = createFileAndRegisterResource(project1, "file3");
		containedURIs = projectsState.getContainedURIs(project1.getName());
		assertEquals(containedURIs.toString(), 5, containedURIs.size());
		assertTrue(containedURIs.contains(uri1));
		assertTrue(containedURIs.contains(simpleUri1));
		assertTrue(containedURIs.contains(uri2));
		assertTrue(containedURIs.contains(simpleUri2));
		assertTrue(containedURIs.contains(uri));
	}
	
	@Test public void testRemoveNature_02() throws CoreException {
		Collection<URI> containedURIs = projectsState.getContainedURIs(project1.getName());
		assertEquals(4, containedURIs.size());
		IResourcesSetupUtil.removeNature(project1, XtextProjectHelper.NATURE_ID);
		containedURIs = projectsState.getContainedURIs(project1.getName());
		assertTrue(containedURIs.isEmpty());
	}
}
