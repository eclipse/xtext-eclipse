/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.lowlevel;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*;
import static org.eclipse.swtbot.swt.finder.waits.Conditions.waitForShell;

import java.util.List;

import org.eclipse.swt.widgets.*;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.hamcrest.Matcher;

/**
 * Our own implementation of SWTBot to add new methods to standard implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTBot extends SWTBot {

	XtextSWTBot(Control widget) {
		super(widget);
	}

	public XtextSWTBotShell shellWithRegex(String regex) {
		return new XtextSWTBotShell(shellsWithRegex(regex).get(0));
	}

	private List<Shell> shellsWithRegex(String regex) {
		Matcher<Shell> withRegex = withRegex(regex);
		WaitForObjectCondition<Shell> waitForShell = waitForShell(withRegex);
		waitUntilWidgetAppears(waitForShell);
		return waitForShell.getAllMatches();
	}

	@Override
	public XtextSWTBotTree tree() {
		return (XtextSWTBotTree) super.tree();
	}

	@Override
	public XtextSWTBotTree tree(int index) {
		Matcher matcher = allOf(widgetOfType(Tree.class));
		return new XtextSWTBotTree((Tree) widget(matcher, index), matcher);
	}

}
