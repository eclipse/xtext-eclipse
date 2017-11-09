/*******************************************************************************
 * Copyright (c) 2017 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.wizard.template;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

/**
 * Page in the new project wizard to display a list with project templates. User get some description text and can select a template. In a
 * later page he can eventually configure the project template further.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class NewProjectWizardTemplateParameterPage extends WizardPage {

	private final AbstractProjectTemplate projectTemplate;
	private final List<Pair<ProjectVariable, Control>> variableWidgets = new ArrayList<>();

	private boolean inUserAction = true;

	public NewProjectWizardTemplateParameterPage(AbstractProjectTemplate projectTemplate) {
		super("NewProjectWizardTemplateParameterPage");
		this.projectTemplate = projectTemplate;
	}

	@Override
	public void createControl(Composite parent) {
		Composite main = new Composite(parent, SWT.NONE);
		main.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		main.setLayout(new GridLayout(2, false));
		for (ProjectVariable variable : projectTemplate.getVariables()) {
			Label label = new Label(main, SWT.NONE);
			label.setText(variable.getLabel());
			label.setToolTipText(variable.getDescription());
			label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
			if (variable instanceof StringProjectVariable) {
				StringProjectVariable stringVariable = (StringProjectVariable) variable;
				Text text = new Text(main, SWT.SINGLE | SWT.BORDER);
				variableWidgets.add(Tuples.create(stringVariable, text));
				text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				text.setText(stringVariable.getValue());
				text.setToolTipText(variable.getDescription());
				text.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						stringVariable.setValue(text.getText());
						update();
					}

				});
			} else if (variable instanceof StringSelectionProjectVariable) {
				StringSelectionProjectVariable selectionVariable = (StringSelectionProjectVariable) variable;
				Combo combo = new Combo(main, SWT.READ_ONLY);
				variableWidgets.add(Tuples.create(selectionVariable, combo));
				combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				combo.setItems(selectionVariable.getPossibleValues());
				combo.setText(selectionVariable.getValue());
				combo.setToolTipText(variable.getDescription());
				combo.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						selectionVariable.setValue(combo.getText());
						update();
					}
				});
			} else if (variable instanceof BooleanProjectVariable) {
				BooleanProjectVariable booleanVariable = (BooleanProjectVariable) variable;
				Button checkbox = new Button(main, SWT.CHECK);
				variableWidgets.add(Tuples.create(booleanVariable, checkbox));
				checkbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				checkbox.setSelection(booleanVariable.getValue());
				checkbox.setToolTipText(variable.getDescription());
				checkbox.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						booleanVariable.setValue(checkbox.getSelection());
						update();
					}
				});
			}
		}
		update();
		setControl(main);
	}

	private void update() {
		if (inUserAction) {
			inUserAction = false;
			refreshVariables();
			validate();
			inUserAction = true;
		}
	}

	protected void validate() {
		IStatus status = projectTemplate.validate();
		if (status == null || status.getSeverity() == IStatus.OK) {
			setErrorMessage(null);
			setMessage(null);
			setPageComplete(true);
		} else if (status.getSeverity() == IStatus.ERROR) {
			setErrorMessage(status.getMessage());
			setPageComplete(false);
		} else if (status.getSeverity() == IStatus.WARNING) {
			setErrorMessage(null);
			setMessage(status.getMessage(), IMessageProvider.WARNING);
			setPageComplete(true);
		} else {
			setErrorMessage(null);
			setMessage(status.getMessage(), IMessageProvider.INFORMATION);
			setPageComplete(true);
		}
	}

	private void refreshVariables() {
		projectTemplate.updateVariables();
		for (Pair<ProjectVariable, Control> pair : variableWidgets) {
			ProjectVariable variable = pair.getFirst();
			Widget widget = pair.getSecond();
			if (variable instanceof StringProjectVariable) {
				StringProjectVariable stringVariable = (StringProjectVariable) variable;
				Text text = (Text) widget;
				if (text.isEnabled() != stringVariable.isEnabled())
					text.setEnabled(stringVariable.isEnabled());
				if (!text.getText().equals(stringVariable.getValue()))
					text.setText(stringVariable.getValue());
			} else if (variable instanceof StringSelectionProjectVariable) {
				StringSelectionProjectVariable selectionVariable = (StringSelectionProjectVariable) variable;
				Combo combo = (Combo) widget;
				if (combo.isEnabled() != selectionVariable.isEnabled())
					combo.setEnabled(selectionVariable.isEnabled());
				if (!combo.getText().equals(selectionVariable.getValue()))
					combo.setText(selectionVariable.getValue());
			} else if (variable instanceof BooleanProjectVariable) {
				BooleanProjectVariable booleanVariable = (BooleanProjectVariable) variable;
				Button checkbox = (Button) widget;
				if (checkbox.isEnabled() != booleanVariable.isEnabled())
					checkbox.setEnabled(booleanVariable.isEnabled());
				if (checkbox.getSelection() != booleanVariable.getValue())
					checkbox.setSelection(booleanVariable.getValue());
			}
		}
	}

}
