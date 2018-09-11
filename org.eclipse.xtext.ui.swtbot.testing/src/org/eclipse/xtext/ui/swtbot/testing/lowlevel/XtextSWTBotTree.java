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
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.*;
import org.hamcrest.Matcher;

/**
 * Our own implementation of SWTBotTree to add new methods to standard implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTBotTree extends SWTBotTree {

	XtextSWTBotTree(Tree widget, Matcher matcher) {
		super(widget, matcher);
	}

	@Override
	public XtextSWTBotTreeItem expandNode(String... nodes) throws WidgetNotFoundException {
		return (XtextSWTBotTreeItem) super.expandNode(nodes);
	}

	@Override
	public XtextSWTBotTreeItem getTreeItem(final String nodeText) throws WidgetNotFoundException {
		try {
			new SWTBot().waitUntil(new DefaultCondition() {
				@Override
				public String getFailureMessage() {
					return "Could not find node with text " + nodeText; //$NON-NLS-1$
				}

				@Override
				public boolean test() throws Exception {
					return getItem(nodeText) != null;
				}
			});
		} catch (TimeoutException e) {
			throw new WidgetNotFoundException("Timed out waiting for tree item " + nodeText, e); //$NON-NLS-1$
		}
		return new XtextSWTBotTreeItem(getItem(nodeText));
	}
	
	protected TreeItem getItem(final String nodeText) {
		return syncExec(new WidgetResult<TreeItem>() {
			@Override
			public TreeItem run() {
				TreeItem[] items = widget.getItems();
				for (TreeItem item : items) {
					if (item.getText().equals(nodeText))
						return item;
				}
				return null;
			}
		});
	}

}
