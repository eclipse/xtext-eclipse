/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.lowlevel;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.internal.WorkbenchPartReference;

/**
 * Our own implementation of SWTBotView to add new methods to standard implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTBotView extends SWTBotView {

	XtextSWTBotView(IViewReference view, XtextSWTWorkbenchBot bot) {
		super(view, bot);
	}

	@Override
	public XtextSWTBot bot() {
		return new XtextSWTBot(getControl());
	}

	protected Control getControl() {
		return ((WorkbenchPartReference) partReference).getPane().getControl();
	}

}
