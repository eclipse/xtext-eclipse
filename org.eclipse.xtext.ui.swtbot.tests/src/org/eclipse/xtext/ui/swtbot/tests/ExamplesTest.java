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
public class ExamplesTest extends AbstractSwtBotTest {

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
	public void xbaseTutorial() throws Exception {
		// create example projects
		mainMenu().openNewProjectWizard().selectXtextExample("Xbase Tutorial").finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist("org.eclipse.xtext.example.xbase.tutorial"));

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());
	}

	@Test
	public void domainModelExample() throws Exception {
		standartXtextExample("Xtext Domain-Model Example", "org.eclipse.xtext.example.domainmodel", "GenerateDomainmodel.mwe2");

		// run the maven build and wait for successful termination
		packageExplorer().runMavenInstall("org.eclipse.xtext.example.domainmodel.releng", "pom.xml");
		consoleView().waitForMavenToFinishWithSuccess();
	}

	@Test
	public void xtextHomeAutomationExample() throws Exception {
		standartXtextExample("Xtext Home Automation Example", "org.eclipse.xtext.example.homeautomation", "GenerateRuleEngine.mwe2");
	}

	@Test
	public void xtextSimpleArithmeticsExample() throws Exception {
		standartXtextExample("Xtext Simple Arithmetics Example", "org.eclipse.xtext.example.arithmetics", "GenerateArithmetics.mwe2");
	}

	@Test
	public void xtextStateMachineExample() throws Exception {
		standartXtextExample("Xtext State-Machine Example", "org.eclipse.xtext.example.fowlerdsl", "GenerateStatemachine.mwe2");
	}

	@Test
	public void xtendActiveAnnotationExamples() throws Exception {
		// create example projects
		mainMenu().openNewProjectWizard().selectXtendExample("Xtend Active Annotation Examples").finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist("xtend-annotation-examples"));
		assertTrue(packageExplorer().projectExist("xtend-annotation-examples-client"));

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());

		// run all unit tests and check there are no test failures
		packageExplorer().runJUnitTests("xtend-annotation-examples", "xtend-gen");
		junitView().waitForTestrunToFinish();
		assertTrue(junitView().isTestrunErrorFree());

	}

	@Test
	public void xtendIntroductoryExamples() throws Exception {
		standartXtendExample("Xtend Introductory Examples", "xtend-examples");
	}

	@Test
	public void xtendSolutionsForEuler() throws Exception {
		standartXtendExample("Xtend Solutions for Euler", "xtend-euler");
	}

	private void standartXtextExample(String exampleLabel, String projectName, String mweFileName) throws Exception {
		// create example projects
		mainMenu().openNewProjectWizard().selectXtextExample(exampleLabel).finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist(projectName));
		assertTrue(packageExplorer().projectExist(projectName + ".ide"));
		assertTrue(packageExplorer().projectExist(projectName + ".tests"));
		assertTrue(packageExplorer().projectExist(projectName + ".ui"));
		assertTrue(packageExplorer().projectExist(projectName + ".tests"));

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());
		// work around PDE bug (missing log4j.jar)
		touchFile(projectName + "/META-INF/MANIFEST.MF");
		waitForBuild();

		// run all unit tests and check there are no test failures
		packageExplorer().runJUnitTests(projectName + ".tests", "xtend-gen");
		junitView().waitForTestrunToFinish();
		assertTrue(junitView().isTestrunErrorFree());

		// run all plugin tests and check there are no test failures
		packageExplorer().runJUnitPluginTests(projectName + ".ui.tests", "xtend-gen");
		junitView().waitForTestrunToFinish();
		assertTrue(junitView().isTestrunErrorFree());

		// check that after a regeneration the projects source folders have not changed
		long oldBytes = calculateFolderSize(projectName + "/src");
		oldBytes += calculateFolderSize(projectName + ".ide/src");
		oldBytes += calculateFolderSize(projectName + ".tests/src");
		oldBytes += calculateFolderSize(projectName + ".ui/src");
		oldBytes += calculateFolderSize(projectName + ".ui.tests/src");
		packageExplorer().runMWE2(projectName, "src", projectName, mweFileName);
		consoleView().waitForMWE2ToFinish();
		long newBytes = calculateFolderSize(projectName + "/src");
		newBytes += calculateFolderSize(projectName + ".ide/src");
		newBytes += calculateFolderSize(projectName + ".tests/src");
		newBytes += calculateFolderSize(projectName + ".ui/src");
		newBytes += calculateFolderSize(projectName + ".ui.tests/src");
		assertEquals(oldBytes, newBytes);
	}

	private void standartXtendExample(String exampleLabel, String projectName) throws Exception {
		// create example projects
		mainMenu().openNewProjectWizard().selectXtendExample(exampleLabel).finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist(projectName));

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());
	}

}
