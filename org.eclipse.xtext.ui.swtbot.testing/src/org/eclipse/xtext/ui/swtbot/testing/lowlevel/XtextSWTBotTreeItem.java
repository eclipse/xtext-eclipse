/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.lowlevel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.*;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ContextMenuHelper;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.utils.TextDescription;
import org.eclipse.swtbot.swt.finder.waits.*;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Our own implementation of SWTBotTreeItem to add new methods to standard implementation.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextSWTBotTreeItem extends SWTBotTreeItem {

	XtextSWTBotTreeItem(TreeItem item) {
		super(item);
	}

	XtextSWTBotTreeItem(TreeItem treeItem, TextDescription textDescription) {
		super(treeItem, textDescription);
	}

	@Override
	public XtextSWTBotTreeItem select() {
		return (XtextSWTBotTreeItem) super.select();
	}

	@Override
	public XtextSWTBotRootMenu contextMenu() {
		return (XtextSWTBotRootMenu) super.contextMenu();
	}

	@Override
	public XtextSWTBotMenu contextMenu(String text) throws WidgetNotFoundException {
		return (XtextSWTBotMenu) super.contextMenu(text);
	}

	@Override
	protected XtextSWTBotRootMenu contextMenu(Control control) throws WidgetNotFoundException {
		ContextMenuHelper.notifyMenuDetect(control, widget);
		WaitForObjectCondition<Menu> waitForMenu = Conditions.waitForPopupMenu(control);
		new SWTBot().waitUntilWidgetAppears(waitForMenu);
		return new XtextSWTBotRootMenu(waitForMenu.get(0));
	}

	@Override
	public List<SWTBotTreeItem> getNodes(final String nodeText) {
		List<SWTBotTreeItem> foundItems = syncExec(new ListResult<SWTBotTreeItem>() {
			@Override
			public List<SWTBotTreeItem> run() {
				TreeItem[] items = widget.getItems();
				List<SWTBotTreeItem> results = new ArrayList<SWTBotTreeItem>();
				for (TreeItem treeItem : items) {
					if (treeItem.getText().equals(nodeText))
						results.add(new XtextSWTBotTreeItem(treeItem,
								new TextDescription("Tree node with text: " + nodeText)));
				}
				return results;
			}
		});
		if (foundItems.isEmpty())
			throw new WidgetNotFoundException("Could not find node with text: " + nodeText); //$NON-NLS-1$
		return foundItems;
	}

}
