/*******************************************************************************
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.homeautomation.jvmmodel

import com.google.inject.Inject
import java.util.Scanner
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.example.homeautomation.ruleEngine.Device
import org.eclipse.xtext.example.homeautomation.ruleEngine.Model
import org.eclipse.xtext.example.homeautomation.ruleEngine.Rule
import org.eclipse.xtext.example.homeautomation.ruleEngine.State
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

/**
 * <p>Infers a JVM model from the source model.</p>
 *
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
class RuleEngineJvmModelInferrer extends AbstractModelInferrer {

	/**
	 * convenience API to build and initialize JVM types and their members.
	 */
	@Inject extension JvmTypesBuilder

	def dispatch void infer(Model element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
		val className = element.eResource.URI.trimFileExtension.lastSegment.toFirstUpper
		acceptor.accept(element.toClass(className)) [
			for (device : element.declarations.filter(Rule)) {
				members += device.toMethod(getRuleMethodName(device), typeRef(void)) [
					static = true
					body = device.thenPart
				]
			}
			members += element.toMethod('fire', typeRef(void)) [
				static = true
				parameters += element.toParameter('event', typeRef(Object))
				body = '''
					«FOR device : element.declarations.filter(Device)»
						«FOR state : device.states»
							if (event == «state.qualifiedJavaName») {
								System.out.println("«device.name» is now «state.name»!");
							}
						«ENDFOR»
					«ENDFOR»
					«FOR rule : element.declarations.filter(Rule)»
						if (event == «rule.deviceState.qualifiedJavaName») {
							«rule.ruleMethodName»();
						}
					«ENDFOR»
				'''
			]
			members += element.toMethod('main', typeRef(void)) [
				static = true
				parameters += element.toParameter('args', typeRef(String).addArrayTypeDimension)
				body = '''
					«Scanner» scanner = new Scanner(System.in);
					System.out.println("Welcome home!");
					System.out.println("Available commands : ");
					«FOR device : element.declarations.filter(Device)»
						«FOR state : device.states»
							System.out.println("  «device.name» «state.name»" );
						«ENDFOR»
					«ENDFOR»
					System.out.println("Have fun!");
					while(true) {
						String command = scanner.next();
						«FOR device : element.declarations.filter(Device)»
							if (command.equalsIgnoreCase("«device.name»")) {
								String secondaryCommand = scanner.next();
								«FOR state : device.states»
									if (secondaryCommand.equalsIgnoreCase("«state.name»")) {
										fire(«state.qualifiedJavaName»);
									} else 
								«ENDFOR»
								{
									System.out.println("«device.name» can only have the following states: «device.states.map[name].
						join(',')».");
								}
							}
						«ENDFOR»
						if (command.equalsIgnoreCase("bye")) {
							System.out.println("Ciao!");
							break;
						}
					}
				'''
			]
		]

		for (device : element.declarations.filter(Device)) {
			acceptor.accept(device.toEnumerationType(device.name)[]) [
				for (state : device.states) {
					members += state.toEnumerationLiteral(state.name) [
						visibility = JvmVisibility.PUBLIC
					]
				}
			]
		}

	}

	private def String getQualifiedJavaName(State state) {
		(state.eContainer as Device).name + '.' + state.name
	}

	static def getRuleMethodName(Rule rule) {
		'execute' + rule.description?.replaceAll('\\W', '_')
	}
}
