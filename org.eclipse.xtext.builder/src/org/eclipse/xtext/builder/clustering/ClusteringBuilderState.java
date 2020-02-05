/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.clustering;

import static com.google.common.collect.Sets.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.builder.MonitorBasedCancelIndicator;
import org.eclipse.xtext.builder.builderState.AbstractBuilderState;
import org.eclipse.xtext.builder.builderState.BuilderStateUtil;
import org.eclipse.xtext.builder.builderState.impl.ResourceDescriptionImpl;
import org.eclipse.xtext.builder.debug.IBuildLogger;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperation;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperationException;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadResult;
import org.eclipse.xtext.resource.CompilerPhases;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescription.Manager.AllChangeAware;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.resource.persistence.SourceLevelURIsAdapter;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @author Thomas Wolf <thomas.wolf@paranor.ch> - Refactored the build phases and documentation
 * @author Lieven Lemiengre <lieven.lemiengre@sigasi.com> - Parallel resource loading
 * @author Holger Schill
 */
public class ClusteringBuilderState extends AbstractBuilderState {

    public static final String RESOURCELOADER_CROSS_LINKING = "org.eclipse.xtext.builder.resourceloader.crossLinking";

    public static final String RESOURCELOADER_GLOBAL_INDEX = "org.eclipse.xtext.builder.resourceloader.globalIndex";

    /** Class-wide logger. */
    private static final Logger LOGGER = Logger.getLogger(ClusteringBuilderState.class);

    @Inject
    private IResourceServiceProvider.Registry managerRegistry;

    @Inject
    private org.eclipse.xtext.resource.clustering.IResourceClusteringPolicy clusteringPolicy;

    @Inject
    @Named(RESOURCELOADER_GLOBAL_INDEX)
    private IResourceLoader globalIndexResourceLoader;

    @Inject
    @Named(RESOURCELOADER_CROSS_LINKING)
    private IResourceLoader crossLinkingResourceLoader;
    
    @Inject
    private IWorkspace workspace;
    
    @Inject
    private IResourceServiceProvider.Registry resourceServiceProviderRegistry;
    
    @Inject
	private CompilerPhases compilerPhases;
    
    @Inject 
    private IBuildLogger buildLogger;
    
