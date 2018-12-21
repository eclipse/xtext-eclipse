/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import org.eclipse.xtext.ui.swtbot.testing.lowlevel.XtextSWTBotShell;

/**
 * API to access functionality from the preference dialog through SWTBot.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class PreferencesDialogAPI {

	private final XtextSWTBotShell shell;

	PreferencesDialogAPI(XtextSWTBotShell shell) {
		this.shell = shell;
	}

	public void cancel() {
		shell.bot().button("Cancel").click();
	}

	public XtendFormatterPreferencePageAPI activateXtendFormatterPage() {
		shell.bot().tree().expandNode("Xtend").select("Formatter");
		return new XtendFormatterPreferencePageAPI(shell.bot());
	}

}
