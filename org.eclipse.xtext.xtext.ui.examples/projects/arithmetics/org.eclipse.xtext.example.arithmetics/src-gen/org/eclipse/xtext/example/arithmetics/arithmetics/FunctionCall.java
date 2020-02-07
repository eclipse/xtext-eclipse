/**
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.arithmetics.arithmetics;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.example.arithmetics.arithmetics.FunctionCall#getFunc <em>Func</em>}</li>
 *   <li>{@link org.eclipse.xtext.example.arithmetics.arithmetics.FunctionCall#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage#getFunctionCall()
 * @model
 * @generated
 */
public interface FunctionCall extends Expression
{
  /**
   * Returns the value of the '<em><b>Func</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Func</em>' reference.
   * @see #setFunc(AbstractDefinition)
   * @see org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage#getFunctionCall_Func()
   * @model
   * @generated
   */
  AbstractDefinition getFunc();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.example.arithmetics.arithmetics.FunctionCall#getFunc <em>Func</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Func</em>' reference.
   * @see #getFunc()
   * @generated
   */
  void setFunc(AbstractDefinition value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.example.arithmetics.arithmetics.Expression}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference list.
   * @see org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage#getFunctionCall_Args()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getArgs();

} // FunctionCall
