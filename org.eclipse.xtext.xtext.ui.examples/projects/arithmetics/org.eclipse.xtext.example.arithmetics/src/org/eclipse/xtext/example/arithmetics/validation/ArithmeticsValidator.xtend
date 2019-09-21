/*******************************************************************************
 * Copyright (c) 2015, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.validation

import com.google.inject.Inject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.example.arithmetics.arithmetics.Div
import org.eclipse.xtext.example.arithmetics.arithmetics.Evaluation
import org.eclipse.xtext.example.arithmetics.arithmetics.Expression
import org.eclipse.xtext.example.arithmetics.arithmetics.FunctionCall
import org.eclipse.xtext.example.arithmetics.arithmetics.NumberLiteral
import org.eclipse.xtext.example.arithmetics.interpreter.Calculator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.ValidationMessageAcceptor

import static org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage.Literals.*

/**
 * Custom validation rules.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class ArithmeticsValidator extends AbstractArithmeticsValidator {

	@Inject Calculator calculator

	@Check
	def checkDivByZero(Div div) {
		val bigDecimal = calculator.evaluate(div.right)
		if (bigDecimal.doubleValue()==0.0)
			error("Division by zero detected.", DIV__RIGHT)
	}

	public static val String NORMALIZABLE = "normalizable-expression"

	@Check
	def void checkNormalizable(Expression expr) {
		// ignore literals
		if (expr instanceof NumberLiteral || expr instanceof FunctionCall)
			return;
		// ignore evaluations
		if (EcoreUtil2.getContainerOfType(expr, Evaluation)!==null)
			return;

		val contents = expr.eAllContents
		while(contents.hasNext()) {
			val next = contents.next()
			if (next instanceof FunctionCall)
				return
		}
		val decimal = calculator.evaluate(expr)
		if (decimal.toString().length()<=8) {
			warning(
					"Expression could be normalized to constant '"+decimal+"'",
					null,
					ValidationMessageAcceptor.INSIGNIFICANT_INDEX,
					NORMALIZABLE,
					decimal.toString())
		}
	}
}
