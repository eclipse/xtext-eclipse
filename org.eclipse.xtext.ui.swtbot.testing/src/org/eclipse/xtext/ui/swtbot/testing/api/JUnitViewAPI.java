/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.*;
import org.eclipse.xtext.ui.swtbot.testing.lowlevel.XtextSWTBotView;

public class JUnitViewAPI {

	private final XtextSWTBotView view;

	JUnitViewAPI(XtextSWTBotView view) {
		this.view = view;
	}

	public void waitForTestrunToFinish() {
		waitForTestsToStart();
		waitForTestsToFinish();
	}
	
	public boolean isTestrunErrorFree() {
		return atLeastOneTestIsRun() && errorCount() == 0 && failureCount() == 0;
	}

	private boolean atLeastOneTestIsRun() {
		return view.bot().text(0).getText().matches("[1-9].*");
	}
	
	private int errorCount() {
		return Integer.parseInt(view.bot().text(1).getText());
	}

	private int failureCount() {
		return Integer.parseInt(view.bot().text(2).getText());
	}

	private void waitForTestsToStart() {
		SWTBotToolbarButton stopButton = view.toolbarButton("Stop JUnit Test Run");
		view.bot().waitUntil(new DefaultCondition() {
			@Override
			public String getFailureMessage() {
				return "Stop button is still not enabled.";
			}

			@Override
			public boolean test() throws Exception {
				return stopButton.isEnabled();
			}
		}, 1000 * 60, 25);
	}
	
	private void waitForTestsToFinish() {
		SWTBotToolbarButton stopButton = view.toolbarButton("Stop JUnit Test Run");
		view.bot().waitUntil(new DefaultCondition() {
			@Override
			public String getFailureMessage() {
				return "Stop button is still enabled.";
			}

			@Override
			public boolean test() throws Exception {
				return !stopButton.isEnabled();
			}
		}, 1000 * 60 * 5, 1000);
	}

}
