/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import static org.eclipse.xtext.builder.impl.BuilderUtil.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.builder.tests.builderTestLanguage.BuilderTestLanguagePackage;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.TextFile;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Sebastian Zarnekow
 */
public class SimpleProjectsIntegrationTest extends AbstractBuilderTest {

	private IProject foo_project;
	private IProject bar_project;
	private IFile foo_file;
	private IFile bar_file;

	@After
	public void resetEvents() {
		getEvents().clear();
		getBuilderState().removeListener(this);
	}
	
	@Test public void testValidSimpleModel() throws Exception {
		createSimpleProjectWithXtextNature("foo");
		IFile file = createFile("foo/foo"+F_EXT, "object Foo ");
		build();
		assertEquals(0, countMarkers(file));
	}

	private IProject createSimpleProjectWithXtextNature(String projectName) throws CoreException {
		IProject project = createProject(projectName);
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		return project;
	}

	@Test public void testSimpleModelWithSyntaxError() throws Exception {
		createSimpleProjectWithXtextNature("foo");
		IFile file = createFile("foo/foo"+F_EXT, "objekt Foo ");
		build();
		assertEquals(1, countMarkers(file));
	}

	@Test public void testTwoFilesInSameProject() throws Exception {
		createSimpleProjectWithXtextNature("foo");
		IFile file1 = createFile("foo/foo"+F_EXT, "object Foo ");
		IFile file2 = createFile("foo/bar"+F_EXT, "object Bar references Foo");
		build();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
		assertTrue(indexContainsElement(file1.getFullPath().toString(),"Foo"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(),"Bar"));
		assertEquals(2, countResourcesInIndex());
	}
	
	@Test public void testTwoFilesInSameProjectRemoveNature() throws Exception {
		IProject project = createSimpleProjectWithXtextNature("foo");
		createFile("foo/foo"+F_EXT, "object Foo ");
		createFile("foo/bar"+F_EXT, "object Bar references Foo");
		build();
		assertEquals(2, countResourcesInIndex());
		removeNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		build();
		assertEquals(0, countResourcesInIndex());
	}
	
