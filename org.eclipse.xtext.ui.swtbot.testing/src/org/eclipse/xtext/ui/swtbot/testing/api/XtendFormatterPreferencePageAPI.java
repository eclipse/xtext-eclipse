/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import org.eclipse.xtext.ui.swtbot.testing.lowlevel.XtextSWTBot;

/**
 * API to access functionality from the xtend formatter preference page through SWTBot.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtendFormatterPreferencePageAPI {

	private XtextSWTBot bot;

	XtendFormatterPreferencePageAPI(XtextSWTBot bot) {
		this.bot = bot;
	}

	public XtendFormatterPreferenceEditDialogAPI openEditDialog() {
		bot.button("Edit...").click();
		return new XtendFormatterPreferenceEditDialogAPI(bot.shellWithRegex("Profile.*"));
	}

}
