/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.example.fowlerdsl.validation;

import org.eclipse.xtext.example.fowlerdsl.statemachine.StatemachinePackage;
import org.eclipse.xtext.validation.Check;

/**
 * This class contains custom validation rules.
 *
 * See
 * https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class StatemachineValidator extends AbstractStatemachineValidator {

	public static final String INVALID_NAME = "invalidName";

	@Check
	public void checkStateNameStartsWithLowerCase(org.eclipse.xtext.example.fowlerdsl.statemachine.State state) {
		if (Character.isUpperCase(state.getName().charAt(0))) {
			warning("Name should start with a lower case letter", StatemachinePackage.Literals.STATE__NAME,
					StatemachineValidator.INVALID_NAME, state.getName());
		}
	}

}
