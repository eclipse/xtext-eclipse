/*******************************************************************************
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.homeautomation.validation

import javax.inject.Inject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.example.homeautomation.jvmmodel.RuleEngineJvmModelInferrer
import org.eclipse.xtext.example.homeautomation.ruleEngine.Device
import org.eclipse.xtext.example.homeautomation.ruleEngine.Model
import org.eclipse.xtext.example.homeautomation.ruleEngine.Rule
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.xbase.XAbstractFeatureCall
import org.eclipse.xtext.xbase.XFeatureCall
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations

import static org.eclipse.xtext.example.homeautomation.ruleEngine.RuleEnginePackage.Literals.*
import static org.eclipse.xtext.xbase.XbasePackage.Literals.*

/**
 * This class contains custom validation rules.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class RuleEngineValidator extends AbstractRuleEngineValidator {

	@Inject extension IJvmModelAssociations

	@Check
	def checkUniqueDeclarations(Model model) {
		val deviceNames = newHashSet
		val ruleDescriptions = newHashSet
		for (decl : model.declarations) {
			if (decl instanceof Device) {
				if (!deviceNames.add(decl.name)) {
					error('Device names must be unique.', decl, DEVICE__NAME)
				}
			} else if (decl instanceof Rule) {
				val methodName = RuleEngineJvmModelInferrer.getRuleMethodName(decl)
				if (!ruleDescriptions.add(methodName)) {
					error('Rule descriptions must be unique.', decl, RULE__DESCRIPTION)
				}
			}
		}
	}

	@Check
	def checkStatesNotEmpty(Device device) {
		if (device.states.empty) {
			error('''The device "«device.name»" must have at least one state.''', device, DEVICE__NAME)
		}
	}

	@Check
	def checkUniqueStates(Device device) {
		val stateNames = newHashSet
		for (state : device.states) {
			if (!stateNames.add(state.name)) {
				error('State names must be unique.', state, STATE__NAME)
			}
		}
	}

	@Check
	def checkRuleDescriptionNotEmpty(Rule rule) {
		if (rule.description.nullOrEmpty) {
			error('A rule description must not be empty.', rule, RULE__DESCRIPTION)
		}
	}

	@Check
	def checkRuleRecursion(XFeatureCall featureCall) {
		val containingRule = EcoreUtil2.getContainerOfType(featureCall, Rule)
		if (containingRule !== null && featureCall.feature instanceof JvmOperation
				&& featureCall.concreteSyntaxFeatureName == 'fire'
				&& featureCall.featureCallArguments.size == 1) {
			val argument = featureCall.featureCallArguments.head
			if (argument instanceof XAbstractFeatureCall) {
				val sourceElem = argument.feature.primarySourceElement
				if (sourceElem == containingRule.deviceState) {
					warning('''Firing the same device state that triggers the rule "«containingRule.description»" may lead to endless recursion.''',
						featureCall, XFEATURE_CALL__FEATURE_CALL_ARGUMENTS, 0)
				}
			}
		}
	}

}
