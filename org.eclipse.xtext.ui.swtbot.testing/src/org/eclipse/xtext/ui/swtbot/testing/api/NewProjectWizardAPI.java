/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.*;

import org.eclipse.xtext.ui.swtbot.testing.lowlevel.XtextSWTBotShell;

public class NewProjectWizardAPI {

	private final XtextSWTBotShell shell;

	NewProjectWizardAPI(XtextSWTBotShell shell) {
		this.shell = shell;
	}

	public NewXtextProjectWizardMainPageAPI selectXtextProject() {
		shell.bot().tree().expandNode("Xtext").select("Xtext Project");
		shell.bot().button("Next >").click();
		return new NewXtextProjectWizardMainPageAPI(shell);
	}

	public NewXtextExampleSecondPageWizardAPI selectXtextExample(String exampleLabel) {
		shell.bot().tree().expandNode("Examples").select(); // Workaround ... a bug leads to collapse of example on first try, also reproducible with vanilla eclipse
		shell.bot().tree().expandNode("Examples", "Xtext Examples", exampleLabel).select();
		shell.bot().button("Next >").click();
		return new NewXtextExampleSecondPageWizardAPI(shell);
	}

	public NewXtextExampleSecondPageWizardAPI selectXtendExample(String exampleLabel) {
		shell.bot().tree().expandNode("Examples").select(); // Workaround ... a bug leads to collapse of example on first try, also reproducible with vanilla eclipse
		shell.bot().tree().expandNode("Examples", "Xtend Examples", exampleLabel).select();
		shell.bot().button("Next >").click();
		return new NewXtextExampleSecondPageWizardAPI(shell);
	}

	public static class NewXtextExampleSecondPageWizardAPI {

		private final XtextSWTBotShell shell;

		NewXtextExampleSecondPageWizardAPI(XtextSWTBotShell shell) {
			this.shell = shell;
		}

		public void finish() {
			shell.bot().button("Finish").click();
			shell.bot().waitUntil(shellCloses(shell), 1000 * 60 * 2, 1000);
		}

	}

	public static class NewXtextProjectWizardMainPageAPI {

		private final XtextSWTBotShell shell;

		NewXtextProjectWizardMainPageAPI(XtextSWTBotShell shell) {
			this.shell = shell;
		}

		public NewXtextProjectWizardConfigurationPageAPI next() {
			shell.bot().button("Next >").click();
			return new NewXtextProjectWizardConfigurationPageAPI(shell);
		}

		public void finish() {
			shell.bot().button("Finish").click();
			shell.bot().waitUntil(shellCloses(shell), 1000 * 60 * 2, 1000);
		}

	}

	public static class NewXtextProjectWizardConfigurationPageAPI {

		private final XtextSWTBotShell shell;

		NewXtextProjectWizardConfigurationPageAPI(XtextSWTBotShell shell) {
			this.shell = shell;
		}

		public NewXtextProjectWizardConfigurationPageAPI toggleEclipsePlugin() {
			shell.bot().checkBox("Eclipse plug-in").click();
			return this;
		}

		public NewXtextProjectWizardConfigurationPageAPI toggleGenericIdeSupport() {
			shell.bot().checkBox("Generic IDE Support").click();
			return this;
		}

		public NewXtextProjectWizardConfigurationPageAPI toggleTestingSupport() {
			shell.bot().checkBox("Testing Support").click();
			return this;
		}

		public NewXtextProjectWizardConfigurationPageAPI setMavenBuildType() {
			shell.bot().comboBox(0).setSelection("Maven");
			return this;
		}

		public void finish() {
			shell.bot().button("Finish").click();
			shell.bot().waitUntil(shellCloses(shell), 1000 * 60 * 2, 1000);
		}

	}

}
