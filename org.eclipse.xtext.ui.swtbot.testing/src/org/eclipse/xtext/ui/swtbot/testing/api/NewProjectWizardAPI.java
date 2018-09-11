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

	public NewXtextDomainModelExampleWizardAPI selectXtextExample(String exampleLabel) {
		shell.bot().tree().expandNode("Xtext Examples").select(exampleLabel);
		shell.bot().button("Next >").click();
		return new NewXtextDomainModelExampleWizardAPI(shell);
	}

	public NewXtextDomainModelExampleWizardAPI selectXtendExample(String exampleLabel) {
		shell.bot().tree().expandNode("Xtend Examples").select(exampleLabel);
		shell.bot().button("Next >").click();
		return new NewXtextDomainModelExampleWizardAPI(shell);
	}
	
	/**
	 * Xtext Domain Model Example selected.
	 */
	public static class NewXtextDomainModelExampleWizardAPI {

		private final XtextSWTBotShell shell;

		NewXtextDomainModelExampleWizardAPI(XtextSWTBotShell shell) {
			this.shell = shell;
		}

		public void finish() {
			shell.bot().button("Finish").click();
			shell.bot().waitUntil(shellCloses(shell), 1000 * 60 * 2, 1000);
		}

	}

}
