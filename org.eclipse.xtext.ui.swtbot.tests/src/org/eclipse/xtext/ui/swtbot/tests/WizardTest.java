/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.tests;

import static org.eclipse.xtext.ui.swtbot.testing.api.EclipseAPI.*;
import static org.junit.Assert.*;

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.xtext.ui.swtbot.testing.AbstractSwtBotTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the formatter preference page.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class WizardTest extends AbstractSwtBotTest {

	@Before
	public void setUp() {
		mainMenu().openJavaPerspective();
		mainMenu().openJunitView();
		mainMenu().openConsoleView();
		closeAllShells();
		deleteAllProjects();
	}

	@After
	public void tearDown() {
		closeAllShells();
		deleteAllProjects();
	}

	@Test
	public void simpleXtextProject() throws Exception {
		// create xtext project
		mainMenu().openNewProjectWizard().selectXtextProject().next().toggleEclipsePlugin().toggleGenericIdeSupport().toggleTestingSupport()
				.finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist("org.xtext.example.mydsl"));

		// generate the language
		packageExplorer().runMWE2("org.xtext.example.mydsl", "src", "org.xtext.example.mydsl", "GenerateMyDsl.mwe2");
		consoleView().waitForMWE2ToFinish();
		waitForBuild();

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());

		// replace grammar with one that uses Xbase
		packageExplorer().openXtextFile("org.xtext.example.mydsl", "src", "org.xtext.example.mydsl", "MyDsl.xtext")
				.relaceContent(helloWorldGrammerWithXbase()).save();

		// generate the language again
		packageExplorer().runMWE2("org.xtext.example.mydsl", "src", "org.xtext.example.mydsl", "GenerateMyDsl.mwe2");
		consoleView().waitForMWE2ToFinish();
		waitForBuild();

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());
	}

	@Test
	public void simpleXtextProjectWithMaven() throws Exception {
		// create xtext project
		mainMenu().openNewProjectWizard().selectXtextProject().next().toggleEclipsePlugin().toggleGenericIdeSupport().toggleTestingSupport()
				.setMavenBuildType().finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist("org.xtext.example.mydsl"));
		assertTrue(packageExplorer().projectExist("org.xtext.example.mydsl.parent"));

		// run the maven build and wait for successful termination
		packageExplorer().runMavenInstall("org.xtext.example.mydsl.parent", "pom.xml");
		consoleView().waitForMavenToFinishWithSuccess();
		waitForBuild();

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());

		// replace grammar with one that uses Xbase
		packageExplorer().openXtextFile("org.xtext.example.mydsl", "src", "org.xtext.example.mydsl", "MyDsl.xtext")
				.relaceContent(helloWorldGrammerWithXbase()).save();

		// generate the language again
		packageExplorer().runMavenInstall("org.xtext.example.mydsl.parent", "pom.xml");
		consoleView().waitForMavenToFinishWithSuccess();
		waitForBuild();

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());
	}

	private String helloWorldGrammerWithXbase() {
		return "grammar org.xtext.example.mydsl.MyDsl with org.eclipse.xtext.xbase.Xbase\n"
				+ "generate myDsl \"http://www.xtext.org/example/mydsl/MyDsl\"\n" + "Model:\n" + "    greetings+=Greeting*;\n" + "    \n"
				+ "Greeting:\n" + "    'Hello' name=ID '=' value=XBlockExpression;";
	}

}
