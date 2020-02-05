/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.builderState;

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.builder.clustering.ClusteringBuilderState;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.builder.impl.QueuedBuildData;
import org.eclipse.xtext.builder.impl.ToBeBuilt;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.shared.internal.EagerContributionInitializer;
import org.eclipse.xtext.ui.shared.internal.SharedModule;
import org.eclipse.xtext.util.Modules2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug349445Test extends Assert implements PersistedStateProvider, IMarkerUpdater, IResourceServiceProvider.Registry {

	private IBuilderState testMe;
	private int loadCalled;
	private Injector injector;

	@Before
	public void setUp() throws Exception {
		injector = Guice.createInjector(Modules2.mixin(new SharedModule(null), new AbstractModule() {
			@Override
			protected void configure() {
				bind(PersistedStateProvider.class).toInstance(Bug349445Test.this);
				bind(IMarkerUpdater.class).toInstance(Bug349445Test.this);
				bind(IResourceServiceProvider.Registry.class).toInstance(Bug349445Test.this);
			}
		}));
		loadCalled = 0;
		testMe = injector.getInstance(ClusteringBuilderState.class);
	}
	
	@After
	public void tearDown() throws Exception {
		EagerContributionInitializer initializer = injector.getInstance(EagerContributionInitializer.class);
		initializer.discard();
	}
	
	@Test public void testUpdate() {
		testMe.update(new BuildData(null, null, new ToBeBuilt(), new QueuedBuildData(null)), null);
		assertEquals(1, loadCalled);
	}
	
	@Test public void testClean() {
		testMe.clean(Collections.<URI>emptySet(), null);
		assertEquals(1, loadCalled);
	}
	
	@Test public void testGetAllResourceDescriptions() {
		testMe.getAllResourceDescriptions();
		assertEquals(1, loadCalled);
	}
	
	@Test public void testGetResourceDescription() {
		testMe.getResourceDescription(URI.createURI(""));
		assertEquals(1, loadCalled);
	}
	
	@Test public void testIsEmpty() {
		testMe.isEmpty();
		assertEquals(1, loadCalled);
	}
	
	@Test public void testGetExportedObjects_1() {
		testMe.getExportedObjects();
		assertEquals(1, loadCalled);
	}
	
	@Test public void testGetExportedObjects_2() {
		testMe.getExportedObjects(EcorePackage.Literals.EOBJECT, QualifiedName.create("a", "name"), true);
		assertEquals(1, loadCalled);
	}
	
	@Test public void testGetExportedObjectsByType() {
		testMe.getExportedObjectsByType(EcorePackage.Literals.EOBJECT);
		assertEquals(1, loadCalled);
	}
	
	@Test public void testGetExportedObjectsByObject() {
		testMe.getExportedObjectsByObject(EcorePackage.Literals.EOBJECT);
		assertEquals(1, loadCalled);
	}
	
	@Override
	public void updateMarkers(Delta resourceDescriptionDeltas, /* @Nullable */ ResourceSet resourceSet,
			IProgressMonitor monitor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<IResourceDescription> load() {
		loadCalled++;
		return Collections.emptyList();
	}

	@Override
	public IResourceServiceProvider getResourceServiceProvider(URI uri, String contentType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IResourceServiceProvider getResourceServiceProvider(URI uri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> getContentTypeToFactoryMap() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> getExtensionToFactoryMap() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> getProtocolToFactoryMap() {
		throw new UnsupportedOperationException();
	}

}
