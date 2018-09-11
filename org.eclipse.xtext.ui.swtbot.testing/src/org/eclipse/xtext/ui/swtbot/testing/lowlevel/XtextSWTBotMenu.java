/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.lowlevel;

import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.waits.*;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.hamcrest.Matcher;

/**
 * Our own implementation of SWTBotMenu to add new methods to standard implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTBotMenu extends SWTBotMenu {

	XtextSWTBotMenu(MenuItem menuItem, Matcher<MenuItem> matcher) {
		super(menuItem, matcher);
	}

	@Override
	public XtextSWTBotMenu menu(final String text) {
		return (XtextSWTBotMenu) menu(text, false, 0);
	}

	@Override
	public XtextSWTBotMenu menu(Matcher<MenuItem> matcher, final boolean recursive, final int index) throws WidgetNotFoundException {
		WaitForObjectCondition<MenuItem> waitForMenuItem = Conditions.waitForMenuItem(this, matcher, recursive, index);
		new SWTBot().waitUntilWidgetAppears(waitForMenuItem);
		return new XtextSWTBotMenu(waitForMenuItem.get(0), matcher);
	}

	public XtextSWTBotMenu menuWithRegex(String regex) {
		final Matcher<MenuItem> matcher = WidgetMatcherFactory.withRegex(regex);
		return menu(matcher, false, 0);
	}

}
