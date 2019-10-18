/*******************************************************************************
 * Copyright (c) 2017, 2019 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.wizard.template;

import com.google.common.annotations.Beta
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Combo
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Group
import org.eclipse.swt.widgets.Text
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Part of a template the variable defines the UI for the user to configure the generated files.
 * A variable will be associated with a widget to represent it inside the UI.
 * 
 * @author Arne Deutsch - Initial contribution and API
 * @since 2.14
 */
@Beta
abstract class TemplateVariable {

	@Accessors(PUBLIC_GETTER) final String label
	@Accessors(PUBLIC_GETTER) final String description
	@Accessors var boolean enabled
	@Accessors(PUBLIC_GETTER) ContainerTemplateVariable container

	package new(String label, String description, ContainerTemplateVariable container) {
		this.label = label
		this.description = description
		this.enabled = true
		this.container = container
	}

	/**
	 * Subclasses should override and create a widget representing the variable in UI. A reference to the widget should
	 * be maintained.
	 */
	def void createWidget(ParameterComposite parameterComposite, Composite parent);

	/**
	 * Subclasses should override to refresh the UI widget with data set to the variable in the meantime.
	 */
	def void refresh()

	def Control getWidget()

	def boolean isLabeled() { true }

}

abstract class ContainerTemplateVariable extends TemplateVariable {

	new(String label, String description, ContainerTemplateVariable container) {
		super(label, description, container)
	}

	override Composite getWidget()

}

class BooleanTemplateVariable extends TemplateVariable {

	@Accessors(PUBLIC_SETTER) boolean value
	Button checkbox

	new(String label, boolean defaultValue, String description, ContainerTemplateVariable container) {
		super(label, description, container)
		value = defaultValue
	}

	def getValue() { value }

	override void createWidget(ParameterComposite parameterComposite, Composite parent) {
		checkbox = new Button(parent, SWT.CHECK)
		checkbox.setText(label)
		checkbox.setSelection(getValue())
		checkbox.setToolTipText(description)
		checkbox.addSelectionListener(new SelectionAdapter() {
			override widgetSelected(SelectionEvent e) {
				setValue(checkbox.getSelection())
				parameterComposite.update()
			}
		})
	}

	override refresh() {
		if (checkbox.isEnabled() != enabled)
			checkbox.setEnabled(enabled)
		if (checkbox.getSelection() != getValue())
			checkbox.setSelection(getValue())
	}

	override isLabeled() { false }

	override getWidget() { checkbox }

	override String toString() { value.toString }

}

class StringTemplateVariable extends TemplateVariable {

	@Accessors String value
	Text text

	new(String label, String defaultValue, String description, ContainerTemplateVariable container) {
		super(label, description, container)
		value = defaultValue
	}

	override createWidget(ParameterComposite parameterComposite, Composite parent) {
		text = new Text(parent, SWT.SINGLE.bitwiseOr(SWT.BORDER))
		text.setText(getValue())
		text.setToolTipText(description)
		text.addModifyListener([
			setValue(text.getText())
			parameterComposite.update()
		])
	}

	override refresh() {
		if (text.isEnabled() != enabled)
			text.setEnabled(enabled)
		if (!text.getText().equals(getValue()))
			text.setText(getValue())
	}

	override getWidget() { text }

	override String toString() { value }

}

class StringSelectionTemplateVariable extends TemplateVariable {

	@Accessors(PUBLIC_GETTER) String[] possibleValues
	@Accessors String value
	Combo combo

	new(String label, String[] possibleValues, String description, ContainerTemplateVariable container) {
		super(label, description, container)
		this.possibleValues = possibleValues
		this.value = possibleValues.get(0)
	}

	def String[] getPossibleValues() { possibleValues }

	override createWidget(ParameterComposite parameterComposite, Composite parent) {
		combo = new Combo(parent, SWT.READ_ONLY)
		combo.setItems(getPossibleValues())
		combo.setText(getValue())
		combo.setToolTipText(description)
		combo.addSelectionListener(new SelectionAdapter() {
			override widgetSelected(SelectionEvent e) {
				setValue(combo.getText())
				parameterComposite.update()
			}
		})
	}

	override refresh() {
		if (combo.isEnabled() != enabled)
			combo.setEnabled(enabled)
		if (!combo.getText().equals(getValue()))
			combo.setText(getValue())
	}

	override getWidget() { combo }

	override String toString() { value }

}

class GroupTemplateVariable extends ContainerTemplateVariable {

	Group group

	new(String label, String description, ContainerTemplateVariable container) {
		super(label, description, container)
	}

	def String[] getPossibleValues() { possibleValues }

	override createWidget(ParameterComposite parameterComposite, Composite parent) {
		group = new Group(parent, SWT.READ_ONLY)
		group.setLayout(new GridLayout(2, false))
		group.setText(label)
		group.setToolTipText(description)
	}

	override refresh() {}

	override getWidget() { group }

	override isLabeled() { false }

	override String toString() { label }

}
