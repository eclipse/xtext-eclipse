/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.builderState;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.builder.clustering.ClusteringBuilderState;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.builder.impl.QueuedBuildData;
import org.eclipse.xtext.builder.impl.ToBeBuilt;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.containers.DelegatingIAllContainerAdapter;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.shared.internal.SharedModule;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@RunWith(XtextRunner.class)
public class PersistableResourceDescriptionsTest {
	private static final String FILE_EXT = ".buildertestlanguage";
	private static Injector builderInjector;

	private Map<String, String> fileSystem = Maps.newHashMap();
	private ExtensibleURIConverterImpl uriConverter;
	private ClusteringBuilderState builderState;

	@BeforeClass
	public static void createBuilderInjector() {
		SharedModule module = new SharedModule(null);
		builderInjector = Guice.createInjector(module);
	}
	@AfterClass
	public static void discardBuilderInjector() {
		builderInjector = null;
	}
	
	@Before
	public void setUp() throws Exception {
		fileSystem = Maps.newHashMap();
		builderState = builderInjector.getInstance(ClusteringBuilderState.class);
		uriConverter = new ExtensibleURIConverterImpl() {
			@Override
			public InputStream createInputStream(org.eclipse.emf.common.util.URI uri, Map<?, ?> options)
					throws IOException {
				return new StringInputStream(fileSystem.get(uri.toString()));
			}
		};

	}
	
	public ResourceSet createResourceSet() {
		ResourceSetImpl resourceSetImpl = new ResourceSetImpl();
		resourceSetImpl.setURIConverter(uriConverter);
		resourceSetImpl.eAdapters().add(new DelegatingIAllContainerAdapter(new IAllContainersState(){

			@Override
			public List<String> getVisibleContainerHandles(String handle) {
				return null;
			}

			@Override
			public Collection<URI> getContainedURIs(String containerHandle) {
				return null;
			}

			@Override
			public String getContainerHandle(URI uri) {
				return null;
			}
			
			@Override
			public boolean isEmpty(String containerHandle) {
				return true;
			}
		}));
		return resourceSetImpl;
	}
	
	@After
	public void tearDown() throws Exception {
		fileSystem = null;
		uriConverter = null;
		builderState = null;
	}

