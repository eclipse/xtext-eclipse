/*******************************************************************************
 * Copyright (c) 2015, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.ui

import com.google.inject.Binder
import com.google.inject.Provider
import com.google.inject.name.Names
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.xtext.example.arithmetics.ui.autoedit.AutoEditStrategy
import org.eclipse.xtext.resource.containers.IAllContainersState
import org.eclipse.xtext.ui.editor.XtextEditor
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider
import org.eclipse.xtext.ui.editor.model.IResourceForEditorInputFactory
import org.eclipse.xtext.ui.editor.model.ResourceForIEditorInputFactory
import org.eclipse.xtext.ui.shared.Access

/**
 * Use this class to register components to be used within the IDE.
 */
class ArithmeticsUiModule extends AbstractArithmeticsUiModule {

	new(AbstractUIPlugin plugin) {
		super(plugin)
	}

	override Provider<IAllContainersState> provideIAllContainersState() {
		return Access.getWorkspaceProjectsState()
	}

	override Class<? extends IResourceForEditorInputFactory> bindIResourceForEditorInputFactory() {
		return ResourceForIEditorInputFactory
	}

	override Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return AutoEditStrategy
	}

	def void configureEditorScope(Binder binder) {
		binder.bindConstant().annotatedWith(Names.named(XtextEditor.KEY_BINDING_SCOPE))
			.to("org.eclipse.xtext.example.arithmetics.ui.editor.XtextEditorScope");
	}
}
