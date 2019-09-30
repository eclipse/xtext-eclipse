/*******************************************************************************
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.domainmodel.validation

import org.eclipse.xtext.example.domainmodel.domainmodel.Entity
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature
import org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.ValidationMessageAcceptor

import static org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelPackage.Literals.*
import static org.eclipse.xtext.example.domainmodel.validation.IssueCodes.*

import static extension java.lang.Character.isLowerCase
import static extension java.lang.Character.isUpperCase

/**
 * This class contains custom validation rules.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class DomainmodelValidator extends AbstractDomainmodelValidator {

	@Check def void checkTypeNameStartsWithCapital(Entity entity) {
		if (!entity.name.charAt(0).isUpperCase) {
			warning("Name should start with a capital", ABSTRACT_ELEMENT__NAME,
				ValidationMessageAcceptor.INSIGNIFICANT_INDEX, INVALID_TYPE_NAME, entity.name)
		}
	}

	@Check def void checkFeatureNameStartsWithLowercase(Feature feature) {
		if (!feature.name.charAt(0).isLowerCase) {
			warning("Name should start with a lowercase", FEATURE__NAME,
				ValidationMessageAcceptor.INSIGNIFICANT_INDEX, INVALID_FEATURE_NAME, feature.name)
		}
	}

	@Check def void checkPackage(PackageDeclaration packages) {
		if (Strings.isEmpty(packages.name)) {
			error("Name cannot be empty", ABSTRACT_ELEMENT__NAME)
		}
		if (packages.name == "java") {
			error("Invalid package name", ABSTRACT_ELEMENT__NAME)
		}
	}

}
