/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.scoping.namespaces;

import java.util.Collection;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.ui.util.JavaProjectClasspathChangeAnalyzer;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.containers.JavaProjectsStateHelper;
import org.eclipse.xtext.ui.containers.StrictJavaProjectsState;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.ui.resource.IStorage2UriMapperJdtExtensions;
import org.eclipse.xtext.ui.resource.Storage2UriMapperImpl;
import org.eclipse.xtext.ui.shared.JdtHelper;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class StrictJavaProjectsStateTest extends AbstractJavaProjectsStateTest {

	@Override
	protected StrictJavaProjectsState createProjectsState(IStorage2UriMapper mapper) {
		StrictJavaProjectsState result = new StrictJavaProjectsState();
		result.setMapper(mapper);
		result.setJdtHelper(new JdtHelper());
		result.setJavaProjectClasspathChangeAnalyzer(new JavaProjectClasspathChangeAnalyzer());
		JavaProjectsStateHelper javaProjectsStateHelper = new JavaProjectsStateHelper();
		javaProjectsStateHelper.setMapper(mapper);
		javaProjectsStateHelper.setUriMapperExtensions((IStorage2UriMapperJdtExtensions) ((Storage2UriMapperImpl)mapper).getContribution());
		javaProjectsStateHelper.setWorkspace(ResourcesPlugin.getWorkspace());
		result.setHelper(javaProjectsStateHelper);
		return result;
	}
	
	@Test public void testGetContainerHandle_01() {
		String uri1ContainerHandle = projectsState.getContainerHandle(uri1);
		String uri2ContainerHandle = projectsState.getContainerHandle(uri2);
		String uri3ContainerHandle = projectsState.getContainerHandle(uri3);
		assertEquals(srcRoot.getHandleIdentifier(), uri1ContainerHandle);
		assertEquals(srcRoot.getHandleIdentifier(), uri2ContainerHandle);
		assertNull(uri3ContainerHandle);
	}
	
	@Test public void testGetContainerHandle_02() throws CoreException {
		IResourcesSetupUtil.removeNature(project1, XtextProjectHelper.NATURE_ID);
		IResourcesSetupUtil.removeNature(project2, XtextProjectHelper.NATURE_ID);
		String uri1ContainerHandle = projectsState.getContainerHandle(uri1);
		String uri2ContainerHandle = projectsState.getContainerHandle(uri2);
		String uri3ContainerHandle = projectsState.getContainerHandle(uri3);
		assertEquals(srcRoot.getHandleIdentifier(), uri1ContainerHandle);
		assertEquals(srcRoot.getHandleIdentifier(), uri2ContainerHandle);
		assertNull(uri3ContainerHandle);
	}
	
	@Test public void testGetContainedURIs() {
		Collection<URI> containedURIs = projectsState.getContainedURIs(srcRoot.getHandleIdentifier());
		assertEquals(2, containedURIs.size());
		assertTrue(containedURIs.contains(uri1));
		assertTrue(containedURIs.contains(uri2));
	}
	
}
