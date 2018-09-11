/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.lowlevel;

import static org.eclipse.swtbot.eclipse.finder.waits.Conditions.waitForView;

import java.util.*;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.finders.WorkbenchContentsFinder;
import org.eclipse.swtbot.eclipse.finder.waits.WaitForView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.ui.IViewReference;
import org.hamcrest.Matcher;

/**
 * Our own implementation of SWTWorkbenchBot to add new methods to standard
 * implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTWorkbenchBot extends SWTWorkbenchBot {

	protected final WorkbenchContentsFinder workbenchContentsFinder;

	public XtextSWTWorkbenchBot() {
		workbenchContentsFinder = new WorkbenchContentsFinder();
	}

	@Override
	public XtextSWTBotShell shell(String text) {
		return shell(text, 0);
	}

	@Override
	public XtextSWTBotShell shell(String text, int index) {
		return new XtextSWTBotShell(shells(text).get(index));
	}

	@Override
	public XtextSWTBotView viewByTitle(String title) {
		return (XtextSWTBotView) super.viewByTitle(title);
	}

	@Override
	public XtextSWTBotView viewById(String title) {
		return (XtextSWTBotView) super.viewById(title);
	}

	@Override
	public XtextSWTBotView view(Matcher<IViewReference> matcher) {
		WaitForView waitForView = waitForView(matcher);
		waitUntilWidgetAppears(waitForView);
		return new XtextSWTBotView(waitForView.get(0), this);
	}

	@Override
	public List<SWTBotView> views(Matcher<?> matcher) {
		List<IViewReference> views = workbenchContentsFinder.findViews(matcher);
		List<SWTBotView> viewBots = new ArrayList<SWTBotView>();
		for (IViewReference viewReference : views)
			viewBots.add(new XtextSWTBotView(viewReference, this));
		return viewBots;
	}

}
