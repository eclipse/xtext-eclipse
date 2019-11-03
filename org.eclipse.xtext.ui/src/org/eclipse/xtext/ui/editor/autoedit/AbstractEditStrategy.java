/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.autoedit;

import org.apache.log4j.Logger;
import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.bindings.keys.KeyLookupFactory;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.xtext.ui.editor.model.DocumentUtil;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public abstract class AbstractEditStrategy implements IAutoEditStrategy, VerifyKeyListener {
	
	private final static Logger log = Logger.getLogger(AbstractEditStrategy.class);

	/**
	 * Named binding key to bind boolean constant 'true' the debug / test mode for the
	 * edit strategies. 
	 * @since 2.0
	 */
	public static final String DEBUG = "org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategy.DEBUG";
	
	/**
	 * Boolean field that can be used to debug edit strategies. BadLocationException will be re-thrown if set to <code>true</code>
	 * @since 2.0
	 */
	@Inject(optional = true)
	@Named(DEBUG)
	protected boolean debug = false;
	
	private boolean skipNext = false;
	
	/**
	 * @since 2.20
	 */
	@Inject
	protected DocumentUtil util = new DocumentUtil();
	
	/**
	 * @since 2.20
	 */
	public void setDocumentUtil(DocumentUtil util) {
		this.util = util;
	}
	
	/**
	 * @since 2.20
	 */
	public DocumentUtil getDocumentUtil() {
		return util;
	}
	
	private boolean shouldSkipNext(int keyCode) {
		IKeyLookup lookUp = KeyLookupFactory.getDefault();
		return lookUp.getCommand() == keyCode || lookUp.getCtrl() == keyCode;
	}
	
	@Override
	public void verifyKey(VerifyEvent event) {
		skipNext = shouldSkipNext(event.keyCode);
	}

	@Override
	public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
		if (skipNext)
			return;
		try {
			internalCustomizeDocumentCommand(document, command);
		} catch (BadLocationException e) {
			handleBadLocationException(e);
		} catch (BadPartitioningException e) {
			handleBadPartitioningException(e);
		}
	}
	
	/**
	 * Logs the exception. Will throw a {@link RuntimeException} if {@link #debug} is set to <code>true</code>.
	 * @since 2.0
	 */
	protected void handleBadLocationException(BadLocationException e) {
		log.error(e.getMessage(), e);
		if (debug)
			throw new RuntimeException(e);
	}
	
	/**
	 * Logs the exception. Will throw a {@link RuntimeException} if {@link #debug} is set to <code>true</code>.
	 * @since 2.20
	 */
	protected void handleBadPartitioningException(BadPartitioningException e) {
		log.error(e.getMessage(), e);
		if (debug)
			throw new RuntimeException(e);
	}

	protected abstract void internalCustomizeDocumentCommand(IDocument document, DocumentCommand command)
			throws BadLocationException, BadPartitioningException;

	public int count(String toFind, String searchMe) throws BadLocationException {
		int count = 0;
		int index = -toFind.length();
		while (true) {
			index = searchMe.indexOf(toFind, index + toFind.length());
			if (index == -1) {
				return count;
			} else {
				count++;
			}
		}
	}

	protected String getDocumentContent(IDocument document, DocumentCommand command) throws BadLocationException {
		return getDocumentUtil().getDocumentContent(document, command);
	}

	protected boolean isIdentifierPart(IDocument doc, int offset) throws BadLocationException {
		if (doc.getLength() <= offset) {
			return false;
		}
		try {
			char c = doc.getChar(offset);
			return Character.isJavaIdentifierPart(c);
		} catch (BadLocationException e) {
			handleBadLocationException(e);
		}
		return false;
	}
}
