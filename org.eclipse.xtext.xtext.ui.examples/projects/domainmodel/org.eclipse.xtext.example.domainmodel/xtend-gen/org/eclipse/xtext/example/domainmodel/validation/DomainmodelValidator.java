/**
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.domainmodel.validation;

import org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelPackage;
import org.eclipse.xtext.example.domainmodel.domainmodel.Entity;
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature;
import org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration;
import org.eclipse.xtext.example.domainmodel.validation.AbstractDomainmodelValidator;
import org.eclipse.xtext.example.domainmodel.validation.IssueCodes;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class DomainmodelValidator extends AbstractDomainmodelValidator {
  @Check
  public void checkTypeNameStartsWithCapital(final Entity entity) {
    boolean _isUpperCase = Character.isUpperCase(entity.getName().charAt(0));
    boolean _not = (!_isUpperCase);
    if (_not) {
      this.warning("Name should start with a capital", DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME, 
        ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.INVALID_TYPE_NAME, entity.getName());
    }
  }
  
  @Check
  public void checkFeatureNameStartsWithLowercase(final Feature feature) {
    boolean _isLowerCase = Character.isLowerCase(feature.getName().charAt(0));
    boolean _not = (!_isLowerCase);
    if (_not) {
      this.warning("Name should start with a lowercase", DomainmodelPackage.Literals.FEATURE__NAME, 
        ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.INVALID_FEATURE_NAME, feature.getName());
    }
  }
  
  @Check
  public void checkPackage(final PackageDeclaration packages) {
    boolean _isEmpty = Strings.isEmpty(packages.getName());
    if (_isEmpty) {
      this.error("Name cannot be empty", DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME);
    }
    boolean _equals = packages.getName().equals("java");
    if (_equals) {
      this.error("Invalid package name", DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME);
    }
  }
}
