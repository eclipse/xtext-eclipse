/*******************************************************************************
 * Copyright (c) 2011, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.builder.DerivedResourceCleanerJob;
import org.eclipse.xtext.builder.DerivedResourceMarkers;
import org.eclipse.xtext.builder.nature.XtextNature;
import org.eclipse.xtext.builder.preferences.BuilderPreferenceAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.OutputConfigurationProvider;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.After;
import org.junit.Test;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class BuilderParticipantTest extends AbstractBuilderParticipantTest {

	@Inject
	private Provider<DerivedResourceCleanerJob> derivedResourceCleanerJobProvider;
	
	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=345545
	 */
	@Test
	public void deconfigureXtextNatureShouldDeleteMarkers() throws Exception {
		final IJavaProject project = createJavaProject("removeXtextNatureShouldDeleteMarkers");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("ob ject Foo"), true, monitor());
		build();
		IMarker[] markers = project.getProject()
				.findMarkers(MarkerTypes.ANY_VALIDATION, true, IResource.DEPTH_INFINITE);
		assertEquals(1, markers.length);
		assertEquals(MarkerTypes.FAST_VALIDATION, markers[0].getType());
		new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
					InterruptedException {
				XtextNature xtextNature = new XtextNature();
				xtextNature.setProject(project.getProject());
				xtextNature.deconfigure();
			}
		}.run(monitor());
		build();
		markers = project.getProject().findMarkers(MarkerTypes.ANY_VALIDATION, true, IResource.DEPTH_INFINITE);
		assertEquals(0, markers.length);
	}
	
	@Test
	public void testGenerateIntoProjectOutputDirectory() throws Exception {
		IJavaProject project = createJavaProject("testGenerateIntoProjectOutputDirectory");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		preferenceStoreAccess.getWritablePreferenceStore(project.getProject()).setValue(getDefaultOutputDirectoryKey(),
				"./");
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		IFile generatedFile = project.getProject().getFile("./Foo.txt");
		assertTrue(generatedFile.exists());
		preferenceStoreAccess.getWritablePreferenceStore(project.getProject()).setValue(getDefaultOutputDirectoryKey(),
				".");
		file = folder.getFile("Bar" + F_EXT);
		file.create(new StringInputStream("object Bar"), true, monitor());
		build();
		generatedFile = project.getProject().getFile("./Bar.txt");
		assertTrue(generatedFile.exists());
	}

	@Test
	public void testCharsetIsHonored() throws Exception {
		IJavaProject project = createJavaProject("testCharsetIsHonored");
		project.getProject().setDefaultCharset(getNonDefaultEncoding(), null);
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		IFile generatedFile = project.getProject().getFile("./src-gen/Foo.txt");
		assertEquals(getNonDefaultEncoding(), generatedFile.getCharset());
	}

	@Test
	public void testGenerateIntoDifferentOutputFolders() throws Exception {
		IJavaProject project = createJavaProject("testGenerateIntoDifferentOutputFolders");
		addSourceFolder(project, "other-src");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IPreferenceStore preferences = preferenceStoreAccess.getWritablePreferenceStore(project.getProject());
		preferences.setValue(getUseOutputPerSourceFolderKey(), "true");
		preferences.setValue(getOutputForSourceFolderKey("other-src"), "other-gen");

		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());

		folder = project.getProject().getFolder("other-src");
		file = folder.getFile("Bar" + F_EXT);
		file.create(new StringInputStream("object Bar"), true, monitor());

		build();
		IFile generatedFile = project.getProject().getFile("src-gen/Foo.txt");
		assertTrue(generatedFile.exists());
		generatedFile = project.getProject().getFile("other-gen/Bar.txt");
		assertTrue(generatedFile.exists());
	}

	@Test
	public void testCleanUpDerivedResources() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		IFile generatedFile = project.getProject().getFile("./src-gen/Foo.txt");
		assertTrue(generatedFile.exists());
		preferenceStoreAccess.getWritablePreferenceStore(project.getProject()).setValue(getDefaultOutputDirectoryKey(),
				"./src2-gen");

		DerivedResourceCleanerJob derivedResourceCleanerJob = derivedResourceCleanerJobProvider.get();
		derivedResourceCleanerJob.setUser(true);
		derivedResourceCleanerJob.initialize(project.getProject(), "src-gen");
		derivedResourceCleanerJob.schedule();
		derivedResourceCleanerJob.join();
		generatedFile = project.getProject().getFile("./src-gen/Foo.txt");
		assertFalse(generatedFile.exists());
		file.touch(monitor());
		build();
		generatedFile = project.getProject().getFile("./src2-gen/Foo.txt");
		assertTrue(generatedFile.exists());
	}

	@Test
	public void testDefaultConfiguration() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		IFile generatedFile = project.getProject().getFile("./src-gen/Foo.txt");
		assertTrue(generatedFile.exists());
		assertTrue(generatedFile.isDerived());
		assertTrue(generatedFile.findMarkers(DerivedResourceMarkers.MARKER_ID, false, IResource.DEPTH_ZERO).length == 1);
		assertEquals("object Foo", readFile(generatedFile).trim());
		file.setContents(new StringInputStream("object Bar"), true, true, monitor());
		build();
		assertFalse(generatedFile.exists());
		generatedFile = project.getProject().getFile("./src-gen/Bar.txt");
		assertTrue(generatedFile.exists());
		assertTrue(generatedFile.isDerived());
		assertTrue(generatedFile.findMarkers(DerivedResourceMarkers.MARKER_ID, false, IResource.DEPTH_ZERO).length == 1);
		assertEquals("object Bar", readFile(generatedFile).trim());
		file.delete(true, monitor());
		build();
		assertFalse(generatedFile.exists());
	}

	@Test
	public void testClean() throws Exception {
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		IFile generatedFile = project.getProject().getFile("./src-gen/Foo.txt");
		assertTrue(generatedFile.exists());
		assertTrue(generatedFile.isDerived());
		assertTrue(generatedFile.findMarkers(DerivedResourceMarkers.MARKER_ID, false, IResource.DEPTH_ZERO).length == 1);
		assertEquals("object Foo", readFile(generatedFile).trim());
		disableAutobuild(()->{
			cleanBuild();
			assertFalse(generatedFile.exists());	
		});
	}

	@Test
	public void testNoCleanUpNoDerived() throws Exception {
		OutputConfigurationProvider outputConfigurationProvider = new OutputConfigurationProvider() {
			@Override
			public Set<OutputConfiguration> getOutputConfigurations() {
				final Set<OutputConfiguration> result = super.getOutputConfigurations();
				OutputConfiguration configuration = result.iterator().next();
				configuration.setCanClearOutputDirectory(false);
				configuration.setCleanUpDerivedResources(false);
				configuration.setSetDerivedProperty(false);
				return result;
			}
		};
		BuilderPreferenceAccess.Initializer initializer = new BuilderPreferenceAccess.Initializer();
		initializer.setOutputConfigurationProvider(outputConfigurationProvider);
		initializer.initialize(preferenceStoreAccess);

		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		IFile generatedFileFoo = project.getProject().getFile("./src-gen/Foo.txt");
		assertTrue(generatedFileFoo.exists());
		assertFalse(generatedFileFoo.isDerived());
		assertTrue(generatedFileFoo.findMarkers(DerivedResourceMarkers.MARKER_ID, false, IResource.DEPTH_ZERO).length == 1);
		assertEquals("object Foo", readFile(generatedFileFoo).trim());
		file.setContents(new StringInputStream("object Bar"), true, true, monitor());
		build();
		assertTrue(generatedFileFoo.exists());
		IFile generatedFileBar = project.getProject().getFile("./src-gen/Bar.txt");
		assertTrue(generatedFileBar.exists());
		assertFalse(generatedFileBar.isDerived());
		assertTrue(generatedFileBar.findMarkers(DerivedResourceMarkers.MARKER_ID, false, IResource.DEPTH_ZERO).length == 1);
		assertEquals("object Bar", readFile(generatedFileBar).trim());
		file.delete(true, monitor());
		build();
		assertTrue(generatedFileBar.exists());
		disableAutobuild(()->{
			cleanBuild();
			assertTrue(generatedFileBar.exists());	
		});
	}

	@Test
	public void testDisabled() throws Exception {
		IJavaProject project = createJavaProject("foo");
		participant.getBuilderPreferenceAccess().setAutoBuildEnabled(project, false);
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		IFile generatedFile = project.getProject().getFile("./src-gen/Foo.txt");
		assertFalse(generatedFile.exists());
		participant.getBuilderPreferenceAccess().setAutoBuildEnabled(project, true);
		file.touch(monitor());
		build();
		assertTrue(generatedFile.exists());
	}

	@Test
	public void testNoOutputFolderCreation() throws Exception {
		OutputConfigurationProvider outputConfigurationProvider = new OutputConfigurationProvider() {
			@Override
			public Set<OutputConfiguration> getOutputConfigurations() {
				final Set<OutputConfiguration> result = super.getOutputConfigurations();
				OutputConfiguration configuration = result.iterator().next();
				configuration.setCreateOutputDirectory(false);
				return result;
			}
		};
		BuilderPreferenceAccess.Initializer initializer = new BuilderPreferenceAccess.Initializer();
		initializer.setOutputConfigurationProvider(outputConfigurationProvider);
		initializer.initialize(preferenceStoreAccess);

		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		build();
		final IFile generatedFile = project.getProject().getFile("./src-gen/Foo.txt");
		assertFalse(generatedFile.exists());
	}
	
	@After
	public void resetPrefs() {
		BuilderPreferenceAccess.Initializer initializer = new BuilderPreferenceAccess.Initializer();
		initializer.setOutputConfigurationProvider(new OutputConfigurationProvider());
		initializer.initialize(preferenceStoreAccess);
	}
}
