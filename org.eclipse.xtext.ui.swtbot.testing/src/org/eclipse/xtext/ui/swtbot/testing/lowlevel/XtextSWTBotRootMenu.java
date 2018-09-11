/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.lowlevel;

import org.eclipse.swt.widgets.*;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.*;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRootMenu;
import org.hamcrest.Matcher;

/**
 * Our own implementation of SWTBotRootMenu to add new methods to standard implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTBotRootMenu extends SWTBotRootMenu {

	XtextSWTBotRootMenu(Menu menu) {
		super(menu);
	}

	@Override
	public XtextSWTBotMenu menu(String... texts) {
		return (XtextSWTBotMenu) super.menu(texts);
	}

	@Override
	public XtextSWTBotMenu menu(final Matcher<MenuItem> matcher, final boolean recursive, final int index)
			throws WidgetNotFoundException {
		WaitForObjectCondition<MenuItem> waitForMenuItem = Conditions.waitForMenuItem(this, matcher, recursive, index);
		new SWTBot().waitUntilWidgetAppears(waitForMenuItem);
		return new XtextSWTBotMenu(waitForMenuItem.get(0), matcher);
	}

}
