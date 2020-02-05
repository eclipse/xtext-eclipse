/*******************************************************************************
 * Copyright (c) 2011, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.autoedit;

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.testlanguages.ui.internal.TestlanguagesActivator;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.testing.AbstractCStyleLanguageAutoEditTest;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil;
import org.eclipse.xtext.ui.util.JREContainerProvider;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.junit.Test;

import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug330096Test extends AbstractCStyleLanguageAutoEditTest {

	private static final String TESTPROJECT_NAME = "foo";
	
	@Override
	protected String getEditorId() {
		return "org.eclipse.xtext.testlanguages.backtracking.SimpleBeeLangTestLanguage";
	}

	@Override
	protected String getFileExtension() {
		return "simplebeelangtestlanguage";
	}
	
	@Test public void testNoException() throws Exception {
		XtextEditor editor = openEditor("unit |a ;");
		selectText(editor, 0, 1);
		pressKey(editor, '"');
		assertState("unit \"|\" ;", editor);
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		IProject project = IResourcesSetupUtil.root().getProject(TESTPROJECT_NAME);
		if (!project.exists()) {
			createPluginProject(TESTPROJECT_NAME);
		}
	}
	
	protected IProject createPluginProject(String name) throws CoreException {
		Injector injector = TestlanguagesActivator.getInstance().getInjector("org.eclipse.xtext.testlanguages.backtracking.SimpleBeeLangTestLanguage");
		PluginProjectFactory projectFactory = injector.getInstance(PluginProjectFactory.class);
		projectFactory.setProjectName(name);
		projectFactory.setBreeToUse(JREContainerProvider.PREFERRED_BREE);
		projectFactory.addFolders(Collections.singletonList("src"));
		projectFactory.addBuilderIds(
			JavaCore.BUILDER_ID, 
			"org.eclipse.pde.ManifestBuilder",
			"org.eclipse.pde.SchemaBuilder",
			XtextProjectHelper.BUILDER_ID);
		projectFactory.addProjectNatures(JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", XtextProjectHelper.NATURE_ID);
		IProject result = projectFactory.createProject(new NullProgressMonitor(), null);
		JavaProjectSetupUtil.setUnixLineEndings(result);
		return result;
	}
}
