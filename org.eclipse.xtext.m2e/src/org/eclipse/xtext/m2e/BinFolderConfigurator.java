/*******************************************************************************
 * Copyright (c) 2019 Sebastian Zarnekow (szarnekow) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.m2e;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.project.configurator.AbstractProjectConfigurator;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;

/**
 * Optionally compile everything to bin
 */
public class BinFolderConfigurator extends AbstractProjectConfigurator {

	@Override
	public void configure(ProjectConfigurationRequest request, IProgressMonitor monitor) throws CoreException {
		IProject project = request.getProject();
		if (compileToBin(project)) {
			IPath projectRoot = project.getFullPath();
			IPath binPath = projectRoot.append("bin");
			IPath binTestPath = projectRoot.append("bin-test");
			IJavaProject javaProject = JavaCore.create(project);
			javaProject.setOutputLocation(binPath, monitor);
			
			IClasspathEntry[] rawClasspath = javaProject.getRawClasspath();
			for(int i = 0; i < rawClasspath.length; i++) {
				IClasspathEntry entry = rawClasspath[i];
				if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					if (entry.isTest()) {
						IClasspathEntry copy = JavaCore.newSourceEntry(entry.getPath(), entry.getInclusionPatterns(), entry.getExclusionPatterns(), binTestPath, entry.getExtraAttributes());
						rawClasspath[i] = copy;
					} else {
						IClasspathEntry copy = JavaCore.newSourceEntry(entry.getPath(), entry.getInclusionPatterns(), entry.getExclusionPatterns(), binPath, entry.getExtraAttributes());
						rawClasspath[i] = copy;
					}
				}
			}
			javaProject.setRawClasspath(rawClasspath, monitor);
		}
	}

	/**
	 * @param project
	 * @return 
	 */
	private boolean compileToBin(IProject project) {
		String pluginId = "org.eclipse.xtext.m2e";
		String key = "compileToBin";
		ProjectScope projectScope = new ProjectScope(project);
		IEclipsePreferences projectPreferences = projectScope.getNode(pluginId);
		String value = projectPreferences.get(key, null);
		if (value != null) {
			return "true".equals(value);
		}
		IEclipsePreferences instancePreferences = InstanceScope.INSTANCE.getNode(pluginId);
		return "true".equals(instancePreferences.get(key, "false"));
	}


}
