/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.quickfix;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.testing.AbstractWorkbenchTest;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil;
import org.eclipse.xtext.ui.tests.internal.TestsActivator;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Injector;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public abstract class AbstractQuickfixTest extends AbstractWorkbenchTest {

	protected XtextEditor newXtextEditor(String projectName, String modelFile, String model) throws CoreException, PartInitException {
		IJavaProject javaProject = JavaProjectSetupUtil.createJavaProject(projectName);
		IFile file = createFile(modelFile, model, javaProject.getProject());
		XtextEditor xtextEditor = (XtextEditor) IDE.openEditor(getActivePage(), file);
		return xtextEditor;
	}

	protected IFile createFile(String modelFile, String model, IProject project) throws CoreException {
		IFile file = project.getFile(modelFile);
		if (file.exists()) {
			file.delete(true, null);
		}
		file.create(new StringInputStream(model), true, null);
		file.refreshLocal(IResource.DEPTH_ONE, null);
		return file;
	}

	protected Injector getInjector() {
		return TestsActivator.getInstance().getInjector("org.eclipse.xtext.ui.tests.quickfix.QuickfixCrossrefTestLanguage");
	}

	protected List<Issue> getIssues(IXtextDocument document) {
		return document.readOnly(new IUnitOfWork<List<Issue>, XtextResource>() {
			@Override
			public List<Issue> exec(XtextResource state) throws Exception {
				return state.getResourceServiceProvider().getResourceValidator().validate(state, CheckMode.ALL, null);
			}
		});
	}
}
