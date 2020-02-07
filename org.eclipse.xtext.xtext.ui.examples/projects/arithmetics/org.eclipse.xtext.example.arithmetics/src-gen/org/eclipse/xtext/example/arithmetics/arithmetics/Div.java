/**
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.arithmetics.arithmetics;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Div</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.example.arithmetics.arithmetics.Div#getLeft <em>Left</em>}</li>
 *   <li>{@link org.eclipse.xtext.example.arithmetics.arithmetics.Div#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage#getDiv()
 * @model
 * @generated
 */
public interface Div extends Expression
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' containment reference.
   * @see #setLeft(Expression)
   * @see org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage#getDiv_Left()
   * @model containment="true"
   * @generated
   */
  Expression getLeft();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.example.arithmetics.arithmetics.Div#getLeft <em>Left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' containment reference.
   * @see #getLeft()
   * @generated
   */
  void setLeft(Expression value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference.
   * @see #setRight(Expression)
   * @see org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage#getDiv_Right()
   * @model containment="true"
   * @generated
   */
  Expression getRight();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.example.arithmetics.arithmetics.Div#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(Expression value);

} // Div
