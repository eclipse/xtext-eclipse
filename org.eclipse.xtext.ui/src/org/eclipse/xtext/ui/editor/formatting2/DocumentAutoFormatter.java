/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.ui.editor.formatting2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Exceptions;

/** A service that enables to do auto-formatting when a document changed.
 *
 * @author Stephane Galland - Initial contribution and API
 */
public class DocumentAutoFormatter implements IDocumentAutoFormatter {

	private Collection<RegionFormattingRequest> formattingRequests = Collections.synchronizedList(new ArrayList<>(1));

	private IDocumentListener autoFormatListener;

	private IXtextDocument document;

	private IContentFormatter contentFormatter;

	@Override
	public void bind(IXtextDocument document, IContentFormatter contentFormatter) {
		assert document != null;
		assert contentFormatter != null;
		this.document = document;
		this.contentFormatter = contentFormatter;
	}

	@Override
	public synchronized void beginAutoFormat() {
		if (this.document != null && this.autoFormatListener == null) {
			this.formattingRequests.clear();
			this.autoFormatListener = new IDocumentListener() {
				@Override
				public void documentAboutToBeChanged(DocumentEvent event) {
					//
				}

				@SuppressWarnings("synthetic-access")
				@Override
				public void documentChanged(DocumentEvent event) {
					if (!Strings.isEmpty(event.getText())
							&& event.getDocument() instanceof IXtextDocument) {
						DocumentAutoFormatter.this.formattingRequests.add(new RegionFormattingRequest(
								(IXtextDocument) event.getDocument(), event.getOffset(), event.getText().length()));
					}
				}
			};
			this.document.addDocumentListener(this.autoFormatListener);
		}
	}

	@Override
	public void endAutoFormat() {
		final Collection<RegionFormattingRequest> requests;
		synchronized (this) {
			requests = this.formattingRequests;
			this.formattingRequests = Collections.synchronizedList(new ArrayList<>(1));
			if (this.autoFormatListener != null) {
				final IDocumentListener listener = this.autoFormatListener;
				this.autoFormatListener = null;
				if (this.document != null) {
					this.document.removeDocumentListener(listener);
				}
			}
		}
		if (this.contentFormatter != null) {
			for (final RegionFormattingRequest request : requests) {
				formatRegion(request.document, request.offset, request.length);
			}
		}
	}

	/** Called for formatting a region.
	 *
	 * @param document the document to format.
	 * @param offset the offset of the text to format.
	 * @param length the length of the text.
	 */
	protected void formatRegion(IXtextDocument document, int offset, int length) {
		try {
			final int startRegionOffset = document.getLineInformationOfOffset(
					previousSiblingChar(document, offset)).getOffset();
			final IRegion endLine = document.getLineInformationOfOffset(offset + length);
			final int endRegionOffset = endLine.getOffset() + endLine.getLength();
			final int regionLength = endRegionOffset - startRegionOffset;
			for (final IRegion region : document.computePartitioning(startRegionOffset, regionLength)) {
				this.contentFormatter.format(document, region);
			}
		} catch (BadLocationException exception) {
			Exceptions.sneakyThrow(exception);
		}
	}

	private static int previousSiblingChar(IXtextDocument document, int offset) throws BadLocationException {
		int off = offset - 1;
		while (off >= 0 && Character.isWhitespace(document.getChar(off))) {
			--off;
		}
		return off;
	}

	/** Request for formatting a region.
	 *
	 * @author Stephane Galland - Initial contribution and API
	 */
	private static class RegionFormattingRequest {

		public final IXtextDocument document;

		public final int offset;

		public final int length;

		RegionFormattingRequest(IXtextDocument document, int offset, int length) {
			this.document = document;
			this.offset = offset;
			this.length = length;
		}

	}

}
