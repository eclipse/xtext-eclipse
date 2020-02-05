/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.example.fowlerdsl.ui.labeling;

import org.eclipse.xtext.ui.label.DefaultDescriptionLabelProvider;

/**
 * Provides labels for IEObjectDescriptions and IResourceDescriptions.
 * 
 * https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#label-provider
 */
public class StatemachineDescriptionLabelProvider extends DefaultDescriptionLabelProvider {

	// Labels and icons can be computed like this:
	
//	String text(IEObjectDescription ele) {
//		return ele.getName().toString();
//	}
//	 
//	String image(IEObjectDescription ele) {
//		return ele.getEClass().getName() + ".gif";
//	}
}
