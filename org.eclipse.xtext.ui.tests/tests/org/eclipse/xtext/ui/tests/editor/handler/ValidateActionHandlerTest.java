/*******************************************************************************
 * Copyright (c) 2010, 2017 Michael Clay and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.handler;

import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.eclipse.xtext.ui.testing.AbstractEditorTest;
import org.junit.Test;

/**
 * @author Michael Clay - Initial contribution and API
 */
public class ValidateActionHandlerTest extends AbstractEditorTest {

	@Override
	protected String getEditorId() {
		return "org.eclipse.xtext.ui.tests.TestLanguage";
	}

	@Test public void testExpensiveMarkerCreation() throws Exception {
		IFile iFile = createFile("foo/bar.testlanguage", "stuff foo");
		XtextEditor xtextEditor = openEditor(iFile);
		IHandlerService handlerService = xtextEditor.getSite().getService(IHandlerService.class);
		handlerService.executeCommand("org.eclipse.xtext.ui.tests.TestLanguage.validate", null);
		closeEditors();
		Job[] find = Job.getJobManager().find(ValidationJob.XTEXT_VALIDATION_FAMILY);
		for (Job job : find) {
			job.join();
		}
		IResource file = xtextEditor.getResource();
		IMarker[] markers = file.findMarkers(MarkerTypes.EXPENSIVE_VALIDATION, true, IResource.DEPTH_ZERO);
		assertEquals(1, markers.length);

	}
}
