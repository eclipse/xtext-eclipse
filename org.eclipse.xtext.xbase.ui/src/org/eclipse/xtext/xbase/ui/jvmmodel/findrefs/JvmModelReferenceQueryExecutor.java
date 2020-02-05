/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.jvmmodel.findrefs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceQueryExecutor;

import com.google.common.base.Predicate;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class JvmModelReferenceQueryExecutor extends ReferenceQueryExecutor {

	@Override
	protected String getLabelPrefix() {
		return "Java References to ";
	}
	
	@Override
	protected Predicate<IReferenceDescription> getFilter(EObject primaryTarget) {
		return new JvmModelReferenceFilter();
	}
}
