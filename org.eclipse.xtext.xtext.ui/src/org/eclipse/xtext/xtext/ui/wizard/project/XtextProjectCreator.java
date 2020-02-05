/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.wizard.project;

import static com.google.common.collect.Sets.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.util.IProjectFactoryContributor;
import org.eclipse.xtext.ui.util.JREContainerProvider;
import org.eclipse.xtext.ui.util.JavaProjectFactory;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;
import org.eclipse.xtext.util.JUnitVersion;
import org.eclipse.xtext.xtext.wizard.AbstractFile;
import org.eclipse.xtext.xtext.wizard.BinaryFile;
import org.eclipse.xtext.xtext.wizard.ParentProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.ProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.ProjectLayout;
import org.eclipse.xtext.xtext.wizard.SourceFolderDescriptor;
import org.eclipse.xtext.xtext.wizard.TestProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.TestedProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.TextFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class XtextProjectCreator extends WorkspaceModifyOperation implements IProjectCreator {
	private static final Logger LOG = Logger.getLogger(XtextProjectCreator.class);
	
	@Inject
	private Provider<PluginProjectFactory> pluginProjectProvider;
	@Inject
	private Provider<JavaProjectFactory> javaProjectProvider;
	@Inject
	private Provider<ProjectFactory> plainProjectProvider;

	private XtextProjectInfo projectInfo;
	Map<ProjectDescriptor, IProject> createdProjects = Maps.newHashMap();
	private IFile result;

	@Override
	protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		SubMonitor subMonitor = SubMonitor.convert(monitor, getCreateModelProjectMessage(), getMonitorTicks());
		for (ProjectDescriptor descriptor : projectInfo.getEnabledProjects()) {
			IProject project = createProject(descriptor, SubMonitor.convert(subMonitor, 1));
			createdProjects.put(descriptor, project);
		}

		IProject runtimeProject = createdProjects.get(projectInfo.getRuntimeProject());
		IFile dslGrammarFile = runtimeProject.getFile(getPath(projectInfo.getRuntimeProject().getGrammarFile()));
		BasicNewResourceWizard.selectAndReveal(dslGrammarFile, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		result = dslGrammarFile;
	}

	private IProject createProject(ProjectDescriptor descriptor, SubMonitor monitor) {
		if (isPluginProject(descriptor)) {
			return createPluginProject(descriptor, monitor);
		} else if (isFeatureProject(descriptor)) {
			return createFeatureProject(descriptor, monitor);
		} else if (isJavaProject(descriptor)) {
			return createJavaProject(descriptor, monitor);
		} else {
			return createPlainProject(descriptor, monitor);
		}
	}

	private IProject createPluginProject(ProjectDescriptor descriptor, SubMonitor monitor) {
		PluginProjectFactory factory = pluginProjectProvider.get();
		configureJavaProject(descriptor, factory);
		factory.addProjectNatures("org.eclipse.pde.PluginNature");
		factory.addBuilderIds("org.eclipse.pde.ManifestBuilder", "org.eclipse.pde.SchemaBuilder");
		factory.addDevelopmentTimeBundles(Lists.newArrayList(descriptor.getDevelopmentBundles()));
		factory.addImportedPackages(Lists.newArrayList(descriptor.getImportedPackages()));
		factory.addRequiredBundles(Lists.newArrayList(descriptor.getRequiredBundles()));
		factory.setBreeToUse(descriptor.getBree());
		return factory.createProject(monitor, null);
	}

	private IProject createJavaProject(ProjectDescriptor descriptor, SubMonitor monitor) {
		JavaProjectFactory factory = javaProjectProvider.get();
		configureJavaProject(descriptor, factory);
		return factory.createProject(monitor, null);
	}

	private void configureJavaProject(ProjectDescriptor descriptor, JavaProjectFactory factory) {
		configurePlainProject(descriptor, factory);
		factory.addProjectNatures(XtextProjectHelper.NATURE_ID);
		factory.addBuilderIds(XtextProjectHelper.BUILDER_ID);
		factory.addProjectNatures(JavaCore.NATURE_ID);
		factory.addBuilderIds(JavaCore.BUILDER_ID);
		for (SourceFolderDescriptor sourceFolder : descriptor.getSourceFolders()) {
			String output = sourceFolder.isTest() ? (needsM2eIntegration(descriptor) ? "target/test-classes" : "test-bin") : null;
			factory.addSourceFolder(sourceFolder.getPath(), output, sourceFolder.isTest());
		}
		factory.setJreContainerEntry(JREContainerProvider.getJREContainerEntry(descriptor.getBree()));
		if (needsM2eIntegration(descriptor)) {
			factory.setDefaultOutput("target/classes");
			if (!descriptor.isEclipsePluginProject()) {
				factory.addClasspathEntries(JavaCore.newContainerEntry(new Path("org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER")));
			}
		}
		if (needsBuildshipIntegration(descriptor) && !descriptor.isEclipsePluginProject()) {
			factory.addClasspathEntries(JavaCore.newContainerEntry(new Path("org.eclipse.buildship.core.gradleclasspathcontainer")));
		}
		if (requiresJUnitLibContainer(descriptor)) {
			JUnitVersion junitVersion = descriptor.getConfig().getJunitVersion();
			factory.addClasspathEntries(JavaCore.newContainerEntry(
					new Path("org.eclipse.jdt.junit.JUNIT_CONTAINER").append(Integer.toString(junitVersion.getVersion())),
					new IAccessRule[0], new IClasspathAttribute[] { JavaCore.newClasspathAttribute("test", "true") },
					false/*not exported*/));
		}
	}
	
	private boolean requiresJUnitLibContainer (ProjectDescriptor descriptor) {
		if (descriptor instanceof TestProjectDescriptor) {
			return true;
		} else if (descriptor instanceof TestedProjectDescriptor) {
			TestedProjectDescriptor tpd = (TestedProjectDescriptor) descriptor;
			if (tpd.getTestProject().isInlined()) {
				return true;
			}
		}
		return false;
	}

	private IProject createFeatureProject(ProjectDescriptor descriptor, SubMonitor monitor) {
		ProjectFactory factory = plainProjectProvider.get();
		configurePlainProject(descriptor, factory);
		factory.addProjectNatures("org.eclipse.pde.FeatureNature");
		factory.addBuilderIds("org.eclipse.pde.FeatureBuilder");
		return factory.createProject(monitor, null);
	}

	private IProject createPlainProject(ProjectDescriptor descriptor, SubMonitor monitor) {
		ProjectFactory factory = plainProjectProvider.get();
		configurePlainProject(descriptor, factory);
		return factory.createProject(monitor, null);
	}

	private void configurePlainProject(ProjectDescriptor descriptor, ProjectFactory factory) {
		factory.setProjectName(descriptor.getName());
		factory.setLocation(new Path(descriptor.getLocation()));
		factory.setProjectDefaultCharset(projectInfo.getEncoding().toString());
		factory.addWorkingSets(Lists.newArrayList(projectInfo.getWorkingSets()));
		factory.addContributor(new DescriptorBasedContributor(descriptor));
		if (needsM2eIntegration(descriptor)) {
			factory.addProjectNatures("org.eclipse.m2e.core.maven2Nature");
			factory.addBuilderIds("org.eclipse.m2e.core.maven2Builder");
		}
		if (needsBuildshipIntegration(descriptor)) {
			factory.addProjectNatures("org.eclipse.buildship.core.gradleprojectnature");
			factory.addBuilderIds("org.eclipse.buildship.core.gradleprojectbuilder");
			factory.addEarlyContributor(new GradleContributor(descriptor));
		}
	}

	private class DescriptorBasedContributor implements IProjectFactoryContributor {
		private ProjectDescriptor descriptor;

		public DescriptorBasedContributor(ProjectDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		@Override
		public void contributeFiles(IProject project, IFileCreator fileWriter) {
			for (AbstractFile file : descriptor.getFiles()) {
				if (!isFiltered(file)) {
					String path = getPath(file);
					IFile created = null;
					if (file instanceof TextFile) {
						created = fileWriter.writeToFile(((TextFile) file).getContent(), path);
					} else if (file instanceof BinaryFile) {
						created = createBinaryFile(fileWriter, path, ((BinaryFile) file).getContent());
					}

					if (created != null && file.isExecutable()) {
						addExecutableFlag(created);
					}
				}
			}
		}

		private void addExecutableFlag(IFile file) {
			ResourceAttributes attributes = file.getResourceAttributes();
			if (attributes != null) {
				attributes.setExecutable(true);
				try {
					file.setResourceAttributes(attributes);
				} catch (CoreException e) {
					LOG.warn("Failed to set executable flag for " + file.getFullPath().toOSString(), e);
				}
			}
		}

		private IFile createBinaryFile(IFileCreator fileWriter, String path, URL url) {
			IFile created = fileWriter.writeToFile("", path);
			InputStream stream = null;
			try {
				stream = url.openStream();
				created.setContents(stream, IResource.FORCE, new NullProgressMonitor());
			} catch (Exception e) {
				LOG.error("Failed to create binary file " + created.getFullPath().toOSString(), e);
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						LOG.warn("Failed to close stream for " + created.getFullPath().toOSString(), e);
					}
				}
			}
			return created;
		}

		private boolean isFiltered(AbstractFile file) {
			if (isPluginProject(descriptor)) {
				return newHashSet("plugin.xml", "MANIFEST.MF").contains(file.getRelativePath());
			}
			return false;
		}
	}

	private static class GradleContributor implements IProjectFactoryContributor {

		private ProjectDescriptor descriptor;

		public GradleContributor(ProjectDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		@Override
		public void contributeFiles(IProject project, IFileCreator fileWriter) {
			fileWriter.writeToFile(
				"connection.arguments=\n" +
				"connection.gradle.distribution=GRADLE_DISTRIBUTION(WRAPPER)\n" +
				"connection.gradle.user.home=null\n" +
				"connection.java.home=null\n" +
				"connection.jvm.arguments=\n" +
				"connection.project.dir=" + getConnectionLogicalPath() +"\n" +
				"eclipse.preferences.version=1\n" +
				"project.path=\\" + getLogicalPath() + "\n",
			".settings/org.eclipse.buildship.core.prefs");
		}

		private String getLogicalPath() {
			if (descriptor instanceof ParentProjectDescriptor) {
				return ":";
			} else {
				return ":" + descriptor.getName();
			}
		}

		private String getConnectionLogicalPath() {
			if (descriptor instanceof ParentProjectDescriptor) {
				return "";
			} else if (descriptor.getConfig().getProjectLayout() == ProjectLayout.FLAT) {
				return "../" + descriptor.getConfig().getParentProject().getName();
			} else {
				return "..";
			}
		}

	}

	private boolean isPluginProject(ProjectDescriptor descriptor) {
		return descriptor.isEclipsePluginProject();
	}

	private boolean isFeatureProject(ProjectDescriptor descriptor) {
		return descriptor.isEclipseFeatureProject();
	}

	private boolean isJavaProject(ProjectDescriptor descriptor) {
		return !descriptor.getSourceFolders().isEmpty();
	}

	private boolean needsM2eIntegration(ProjectDescriptor descriptor) {
		return descriptor.isPartOfMavenBuild() && descriptor.getConfig().needsMavenBuild();
	}

	private boolean needsBuildshipIntegration(ProjectDescriptor descriptor) {
		return descriptor.isPartOfGradleBuild() && descriptor.getConfig().needsGradleBuild();
	}

	private int getMonitorTicks() {
		return projectInfo.getEnabledProjects().size();
	}

	private String getCreateModelProjectMessage() {
		return Messages.XtextProjectCreator_CreatingProjectsMessage2 + projectInfo.getProjectName();
	}

	@Override
	public void setProjectInfo(IProjectInfo projectInfo) {
		this.projectInfo = (XtextProjectInfo) projectInfo;
	}

	@Override
	public IFile getResult() {
		return result;
	}

	private String getPath(AbstractFile file) {
		return file.getProject().sourceFolder(file.getOutlet()) + "/" + file.getRelativePath();
	}

}