    /**
     * Actually do the build.
     *
     * @param buildData
     *            the data that should be considered for the update
     * @param newData
     *            the new resource descriptions as they are to be persisted (the new index after the build).
     *            Initially contains the old resource descriptions.
     * @param monitor
     *            The progress monitor
     * @return A list of deltas describing all changes made by the build.
     */
    @Override
    protected Collection<Delta> doUpdate(BuildData buildData, ResourceDescriptionsData newData, IProgressMonitor monitor) {
        // We assume that we have 101 ticks to work with:
    	// 20 for writeNewResourceDescription
    	// 1 for queueAffectedResources
    	// 80 for updating and queueAffectedResources
    	// Within the mentioned 80 ticks we assume that 2/3 is spent for updating and 1/3 for queueAffectedResources
    	final SubMonitor progress = SubMonitor.convert(monitor, 101);


        // Step 1: Clean the set of deleted URIs. If any of them are also added, they're not deleted.
        final Set<URI> toBeDeleted = buildData.getAndRemoveToBeDeleted();

        // Step 2: Create a new state (old state minus the deleted resources). This, by virtue of the flag
        // NAMED_BUILDER_SCOPE in the resource set's load options
        // and a Guice binding, is the index that is used during the build; i.e., linking during the build will
        // use this. Once the build is completed, the persistable index is reset to the contents of newState by
        // virtue of the newMap, which is maintained in synch with this.
        ResourceSet resourceSet = buildData.getResourceSet();
        final CurrentDescriptions newState = new CurrentDescriptions(resourceSet, newData, buildData);
        buildData.getSourceLevelURICache().cacheAsSourceURIs(toBeDeleted);
        installSourceLevelURIs(buildData);
        // Step 3: Create a queue; write new temporary resource descriptions for the added or updated resources so that we can link
        // subsequently; put all the added or updated resources into the queue.
        writeNewResourceDescriptions(buildData, this, newState, progress.split(20));

        if (progress.isCanceled()) {
            throw new OperationCanceledException();
        }

        // Step 4: Create a URI set of resources not yet in the delta. This is used for queuing; whenever a resource is
        // queued for processing, its URI is removed from this set. queueAffectedResources will consider only resources
        // in this set as potential candidates.
        for (final URI uri : toBeDeleted) {
            newData.removeDescription(uri);
        }
        final Set<URI> allRemainingURIs = Sets.newLinkedHashSet(newData.getAllURIs());
        allRemainingURIs.removeAll(buildData.getToBeUpdated());
        for(URI remainingURI: buildData.getAllRemainingURIs()) {
            allRemainingURIs.remove(remainingURI);
        }
        // TODO: consider to remove any entry from upstream projects and independent projects
        // from the set of remaining uris (template method or service?)
        // this should reduce the number of to-be-checked descriptions significantly
        // for common setups (large number of reasonable sized projects)

        // Our return value. It contains all the deltas resulting from this build.
        final Set<Delta> allDeltas = Sets.newHashSet();

        // Step 5: Put all resources depending on a deleted resource into the queue. Also register the deltas in allDeltas.
        if (!toBeDeleted.isEmpty()) {
            for (final URI uri : toBeDeleted) {
                final IResourceDescription oldDescription = this.getResourceDescription(uri);
                if (oldDescription != null) {
                    allDeltas.add(new DefaultResourceDescriptionDelta(oldDescription, null));
                }
            }
        }
        // Add all pending deltas to all deltas (may be scheduled java deltas)
        Collection<Delta> pendingDeltas = buildData.getAndRemovePendingDeltas();
        allDeltas.addAll(pendingDeltas);
        queueAffectedResources(allRemainingURIs, this, newState, allDeltas, allDeltas, buildData, progress.split(1));
        installSourceLevelURIs(buildData);

        IProject currentProject = getBuiltProject(buildData);
        LoadOperation loadOperation = null;
        try {
            Queue<URI> queue = buildData.getURIQueue();
            
			loadOperation = crossLinkingResourceLoader.create(resourceSet, currentProject);
            loadOperation.load(queue);

            // Step 6: Iteratively got through the queue. For each resource, create a new resource description and queue all depending
            // resources that are not yet in the delta. Validate resources.
            final SubMonitor subProgress = progress.split(80);
            CancelIndicator cancelMonitor = new MonitorBasedCancelIndicator(progress);

            while (!queue.isEmpty()) {
            	// heuristic: we multiply the size of the queue by 3 and spent 2 ticks for updating
            	// and 1 tick for queueAffectedResources since we assume that loading etc. is 
            	// slower than queueAffectedResources.
            	subProgress.setWorkRemaining((queue.size() + 1) * 3);
                int clusterIndex = 0;
                final List<Delta> changedDeltas = Lists.newArrayList();
                while (!queue.isEmpty()) {
                    if (subProgress.isCanceled()) {
                        loadOperation.cancel();
                        throw new OperationCanceledException();
                    }
                    if (!clusteringPolicy.continueProcessing(resourceSet, null, clusterIndex)) {
                        break;
                    }
                    URI changedURI = null;
                    URI actualResourceURI = null;
                    Resource resource = null;
                    Delta newDelta = null;

                    try {
                        // Load the resource and create a new resource description
                        LoadResult loadResult = loadOperation.next();
                        changedURI = loadResult.getUri();
                        actualResourceURI = loadResult.getResource().getURI();
                        resource = addResource(loadResult.getResource(), resourceSet);
                        subProgress.subTask("Updating resource " + resource.getURI().lastSegment());
                        if (LOGGER.isDebugEnabled()) {
                        	LOGGER.debug("Update resource description " + actualResourceURI);
                        }
                        queue.remove(changedURI);
                        if(toBeDeleted.contains(changedURI)) {
                            break;
                        }
                        buildLogger.log("indexing " + changedURI);
                        final IResourceDescription.Manager manager = getResourceDescriptionManager(actualResourceURI);
                        if (manager != null) {
                            // Resolve links here!
                        	try {
	                            EcoreUtil2.resolveLazyCrossReferences(resource, cancelMonitor);
	                            final IResourceDescription description = manager.getResourceDescription(resource);
	                            final IResourceDescription copiedDescription = BuilderStateUtil.create(description);
	                            newDelta = manager.createDelta(this.getResourceDescription(actualResourceURI), copiedDescription);
                        	} catch (OperationCanceledException e) {
                        		loadOperation.cancel();
                        		throw e;
                        	} catch (WrappedException e) {
                        		throw e;
                        	} catch (RuntimeException e) {
                        		LOGGER.error("Error resolving cross references on resource '"+actualResourceURI+"'", e);
                        		throw new LoadOperationException(actualResourceURI, e);
                        	}
                        }
                    } catch (final WrappedException ex) {
                        if(ex instanceof LoadOperationException) {
                            changedURI = ((LoadOperationException) ex).getUri();
                        } 
                        Throwable cause = ex.getCause();
                        boolean wasResourceNotFound = false;
                    	if (cause instanceof CoreException) {
                    		if (IResourceStatus.RESOURCE_NOT_FOUND == ((CoreException) cause).getStatus().getCode()) {
                    			wasResourceNotFound = true;
                    		}
                    	}
                        if(changedURI == null) {
                            LOGGER.error("Error loading resource", ex); //$NON-NLS-1$
                        } else {
                            queue.remove(changedURI);
                            if(toBeDeleted.contains(changedURI)) break;
                            if (!wasResourceNotFound)
                            	LOGGER.error("Error loading resource from: " + changedURI.toString(), ex); //$NON-NLS-1$
                            if (resource != null) {
                                resourceSet.getResources().remove(resource);
                            }
                            final IResourceDescription oldDescription = this.getResourceDescription(changedURI);
                            final IResourceDescription newDesc = newState.getResourceDescription(changedURI);
                            ResourceDescriptionImpl indexReadyDescription = newDesc != null ? BuilderStateUtil.create(newDesc) : null;
                            if ((oldDescription != null || indexReadyDescription != null) && oldDescription != indexReadyDescription) {
                                newDelta = new DefaultResourceDescriptionDelta(oldDescription, indexReadyDescription);
                            }
                        }
                    }
                    if (newDelta != null) {
                        allDeltas.add(newDelta);
                    	clusterIndex++;
                        if (newDelta.haveEObjectDescriptionsChanged())
                            changedDeltas.add(newDelta);
                        // Make the new resource description known and update the map.
                        newState.register(newDelta);
                        // Validate now.
                        if (!buildData.isIndexingOnly()) {
	                        try {
	                        	updateMarkers(newDelta, resourceSet, subProgress);
	                        } catch (OperationCanceledException e) {
	                        	loadOperation.cancel();
	                        	throw e;
	                        } catch (Exception e) {
	                        	LOGGER.error("Error validating "+newDelta.getUri(), e);
	                        }
                        }
                    }
                    // 2 ticks for updating since updating makes 2/3 of the work
                    subProgress.split(2);
                }

                loadOperation.cancel();

                queueAffectedResources(allRemainingURIs, this, newState, changedDeltas, allDeltas, buildData, subProgress.split(1));
                installSourceLevelURIs(buildData);
                if(queue.size() > 0) {
                    loadOperation = crossLinkingResourceLoader.create(resourceSet, currentProject);
                    loadOperation.load(queue);
                }

                // Release memory
                if (!queue.isEmpty() && !clusteringPolicy.continueProcessing(resourceSet, null, clusterIndex))
                    clearResourceSet(resourceSet);
            }
        } finally {
            if(loadOperation != null) loadOperation.cancel();
            if (!progress.isCanceled())
            	progress.done();
        }
        return allDeltas;
    }

