/*******************************************************************************
 * Copyright (c) 2010, 2017 Christoph Kulla
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Christoph Kulla - Initial API and implementation
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.hover;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.Region;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.undo.CreateMarkersOperation;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.RepeatedTest;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.hover.ProblemAnnotationHover;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.validation.MarkerCreator;
import org.eclipse.xtext.ui.testing.AbstractEditorTest;
import org.eclipse.xtext.ui.testing.util.TestedWorkspaceWithJDT;
import org.eclipse.xtext.ui.tests.ui.tests.TestLanguageUiInjectorProvider;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Christoph Kulla - Initial contribution and API
 * @author Holger Schill
 */
@RepeatedTest(times=500)
@RunWith(XtextRunner.class)
@InjectWith(TestLanguageUiInjectorProvider.class)
public class ProblemHoverTest extends AbstractEditorTest {

	@Rule
	@Inject
	public TestedWorkspaceWithJDT testedWorkspace; 
	
	private static final String CUSTOM_MARKER_ID = "org.eclipse.xtext.ui.tests.customMarker";

	private static final String CUSTOM_MARKER_TEST_MESSAGE = "CustomMarkerTest";

	protected XtextEditor editor;
	
	protected IXtextDocument document;

	protected String modelAsText = "stuff mystuff\nstuff yourstuff refs _mystuff stuff hisstuff refs _yourstuff// Comment";
	
	@Inject
	private Provider<ProblemAnnotationHover> hoverProvider;
	
	@Inject
	private MarkerCreator markerCreator;
	
	private ProblemAnnotationHover hover;

	@Before
	public void prepareTest() throws Exception {
		IFile file = testedWorkspace.createFile("test/test.testlanguage", modelAsText);
		editor = openEditor(file);
		document = editor.getDocument();
		hover = hoverProvider.get();
		hover.setSourceViewer(editor.getInternalSourceViewer());
		// file is in a project without Xtext nature - we need to create the markers manually
		List<Issue> issues = document.readOnly(new IUnitOfWork<List<Issue>, XtextResource>() {
			@Override
			public List<Issue> exec(XtextResource state) throws Exception {
				return state.getResourceServiceProvider().getResourceValidator().validate(state, CheckMode.ALL, null);
			}	
		});
		for (Issue issue : issues) {
			markerCreator.createMarker(issue, file, MarkerTypes.forCheckType(issue.getType()));
		}
	}
	
	@Override
	public void setUp() throws Exception {
		// disabled since the testedWorkspace takes care of it
	}
	
	@Override
	public void tearDown() throws Exception {
		closeEditors();
		// only close the editor, since the testedWorkspace takes care of the other cleanup
	}

	@SuppressWarnings("deprecation")
	@Test public void testAnnotations () {	
		assertNull(hover.getHoverInfo(editor.getInternalSourceViewer(), new Region(34, 1)));
		assertEquals("Couldn't resolve reference to Stuff '_mystuff'.", hover.getHoverInfo(editor.getInternalSourceViewer(), new Region(35, 7)));
		
		//The order of annotations in the annotation model is not stable
		final String hoverInfo = hover.getHoverInfo(editor.getInternalSourceViewer(), 1);
		String expected1 = "Multiple markers at this line\n";
		String expected2 = "- Couldn't resolve reference to Stuff '_mystuff'.";
		String expected3 = "- Couldn't resolve reference to Stuff '_yourstuff'.";
		assertTrue(hoverInfo.startsWith(expected1));
		assertTrue(hoverInfo.contains(expected2));
		assertTrue(hoverInfo.contains(expected3));
	}
	
	@Test public void testBug357516_warning() throws Exception {
		IResource resource = editor.getResource();
		createCustomMarkerOnResource(resource, IMarker.SEVERITY_WARNING);
		String hoverInfo = hover.getHoverInfo(editor.getInternalSourceViewer(), 0);
		assertNotNull(hoverInfo);
		assertTrue(hoverInfo, hoverInfo.contains(CUSTOM_MARKER_TEST_MESSAGE));
	}
	
	@Test public void testBug357516_error() throws Exception {
		IResource resource = editor.getResource();
		createCustomMarkerOnResource(resource, IMarker.SEVERITY_ERROR);
		String hoverInfo = hover.getHoverInfo(editor.getInternalSourceViewer(), 0);
		assertNotNull(hoverInfo);
		assertTrue(hoverInfo.contains(CUSTOM_MARKER_TEST_MESSAGE));
	}
	
	@Test public void testBug357516_info() throws Exception {
		IResource resource = editor.getResource();
		createCustomMarkerOnResource(resource, IMarker.SEVERITY_INFO);
		String hoverInfo = hover.getHoverInfo(editor.getInternalSourceViewer(), 0);
		assertNotNull(hoverInfo);
		assertEquals(CUSTOM_MARKER_TEST_MESSAGE, hoverInfo);
	}
	
	@Test public void testBug357516_bookmark() throws Exception {
		IResource resource = editor.getResource();
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(IMarker.MESSAGE, CUSTOM_MARKER_TEST_MESSAGE);
		attributes.put(IMarker.LINE_NUMBER, 1);
		attributes.put(IMarker.LOCATION, resource.getFullPath().toPortableString());
		IUndoableOperation operation= new CreateMarkersOperation(IMarker.BOOKMARK, attributes, resource, CUSTOM_MARKER_TEST_MESSAGE);
		IOperationHistory operationHistory= PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		try {
			operationHistory.execute(operation, null, null);
		} catch (ExecutionException x) {
			fail(x.getMessage());
		}
		String hoverInfo = hover.getHoverInfo(editor.getInternalSourceViewer(), 0);
		assertNotNull(hoverInfo);
		assertTrue(hoverInfo.contains(CUSTOM_MARKER_TEST_MESSAGE));
	}
	
	private void createCustomMarkerOnResource(IResource resource, int severenity) throws CoreException{
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(IMarker.MESSAGE, CUSTOM_MARKER_TEST_MESSAGE);
		attributes.put(IMarker.LINE_NUMBER, 1);
		attributes.put(IMarker.LOCATION, resource.getFullPath().toPortableString());
		attributes.put(IMarker.SEVERITY, severenity); 
		MarkerUtilities.createMarker(resource, attributes, CUSTOM_MARKER_ID);
	}
	
}
