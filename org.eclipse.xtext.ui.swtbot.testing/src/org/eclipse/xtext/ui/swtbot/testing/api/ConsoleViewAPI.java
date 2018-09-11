/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.xtext.ui.swtbot.testing.lowlevel.XtextSWTBotView;

public final class ConsoleViewAPI {

	private final XtextSWTBotView view;

	ConsoleViewAPI(XtextSWTBotView view) {
		this.view = view;
	}

	public void waitForMWE2ToFinish() {
		view.bot().waitUntil(new DefaultCondition() {
			@Override
			public String getFailureMessage() {
				return "Process still not terminated.";
			}

			@Override
			public boolean test() throws Exception {
				return view.bot().styledText().getText().contains("- Done.");
			}
		}, 1000 * 60 * 2, 500);
	}

	public void waitForMavenToFinishWithSuccess() {
		view.bot().waitUntil(new DefaultCondition() {
			@Override
			public String getFailureMessage() {
				return "Process still not terminated.";
			}

			@Override
			public boolean test() throws Exception {
				return view.bot().styledText().getText().contains("BUILD SUCCESS");
			}
		}, 1000 * 60 * 10, 500);
	}

}
