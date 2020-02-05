/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.resource;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.ui.resource.XtextLiveScopeResourceSetProvider;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 * @author Moritz Eysholdt
 */
public class LiveScopeResourceDescriptionsTest extends AbstractScopeResourceDescriptionsTest {

	@Inject
	private XtextLiveScopeResourceSetProvider liveScopeResourceSetProvider;

	@Test
	public void testDirtyStateAware() throws IOException {
		assertDirtyState(true);
	}

	@Test
	public void testLiveScope() throws Exception {
		assertLiveModelScopeLocal(true);
	}

	@Test
	public void testLiveScope2() throws Exception {
		assertLiveModelScopeExternalFile(true);
	}

	@Override
	protected ResourceSet createResourceSet(IProject project) {
		return liveScopeResourceSetProvider.get(project);
	}
}
