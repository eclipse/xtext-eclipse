package org.eclipse.xtext.ui.wizard.template;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
/*******************************************************************************
 * Copyright (c) 2017 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormText;

/**
 * Page of the new project wizard to present a list of templates to the user. The user can select a template and press finish. If the
 * template is configurable the variables can be configured in the following page, the NewProjectWizardTemplateParameterPage.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class NewProjectWizardTemplateSelectionPage extends WizardPage {

	private IProjectTemplateProvider templateProvider;
	private ProjectTemplateLabelProvider labelProvider;
	private AbstractProjectTemplate selectedTemplate;

	public NewProjectWizardTemplateSelectionPage(String pageName, IProjectTemplateProvider templateProvider,
			ProjectTemplateLabelProvider labelProvider) {
		super(pageName);
		this.templateProvider = templateProvider;
		this.labelProvider = labelProvider;
	}

	@Override
	public void createControl(Composite parent) {
		Composite main = new Composite(parent, SWT.NONE);
		main.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		main.setLayout(new GridLayout(1, false));

		Label availableTemplatesLabel = new Label(main, SWT.NONE);
		availableTemplatesLabel.setText("Available Templates:");
		availableTemplatesLabel.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false));

		SashForm sash = new SashForm(main, SWT.HORIZONTAL);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.widthHint = 400;
		sash.setLayoutData(data);

		TableViewer templateTable = new TableViewer(sash, SWT.BORDER);
		templateTable.setContentProvider(new ArrayContentProvider());
		templateTable.setLabelProvider(labelProvider);
		templateTable.setInput(templateProvider.getProjectTemplates());

		FormText text = new FormText(sash, SWT.BORDER);
		text.setText("", false, false);
		text.setBackground(templateTable.getTable().getBackground());

		templateTable.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					Object element = structuredSelection.getFirstElement();
					if (element instanceof AbstractProjectTemplate) {
						selectedTemplate = (AbstractProjectTemplate) element;
						setPageComplete(true);
						String content = "<form>" + selectedTemplate.getDescription() + "</form>";
						try {
							text.setText(content, true, true);
						} catch (Exception e) {
							text.setText(e.getMessage(), false, false);
						}
					} else {
						selectedTemplate = null;
						text.setText("", false, false);
						setPageComplete(false);
					}
				} else {
					selectedTemplate = null;
					text.setText("", false, false);
					setPageComplete(false);
				}
			}
		});

		templateTable.setSelection(new StructuredSelection(templateTable.getElementAt(0)));

		setControl(main);
	}

	public AbstractProjectTemplate getSelectedTemplate() {
		return selectedTemplate;
	}

}
