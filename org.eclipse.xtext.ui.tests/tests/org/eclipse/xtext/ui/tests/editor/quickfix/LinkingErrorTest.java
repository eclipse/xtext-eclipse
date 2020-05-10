/*******************************************************************************
 * Copyright (c) 2010, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.quickfix;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.tests.quickfix.quickfixCrossref.Element;
import org.eclipse.xtext.ui.tests.quickfix.quickfixCrossref.Main;
import org.eclipse.xtext.ui.tests.quickfix.ui.quickfix.QuickfixCrossrefTestLanguageQuickfixProvider;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.Issue;
import org.junit.Test;

/**
 * @author Heiko Behrens - Initial contribution and API
 * @author Jan Koehnlein
 */
public class LinkingErrorTest extends AbstractQuickfixTest {

	private static final String PROJECT_NAME = "quickfixtest";
	private static final String MODEL_FILE = "test";
	private static final String MODEL_WITH_LINKING_ERROR = "Foo { ref Bor }\n" + "Bar { }\n Bar1{} Bar2{} Bar3{} Bar4{} Bar5{}";
	private static final String MODEL_WITH_LINKING_ERROR_361509 = "^ref { ref raf }\n";
	
	@Test public void testQuickfixTurnaround() throws Exception {
		IFile dslFile = dslFile(PROJECT_NAME, MODEL_FILE, MODEL_WITH_LINKING_ERROR);
		XtextEditor xtextEditor = openEditor(dslFile);
		IXtextDocument document = xtextEditor.getDocument();

		List<Issue> issues = getIssues(document);
		assertFalse(issues.isEmpty());
		Issue issue = issues.get(0);
		assertNotNull(issue);
		IssueResolutionProvider quickfixProvider = getInjector().getInstance(IssueResolutionProvider.class);
		List<IssueResolution> resolutions = quickfixProvider.getResolutions(issue);

		assertEquals(1, resolutions.size());
		IssueResolution resolution = resolutions.get(0);
		assertEquals("Change to 'Bar'", resolution.getLabel());
		resolution.apply();
		issues = getIssues(document);
		assertTrue(issues.isEmpty());
	}
	
	@Test public void testBug361509() throws Exception {
		IFile dslFile = dslFile(PROJECT_NAME, MODEL_FILE, MODEL_WITH_LINKING_ERROR_361509);
		XtextEditor xtextEditor = openEditor(dslFile);
		IXtextDocument document = xtextEditor.getDocument();

		List<Issue> issues = getIssues(document);
		assertFalse(issues.isEmpty());
		Issue issue = issues.get(0);
		assertNotNull(issue);
		IssueResolutionProvider quickfixProvider = getInjector().getInstance(IssueResolutionProvider.class);
		List<IssueResolution> resolutions = quickfixProvider.getResolutions(issue);

		assertEquals(1, resolutions.size());
		IssueResolution resolution = resolutions.get(0);
		assertEquals("Change to 'ref'", resolution.getLabel());
		resolution.apply();
		issues = getIssues(document);
		assertTrue(issues.isEmpty());
		assertEquals(MODEL_WITH_LINKING_ERROR_361509.replace("raf", "^ref"), document.get());
	}

	@Test public void testSemanticIssueResolution() throws Exception {
		IFile dslFile = dslFile(PROJECT_NAME, MODEL_FILE, MODEL_WITH_LINKING_ERROR);
		XtextEditor xtextEditor = openEditor(dslFile);
		URI uriToProblem = xtextEditor.getDocument().readOnly(new IUnitOfWork<URI, XtextResource>() {
			@Override
			public URI exec(XtextResource state) throws Exception {
				Main main = (Main) state.getContents().get(0);
				Element element = main.getElements().get(1);
				return EcoreUtil.getURI(element);
			}
		});
		Issue.IssueImpl issue = new Issue.IssueImpl();
		issue.setUriToProblem(uriToProblem);
		issue.setCode(QuickfixCrossrefTestLanguageQuickfixProvider.SEMANTIC_FIX_ID);

		IssueResolutionProvider quickfixProvider = getInjector().getInstance(IssueResolutionProvider.class);
		List<IssueResolution> resolutions = quickfixProvider.getResolutions(issue);
		assertEquals(1, resolutions.size());
		IssueResolution issueResolution = resolutions.get(0);
		issueResolution.apply();
		xtextEditor.doSave(null);
		List<Issue> issues = getIssues(xtextEditor.getDocument());
		assertTrue(issues.isEmpty());
	}
}