	@Test public void testSimpleAdd() throws Exception {
		addToFileSystem("foo", "namespace foo { object A }");
		Map<URI, Delta> reload = update(uris("foo"), null);
		assertNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("foo")).getNew());
	}

	@Test public void testMultipleAdd() throws Exception {
		addToFileSystem("bar", "namespace bar { object B }");
		addToFileSystem("foo", "namespace foo { object A references bar.B}");
		Map<URI, Delta> reload = update(uris("foo", "bar"), null); // update
		assertNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("foo")).getNew());
	}

	@Test public void testUpdate() throws Exception {
		addToFileSystem("bar", "namespace bar { object B }");
		addToFileSystem("foo", "namespace foo { object A references bar.B}");
		Map<URI, Delta> reload = update(uris("foo", "bar"), null); // add
		assertNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("foo")).getNew());

		addToFileSystem("bar", "namespace bar { object C }");
		reload = update(uris("bar"), null); // update
		assertNotNull(reload.get(uri("bar")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("foo")).getNew());
	}

	@Test public void testUpdate_1() throws Exception {
		addToFileSystem("foo", "namespace foo { object A }");
		addToFileSystem("bar", "namespace bar { object B references foo.A}");
		addToFileSystem("baz", "namespace baz { object C references bar.B}");
		Map<URI, Delta> reload = update(uris("foo", "bar", "baz"), null); // add
		assertNull(reload.get(uri("foo")).getOld());
		assertNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("baz")).getOld());
		assertNotNull(reload.get(uri("foo")).getNew());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("baz")).getNew());

		addToFileSystem("foo", "namespace foo { object X }");
		reload = update(uris("foo"), null); // update
		assertNotNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("foo")).getNew());
		assertNotNull(reload.get(uri("bar")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNull(reload.get(uri("baz")));
	}

	@Test public void testUpdate_2() throws Exception {
		addToFileSystem("foo", "namespace foo { object A }");
		addToFileSystem("bar", "namespace bar { object B references foo.A}");
		addToFileSystem("baz", "namespace baz { object C references bar.B}");
		Map<URI, Delta> reload = update(uris("foo", "bar", "baz"), null); // add
		assertNull(reload.get(uri("foo")).getOld());
		assertNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("baz")).getOld());
		assertNotNull(reload.get(uri("foo")).getNew());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("baz")).getNew());

		addToFileSystem("foo", "namespace foo { object X }");
		addToFileSystem("bar", "namespace bar { object B references foo.X}");
		reload = update(uris("foo"), null); // update
		assertNotNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("foo")).getNew());
		assertNotNull(reload.get(uri("bar")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNull(reload.get(uri("baz")));
	}

	@Test public void testDelete() throws Exception {
		addToFileSystem("bar", "namespace bar { object B }");
		addToFileSystem("foo", "namespace foo { object A references bar.B}");
		Map<URI, Delta> reload = update(uris("foo", "bar"), null); // add
		assertNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("foo")).getNew());

		reload = update(null, uris("bar")); // delete
		assertNotNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("foo")).getNew());
	}

	@Test public void testDelete_1() throws Exception {
		addToFileSystem("bar", "namespace bar { object B }");
		addToFileSystem("foo", "namespace foo { object A references bar.B}");
		Map<URI, Delta> reload = update(uris("foo", "bar"), null); // add
		assertNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("foo")).getNew());

		reload = update(null, uris("foo")); // delete
		assertNull(reload.get(uri("bar")));
		assertNotNull(reload.get(uri("foo")).getOld());
		assertNull(reload.get(uri("foo")).getNew());
	}

	@Test public void testUpdateNoChange() throws Exception {
		addToFileSystem("bar", "namespace bar { object B }");
		addToFileSystem("foo", "namespace foo { object A references bar.B}");
		Map<URI, Delta> reload = update(uris("foo", "bar"), null); // add
		assertNull(reload.get(uri("bar")).getOld());
		assertNull(reload.get(uri("foo")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
		assertNotNull(reload.get(uri("foo")).getNew());

		reload = update(uris("bar"), null); // delete
		assertEquals(1, reload.size());
		assertNotNull(reload.get(uri("bar")).getOld());
		assertNotNull(reload.get(uri("bar")).getNew());
	}

	@Test public void testSave() throws Exception {
		addToFileSystem("bar", "namespace bar { object B }");
		addToFileSystem("foo", "namespace foo { object A references bar.B}");
		update(uris("foo", "bar"), null);
		ListBasedPersister persister = new ListBasedPersister();
		builderState.setPersister(persister);
		persister.save(builderState.getAllResourceDescriptions());
		assertEquals(2, persister.descriptions.size());
	}
	
	@Test public void testLoad() throws Exception {
		URI uri1 = URI.createFileURI("1.uri");
		URI uri2 = URI.createFileURI("2.uri");
		URI uri3 = URI.createFileURI("3.uri");
		ListBasedPersister persister = new ListBasedPersister();
		persister.descriptions.add(new URIBasedTestResourceDescription(uri1));
		persister.descriptions.add(new URIBasedTestResourceDescription(uri2));
		builderState.setPersister(persister);
		builderState.load();
		assertNotNull(builderState.getResourceDescription(uri1));
		assertNotNull(builderState.getResourceDescription(uri2));
		assertNull(builderState.getResourceDescription(uri3));
	}
	
	private static class ListBasedPersister implements PersistedStateProvider {

		public List<IResourceDescription> descriptions = Lists.newArrayList();
		
		@Override
		public Iterable<IResourceDescription> load() {
			return descriptions;
		}

		public void save(Iterable<IResourceDescription> descriptions) throws Exception {
			this.descriptions = Lists.newArrayList(descriptions);
		}
		
	}
	
	private void addToFileSystem(String uri, String contents) {
		fileSystem.put(uri + FILE_EXT, contents);
	}

	private URI uri(String name) {
		return URI.createURI(name + FILE_EXT);
	}

	private Set<URI> uris(String... urisAsStrings) {
		Set<URI> result = Sets.newHashSet();
		for (String string : urisAsStrings) {
			result.add(uri(string));
		}
		return result;
	}

	private Map<URI, IResourceDescription.Delta> update(Set<URI> toBeUpdated, Set<URI> toBeDeleted) {
		ResourceSet resourceSet = createResourceSet();
		try {
			ToBeBuilt toBeBuilt = new ToBeBuilt();
			if (toBeDeleted != null)
				toBeBuilt.getToBeDeleted().addAll(toBeDeleted);
			if (toBeUpdated != null)
				toBeBuilt.getToBeUpdated().addAll(toBeUpdated);
			BuildData buildData = new BuildData("", resourceSet, toBeBuilt, builderInjector.getInstance(QueuedBuildData.class));
			ImmutableList<Delta> update = builderState.update(buildData, new NullProgressMonitor());
			return Maps.uniqueIndex(update, new Function<IResourceDescription.Delta, URI>() {
				@Override
				public URI apply(IResourceDescription.Delta from) {
					return from.getUri();
				}
			});
		} finally {
			resourceSet.getResources().clear();
		}
	}
	
	private static class URIBasedTestResourceDescription extends AbstractResourceDescription {

		private URI uri;
		
		public URIBasedTestResourceDescription(URI uri) {
			this.uri = uri;
		}

		@Override
		protected List<IEObjectDescription> computeExportedObjects() {
			return Collections.emptyList();
		}
		
		@Override
		public Iterable<QualifiedName> getImportedNames() {
			return Collections.emptyList();
		}

		@Override
		public Iterable<IReferenceDescription> getReferenceDescriptions() {
			return Collections.emptyList();
		}

		@Override
		public URI getURI() {
			return uri;
		}
		
	}

}
