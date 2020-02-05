/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.resource;

import static com.google.common.collect.Maps.*;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.shared.contribution.ISharedStateContributionRegistry;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @noextend This class is not intended to be subclassed by clients.
 */
@Singleton
public class Storage2UriMapperImpl implements IStorage2UriMapperExtension {
	
	private final static Logger log = Logger.getLogger(Storage2UriMapperImpl.class);
	
	@Inject private UriValidator uriValidator;

	private IStorage2UriMapperContribution contribution = new IStorage2UriMapperContribution() {
		@Override
		public void initializeCache() {
			// nothing to do
		}
		@Override
		public boolean isRejected(/* @NonNull */ IFolder folder) {
			return false;
		}
		/* @NonNull */ 
		@Override
		public Iterable<Pair<IStorage, IProject>> getStorages(/* @NonNull */ URI uri) {
			return Collections.emptyList();
		}
		@Override
		public URI getUri(/* @NonNull */ IStorage storage) {
			return null;
		}
	};
	
	/**
	 * @since 2.4
	 */
	public final void setUriValidator(UriValidator uriValidator) {
		this.uriValidator = uriValidator;
	}
	
	/**
	 * Public for testing purpose
	 * 
	 * @since 2.5
	 * @nooverride This method is not intended to be re-implemented or extended by clients.
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public void setContribution(IStorage2UriMapperContribution contribution) {
		this.contribution = contribution;
	}
	
	/**
	 * Public for testing purpose
	 * 
	 * @since 2.5
	 * @nooverride This method is not intended to be re-implemented or extended by clients.
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public IStorage2UriMapperContribution getContribution() {
		return contribution;
	}
	
	@Inject
	private void initializeContributions(ISharedStateContributionRegistry registry) {
		final ImmutableList<? extends IStorage2UriMapperContribution> allContributions = registry.getContributedInstances(IStorage2UriMapperContribution.class);
		final int size = allContributions.size();
		switch(size) {
			case 0: 
				// nothing to do
				break;
			case 1:
				contribution = allContributions.get(0);
				break;
			default:
				contribution = new IStorage2UriMapperContribution() {
					@Override
					public void initializeCache() {
						for(IStorage2UriMapperContribution contribution: allContributions) {
							contribution.initializeCache();
						}
					}
					@Override
					public boolean isRejected(/* @NonNull */ IFolder folder) {
						for(int i = 0; i < size; i++) {
							if (allContributions.get(i).isRejected(folder)) {
								return true;
							}
						}
						return false;
					}
					/* @NonNull */
					@Override
					public Iterable<Pair<IStorage, IProject>> getStorages(/* @NonNull */ final URI uri) {
						return Iterables.concat(Lists.transform(allContributions, new Function<IStorage2UriMapperContribution, Iterable<Pair<IStorage, IProject>>>() {
							/* @NonNull */
							@Override
							public Iterable<Pair<IStorage, IProject>> apply(IStorage2UriMapperContribution contribution) {
								return contribution.getStorages(uri);
							}
						}));
					}
					/* @Nullable */
					@Override
					public URI getUri(/* @NonNull */ IStorage storage) {
						for(int i = 0; i < size; i++) {
							URI result = allContributions.get(i).getUri(storage);
							if (result != null) {
								return result;
							}
						}
						return null;
					}
				};
		}
	}
	
	/**
	 * @since 2.5
	 */
	@Override
	public void initializeCache() {
		contribution.initializeCache();
	}
	
	/**
	 * @since 2.4
	 */
	@Override
	public Map<URI, IStorage> getAllEntries(IContainer container) {
		final Map<URI,IStorage> result = newLinkedHashMap();
		try {
			container.accept(new IResourceVisitor() {
				@Override
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile) {
						final IFile storage = (IFile) resource;
						URI uri = getUri(storage);
						if (uri != null)
							result.put(uri, storage);
					}
					if (resource instanceof IFolder) {
						return isHandled((IFolder)resource);
					}
					return true;
				}
			});
		} catch (CoreException e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * Return <code>true</code> if the folder should be traversed. <code>False</code> otherwise.
	 * Defaults to <code>true</code> for all folders.
	 * @return <code>true</code> if the folder should be traversed. <code>False</code> otherwise.
	 * @since 2.4
	 */
	protected boolean isHandled(IFolder folder) {
		return !contribution.isRejected(folder);
	}

	@Override
	public Iterable<Pair<IStorage, IProject>> getStorages(URI uri) {
		if (!uri.isPlatformResource()) {
			// support storage lookup by absolute file URI as it is possibly resolved by importURI references
			if (uri.isFile()) {
				IPath path = new Path(uri.toFileString());
				if (path.isAbsolute()) {
					IFile file = getWorkspaceRoot().getFileForLocation(path);
					return getStorages(uri, file);
				}
			}
			return contribution.getStorages(uri);
		}
		IFile file = getWorkspaceRoot().getFile(new Path(uri.toPlatformString(true)));
		return getStorages(uri, file);
	}

	private Iterable<Pair<IStorage, IProject>> getStorages(/* @NonNull */ URI uri, IFile file) {
		if (file == null || !file.isAccessible()) {
			Iterable<Pair<IStorage, IProject>> result = contribution.getStorages(uri);
			if (result == null || !result.iterator().hasNext()) {
				 // Handle files external to the workspace. But check contribution first to be backwards compatible.
				if (uri.isFile()) {
					Path filePath = new Path(uri.toFileString());
					IFileStore fileStore = EFS.getLocalFileSystem().getStore(filePath);
					IFileInfo fileInfo = fileStore.fetchInfo();
					if (fileInfo.exists() && !fileInfo.isDirectory()) {
						return Collections.singletonList(
								Tuples.<IStorage, IProject> create(new FileStoreStorage(fileStore, fileInfo, filePath), (IProject) null));
					}
				}
			}
			return result;
		}
		return Collections.singleton(Tuples.<IStorage, IProject> create(file, file.getProject()));
	}

	protected IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	@Override
	public URI getUri(IStorage storage) {
		if (!uriValidator.isPossiblyManaged(storage))
			return null;
		URI uri = internalGetUri(storage);
		if (uri != null && isValidUri(uri,storage))
			return uri;
		return null;
	}

	private URI internalGetUri(/* @NonNull */ IStorage storage) {
		if (storage instanceof IFile) {
			return URI.createPlatformResourceURI(storage.getFullPath().toString(), true);
		} else if (storage instanceof FileStoreStorage) {
			return URI.createFileURI(((FileStoreStorage) storage).getFullPath().makeAbsolute().toOSString());
		}
		return contribution.getUri(storage);
	}	

	public boolean isValidUri(URI uri, IStorage storage) {
		boolean valid = uriValidator.isValid(uri, storage);
		return valid;
	}
	
}
