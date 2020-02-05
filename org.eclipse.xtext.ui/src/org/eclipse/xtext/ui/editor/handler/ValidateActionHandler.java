/*******************************************************************************
 * Copyright (c) 2008, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.ui.editor.validation.AnnotationIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.IValidationIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.MarkerCreator;
import org.eclipse.xtext.ui.editor.validation.MarkerIssueProcessor;
import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.eclipse.xtext.ui.validation.MarkerTypeProvider;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;

import com.google.inject.Inject;

/**
 * @author Dennis H�bner - Initial contribution and API
 * @author Michael Clay
 * @author Peter Friese
 */
public class ValidateActionHandler extends AbstractHandler {
	@Inject
	private IResourceValidator resourceValidator;
	@Inject 
	private MarkerCreator markerCreator;
	@Inject
	private MarkerTypeProvider markerTypeProvider;
	@Inject
	private IssueResolutionProvider issueResolutionProvider;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor(event);
		if (xtextEditor != null) {
			IValidationIssueProcessor issueProcessor;
			IXtextDocument xtextDocument = xtextEditor.getDocument();
			IResource resource = xtextEditor.getResource();
			if(resource != null)
				issueProcessor = new MarkerIssueProcessor(resource, xtextEditor.getInternalSourceViewer().getAnnotationModel(), markerCreator, markerTypeProvider);
			else
				issueProcessor = new AnnotationIssueProcessor(xtextDocument, xtextEditor.getInternalSourceViewer().getAnnotationModel(), issueResolutionProvider);
			ValidationJob validationJob = new ValidationJob(resourceValidator, xtextDocument, issueProcessor,
					CheckMode.ALL);
			validationJob.schedule();
		}
		return this;
	}
}
