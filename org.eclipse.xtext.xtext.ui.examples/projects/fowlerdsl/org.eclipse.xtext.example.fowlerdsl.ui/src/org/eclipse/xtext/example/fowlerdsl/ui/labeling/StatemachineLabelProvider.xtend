/*******************************************************************************
 * Copyright (c) 2013, 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.fowlerdsl.ui.labeling

import com.google.inject.Inject
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.xtext.example.fowlerdsl.statemachine.State
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider

/**
 * Provides labels for a EObjects.
 *
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
class StatemachineLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	new(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	def text(State state) {
		'state ' + state.name
	}

//	def image(State ele) {
//		'State.gif'
//	}
}
