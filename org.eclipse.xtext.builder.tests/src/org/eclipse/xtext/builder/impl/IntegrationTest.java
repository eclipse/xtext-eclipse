/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import static org.eclipse.xtext.builder.impl.BuilderUtil.*;
import static org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.*;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.builder.tests.builderTestLanguage.BuilderTestLanguagePackage;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.resource.IResourceUIServiceProvider;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.TextFile;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Sebastian Zarnekow
 */
public class IntegrationTest extends AbstractBuilderTest {

	private IJavaProject foo_project;
	private IJavaProject bar_project;
	private IFile foo_file;
	private IFile bar_file;
	
	@Inject
	private IResourceUIServiceProvider serviceProvider;

	@Test public void testValidSimpleModel() throws Exception {
		createJavaProjectWithRootSrc("foo");
		IFile file = createFile("foo/src/foo" + F_EXT, "object Foo ");
		build();
		assertEquals(0, countMarkers(file));
	}
	
	@After
	public void resetEvents() {
		getEvents().clear();
		getBuilderState().removeListener(this);
	}

	private IJavaProject createJavaProjectWithRootSrc(String string) throws CoreException {
		IJavaProject project = createJavaProject(string);
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		return project;
	}

	@Test public void testSimpleModelWithSyntaxError() throws Exception {
		createJavaProjectWithRootSrc("foo");
		IFile file = createFile("foo/src/foo" + F_EXT, "objekt Foo ");
		build();
		assertEquals(1, countMarkers(file));
	}

