/*******************************************************************************
 * Copyright (c) 2012, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.model;

import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Lists.*;

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TypedPosition;
import org.eclipse.swt.SWT;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.testlanguages.ui.internal.TestlanguagesActivator;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.testing.AbstractAutoEditTest;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil;
import org.eclipse.xtext.ui.util.JREContainerProvider;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.inject.Injector;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class Bug369087Test extends AbstractAutoEditTest {

	@Override
	protected String getFileExtension() {
		return "xtext";
	}

	@Override
	protected String getEditorId() {
		return "org.eclipse.xtext.Xtext";
	}
	
	protected static IProject createPluginProject(String name) throws CoreException {
		Injector injector = TestlanguagesActivator.getInstance().getInjector(TestlanguagesActivator.ORG_ECLIPSE_XTEXT_TESTLANGUAGES_XTEXTGRAMMAR_XTEXTGRAMMARTESTLANGUAGE);
		PluginProjectFactory projectFactory = injector.getInstance(PluginProjectFactory.class);
		projectFactory.setBreeToUse(JREContainerProvider.PREFERRED_BREE);
		projectFactory.setProjectName(name);
		projectFactory.addFolders(Collections.singletonList("src"));
		projectFactory.addBuilderIds(XtextProjectHelper.BUILDER_ID);
		projectFactory.addProjectNatures(XtextProjectHelper.NATURE_ID);
		IProject result = projectFactory.createProject(new NullProgressMonitor(), null);
		JavaProjectSetupUtil.setUnixLineEndings(result);
		return result;
	}

	@Override
	public void setUp() throws Exception {
		createPluginProject("foo");
	}

	@Test 
	public void testPositionTypesDontChange() throws Exception {
		String model = 
				"grammar org.xtext.example.mydsl.MyDsl with org.eclipse.xtext.xbase.Xbase\n" +
				"\n" +
				"generate myDsl \"http://www.xtext.org/example/mydsl/MyDsl\"\n" +
				"\n" +
				"Model:\n" +
				"    'package' name=ID '{'\n" +
				"'}';";
		XtextEditor editor = openEditor(model);
		IXtextDocument xtextDocument = editor.getDocument();
		String category = getContentTypeCategory(xtextDocument).toString();
		Position[] positions = xtextDocument.getPositions(category);
		for(int i=1; i<xtextDocument.getLength(); ++i) {
			if(!Character.isLetter(xtextDocument.getChar(i-1)) || !Character.isLetter(xtextDocument.getChar(i))) {
				editor.getInternalSourceViewer().setSelectedRange(i, 0);
				editor.getInternalSourceViewer().getTextWidget().setFocus();
				pressKey(editor, ' ');
				reconcile(xtextDocument);
				assertSamePositionTypes(positions, xtextDocument.getPositions(category));
				pressKey(editor, SWT.BS);
			}
		}
	}

	protected Object getContentTypeCategory(IXtextDocument xtextDocument) {
		return find(newArrayList(xtextDocument.getPositionCategories()), new Predicate<Object>() {
			@Override
			public boolean apply(Object input) {
				return input.toString().startsWith("__content_types_category");
			}
		});
	}

	protected void reconcile(IXtextDocument xtextDocument) {
		xtextDocument.readOnly(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource state) throws Exception {
			}
		});
	}
	
	protected void assertSamePositionTypes(Position[] expected, Position[] actual) {
		assertEquals(expected.length, actual.length);
		for(int i=0; i<expected.length; ++i) 
			assertEquals(((TypedPosition)expected[i]).getType(), ((TypedPosition)actual[i]).getType());
	}
	
}