	protected void installSourceLevelURIs(BuildData buildData) {
		ResourceSet resourceSet = buildData.getResourceSet();
		Iterable<URI> sourceLevelUris = Iterables.concat(buildData.getToBeUpdated(), buildData.getURIQueue());
		Set<URI> sourceUris = newHashSet();
		for (URI uri : sourceLevelUris) {
			if (buildData.getSourceLevelURICache().getOrComputeIsSource(uri, resourceServiceProviderRegistry)) {
				sourceUris.add(uri);
				// unload resources loaded from storage previously
				Resource resource = resourceSet.getResource(uri, false);
				if (resource instanceof StorageAwareResource) {
					if (((StorageAwareResource) resource).isLoadedFromStorage()) {
						resource.unload();
					}
				}
			}
		}
		SourceLevelURIsAdapter.setSourceLevelUris(resourceSet, sourceUris);
	}
    
    /**
     * Create new resource descriptions for a set of resources given by their URIs.
     *
     * @param buildData
     *            The underlying data for the write operation.
     * @param oldState
     *            The old index
     * @param newState
     *            The new index
     * @param monitor
     *            The progress monitor used for user feedback
     */
    protected void writeNewResourceDescriptions(
            BuildData buildData,
            IResourceDescriptions oldState,
            CurrentDescriptions newState,
            final IProgressMonitor monitor) {
        int index = 0;
        ResourceSet resourceSet = buildData.getResourceSet();
        Set<URI> toBeUpdated = buildData.getToBeUpdated();
        final SubMonitor subMonitor = SubMonitor.convert(monitor, "Write new resource descriptions", toBeUpdated.size() + 1); // TODO: NLS
        IProject currentProject = getBuiltProject(buildData);
        LoadOperation loadOperation = null;
        try {
        	compilerPhases.setIndexing(resourceSet, true);
            loadOperation = globalIndexResourceLoader.create(resourceSet, currentProject);
            loadOperation.load(toBeUpdated);

            while (loadOperation.hasNext()) {
                if (subMonitor.isCanceled()) {
                    loadOperation.cancel();
                    throw new OperationCanceledException();
                }

                if (!clusteringPolicy.continueProcessing(resourceSet, null, index)) {
                    clearResourceSet(resourceSet);
                }

                URI uri = null;
                Resource resource = null;
                try {
                    LoadResult loadResult = loadOperation.next();
                    uri = loadResult.getUri();
                    resource = addResource(loadResult.getResource(), resourceSet);
                    subMonitor.subTask("Writing new resource description " + resource.getURI().lastSegment());
                    if (LOGGER.isDebugEnabled()) {
                    	LOGGER.debug("Writing new resource description " + uri);
                    }

                    final IResourceDescription.Manager manager = getResourceDescriptionManager(uri);
                    if (manager != null) {
                        // We don't care here about links, we really just want the exported objects so that we can link in the
                        // next phase.
                        final IResourceDescription description = manager.getResourceDescription(resource);
                        final IResourceDescription copiedDescription = new CopiedResourceDescription(description);
                        // We also don't care what kind of Delta we get here; it's just a temporary transport vehicle. That interface
                        // could do with some clean-up, too, because all we actually want to do is register the new resource
                        // description, not the delta.
                        newState.register(new DefaultResourceDescriptionDelta(oldState.getResourceDescription(uri), copiedDescription));
                        buildData.queueURI(uri);
                    }
                } catch (final RuntimeException ex) {
                    if(ex instanceof LoadOperationException) {
                        uri = ((LoadOperationException) ex).getUri();
                    }
                    if (uri == null) {
                        LOGGER.error("Error loading resource", ex); //$NON-NLS-1$
                    } else {
                        if (resourceSet.getURIConverter().exists(uri, Collections.emptyMap())) {
                            LOGGER.error("Error loading resource from: " + uri.toString(), ex); //$NON-NLS-1$
                        }
                        if (resource != null) {
                            resourceSet.getResources().remove(resource);
                        }
                        final IResourceDescription oldDescription = oldState.getResourceDescription(uri);
                        if (oldDescription != null) {
                            newState.register(new DefaultResourceDescriptionDelta(oldDescription, null));
                        }
                    }
                    // If we couldn't load it, there's no use trying again: do not add it to the queue
                }
                index++;
                subMonitor.split(1);
            }
        } finally {
        	compilerPhases.setIndexing(resourceSet, false);
            if(loadOperation != null) loadOperation.cancel();
        }
    }