	protected String printMarkers(IFile file) throws CoreException {
		return printMarkers(file.findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE));
	}
	
	protected String printMarkers(IMarker[] findMarkers) throws CoreException {
		StringBuffer buff = new StringBuffer();
		for (IMarker iMarker : findMarkers) {
			buff.append("\n");
			buff.append(iMarker.getAttribute(IMarker.MESSAGE));
		}
		return buff.toString();
	}

	@Test public void testTwoFilesInSameProjectWithLinkingError() throws Exception {
		createSimpleProjectWithXtextNature("foo");
		createFile("foo/foo"+F_EXT, "object Foo ");
		IFile file = createFile("foo/bar"+F_EXT, "object Bar references Fuu");
		build();
		assertEquals(1, countMarkers(file));
	}
	
	@Test public void testTwoFilesInTwoReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
	}
	
	@Test public void testTwoFilesInTwoReferencedProjectsRemoveNature() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeNature(foo_project.getProject(), XtextProjectHelper.NATURE_ID);
		build();
		assertEquals(1, countMarkers(bar_file));
	}
	
	@Test public void testTwoFilesInTwoReferencedProjectsAddNature() throws Exception {
		foo_project = createSimpleProjectWithXtextNature("foo");
		removeNature(foo_project.getProject(), XtextProjectHelper.NATURE_ID);
		bar_project = createSimpleProjectWithXtextNature("bar");
		foo_file = createFile("foo/foo"+F_EXT, "object Foo ");
		bar_file = createFile("bar/bar"+F_EXT, "object Bar references Foo");
		build();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 1, countMarkers(bar_file));
		setReference(bar_project, foo_project);
		build();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 1, countMarkers(bar_file));
		addNature(foo_project.getProject(), XtextProjectHelper.NATURE_ID);
		build();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
	}
	
	protected void createTwoFilesInTwoReferencedProjects() throws Exception {
		foo_project = createSimpleProjectWithXtextNature("foo");
		bar_project = createSimpleProjectWithXtextNature("bar");
		foo_file = createFile("foo/foo"+F_EXT, "object Foo ");
		bar_file = createFile("bar/bar"+F_EXT, "object Bar references Foo");
		build();
		setReference(bar_project, foo_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testTwoFilesInTwoInversedReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeReference(bar_project, foo_project);
		build();
		setReference(foo_project, bar_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));
	}

	@Test public void testTwoFilesInTwoNonReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeReference(bar_project, foo_project);

		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));
	}

	@Test public void testChangeReferenceOfProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		
		removeReference(bar_project, foo_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));
		
		setReference(bar_project, foo_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testOpenAndCloseReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		// close project
		foo_project.getProject().close(monitor());
		build();
		assertEquals(1, countMarkers(bar_file));
		foo_project.getProject().open(monitor());
		build();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
	}

	@Test public void testChangeReferencedFile() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		
		// change referenced file
		foo_file.setContents(new StringInputStream("object Baz "),true,true,monitor());
		build();
		assertEquals(1, countMarkers(bar_file));
		
		//change back to valid state
		foo_file.setContents(new StringInputStream("object Foo "),true,true,monitor());
		build();
		assertEquals(0, countMarkers(bar_file));
	}
	
	@Test public void testDeleteReferencedFile() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		
		// delete referenced file
		foo_file.delete(true, new NullProgressMonitor());
		build();
		assertEquals(1, countMarkers(bar_file));
		
		// create new
		foo_file = createFile("foo/foo"+F_EXT, "object Foo ");
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}
	
	@Test public void testUpdateOfReferencedFile() throws Exception {
		IProject project = createProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = createSubFolder(project, "subFolder");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		IFile fileB = folder.getFile("Boo" + F_EXT);
		fileB.create(new StringInputStream("object Bar references Foo"), true, monitor());
		build();
		assertTrue(indexContainsElement(file.getFullPath().toString(),"Foo"));
		assertTrue(indexContainsElement(fileB.getFullPath().toString(),"Bar"));
		assertEquals(2, countResourcesInIndex());
		
		getBuilderState().addListener(this);
		file.setContents(new StringInputStream("object Foo"), true,true, monitor());
		build();
		assertEquals(1,getEvents().get(0).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);
		assertEquals(1,getIncomingReferences(URI.createPlatformResourceURI("foo/subFolder/Foo"+F_EXT,true)).size());
		
		file.setContents(new StringInputStream("object Fop"), true,true, monitor());
		build();
		assertEquals(2,getEvents().get(1).getDeltas().size());
		assertNumberOfMarkers(fileB, 1);
		assertEquals(0,getIncomingReferences(URI.createPlatformResourceURI("foo/subFolder/Foo"+F_EXT,true)).size());
		
		file.setContents(new StringInputStream("object Foo"), true,true, monitor());
		build();
		assertEquals(2,getEvents().get(2).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);
		
		file.setContents(new StringInputStream("object Foo"), true,true, monitor());
		build();
		assertEquals(1,getEvents().get(3).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);
	}
	
	@Test public void testDeleteFile() throws Exception {
		IProject project = createProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = createSubFolder(project, "subFolder");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		assertTrue(indexContainsElement(file.getFullPath().toString(),"Foo"));
		assertEquals(1, countResourcesInIndex());
		
		getBuilderState().addListener(this);
		file.delete(true, monitor());
		build();
		assertEquals(1,getEvents().get(0).getDeltas().size());
		assertNull(getEvents().get(0).getDeltas().get(0).getNew());
		assertEquals(0, countResourcesInIndex());
	}
	
	@Test public void testCleanBuild() throws Exception {
		IProject project = createProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = createSubFolder(project, "subFolder");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		assertTrue(indexContainsElement(file.getFullPath().toString(),"Foo"));
		assertEquals(1, countResourcesInIndex());
		
		getBuilderState().addListener(this);
		
		project.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		assertEquals(1, getEvents().size());
		build();
		// clean build should first remove the IResourceDescriptor and then add it again  
		assertEquals(2, getEvents().size());
		assertEquals(1, getEvents().get(0).getDeltas().size());
		assertNotNull(getEvents().get(0).getDeltas().get(0).getOld());
		assertNull(getEvents().get(0).getDeltas().get(0).getNew());
		assertEquals(1,getEvents().get(1).getDeltas().size());
		assertNull(getEvents().get(1).getDeltas().get(0).getOld());
		assertNotNull(getEvents().get(1).getDeltas().get(0).getNew());
	}
	
	@Test public void testProjectNameWithSpace() throws Exception {
		createSimpleProjectWithXtextNature("foo bar");
		IFile file = createFile("foo bar/foo"+F_EXT, "objekt Foo ");
		build();
		assertEquals(1, countMarkers(file));
	}
	
	@Test
	public void testReexportedSource() throws Exception {
		IJavaProject foo = createJavaProject("foo");
		IJavaProject bar = createJavaProject("bar");
		IJavaProject baz = createJavaProject("baz");
		addNature(foo.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(bar.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(baz.getProject(), XtextProjectHelper.NATURE_ID);
		IFile file = foo.getProject().getFile("foo.jar");
		file.create(JavaProjectSetupUtil.jarInputStream(new TextFile("foo/Foo"+F_EXT, "object Foo")), true, monitor());
		IClasspathEntry newLibraryEntry = JavaCore.newLibraryEntry(file.getFullPath(), null, null,true);
		addToClasspath(foo, newLibraryEntry);
		addToClasspath(bar, JavaCore.newProjectEntry(foo.getPath(), true));
		addToClasspath(baz, JavaCore.newProjectEntry(bar.getPath(), false));
		addSourceFolder(baz, "src");
		IFile bazFile = createFile("baz/src/Baz"+F_EXT, "object Baz references Foo");
		build();
		assertEquals(0,countMarkers(bazFile));
		assertEquals(2, countResourcesInIndex());
		Iterator<IReferenceDescription> references = getContainedReferences(URI.createPlatformResourceURI(bazFile.getFullPath().toString(),true)).iterator();
		IReferenceDescription next = references.next();
		assertFalse(references.hasNext());
		assertEquals("platform:/resource/baz/src/Baz.buildertestlanguage#/",next.getSourceEObjectUri().toString());
		assertEquals("archive:platform:/resource/foo/foo.jar!/foo/Foo.buildertestlanguage#/",next.getTargetEObjectUri().toString());
		assertEquals(-1,next.getIndexInList());
		assertEquals(BuilderTestLanguagePackage.Literals.ELEMENT__REFERENCES,next.getEReference());
	}
	
	// TODO fix https://github.com/eclipse/xtext-eclipse/issues/400
	@Ignore("TODO fix https://github.com/eclipse/xtext-eclipse/issues/400")
	@Test
	public void testNewlyAddedReexportedSource() throws Exception {
		IJavaProject foo = createJavaProject("foo");
		IJavaProject bar = createJavaProject("bar");
		IJavaProject baz = createJavaProject("baz");
		addNature(foo.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(bar.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(baz.getProject(), XtextProjectHelper.NATURE_ID);
		addToClasspath(bar, JavaCore.newProjectEntry(foo.getPath(), true));
		addToClasspath(baz, JavaCore.newProjectEntry(bar.getPath(), false));
		addSourceFolder(baz, "src");
		IFile bazFile = createFile("baz/src/Baz"+F_EXT, "object Baz references Foo");
		build();
		assertEquals(1,countMarkers(bazFile));
		IFile file = foo.getProject().getFile("foo.jar");
		file.create(JavaProjectSetupUtil.jarInputStream(new TextFile("foo/Foo"+F_EXT, "object Foo")), true, monitor());
		IClasspathEntry newLibraryEntry = JavaCore.newLibraryEntry(file.getFullPath(), null, null,true);
		addToClasspath(foo, newLibraryEntry);
		build();
		assertEquals(0,countMarkers(bazFile));
	}
	
	// TODO fix https://github.com/eclipse/xtext-eclipse/issues/400
	@Ignore("TODO fix https://github.com/eclipse/xtext-eclipse/issues/400")
	@Test
	public void testReexportedJarRemoved() throws Exception {
		IJavaProject foo = createJavaProject("foo");
		IJavaProject bar = createJavaProject("bar");
		IJavaProject baz = createJavaProject("baz");
		addNature(foo.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(bar.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(baz.getProject(), XtextProjectHelper.NATURE_ID);
		IFile file = foo.getProject().getFile("foo.jar");
		file.create(JavaProjectSetupUtil.jarInputStream(new TextFile("foo/Foo"+F_EXT, "object Foo")), true, monitor());
		IClasspathEntry newLibraryEntry = JavaCore.newLibraryEntry(file.getFullPath(), null, null,true);
		addToClasspath(foo, newLibraryEntry);
		addToClasspath(bar, JavaCore.newProjectEntry(foo.getPath(), true));
		addToClasspath(baz, JavaCore.newProjectEntry(bar.getPath(), false));
		addSourceFolder(baz, "src");
		IFile bazFile = createFile("baz/src/Baz"+F_EXT, "object Baz references Foo");
		build();
		assertEquals(0,countMarkers(bazFile));
		deleteClasspathEntry(foo, newLibraryEntry.getPath());
		build();
		assertEquals(1, countMarkers(bazFile));
		assertEquals(1, countResourcesInIndex());
		Iterator<IReferenceDescription> references = getContainedReferences(URI.createPlatformResourceURI(bazFile.getFullPath().toString(),true)).iterator();
		assertFalse(references.hasNext());
	}
	
	@Test
	public void testJarOnTwoProjectsRemovedFromOne() throws Exception {
		IJavaProject foo = createJavaProject("foo");
		IJavaProject bar = createJavaProject("bar");
		IJavaProject baz = createJavaProject("baz");
		addNature(foo.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(bar.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(baz.getProject(), XtextProjectHelper.NATURE_ID);
		IFile file = foo.getProject().getFile("foo.jar");
		file.create(JavaProjectSetupUtil.jarInputStream(new TextFile("foo/Foo"+F_EXT, "object Foo")), true, monitor());
		IClasspathEntry newLibraryEntry = JavaCore.newLibraryEntry(file.getFullPath(), null, null,true);
		addToClasspath(foo, newLibraryEntry);
		addToClasspath(bar, JavaCore.newProjectEntry(foo.getPath(), true));
		addToClasspath(bar, JavaCore.newLibraryEntry(file.getFullPath(), null, null,true));
		addToClasspath(baz, JavaCore.newProjectEntry(bar.getPath(), false));
		addSourceFolder(baz, "src");
		IFile bazFile = createFile("baz/src/Baz"+F_EXT, "object Baz references Foo");
		build();
		assertEquals(0,countMarkers(bazFile));
		assertEquals(2, countResourcesInIndex());
		deleteClasspathEntry(foo, newLibraryEntry.getPath());
		build();
		assertEquals(0,countMarkers(bazFile));
		assertEquals(2, countResourcesInIndex());
	}
	
	@Test
	public void testFullBuild() throws Exception {
		IProject project = createProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		createProject("bar");
		build();
		assertEquals(0, countResourcesInIndex());
		createFile("bar/bar"+F_EXT, "objekt Foo ");
		build();
		assertEquals(0, countResourcesInIndex());
		createFile("foo/bar"+F_EXT, "objekt Foo ");
		build();
		assertEquals(1, countResourcesInIndex());

		getBuilderState().addListener(this);
		fullBuild();
		assertEquals(1, countResourcesInIndex());
		assertEquals(1,getEvents().size());
	}

	private int countMarkers(IFile file) throws CoreException {
		return file.findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE).length;
	}
	
	@Test
	public void testEvents() throws Exception {
		IJavaProject xtextProject = createJavaProject("xtextProject");
		addNature(xtextProject.getProject(), XtextProjectHelper.NATURE_ID);
		IProject projectWithJarFile = createProject("projectWithJar");
		IFile jarFile = projectWithJarFile.getFile("jarFile.jar");
		jarFile.create(JavaProjectSetupUtil.jarInputStream(new TextFile("inJar/Bar"+F_EXT, "object InJar")), true, monitor());
		jarFile.setLocalTimeStamp(100L);
		addJarToClasspath(xtextProject, jarFile);
		projectWithJarFile.delete(true, monitor());
		build();
		assertEmptyIndex();
	}
	
}
