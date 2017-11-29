/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.formatting.codebuff;

import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.formatting.IContentFormatterFactory;

import com.google.inject.Inject;

/**
 * @author Holger Schill - Initial contribution and API
 */
public class CodebuffContentFormatterFactory implements IContentFormatterFactory {

	@Inject CodebuffContentFormatter formatter;
	@Override
	public IContentFormatter createConfiguredFormatter(SourceViewerConfiguration configuration, ISourceViewer sourceViewer) {
		return formatter;
	}

}
