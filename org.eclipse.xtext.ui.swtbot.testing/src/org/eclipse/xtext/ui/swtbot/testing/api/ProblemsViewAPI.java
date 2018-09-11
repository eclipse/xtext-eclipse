/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import org.eclipse.swtbot.swt.finder.widgets.*;
import org.eclipse.xtext.ui.swtbot.testing.lowlevel.XtextSWTBotView;

/**
 * API to access functionality through the Problems View UI.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class ProblemsViewAPI {

	private final XtextSWTBotView view;

	ProblemsViewAPI(XtextSWTBotView view) {
		this.view = view;
	}

	public int errorCount() {
		SWTBotTree tree = view.bot().tree();
		for (SWTBotTreeItem i : tree.getAllItems()) {
			String text = i.getText();
			if (text.startsWith("Errors")) {
				text = text.substring("Errors (".length());
				text = text.substring(0, text.indexOf(" "));
				return Integer.parseInt(text);
			}
		}
		return 0;
	}

}
