/*******************************************************************************
 * Copyright (c) 2008, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor;

import static org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.testing.AbstractEditorTest;
import org.eclipse.xtext.ui.tests.internal.TestsActivator;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.junit.Test;

/**
 * A test for the Xtext editor.
 * 
 * @author Dennis H�bner - Initial contribution and API
 * @author Peter Friese
 */
public class SimpleEditorTest extends AbstractEditorTest {

	@Override
	public void setUp() throws Exception {
		super.setUp();

		// listen to CoreLog use RuntimeLog to hear more
		TestsActivator.getInstance().getLog().addLogListener(new ILogListener() {
			@Override
			public void logging(IStatus status, String plugin) {
				if (IStatus.ERROR == status.getSeverity()) {
					fail(status.getMessage());
				}
			}
		});

	}

	@Override
	public void tearDown() throws Exception {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
		super.tearDown();
	}

	@Test public void testOpenBlankFile() throws Exception {
		IFile file = createFile("foo/x.testlanguage", "");
		XtextEditor openedEditor = openEditor(file);
		assertNotNull(openedEditor);
		IXtextDocument document = openedEditor.getDocument();
		document.readOnly(new IUnitOfWork.Void<XtextResource>() {

			@Override
			public void process(XtextResource resource) throws Exception {
				assertNotNull(resource);
				assertTrue(resource.getContents().isEmpty());
			}
		});
		openedEditor.close(false);
	}
	
	@Test public void testOpenFileWithWrongFileExtension() throws Exception {
		IFile file = createFile("foo/y.unkownextension", "/* multi line */\n" + "stuff foo\n" + "stuff bar\n" + "// end");
		XtextEditor openEditor = openEditor(file);
		assertNotNull(openEditor);
	}

	@Test public void testOpenFileReadModifyRead() throws Exception {
		IFile file = createFile("foo/y.testlanguage", "/* multi line */\n" + "stuff foo\n" + "stuff bar\n" + "// end");
		XtextEditor openEditor = openEditor(file);
		assertNotNull(openEditor);
		IXtextDocument document = openEditor.getDocument();

		Display.getDefault().readAndDispatch();
		document.readOnly(new IUnitOfWork.Void<XtextResource>() {

			@Override
			public void process(XtextResource resource) throws Exception {
				assertNotNull(resource);
				EList<EObject> contents = resource.getContents();
				EObject object = contents.get(0);
				assertEquals(2, object.eContents().size());
			}
		});
		document.replace(23, 3, "honolulu");
		document.readOnly(new IUnitOfWork.Void<XtextResource>() {

			@Override
			public void process(XtextResource resource) throws Exception {
				assertNotNull(resource);
				EList<EObject> contents = resource.getContents();
				EObject object = contents.get(0);
				assertEquals(2, object.eContents().size());
				EObject object2 = object.eContents().get(0);
				assertEquals("honolulu", object2.eGet(object2.eClass().getEStructuralFeature("name")));
			}
		});
		openEditor.doSave(null);
		openEditor.close(true);
	}

	@Test public void testOpenFileReadModifyReadSecond() throws Exception {
		IFile file = createFile("foo/z.testlanguage", "/* multi line */\n" + "stuff foo\n" + "stuff bar\n" + "// end");
		XtextEditor openEditor = openEditor(file);
		assertNotNull(openEditor);
		IXtextDocument document = openEditor.getDocument();

		Display.getDefault().readAndDispatch();
		document.readOnly(new IUnitOfWork.Void<XtextResource>() {

			@Override
			public void process(XtextResource resource) throws Exception {
				assertNotNull(resource);
				EList<EObject> contents = resource.getContents();
				EObject object = contents.get(0);
				assertEquals(2, object.eContents().size());
			}
		});
		document.replace(36, 0, "a");
		document.readOnly(new IUnitOfWork.Void<XtextResource>() {

			@Override
			public void process(XtextResource resource) throws Exception {
				assertNotNull(resource);
				EList<EObject> contents = resource.getContents();

				EObject object = contents.get(0);
				assertEquals(2, object.eContents().size());

				EObject object2 = object.eContents().get(0);
				assertEquals("foo", object2.eGet(object2.eClass().getEStructuralFeature("name")));

				EObject object3 = object.eContents().get(1);
				Object name = object3.eGet(object3.eClass().getEStructuralFeature("name"));
				assertEquals("bara", name);

			}
		});
		openEditor.doSave(null);
		openEditor.close(true);
	}

	@Override
	protected String getEditorId() {
		return "org.eclipse.xtext.ui.tests.TestLanguage";
	}

}
