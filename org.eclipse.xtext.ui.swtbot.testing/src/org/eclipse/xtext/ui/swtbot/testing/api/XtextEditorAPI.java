/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.swtbot.testing.api;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

/**
 * @author Arne Deutsch - Initial contribution and API
 */
public class XtextEditorAPI {

	private SWTBotEditor editor;

	public XtextEditorAPI(SWTBotEditor editor) {
		this.editor = editor;
	}

	public XtextEditorAPI relaceContent(String newContent) {
		editor.bot().styledText().setText(newContent);
		return this;
	}

	public XtextEditorAPI save() {
		editor.bot().styledText().contextMenu().menu("Save").click();
		return this;
	}

}
