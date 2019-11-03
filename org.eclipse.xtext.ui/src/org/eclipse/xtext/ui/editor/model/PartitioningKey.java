/*******************************************************************************
 * Copyright (c) 2019 Sebastian Zarnekow and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.model;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.xtext.LanguageInfo;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The partitioning key used by the document related services that are aware of active partitioners.
 * 
 * @see IDocumentExtension3
 * @since 2.20
 */
@Singleton
public class PartitioningKey {

	private final String key;

	@Inject
	public PartitioningKey(LanguageInfo languageInfo) {
		this(languageInfo.getLanguageName() + ".Partitioning");
	}
	
	public PartitioningKey(String key) {
		this.key = key;
	}

	public String getPartitioning() {
		return key;
	}

	public ITypedRegion getPartition(IDocument doc, int offset) throws BadLocationException, BadPartitioningException {
		if (doc instanceof IDocumentExtension3) {
			return ((IDocumentExtension3) doc).getPartition(key, offset, false);
		}
		return doc.getPartition(offset);
	}

}