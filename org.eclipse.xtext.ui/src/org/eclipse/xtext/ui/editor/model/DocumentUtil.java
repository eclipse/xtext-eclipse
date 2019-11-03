/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.model;

import java.util.Arrays;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class DocumentUtil {
	
	/**
	 * @since 2.20
	 */
	private final PartitioningKey partitioning;
	
	/**
	 * @since 2.20
	 */
	@Inject
	public DocumentUtil(PartitioningKey key) {
		partitioning = key;
	}
	
	/**
	 * @deprecated Use {@link DocumentUtil#DocumentUtil(PartitioningKey) instead}
	 */
	@Deprecated
	public DocumentUtil() {
		this(new DefaultPartitioningKey());
	}
	
	/**
	 * searches backwards for the given string within the same partition type
	 * @return the region of the match or <code>null</code> if no match were found
	 * @throws BadPartitioningException 
	 */
	public IRegion searchBackwardsInSamePartition(String toFind, IDocument document, int endOffset) throws BadLocationException, BadPartitioningException {
		return searchBackwardsInSamePartition(toFind, document.get(), document, endOffset);
	}
	
	/**
	 * searches backwards for the given string within the same partition type
	 * @return the region of the match or <code>null</code> if no match were found
	 * @throws BadPartitioningException 
	 * @since 2.4
	 */
	public IRegion searchBackwardsInSamePartition(String toFind, String documentText, IDocument document, int endOffset) throws BadLocationException, BadPartitioningException {
		if (endOffset < 0) {
			return null;
		}
		int length = toFind.length();
		String text = preProcessSearchString(documentText);
		ITypedRegion partition = getPartition(document, endOffset);
		int indexOf = text.lastIndexOf(toFind, endOffset - length);
		while (indexOf >= 0) {
			ITypedRegion partition2 = getPartition(document, indexOf);
			if (partition2.getType().equals(partition.getType())) {
				return new Region(indexOf, length);
			}
			indexOf = text.lastIndexOf(toFind, partition2.getOffset() - length);
		}
		String trimmed = toFind.trim();
		if (trimmed.length() > 0 && trimmed.length() != length) {
			return searchBackwardsInSamePartition(trimmed, documentText, document, endOffset);
		}
		return null;
	}
	
	protected String preProcessSearchString(String string) {
		return string;
	}

	/**
	 * searches for the given string within the same partition type
	 * @return the region of the match or <code>null</code> if no match were found
	 * @throws BadPartitioningException 
	 */
	public IRegion searchInSamePartition(String toFind, IDocument document, int startOffset) throws BadLocationException, BadPartitioningException {
		return searchInSamePartition(toFind, document.get(), document, startOffset);
	}
	
	/**
	 * searches for the given string within the same partition type
	 * 
	 * @return the region of the match or <code>null</code> if no match were found
	 * @throws BadPartitioningException 
	 * @since 2.4
	 */
	public IRegion searchInSamePartition(String toFind, String documentText, IDocument document, int startOffset)
			throws BadLocationException, BadPartitioningException {
		if (startOffset >= document.getLength()) {
			return null;
		}
		String text = preProcessSearchString(documentText);
		ITypedRegion partition = getPartition(document, startOffset);
		
		int indexOf = text.indexOf(toFind, getOffset(toFind, startOffset));
		while (indexOf >= 0 && indexOf < document.getLength()) {
			ITypedRegion partition2 = getPartition(document, indexOf);
			if (partition2.getType().equals(partition.getType())) {
				return new Region(indexOf, toFind.length());
			}
			indexOf = text.indexOf(toFind, partition2.getOffset() + partition2.getLength());
		}
		String trimmed = toFind.trim();
		if (trimmed.length() > 0 && trimmed.length() != toFind.length()) {
			return searchInSamePartition(trimmed, documentText, document, startOffset);
		}
		return null;
	}

	private int getOffset(String toFind, int startOffset) {
		int whitespacesCount = getWhitespacesCount(toFind);
		return Math.max(startOffset - whitespacesCount, 0);
	}

	private int getWhitespacesCount(String toFind) {
		int whitespacesCount = 0;
		while (toFind.length() > whitespacesCount && Character.isWhitespace(toFind.charAt(whitespacesCount))) {
			whitespacesCount++;
		}
		return whitespacesCount;
	}

	public boolean isSameLine(IDocument doc, int offset, int offset2) throws BadLocationException {
		return doc.getLineOfOffset(offset)==doc.getLineOfOffset(offset2);
	}
	
	public int findNextOffSetInPartition(IDocument doc, int partitionOffSet, int minIndex) throws BadLocationException, BadPartitioningException {
		ITypedRegion partition = getPartition(doc, partitionOffSet);
		ITypedRegion partition2 = getPartition(doc, minIndex);
		if (partition.getType().equals(partition2.getType()) || partition2.getLength() == 0) {
			return minIndex;
		} else {
			return findNextOffSetInPartition(doc, partitionOffSet, minIndex + partition2.getLength());
		}
	}
	
	/**
	 * @since 2.20
	 */
	public String getDocumentContent(IDocument document, DocumentCommand command) throws BadLocationException {
		ITypedRegion partition;
		ITypedRegion[] partitions;
		if (document instanceof IDocumentExtension3) {
			ITypedRegion partition2;
			try {
				IDocumentExtension3 casted = (IDocumentExtension3) document;
				partition2 = casted.getPartition(getPartitioning(), command.offset, false);
				partitions = casted.getDocumentPartitioner(getPartitioning()).computePartitioning(0, document.getLength());
			} catch (BadPartitioningException e) {
				partition2 = document.getPartition(command.offset);
				partitions = document.getDocumentPartitioner().computePartitioning(0, document.getLength());
			}
			partition = partition2;
		} else {
			partition = document.getPartition(command.offset);
			partitions = document.getDocumentPartitioner().computePartitioning(0, document.getLength());
		}

		Iterable<ITypedRegion> partitionsOfCurrentType = Iterables.filter(Arrays.asList(partitions),
				input -> input.getType().equals(partition.getType()));
		StringBuilder builder = new StringBuilder();
		for (ITypedRegion position : partitionsOfCurrentType) {
			builder.append(document.get(position.getOffset(), position.getLength()));
		}
		return builder.toString();
	}

	/**
	 * @since 2.20
	 */
	public ITypedRegion getPartition(IDocument doc, int partitionOffSet) throws BadLocationException, BadPartitioningException {
		return partitioning.getPartition(doc, partitionOffSet);
	}
	
	/**
	 * @since 2.20
	 */
	public String getPartitioning() {
		return partitioning.getPartitioning();
	}
}
