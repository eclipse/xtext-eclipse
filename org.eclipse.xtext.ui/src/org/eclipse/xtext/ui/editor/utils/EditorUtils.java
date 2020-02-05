/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.utils;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.jdt.core.IJarEntryResource;
import org.eclipse.jdt.internal.ui.javaeditor.JarEntryEditorInput;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.DataFormatException;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextReadonlyEditorInput;
import org.eclipse.xtext.ui.resource.FileStoreStorage;

/**
 * @author Dennis H�bner - Initial contribution and API
 * @author Peter Friese
 */
public class EditorUtils {

	private static final Logger log = Logger.getLogger(EditorUtils.class);

	public static Font fontFromFontData(FontData[] fontDataArray) {
		if (fontDataArray != null && fontDataArray.length > 0) {
			String fontData = PreferenceConverter.getStoredRepresentation(fontDataArray);
			if (!JFaceResources.getFontRegistry().hasValueFor(fontData)) {
				FontData[] fData = PreferenceConverter.basicGetFontData(fontData);
				JFaceResources.getFontRegistry().put(fontData, fData);
			}
			Font font = JFaceResources.getFontRegistry().get(fontData);
			return font;
		}
		return null;
	}

	public static Color colorFromString(String rgbString) {
		if (rgbString != null && rgbString.trim().length() > 0) {
			Color col = JFaceResources.getColorRegistry().get(rgbString);
			try {
				if (col == null) {
					RGB rgb = StringConverter.asRGB(rgbString);
					JFaceResources.getColorRegistry().put(rgbString, rgb);
					col = JFaceResources.getColorRegistry().get(rgbString);
				}
			}
			catch (DataFormatException e) {
				log.error("Corrupt color value: " + rgbString, e);
			}
			return col;
		}
		return null;
	}

	public static Color colorFromRGB(RGB rgb) {
		if (rgb == null)
			return null;
		return colorFromString(StringConverter.asString(rgb));
	}
	
	public static XtextEditor getActiveXtextEditor(ExecutionEvent event) {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		if (activeEditor == null)
			return null;
		XtextEditor xtextEditor = activeEditor.getAdapter(XtextEditor.class);
		return xtextEditor;
	}

	public static XtextEditor getActiveXtextEditor() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		if (workbenchWindow == null) 
			return null;
		IWorkbenchPage activePage = workbenchWindow.getActivePage();
		if (activePage == null) 
			return null;
		IEditorPart activeEditor = activePage.getActiveEditor();
		if (activeEditor == null)
			return null;
		XtextEditor xtextEditor = activeEditor.getAdapter(XtextEditor.class);
		return xtextEditor;
	}

	public static XtextEditor getXtextEditor(IEditorPart openEditor) {
		if (openEditor != null) {
			return openEditor.getAdapter(XtextEditor.class);
		}
		return null;
	}
	
	/**
	 * @since 2.8
	 */
	public static IEditorInput createEditorInput(IStorage storage) {
		if (storage instanceof IFile)
			return new FileEditorInput((IFile) storage);
		try {
			if (storage instanceof IJarEntryResource)
				return new JarEntryEditorInput(storage);
		} catch (NoClassDefFoundError e) {
			// ignore. can happen if JDT is not available.
		}
		if (storage instanceof FileStoreStorage) {
			return new FileStoreEditorInput(((FileStoreStorage)storage).getFileStore());
		}
		return new XtextReadonlyEditorInput(storage);
	}
	
}
