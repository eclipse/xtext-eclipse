/*******************************************************************************
 * Copyright (c) 2017 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.wizard.template;

/**
 * Part of a project template the variable defines the UI for the user to configure the generated files. Is generated
 * by reflection by fields annotated with @Variable from classes annotated with @Template. There is a fixed set of
 * subclasses to represent ProjectVariables of various types (boolean, string ...).
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
abstract class ProjectVariable {

	private String id
	private final String label
	private final String description
	protected var boolean enabled

	package new(String label, String description) {
		this.label = label
		this.description = description
		this.enabled = true
	}

	package def setId(String value) { id = value }

	def String getId() { id }

	def String getLabel() { label }

	def String getDescription() { description }

	def boolean isEnabled() { enabled }

	def setEnabled(boolean value) { enabled = value }

}

public final class BooleanProjectVariable extends ProjectVariable {

	boolean value

	new(String label, boolean defaultValue, String description) {
		super(label, description)
		value = defaultValue
	}
	
	def getValue() { value }
	
	def setValue(boolean value) { this.value = value }

	override String toString() { value.toString }

}

public final class StringProjectVariable extends ProjectVariable {

	String value

	new(String label, String defaultValue, String description) {
		super(label, description)
		value = defaultValue
	}

	def getValue() { value }
	
	def setValue(String value) { this.value = value }

	override String toString() { value }

}

public final class StringSelectionProjectVariable extends ProjectVariable {

	String[] possibleValues
	String value

	new(String label, String[] possibleValues, String description) {
		super(label, description)
		this.possibleValues = possibleValues
		this.value = possibleValues.get(0)
	}

	def String[] getPossibleValues() { possibleValues }

	def String getValue() { value }

	def setValue(String value) { this.value = value }
	
	override String toString() { value }

}
