/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.xtext.ui.swtbot.testing.lowlevel.*;

public class PackageExplorerAPI {

	private final XtextSWTBotView view;

	PackageExplorerAPI(XtextSWTBotView view) {
		this.view = view;
	}

	public boolean projectExist(String projectName) {
		for (SWTBotTreeItem i : view.bot().tree().getAllItems()) {
			if (projectName.equals(i.getText())) {
				return true;
			}
		}
		return false;
	}

	public PackageExplorerAPI runJUnitTests(String... path) {
		view.bot().tree().expandNode(path).select().contextMenu("Run As").menuWithRegex("? JUnit Test").click();
		return this;
	}

	public PackageExplorerAPI runJUnitPluginTests(String... path) {
		view.bot().tree().expandNode(path).select().contextMenu("Run As").menuWithRegex("? JUnit Plug-in Test").click();
		return this;
	}

	public PackageExplorerAPI runMWE2(String... path) {
		view.bot().tree().expandNode(path).select().contextMenu("Run As").menuWithRegex("MWE2 Workflow").click();
		return this;
	}

	public PackageExplorerAPI runMavenInstall(String... path) {
		view.bot().tree().expandNode(path).select().contextMenu("Run As").menuWithRegex("? Maven install").click();
		return this;
	}

}
