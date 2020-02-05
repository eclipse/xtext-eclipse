/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.containers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJarEntryResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.xtext.ui.resource.IStorage2UriMapperJdtExtensions;
import org.eclipse.xtext.util.Pair;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@Singleton
public class JavaProjectsStateHelper extends AbstractProjectsStateHelper {

	private final static Logger log = Logger.getLogger(JavaProjectsStateHelper.class);
	
	@Inject
	private IStorage2UriMapperJdtExtensions uriMapperExtensions;
	
	/**
	 * Public for testing purpose
	 * 
	 * @since 2.5
	 * @nooverride This method is not intended to be re-implemented or extended by clients.
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public void setUriMapperExtensions(IStorage2UriMapperJdtExtensions uriMapperExtensions) {
		this.uriMapperExtensions = uriMapperExtensions;
	}
	
	public String initHandle(URI uri) {
		IPackageFragmentRoot root = getPackageFragmentRoot(uri);
		if (root != null)
			return root.getHandleIdentifier();
		return null;
	}
	
	public List<String> initVisibleHandles(String handle) {
		IJavaElement javaElement = JavaCore.create(handle);
		if (javaElement != null) {
			IJavaProject project = javaElement.getJavaProject();
			if (isAccessibleXtextProject(project.getProject())) {
				List<String> rootHandles = getPackageFragmentRootHandles(project);
				return rootHandles;
			} 
			return Collections.emptyList();
		}
		return Collections.emptyList();
	}
	
	public Collection<URI> initContainedURIs(String containerHandle) {
		IJavaElement javaElement = JavaCore.create(containerHandle);
		if (javaElement instanceof IPackageFragmentRoot) {
			IPackageFragmentRoot root = (IPackageFragmentRoot) javaElement;
			IJavaProject javaProject = root.getJavaProject();
			if (!isAccessibleXtextProject(javaProject.getProject())) {
				return Collections.emptyList();
			}
			Map<URI, IStorage> entries = uriMapperExtensions.getAllEntries(root);
			return entries.keySet();
		}
		return Collections.emptyList();
	}
	
	protected List<String> getPackageFragmentRootHandles(IJavaProject project) {
		List<String> result = Lists.newArrayList();
		List<String> binaryAndNonLocalFragments = Lists.newArrayList();
		try {
			IPackageFragmentRoot[] roots = project.getAllPackageFragmentRoots();
			result = Lists.newArrayListWithCapacity(roots.length);
			for (IPackageFragmentRoot root : roots) {
				if (root != null && !JavaRuntime.newDefaultJREContainerPath().isPrefixOf(root.getRawClasspathEntry().getPath())) {
					if (root.getKind() == IPackageFragmentRoot.K_SOURCE && project.equals(root.getJavaProject())) {
						// treat local sources with higher priority
						// see Java behavior in SameClassNamesTest
						result.add(root.getHandleIdentifier());	
					} else {
						binaryAndNonLocalFragments.add(root.getHandleIdentifier());
					}
				}
			}
		} catch (JavaModelException e) {
			if (!e.isDoesNotExist()) {
				log.error("Cannot find rootHandles in project " + project.getProject().getName(), e);
			}
		}
		result.addAll(binaryAndNonLocalFragments);
		return result;
	}
	
	protected IPackageFragmentRoot getPackageFragmentRoot(URI uri) {
		if (uri.isArchive() || !uri.isPlatform()) {
			return getJarWithEntry(uri);
		}
		final IFile file = getWorkspaceRoot().getFile(new Path(uri.toPlatformString(true)));
		if (file == null) {
			return getJarWithEntry(uri);
		}
		IPackageFragmentRoot root = getJavaElement(file);
		if (root == null)
			return getJarWithEntry(uri);
		return root;
	}
	
	protected IPackageFragmentRoot getJavaElement(final IFile file) {
		IJavaProject jp = JavaCore.create(file.getProject());
		if (!jp.exists())
			return null;
		IPackageFragmentRoot[] roots;
		try {
			roots = jp.getPackageFragmentRoots();
			for (IPackageFragmentRoot root : roots) {
				if (root.getKind() == IPackageFragmentRoot.K_SOURCE) {
					IResource resource2 = root.getUnderlyingResource();
					if (resource2.contains(file))
						return root;
				}
			}
		} catch (JavaModelException e) {
			if (!e.isDoesNotExist())
				log.error(e.getMessage(), e);
		}
		return null;
	}

	protected IPackageFragmentRoot getJarWithEntry(URI uri) {
		Iterable<Pair<IStorage, IProject>> storages = getStorages(uri);
		IPackageFragmentRoot result = null;
		for (Pair<IStorage, IProject> storage2Project : storages) {
			IStorage storage = storage2Project.getFirst();
			if (storage instanceof IJarEntryResource) {
				IPackageFragmentRoot fragmentRoot = ((IJarEntryResource) storage).getPackageFragmentRoot();
				if (fragmentRoot != null) {
					// IPackageFragmentRoot has some unexpected caching - it may return a different project
					// thus we use the one that was used to record the IPackageFragmentRoot
					IProject actualProject = storage2Project.getSecond();
					IJavaProject javaProject = JavaCore.create(actualProject);
					if (!javaProject.exists()) {
						javaProject = fragmentRoot.getJavaProject();
					}
					if (isAccessibleXtextProject(javaProject.getProject())) {
						// if both projects are the same - fine
						if (javaProject.equals(fragmentRoot.getJavaProject()))
							return fragmentRoot;
						// otherwise re-obtain the fragment root from the real project
						if (fragmentRoot.isExternal()) {
							IPackageFragmentRoot actualRoot = javaProject.getPackageFragmentRoot(fragmentRoot.getPath().toString());
							if (actualProject.exists()) {
								return actualRoot;
							}
						} else {
							IPackageFragmentRoot actualRoot = javaProject.getPackageFragmentRoot(fragmentRoot.getResource());
							if (actualRoot.exists()) {
								return actualRoot;
							}
						}
						result = fragmentRoot;
					}
					if (result == null)
						result = fragmentRoot;
				}
			}
		}
		return result;
	}
	
}