	@Test public void testTwoFilesInSameProject() throws Exception {
		createJavaProjectWithRootSrc("foo");
		IFile file1 = createFile("foo/src/foo" + F_EXT, "object Foo ");
		IFile file2 = createFile("foo/src/bar" + F_EXT, "object Bar references Foo");
		build();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
		assertTrue(indexContainsElement(file1.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());
	}

	@Test
	@Ignore("TODO (since 2.0) the expectation would be markers on the declarations, rather than on the reference. " +
			"FIXME as soon as we have duplicate name validation on containers.")
	public void testTwoFilesInSameProjectCopyAndDelete() throws Exception {
		createJavaProjectWithRootSrc("foo");
		IFile file1 = createFile("foo/src/foo"+F_EXT, "object Foo ");
		IFile file2 = createFile("foo/src/bar"+F_EXT, "object Bar references Foo");
		build();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
		IFile file3 = createFile("foo/src/foo2"+F_EXT, "object Foo ");
		build();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 1, countMarkers(file2));
		assertEquals(printMarkers(file3), 0, countMarkers(file1));
		assertEquals(3, countResourcesInIndex());
		file3.delete(true, null);
		build();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
		assertEquals(2, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectRemoveNature() throws Exception {
		IJavaProject project = createJavaProjectWithRootSrc("foo");
		createFile("foo/src/foo" + F_EXT, "object Foo ");
		createFile("foo/src/bar" + F_EXT, "object Bar references Foo");
		build();
		assertEquals(2, countResourcesInIndex());
		removeNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		build();
		assertEquals(0, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectRemoveNatureNotSrc() throws Exception {
		IJavaProject project = createJavaProjectWithRootSrc("foo");
		createFile("foo/foo" + F_EXT, "object Foo ");
		createFile("foo/bar" + F_EXT, "object Bar references Foo");
		build();
		assertEquals(2, countResourcesInIndex());
		removeNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		build();
		assertEquals(0, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectNotInSrc() throws Exception {
		createJavaProjectWithRootSrc("foo");
		IFile file1 = createFile("foo/foo" + F_EXT, "object Foo ");
		IFile file2 = createFile("foo/bar" + F_EXT, "object Bar references Foo");
		build();
		assertTrue(indexContainsElement(file1.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
	}

	@Test public void testTwoFilesInSameProjectReferencedFileNotInSrc() throws Exception {
		createJavaProjectWithRootSrc("foo");
		IFile file1 = createFile("foo/foo" + F_EXT, "object Foo ");
		IFile file2 = createFile("foo/src/bar" + F_EXT, "object Bar references Foo");
		build();
		assertTrue(indexContainsElement(file1.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 1, countMarkers(file2));
	}

	@Test public void testTwoFilesInSameProjectReferingFileNotInSrc() throws Exception {
		createJavaProjectWithRootSrc("foo");
		IFile file1 = createFile("foo/src/foo" + F_EXT, "object Foo ");
		IFile file2 = createFile("foo/bar" + F_EXT, "object Bar references Foo");
		build();
		assertTrue(indexContainsElement(file1.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
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
		createJavaProjectWithRootSrc("foo");
		createFile("foo/src/foo" + F_EXT, "object Foo ");
		IFile file = createFile("foo/src/bar" + F_EXT, "object Bar references Fuu");
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

	// TODO fix https://github.com/eclipse/xtext-eclipse/issues/400
	@Ignore("TODO fix https://github.com/eclipse/xtext-eclipse/issues/400")
	@Test public void testTwoFilesInTwoReferencedProjectsAddNature() throws Exception {
		foo_project = createJavaProjectWithRootSrc("foo");
		removeNature(foo_project.getProject(), XtextProjectHelper.NATURE_ID);
		bar_project = createJavaProjectWithRootSrc("bar");
		foo_file = createFile("foo/src/foo" + F_EXT, "object Foo ");
		bar_file = createFile("bar/src/bar" + F_EXT, "object Bar references Foo");
		build();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 1, countMarkers(bar_file));
		addProjectReference(bar_project, foo_project);
		build();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 1, countMarkers(bar_file));
		addNature(foo_project.getProject(), XtextProjectHelper.NATURE_ID);
		build();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
	}

	protected void createTwoFilesInTwoReferencedProjects() throws Exception {
		foo_project = createJavaProjectWithRootSrc("foo");
		bar_project = createJavaProjectWithRootSrc("bar");
		foo_file = createFile("foo/src/foo" + F_EXT, "object Foo ");
		bar_file = createFile("bar/src/bar" + F_EXT, "object Bar references Foo");
		build();
		addProjectReference(bar_project, foo_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testTwoFilesInTwoInversedReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeProjectReference(bar_project, foo_project);
		build();
		addProjectReference(foo_project, bar_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));
	}

	@Test public void testTwoFilesInTwoNonReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeProjectReference(bar_project, foo_project);

		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));
	}

	@Test public void testChangeReferenceOfProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();

		removeProjectReference(bar_project, foo_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));

		addProjectReference(bar_project, foo_project);
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testOpenAndCloseReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		foo_project.getProject().close(monitor());
		build();
		assertEquals(1, countMarkers(bar_file));
		foo_project.getProject().open(monitor());
		build();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
	}

	// TODO fix https://github.com/eclipse/xtext-eclipse/issues/400
	@Ignore("TODO fix https://github.com/eclipse/xtext-eclipse/issues/400")
	@Test public void testChangeReferencedFile() throws Exception {
		createTwoFilesInTwoReferencedProjects();

		// change referenced file
		foo_file.setContents(new StringInputStream("object Baz "), true, true, monitor());
		build();
		assertEquals(1, countMarkers(bar_file));

		//change back to valid state
		foo_file.setContents(new StringInputStream("object Foo "), true, true, monitor());
		build();
		assertEquals(0, countMarkers(bar_file));
	}

	// TODO fix https://github.com/eclipse/xtext-eclipse/issues/400
	@Ignore("TODO fix https://github.com/eclipse/xtext-eclipse/issues/400")
	@Test public void testDeleteReferencedFile() throws Exception {
		createTwoFilesInTwoReferencedProjects();

		// delete referenced file
		foo_file.delete(true, new NullProgressMonitor());
		build();
		assertEquals(1, countMarkers(bar_file));

		// create new
		foo_file = createFile("foo/src/foo" + F_EXT, "object Foo ");
		build();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testUpdateOfReferencedFile() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		IFile fileB = folder.getFile("Boo" + F_EXT);
		fileB.create(new StringInputStream("object Bar references Foo"), true, monitor());
		build();
		assertTrue(indexContainsElement(file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(fileB.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());

		getBuilderState().addListener(this);
		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		build();
		assertEquals(1, getEvents().get(0).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);
		assertEquals(1, getIncomingReferences(URI.createPlatformResourceURI("foo/src/Foo" + F_EXT, true)).size());

		file.setContents(new StringInputStream("object Fop"), true, true, monitor());
		build();
		assertEquals(2, getEvents().get(1).getDeltas().size());
		assertNumberOfMarkers(fileB, 1);
		assertEquals(0, getIncomingReferences(URI.createPlatformResourceURI("foo/src/Foo" + F_EXT, true)).size());

		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		build();
		assertEquals(2, getEvents().get(2).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);

		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		build();
		assertEquals(1, getEvents().get(3).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);
	}

	@Test public void testDeleteFile() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		assertTrue(indexContainsElement(file.getFullPath().toString(), "Foo"));
		assertEquals(1, countResourcesInIndex());

		getBuilderState().addListener(this);
		file.delete(true, monitor());
		build();
		assertEquals(1, getEvents().get(0).getDeltas().size());
		assertNull(getEvents().get(0).getDeltas().get(0).getNew());
		assertEquals(0, countResourcesInIndex());
	}

	@Test public void testCleanBuild() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		assertTrue(indexContainsElement(file.getFullPath().toString(), "Foo"));
		assertEquals(1, countResourcesInIndex());

		getBuilderState().addListener(this);
		project.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		build();
		// clean build should first remove the IResourceDescriptor and then add it again  
		assertEquals(2, getEvents().size());
		assertEquals(1, getEvents().get(0).getDeltas().size());
		assertNotNull(getEvents().get(0).getDeltas().get(0).getOld());
		assertNull(getEvents().get(0).getDeltas().get(0).getNew());
		assertEquals(1, getEvents().get(1).getDeltas().size());
		assertNull(getEvents().get(1).getDeltas().get(0).getOld());
		assertNotNull(getEvents().get(1).getDeltas().get(0).getNew());
	}

	@Test public void testCleanIsNotTransitive() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		build();
		assertTrue(indexContainsElement(foo_file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(bar_file.getFullPath().toString(), "Bar"));
		foo_project.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		assertFalse(indexContainsElement(foo_file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(bar_file.getFullPath().toString(), "Bar"));
		build();
		assertTrue(indexContainsElement(foo_file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(bar_file.getFullPath().toString(), "Bar"));
	}

	@Test public void testCleanRemovesMarkers() throws Exception {
		IJavaProject javaProject = createJavaProjectWithRootSrc("foo");
		IFile file = createFile("foo/src/bar" + F_EXT, "object Bar references Foo");
		build();
		assertEquals(printMarkers(file), 1, countMarkers(file));
		javaProject.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		assertEquals(printMarkers(file), 0, countMarkers(file));
		build();
		assertEquals(printMarkers(file), 1, countMarkers(file));
	}

	@Test public void testFileInJar() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFile file = project.getProject().getFile("foo.jar");
		file.create(jarInputStream(new TextFile("foo/Bar" + F_EXT, "object Foo")), true, monitor());
		assertEquals(0, countResourcesInIndex());
		addJarToClasspath(project, file);
		assertEquals(1, countResourcesInIndex());
	}

	@Test public void testTwoJars() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFile file = project.getProject().getFile("foo.jar");
		file.create(jarInputStream(new TextFile("foo/Bar" + F_EXT, "object Foo")), true, monitor());
		IFile file2 = project.getProject().getFile("bar.jar");
		file2.create(
				jarInputStream(new TextFile("foo/Bar" + F_EXT, "object Foo"), new TextFile("foo/Bar2" + F_EXT,
						"object Bar references Foo")), true, monitor());

		addJarToClasspath(project, file);
		addJarToClasspath(project, file2);
		assertEquals(3, countResourcesInIndex());
	}

	@Test public void testReexportedSource() throws Exception {
		IJavaProject foo = createJavaProject("foo");
		IJavaProject bar = createJavaProject("bar");
		IJavaProject baz = createJavaProject("baz");
		addNature(foo.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(bar.getProject(), XtextProjectHelper.NATURE_ID);
		addNature(baz.getProject(), XtextProjectHelper.NATURE_ID);
		IFile file = foo.getProject().getFile("foo.jar");
		file.create(jarInputStream(new TextFile("foo/Foo" + F_EXT, "object Foo")), true, monitor());
		IClasspathEntry newLibraryEntry = JavaCore.newLibraryEntry(file.getFullPath(), null, null, true);
		addToClasspath(foo, newLibraryEntry);
		addToClasspath(bar, JavaCore.newProjectEntry(foo.getPath(), true));
		addToClasspath(baz, JavaCore.newProjectEntry(bar.getPath(), false));
		IFile bazFile = createFile("baz/src/Baz" + F_EXT, "object Baz references Foo");
		build();
		assertEquals(0, countMarkers(bazFile));
		assertEquals(2, countResourcesInIndex());
		Iterator<IReferenceDescription> references = getContainedReferences(
				URI.createPlatformResourceURI(bazFile.getFullPath().toString(), true)).iterator();
		IReferenceDescription next = references.next();
		assertFalse(references.hasNext());
		assertEquals("platform:/resource/baz/src/Baz.buildertestlanguage#/", next.getSourceEObjectUri().toString());
		assertEquals("archive:platform:/resource/foo/foo.jar!/foo/Foo.buildertestlanguage#/", next
				.getTargetEObjectUri().toString());
		assertEquals(-1, next.getIndexInList());
		assertEquals(BuilderTestLanguagePackage.Literals.ELEMENT__REFERENCES, next.getEReference());
	}

	@Test public void testFullBuild() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IProject someProject = createProject("bar");
		IFile file = someProject.getFile("foo.jar");
		file.create(jarInputStream(new TextFile("foo/Bar" + F_EXT, "object Foo")), true, monitor());
		assertEquals(0, countResourcesInIndex());
		addJarToClasspath(project, file);
		assertEquals(1, countResourcesInIndex());
		getBuilderState().addListener(this);
		fullBuild();
		assertEquals(1, countResourcesInIndex());
		assertEquals(1, getEvents().size());
	}

	private int countMarkers(IFile file) throws CoreException {
		return file.findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE).length;
	}

	@Test public void testEvents() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IProject someProject = createProject("bar");
		IFile file = someProject.getFile("foo.jar");
		file.create(jarInputStream(new TextFile("foo/Bar" + F_EXT, "object Foo")), true, monitor());
		file.setLocalTimeStamp(100L);
		addJarToClasspath(project, file);
		someProject.delete(true, monitor());
		build();
		assertEmptyIndex();
	}

	@Test public void testIgnoreFilesInOutputFolder() throws Exception {
		IJavaProject javaProject = createJavaProjectWithRootSrc("foo");
		createFile("foo/src/foo" + F_EXT, "object Foo ");
		createFile("foo/bar" + F_EXT, "object Bar references Foo");
		build();
		IProject project = javaProject.getProject();
		IResource resourceFromBin = project.findMember(new Path("/bin/foo" + F_EXT));
		assertNotNull(resourceFromBin);
		assertTrue(resourceFromBin instanceof IStorage);
		assertTrue(resourceFromBin.exists());
		URI fakeBinURI = URI.createPlatformResourceURI("/" + project.getName() + "/bin/foo" + F_EXT, true);
		assertFalse(serviceProvider.canHandle(fakeBinURI, (IStorage) resourceFromBin));
		assertTrue(serviceProvider.canHandle(fakeBinURI));
		IResource resourceFromRoot = project.findMember(new Path("/bar" + F_EXT));
		assertNotNull(resourceFromRoot);
		assertTrue(resourceFromRoot instanceof IStorage);
		URI fromRootURI = URI.createPlatformResourceURI("/" + project.getName() + "/bar" + F_EXT, true);
		assertFalse(serviceProvider.canHandle(fromRootURI, (IStorage) resourceFromBin));
		assertTrue(serviceProvider.canHandle(fromRootURI));
	}

	@SuppressWarnings("deprecation")
	@Test public void testModelWithSyntaxErrorInDerivedSrcFolder() throws Exception {
		IJavaProject javaProject = createJavaProjectWithRootSrc("foo");
		IProject project = javaProject.getProject();
		IFolder sourceFolder = project.getFolder("src");
		sourceFolder.setDerived(true);
		IFile file = createFile("foo/src/foo" + F_EXT, "objekt Foo ");
		file.setDerived(true);
		build();
		assertEquals(1, countMarkers(file));
		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		assertTrue(file.isDerived());
		build();
		assertEquals(0, countMarkers(file));
	}

	@SuppressWarnings("deprecation")
	@Test public void testModelWithSyntaxErrorInDerivedFolder() throws Exception {
		IJavaProject javaProject = createJavaProjectWithRootSrc("foo");
		IProject project = javaProject.getProject();
		IFolder folder = project.getFolder("non-src");
		folder.create(true, true, monitor());
		folder.setDerived(true);
		IFile file = createFile("foo/non-src/foo" + F_EXT, "objekt Foo ");
		file.setDerived(true);
		build();
		assertEquals(1, countMarkers(file));
		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		assertTrue(file.isDerived());
		build();
		assertEquals(0, countMarkers(file));
	}

	@Test public void testOpenAndCloseReferencedProjectsTogether_01() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		build();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
					InterruptedException {
				foo_project.getProject().close(monitor);
				bar_project.getProject().close(monitor);
			}
		}.run(monitor());
		build();
		new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
					InterruptedException {
				foo_project.getProject().open(monitor);
				bar_project.getProject().open(monitor);
			}
		}.run(monitor());
		build();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
	}

	@Test public void testOpenAndCloseReferencedProjectsTogether_02() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		build();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		foo_project.getProject().close(monitor());
		bar_project.getProject().close(monitor());
		build();
		foo_project.getProject().open(monitor());
		bar_project.getProject().open(monitor());
		build();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
	}

	@Test public void testBug342875() throws Exception {

		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFile file = createFile("foo/src/foo" + F_EXT, "objekt Foo ");
		ResourceAttributes resourceAttributes = file.getResourceAttributes();
		resourceAttributes.setReadOnly(true);
		file.setResourceAttributes(resourceAttributes);
		try {
			build();
			assertTrue(file.isReadOnly());
			assertEquals(1, countMarkers(file));
		} finally {
			resourceAttributes.setReadOnly(false);
			file.setResourceAttributes(resourceAttributes);
		}
	}
	
}
