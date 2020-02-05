/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.refactoring;

import static com.google.common.collect.Iterables.*;

import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.ui.refactoring.IDependentElementsCalculator;

import com.google.common.base.Function;

/**
 * @author Jan Koehnlein - Initial contribution and API
 * @author Holger Schill
 */
@SuppressWarnings("restriction")
public class XtextDependentElementsCalculator implements IDependentElementsCalculator {

	@Override
	public Iterable<URI> getDependentElementURIs(EObject baseElement, IProgressMonitor monitor) {
		return Collections.<URI> emptySet();
	}

	protected Iterable<URI> uris(Iterable<? extends EObject> elements) {
		return transform(elements, new Function<EObject, URI>() {
			@Override
			public URI apply(EObject from) {
				return EcoreUtil.getURI(from);
			}
		});
	}
}
