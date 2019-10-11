/*******************************************************************************
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.domainmodel.ui.labeling

import com.google.inject.Inject
import java.util.Iterator
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.xtext.common.types.JvmArrayType
import org.eclipse.xtext.common.types.JvmFormalParameter
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.JvmTypeConstraint
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.JvmUpperBound
import org.eclipse.xtext.common.types.JvmWildcardTypeReference
import org.eclipse.xtext.example.domainmodel.domainmodel.Operation
import org.eclipse.xtext.example.domainmodel.domainmodel.Property
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider

import static org.eclipse.xtext.util.Strings.*

/**
 * Provides labels for a EObjects.
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#label-provider
 */
class DomainmodelLabelProvider extends XbaseLabelProvider {
	@Inject new(AdapterFactoryLabelProvider delegate) {
		super(delegate)
	}

	override protected Object doGetImage(Object element) {
		if (element instanceof EObject && !(element instanceof JvmIdentifiableElement)) {
			return '''«((element as EObject)).eClass().getName()».gif'''.toString
		}
		return super.doGetImage(element)
	}

	def String text(Property property) {
		var StringBuilder builder = new StringBuilder()
		builder.append(notNull(property.getName()))
		builder.append(" : ")
		append(builder, property.getType())
		return builder.toString()
	}

	def String text(Operation operation) {
		var StringBuilder builder = new StringBuilder()
		builder.append(notNull(operation.getName()))
		builder.append("(")
		var boolean isFirst = true
		for (JvmFormalParameter param : operation.getParams()) {
			if(!isFirst) builder.append(", ")
			isFirst = false
			append(builder, param.getParameterType())
		}
		builder.append(") : ")
		append(builder, operation.getType())
		return builder.toString()
	}

	def protected void append(StringBuilder builder, JvmTypeReference typeRef) {
		if (typeRef instanceof JvmParameterizedTypeReference) {
			val JvmType type = typeRef.getType()
			append(builder, type)
			var EList<JvmTypeReference> arguments = typeRef.getArguments()
			if (!arguments.isEmpty()) {
				builder.append("<")
				var Iterator<JvmTypeReference> iterator = arguments.iterator()
				while (iterator.hasNext()) {
					var JvmTypeReference jvmTypeReference = iterator.next()
					append(builder, jvmTypeReference)
					if(iterator.hasNext()) builder.append(",")
				}
				builder.append(">")
			}
		} else if (typeRef instanceof JvmWildcardTypeReference) {
			builder.append("?")
			var Iterator<JvmTypeConstraint> iterator = typeRef.getConstraints().
				iterator()
			while (iterator.hasNext()) {
				var JvmTypeConstraint constraint = iterator.next()
				if (constraint instanceof JvmUpperBound) {
					builder.append(" extends ")
				} else {
					builder.append(" super ")
				}
				append(builder, constraint.getTypeReference())
				if(iterator.hasNext()) builder.append(" & ")
			}
		} else if (typeRef instanceof JvmGenericArrayTypeReference) {
			append(builder, typeRef.getType())
		}
	}

	def protected void append(StringBuilder builder, JvmType type) {
		if (type instanceof JvmArrayType) {
			append(builder, type.getComponentType())
			builder.append("[]")
		} else {
			builder.append(notNull(type.getSimpleName()))
		}
	}
}