	protected IProject getBuiltProject(BuildData buildData) {
		if (Strings.isEmpty(buildData.getProjectName()))
			return null;
		return workspace.getRoot().getProject(buildData.getProjectName());
	}

    /**
     * Clears the content of the resource set without sending notifications.
     * This avoids unnecessary, explicit unloads.
     */
    protected void clearResourceSet(ResourceSet resourceSet) {
        boolean wasDeliver = resourceSet.eDeliver();
        try {
            resourceSet.eSetDeliver(false);
            for (Resource resource : resourceSet.getResources()) {
                resource.eSetDeliver(false);
            }
            resourceSet.getResources().clear();
        } finally {
            resourceSet.eSetDeliver(wasDeliver);
        }
    }

    /**
     * Adds a resource to the ResourceSet if the ResourceSet doesn't contain it yet.
     *
     * @param resource the resource
     * @param resourceSet the resource set
     * @return the resource
     */
    protected Resource addResource(Resource resource, ResourceSet resourceSet) {
        URI uri = resource.getURI();
        Resource r = resourceSet.getResource(uri, false);
        if (r == null) {
            resourceSet.getResources().add(resource);
            return resource;
        } else {
            return r;
        }
    }
    
    /**
     * Put all resources that depend on some changes onto the queue of resources to be processed.
     * Updates notInDelta by removing all URIs put into the queue.
     *
     * @param allRemainingURIs
     *            URIs that were not considered by prior operations.
     * @param oldState
     *            State before the build
     * @param newState
     *            The current state
     * @param changedDeltas
     *            the deltas that have changed {@link IEObjectDescription}s
     * @param allDeltas 
     *            All deltas 
     * @param buildData
     *            the underlying data for this build run.
     * @param monitor
     *            The progress monitor used for user feedback
     */
    protected void queueAffectedResources(
            Set<URI> allRemainingURIs,
            IResourceDescriptions oldState,
            CurrentDescriptions newState,
            Collection<Delta> changedDeltas,
            Collection<Delta> allDeltas,
            BuildData buildData,
            final IProgressMonitor monitor) {
        if (allDeltas.isEmpty()) {
            return;
        }
        final SubMonitor progress = SubMonitor.convert(monitor, allRemainingURIs.size() + 1);
        Iterator<URI> iter = allRemainingURIs.iterator();
        while (iter.hasNext()) {
            if (progress.isCanceled()) {
                throw new OperationCanceledException();
            }
            final URI candidateURI = iter.next();
            final IResourceDescription candidateDescription = oldState.getResourceDescription(candidateURI);
            final IResourceDescription.Manager manager = getResourceDescriptionManager(candidateURI);
            if (candidateDescription == null || manager == null) {
                // If there is no description in the old state, there's no need to re-check this over and over.
                iter.remove();
            } else {
            	boolean affected;
            	if ((manager instanceof IResourceDescription.Manager.AllChangeAware)) {
            		affected = ((AllChangeAware) manager).isAffectedByAny(allDeltas, candidateDescription, newState);
            	} else {
            		if (changedDeltas.isEmpty()) {
            			affected = false;
            		} else {
            			affected = manager.isAffected(changedDeltas, candidateDescription, newState);
            		}
            	}
                if (affected) {
                    buildData.queueURI(candidateURI);
                    iter.remove();
                }
            }
            progress.split(1);
        }
    }

    protected IResourceDescription.Manager getResourceDescriptionManager(URI uri) {
        IResourceServiceProvider resourceServiceProvider = managerRegistry.getResourceServiceProvider(uri);
        if (resourceServiceProvider == null) {
            return null;
        }
        return resourceServiceProvider.getResourceDescriptionManager();
    }

}
