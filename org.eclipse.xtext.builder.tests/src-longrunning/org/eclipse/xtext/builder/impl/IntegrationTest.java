/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import static org.eclipse.xtext.builder.impl.BuilderUtil.*;
import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.*;
import static org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.*;

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
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
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
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.ProjectDescriptor;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.TextFile;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Sebastian Zarnekow
 */
public class IntegrationTest extends AbstractBuilderTest {

	private IJavaProject foo_project;
	private IJavaProject bar_project;
	private IFile foo_file;
	private IFile bar_file;
	private final String PATH_FOO = "src/foo" + F_EXT;
	private final String PATH_BAR = "src/bar" + F_EXT;

	@Test public void testValidSimpleModel() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/foo" + F_EXT, "object Foo");
		descriptor.createProject();
		IFile file = descriptor.getFile(0);

		assertEquals(0, countMarkers(file));
	}

	private IJavaProject createJavaProject(String name) throws CoreException {
		return createJavaProject(name, false);
	}

	private IJavaProject createJavaProject(String name, boolean withBarFile) throws CoreException {
		ProjectDescriptor descriptor = new ProjectDescriptor(name)
				.withTextFile(PATH_FOO, "object Foo");
		if (withBarFile) {
			descriptor = descriptor.withTextFile(PATH_BAR, "object Bar references Foo");
		}
		return descriptor.createProject();
	}

	@Test public void testSimpleModelWithSyntaxError() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("foo" + F_EXT, "objekt Foo");
		descriptor.createProject();
		
		IFile file = descriptor.getFile(0);
		assertEquals(1, countMarkers(file));
	}

	@Test public void testTwoFilesInSameProject() throws Exception {
		IJavaProject javaProject = createJavaProject("foo", true);
		IFile file1 = javaProject.getProject().getFile(PATH_FOO);
		IFile file2 = javaProject.getProject().getFile(PATH_BAR);

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
		IJavaProject javaProject = createJavaProject("foo", true);
		IFile file1 = javaProject.getProject().getFile(PATH_FOO);
		IFile file2 = javaProject.getProject().getFile(PATH_BAR);

		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
		IFile file3 = createFile("foo/src/foo2"+F_EXT, "object Foo");
		waitForBuild();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 1, countMarkers(file2));
		assertEquals(printMarkers(file3), 0, countMarkers(file1));
		assertEquals(3, countResourcesInIndex());
		file3.delete(true, null);
		waitForBuild();
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
		assertEquals(2, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectRemoveNature() throws Exception {
		IJavaProject project = createJavaProject("foo", true);

		assertEquals(2, countResourcesInIndex());
		removeNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		waitForBuild();
		assertEquals(0, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectRemoveNatureNotSrc() throws Exception {
		IJavaProject project = new ProjectDescriptor("foo")
			.withTextFile("foo" + F_EXT, "object Foo")
			.withTextFile("bar" + F_EXT, "object Bar references Foo")
			.createProject();

		assertEquals(2, countResourcesInIndex());
		removeNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		waitForBuild();
		assertEquals(0, countResourcesInIndex());
	}

	@Test public void testTwoFilesInSameProjectNotInSrc() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("foo" + F_EXT, "object Foo")
				.withTextFile("bar" + F_EXT, "object Bar references Foo");
		descriptor.createProject();
		IFile file1 = descriptor.getFile(0);
		IFile file2 = descriptor.getFile(1);
		
		assertTrue(indexContainsElement(file1.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 0, countMarkers(file2));
	}

	@Test public void testTwoFilesInSameProjectReferencedFileNotInSrc() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("foo" + F_EXT, "object Foo")
				.withTextFile("bar" + F_EXT, "object Bar references Foo");
		descriptor.createProject();
		IFile file1 = descriptor.getFile(0);
		IFile file2 = descriptor.getFile(1);

		assertTrue(indexContainsElement(file1.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(file2.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());
		assertEquals(printMarkers(file1), 0, countMarkers(file1));
		assertEquals(printMarkers(file2), 1, countMarkers(file2));
	}

	@Test public void testTwoFilesInSameProjectReferingFileNotInSrc() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/foo" + F_EXT, "object Foo")
				.withTextFile("bar" + F_EXT, "object Bar references Foo");
		descriptor.createProject();
		IFile file1 = descriptor.getFile(0);
		IFile file2 = descriptor.getFile(1);

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
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/foo" + F_EXT, "object Foo")
				.withTextFile("src/bar" + F_EXT, "object Bar references Fuu");
		descriptor.createProject();
		IFile file = descriptor.getFile(0);

		assertEquals(1, countMarkers(file));
	}

	@Test public void testTwoFilesInTwoReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
	}

	@Test public void testTwoFilesInTwoReferencedProjectsRemoveNature() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeNature(foo_project.getProject(), XtextProjectHelper.NATURE_ID);
		waitForBuild();
		assertEquals(1, countMarkers(bar_file));
	}

	@Test public void testTwoFilesInTwoReferencedProjectsAddNature() throws Exception {
		ProjectDescriptor foo_descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/Foo" + F_EXT, "object Foo")
				.withoutXtextNature();
		foo_project = foo_descriptor.createProject();
		foo_file = foo_descriptor.getFile(0);
		ProjectDescriptor bar_descriptor = new ProjectDescriptor("bar")
				.withTextFile("src/Bar" + F_EXT, "object Bar references Foo");
		bar_project = bar_descriptor.createProject();
		bar_file = bar_descriptor.getFile(0);

		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 1, countMarkers(bar_file));
		addProjectReference(bar_project, foo_project);
		waitForBuild();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 1, countMarkers(bar_file));
		addNature(foo_project.getProject(), XtextProjectHelper.NATURE_ID);
		waitForBuild();
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
	}

	protected void createTwoFilesInTwoReferencedProjects() throws Exception {
		ProjectDescriptor foo_descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/foo" + F_EXT, "object Foo");
		foo_project = foo_descriptor.createProject();
		foo_file = foo_descriptor.getFile(0);
		ProjectDescriptor bar_descriptor = new ProjectDescriptor("bar")
				.withTextFile("src/bar" + F_EXT, "object Bar references Foo");
		bar_project = bar_descriptor.createProject();
		bar_file = bar_descriptor.getFile(0);

		addProjectReference(bar_project, foo_project);
		waitForBuild();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testTwoFilesInTwoInversedReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeProjectReference(bar_project, foo_project);
		waitForBuild();
		addProjectReference(foo_project, bar_project);
		waitForBuild();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));
	}

	@Test public void testTwoFilesInTwoNonReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		removeProjectReference(bar_project, foo_project);

		waitForBuild();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));
	}

	@Test public void testChangeReferenceOfProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();

		removeProjectReference(bar_project, foo_project);
		waitForBuild();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(1, countMarkers(bar_file));

		addProjectReference(bar_project, foo_project);
		waitForBuild();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testOpenAndCloseReferencedProjects() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		// close project
		foo_project.getProject().close(monitor());
		waitForBuild();
		assertEquals(1, countMarkers(bar_file));
		foo_project.getProject().open(monitor());
		waitForBuild();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
	}

	@Test public void testChangeReferencedFile() throws Exception {
		createTwoFilesInTwoReferencedProjects();

		// change referenced file
		foo_file.setContents(new StringInputStream("object Baz "), true, true, monitor());
		waitForBuild();
		assertEquals(1, countMarkers(bar_file));

		//change back to valid state
		foo_file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		waitForBuild();
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testDeleteReferencedFile() throws Exception {
		createTwoFilesInTwoReferencedProjects();

		// delete referenced file
		foo_file.delete(true, monitor());
		waitForBuild();
		assertEquals(1, countMarkers(bar_file));

		// create new
		foo_file = createFile("foo/src/foo" + F_EXT, "object Foo");
		waitForBuild();
		assertEquals(0, countMarkers(foo_file));
		assertEquals(0, countMarkers(bar_file));
	}

	@Test public void testUpdateOfReferencedFile() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("foo" + F_EXT, "object Foo")
				.withTextFile("boo" + F_EXT, "object Bar references Foo");
		descriptor.createProject();
		IFile file = descriptor.getFile(0);
		IFile fileB = descriptor.getFile(1);

		assertTrue(indexContainsElement(file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(fileB.getFullPath().toString(), "Bar"));
		assertEquals(2, countResourcesInIndex());

		getBuilderState().addListener(this);
		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		waitForBuild();
		assertEquals(1, getEvents().get(0).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);
		assertEquals(1, getIncomingReferences(URI.createPlatformResourceURI("foo/src/Foo" + F_EXT, true)).size());

		file.setContents(new StringInputStream("object Fop"), true, true, monitor());
		waitForBuild();
		assertEquals(2, getEvents().get(1).getDeltas().size());
		assertNumberOfMarkers(fileB, 1);
		assertEquals(0, getIncomingReferences(URI.createPlatformResourceURI("foo/src/Foo" + F_EXT, true)).size());

		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		waitForBuild();
		assertEquals(2, getEvents().get(2).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);

		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		waitForBuild();
		assertEquals(1, getEvents().get(3).getDeltas().size());
		assertNumberOfMarkers(fileB, 0);
	}

	@Test public void testDeleteFile() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/Foo" + F_EXT, "object Foo");
		descriptor.createProject();
		IFile file = descriptor.getFile(0);

		assertTrue(indexContainsElement(file.getFullPath().toString(), "Foo"));
		assertEquals(1, countResourcesInIndex());

		getBuilderState().addListener(this);
		file.delete(true, monitor());
		waitForBuild();
		assertEquals(1, getEvents().get(0).getDeltas().size());
		assertNull(getEvents().get(0).getDeltas().get(0).getNew());
		assertEquals(0, countResourcesInIndex());
	}

	@Test public void testCleanBuild() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/Foo" + F_EXT, "object Foo");
		IJavaProject project = descriptor.createProject();
		IFile file = descriptor.getFile(0);

		assertTrue(indexContainsElement(file.getFullPath().toString(), "Foo"));
		assertEquals(1, countResourcesInIndex());

		getBuilderState().addListener(this);
		project.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		waitForBuild();
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
		waitForBuild();
		assertTrue(indexContainsElement(foo_file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(bar_file.getFullPath().toString(), "Bar"));
		foo_project.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		assertFalse(indexContainsElement(foo_file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(bar_file.getFullPath().toString(), "Bar"));
		waitForBuild();
		assertTrue(indexContainsElement(foo_file.getFullPath().toString(), "Foo"));
		assertTrue(indexContainsElement(bar_file.getFullPath().toString(), "Bar"));
	}

	@Test public void testCleanRemovesMarkers() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/Bar" + F_EXT, "object Bar references Foo");
		IJavaProject project = descriptor.createProject();
		IFile file = descriptor.getFile(0);

		assertEquals(printMarkers(file), 1, countMarkers(file));
		project.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		assertEquals(printMarkers(file), 0, countMarkers(file));
		waitForBuild();
		assertEquals(printMarkers(file), 1, countMarkers(file));
	}

	@Test public void testFileInJar() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withInitializer((p) -> {
					IFile file = p.getProject().getFile("foo.jar");
					try {
						file.create(jarInputStream(new TextFile("Bar" + F_EXT, "object Foo")), true, monitor());
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				});
		IJavaProject project = descriptor.createProject();
		IFile file = project.getProject().getFile("foo.jar");

		assertEquals(0, countResourcesInIndex());
		addJarToClasspath(project, file);
		waitForBuild();
		assertEquals(1, countResourcesInIndex());
	}

	@Test public void testTwoJars() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withInitializer((p) -> {
					IFile file = p.getProject().getFile("foo.jar");
					IFile file2 = p.getProject().getFile("bar.jar");
					try {
						file.create(jarInputStream(new TextFile("Foo" + F_EXT, "object Foo")), true, monitor());
						file2.create(jarInputStream(new TextFile("Foo" + F_EXT, "object Foo"), new TextFile("Bar" + F_EXT, "object Bar references Foo")), true, monitor());
						addJarToClasspath(p, file);
						addJarToClasspath(p, file2);
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				});
		descriptor.createProject();
		
		assertEquals(3, countResourcesInIndex());
	}

	@Test public void testReexportedSource() throws Exception {
		ProjectDescriptor foo_descriptor = new ProjectDescriptor("foo")
			.withInitializer((p) -> {
				IFile file = p.getProject().getFile("foo.jar");
				try {
					file.create(jarInputStream(new TextFile("Foo" + F_EXT, "object Foo")), true, monitor());
					IClasspathEntry newLibraryEntry = JavaCore.newLibraryEntry(file.getFullPath(), null, null, true);
					addToClasspath(p, newLibraryEntry);
				} catch (CoreException e) {
					throw new RuntimeException(e);
				}
			});
		final IJavaProject foo = foo_descriptor.createProject();

		ProjectDescriptor bar_descriptor = new ProjectDescriptor("bar")
			.withInitializer((p) -> {
				try {
					addToClasspath(p, JavaCore.newProjectEntry(foo.getPath(), true));
				} catch (CoreException e) {
					throw new RuntimeException(e);
				}
			});
		final IJavaProject bar = bar_descriptor.createProject();
		
		ProjectDescriptor baz_descriptor = new ProjectDescriptor("baz")
			.withTextFile("src/Baz" + F_EXT, "object Baz references Foo")
			.withInitializer((p) -> {
				try {
					addToClasspath(p, JavaCore.newProjectEntry(bar.getPath(), true));
				} catch (CoreException e) {
					throw new RuntimeException(e);
				}
			});
		bar_descriptor.createProject();
		IFile bazFile = baz_descriptor.getFile(0);

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
		ProjectDescriptor bar_descriptor = new ProjectDescriptor("bar")
				.withInitializer((p) -> {
					IFile file = p.getProject().getFile("foo.jar");
					try {
						file.create(jarInputStream(new TextFile("Foo" + F_EXT, "object Foo")), true, monitor());
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				});
		final IJavaProject someProject = bar_descriptor.createProject();
		final IFile file = someProject.getProject().getFile("foo.jar");

		ProjectDescriptor foo_descriptor = new ProjectDescriptor("foo")
				.withInitializer((p) -> {
					try {
						assertEquals(0, countResourcesInIndex());
						addJarToClasspath(p, file);
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				});
		foo_descriptor.createProject();

		assertEquals(1, countResourcesInIndex());

		getBuilderState().addListener(this);
		fullBuild();
		assertEquals(1, countResourcesInIndex());
		//		System.out.println(print(getEvents().get(0).getDeltas()));
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
		addJarToClasspath(project, file);
		waitForBuild();
		//		JavaCore.addElementChangedListener(new IElementChangedListener() {
		//			
		//			public void elementChanged(ElementChangedEvent event) {
		//				System.out.println(event);
		//			}
		//		});
		someProject.delete(true, monitor());
	}

	@Test public void testIgnoreFilesInOutputFolder() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/Foo" + F_EXT, "object Foo")
				.withTextFile("Bar" + F_EXT, "object Bar references Foo");
		IJavaProject javaProject = descriptor.createProject();

		IProject project = javaProject.getProject();
		IResource resourceFromBin = project.findMember(new Path("/bin/foo" + F_EXT));
		assertNotNull(resourceFromBin);
		assertTrue(resourceFromBin instanceof IStorage);
		assertTrue(resourceFromBin.exists());
		IResourceUIServiceProvider serviceProvider = getInstance(IResourceUIServiceProvider.class);
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
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/Foo" + F_EXT, "objekt Foo")
				.withInitializer((p) -> {
					try {
						p.getProject().getFolder("src").setDerived(true);
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				});
		IJavaProject javaProject = descriptor.createProject();
		IFile file = javaProject.getProject().getFile("src/Foo" + F_EXT);

		assertEquals(1, countMarkers(file));
		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		assertTrue(file.isDerived());
		waitForBuild();
		assertEquals(0, countMarkers(file));
	}

	@SuppressWarnings("deprecation")
	@Test public void testModelWithSyntaxErrorInDerivedFolder() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withInitializer((p) -> {
					try {
						IFolder folder = p.getProject().getFolder("non-src");
						folder.create(true, true, monitor());
						folder.setDerived(true);
						p.getProject().getFolder("src").setDerived(true);
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				})
				.withTextFile("non-src/Foo" + F_EXT, "objekt Foo", true);
		descriptor.createProject();
		IFile file = descriptor.getFile(0);

		assertEquals(1, countMarkers(file));
		file.setContents(new StringInputStream("object Foo"), true, true, monitor());
		assertTrue(file.isDerived());
		waitForBuild();
		assertEquals(0, countMarkers(file));
	}

	@Test public void testOpenAndCloseReferencedProjectsTogether_01() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		waitForBuild();
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
		waitForBuild();
		new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
					InterruptedException {
				foo_project.getProject().open(monitor);
				bar_project.getProject().open(monitor);
			}
		}.run(monitor());
		waitForBuild();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
	}

	@Test public void testOpenAndCloseReferencedProjectsTogether_02() throws Exception {
		createTwoFilesInTwoReferencedProjects();
		waitForBuild();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
		foo_project.getProject().close(monitor());
		bar_project.getProject().close(monitor());
		waitForBuild();
		foo_project.getProject().open(monitor());
		bar_project.getProject().open(monitor());
		waitForBuild();
		assertEquals(printMarkers(bar_file), 0, countMarkers(bar_file));
		assertEquals(printMarkers(foo_file), 0, countMarkers(foo_file));
	}

	@Test public void testBug342875() throws Exception {
		ProjectDescriptor descriptor = new ProjectDescriptor("foo")
				.withTextFile("src/Foo" + F_EXT, "objekt Foo");
		descriptor.createProject();
		IFile file = descriptor.getFile(0);

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
	
	protected void waitForBuild() {
		reallyWaitForAutoBuild();
		try {
			ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new NullProgressMonitor());
		} catch (CoreException e) {
			throw new OperationCanceledException(e.getMessage());
		}
		
	}
}
