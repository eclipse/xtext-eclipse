/*******************************************************************************
 * Copyright (c) 2015, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.ui.autoedit

import org.eclipse.jface.text.IDocument
import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider
import com.google.inject.Provider
import com.google.inject.Inject

class AutoEditStrategy extends DefaultAutoEditStrategyProvider {
	@Inject Provider<InterpreterAutoEdit> interpreterAutoEdit
	
	override protected void configure(IEditStrategyAcceptor acceptor) {
		super.configure(acceptor)
		acceptor.accept(interpreterAutoEdit.get, IDocument.DEFAULT_CONTENT_TYPE)
	}
}
