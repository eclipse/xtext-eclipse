/**
 * Copyright (c) 2011, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.domainmodel.domainmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.common.types.JvmFormalParameter;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Feature
{
  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.common.types.JvmFormalParameter}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelPackage#getOperation_Params()
   * @model containment="true"
   * @generated
   */
  EList<JvmFormalParameter> getParams();

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(XExpression)
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelPackage#getOperation_Body()
   * @model containment="true"
   * @generated
   */
  XExpression getBody();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(XExpression value);

} // Operation
