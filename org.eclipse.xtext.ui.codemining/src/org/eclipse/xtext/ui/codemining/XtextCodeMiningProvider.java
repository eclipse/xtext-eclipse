/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.codemining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.codemining.AbstractCodeMiningProvider;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.LineContentCodeMining;
import org.eclipse.jface.text.codemining.LineHeaderCodeMining;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.XtextDocumentUtil;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.concurrent.CancelableUnitOfWork;

/**
 * @Beta
 * @since 2.14
 * @author René Purrio - Initial contribution and API
 */
 public abstract class XtextCodeMiningProvider extends AbstractCodeMiningProvider {
	/*
	 * This indicator checks if monitor is canceled or if
	 * CancelableUnitOfWork-cancelIndicator is canceled.
	 */
	private static class CombinedCancelIndicator implements CancelIndicator {
		private IProgressMonitor monitor;
		private CancelIndicator uowCancelIndicator;

		public CombinedCancelIndicator(IProgressMonitor monitor, CancelIndicator uowCancelIndicator) {
			this.monitor = monitor;
			this.uowCancelIndicator = uowCancelIndicator;
		}

		@Override
		public boolean isCanceled() {
			// uowCancelIndicator.isCanceled() will return true, when the resource was changed
			// thanks to CancelableUnitOfWork
			return (uowCancelIndicator != null && uowCancelIndicator.isCanceled())
					// monitor.isCanceled() throws an CancellationException when monitor is canceled
					|| (monitor != null && monitor.isCanceled());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public CompletableFuture<List<? extends ICodeMining>> provideCodeMinings(ITextViewer viewer,
			IProgressMonitor monitor) {
		CompletableFuture<List<? extends ICodeMining>> future = CompletableFuture.supplyAsync(() -> {
			CancelableUnitOfWork<Object, XtextResource> uow = new CancelableUnitOfWork<Object, XtextResource>() {
				@Override
				public Object exec(XtextResource resource, CancelIndicator uowCancelIndicator) throws Exception {
					List<ICodeMining> codeMiningList = new ArrayList<>();
					IAcceptor<ICodeMining> acceptor = new IAcceptor<ICodeMining>() {
						@Override
						public void accept(ICodeMining codeMiningObject) {
							codeMiningList.add(codeMiningObject);
						}
					};
					
					CombinedCancelIndicator indicator = new CombinedCancelIndicator(monitor, uowCancelIndicator);
					if (indicator.isCanceled()) {
						return Collections.emptyList();
					}
					createLineHeaderCodeMinings(viewer.getDocument(), resource, indicator, acceptor);
					if (indicator.isCanceled()) {
						return Collections.emptyList();
					}
					createLineContentCodeMinings(viewer.getDocument(), resource, indicator, acceptor);
					if (indicator.isCanceled()) {
						return Collections.emptyList();
					}
					return codeMiningList;
				}
			};
			return (List<ICodeMining>) XtextDocumentUtil.get(viewer).readOnly(uow);
		});
		return future;
	}
	
	/**
	 * It creates a {@link LineContentCodeMining} object for an inline annotation.
	 * @param beforeCharacter inline annotation will be created before this character number.
	 * @param contentText text for inline annotation.
	 * @return a new {@link LineContentCodeMining} object.
	 */
	protected LineContentCodeMining createNewLineContentCodeMining(int beforeCharacter, String contentText) {
		return createNewLineContentCodeMining(beforeCharacter, contentText, null);
	}
	
	/**
	 * It creates a {@link LineContentCodeMining} object for an inline annotation.
	 * @param beforeCharacter inline annotation will be created before this character number.
	 * @param contentText text for inline annotation.
	 * @param action the action to execute when mining is clicked and null otherwise.
	 * @return a new {@link LineContentCodeMining} object.
	 */
	protected LineContentCodeMining createNewLineContentCodeMining(int beforeCharacter, String contentText,
			Consumer<MouseEvent> action) {
		return new LineContentCodeMining(new Position(beforeCharacter), this, action) {
			@Override
			protected CompletableFuture<Void> doResolve(ITextViewer viewer, IProgressMonitor monitor) {
				return CompletableFuture.runAsync(() -> {
					super.setLabel(contentText);
				});
			}
		};
	}

	/**
	 * It creates a {@link LineHeaderCodeMining} object for a header annotation.
	 * @param beforeLineNumber the line number where codemining must be drawn. Use 0 if you wish to locate the code mining before the first line number (1).
	 * @param document the document.
	 * @param headerText text for header annotation.
	 * @return a new {@link LineHeaderCodeMining} object.
	 * @throws BadLocationException
	 */
	protected LineHeaderCodeMining createNewLineHeaderCodeMining(int beforeLineNumber, IDocument document,
			String headerText) throws BadLocationException {
		return createNewLineHeaderCodeMining(beforeLineNumber, document, headerText, null);
	}

	/**
	 * It creates a {@link LineHeaderCodeMining} object for a header annotation.
	 * @param beforeLineNumber the line number where codemining must be drawn. Use 0 if you wish to locate the code mining before the first line number (1).
	 * @param document the document.
	 * @param headerText text for header annotation.
	 * @param action the action to execute when mining is clicked and null otherwise.
	 * @return a new {@link LineHeaderCodeMining} object.
	 * @throws BadLocationException
	 */
	protected LineHeaderCodeMining createNewLineHeaderCodeMining(int beforeLineNumber, IDocument document,
		String headerText, Consumer<MouseEvent> action)  throws BadLocationException {
		return new LineHeaderCodeMining(beforeLineNumber, document, this, action) {
			@Override
			protected CompletableFuture<Void> doResolve(ITextViewer viewer, IProgressMonitor monitor) {
				return CompletableFuture.runAsync(() -> {
					super.setLabel(headerText);
				});
			}
		};
	}

	protected abstract void createLineHeaderCodeMinings(IDocument document, XtextResource resource,
			CancelIndicator indicator, IAcceptor<ICodeMining> acceptor) throws BadLocationException;

	protected abstract void createLineContentCodeMinings(IDocument document, XtextResource resource,
			CancelIndicator indicator, IAcceptor<ICodeMining> acceptor) throws BadLocationException;
}
