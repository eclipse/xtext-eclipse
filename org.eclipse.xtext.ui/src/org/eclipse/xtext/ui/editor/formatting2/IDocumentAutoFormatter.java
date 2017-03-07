/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.ui.editor.formatting2;

import com.google.inject.ImplementedBy;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

/** A service that enables to do auto-formatting when a document changed.
 *
 * @author Stephane Galland - Initial contribution and API
 */
@ImplementedBy(DocumentAutoFormatter.class)
public interface IDocumentAutoFormatter {

	/** Create an instance of document auto formatter.
	 *
	 * @param document the Xtext document associated to this auto-formatter.
	 * @param contentFormatter the formatter of content to be used.
	 */
	void bind(IXtextDocument document, IContentFormatter contentFormatter);

	/** Start auto-formating.
	 */
	void beginAutoFormat();

	/** End auto-formating.
	 */
	void endAutoFormat();

	/** A service that enables to do auto-formatting when a document changed.
	 *
	 * @author Stephane Galland - Initial contribution and API
	 */
	IDocumentAutoFormatter NONE = new IDocumentAutoFormatter() {
		@Override
		public void bind(IXtextDocument document, IContentFormatter contentFormatter) {
		}
		@Override
		public void beginAutoFormat() {
		}
		@Override
		public void endAutoFormat() {
		}
	};

}
