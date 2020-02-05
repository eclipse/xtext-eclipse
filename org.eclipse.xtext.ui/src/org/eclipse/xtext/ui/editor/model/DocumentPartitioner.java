/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IDocumentPartitionerExtension;
import org.eclipse.jface.text.IDocumentPartitionerExtension2;
import org.eclipse.jface.text.IDocumentPartitionerExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TypedPosition;
import org.eclipse.jface.text.TypedRegion;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IToken;

import com.google.inject.Inject;

/**
 * Initially copied from {@link org.eclipse.jface.text.rules.FastPartitioner} in order to be able to fix
 * {@link #documentChanged2(DocumentEvent)}
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 * @since 2.2
 */
public class DocumentPartitioner implements IDocumentPartitioner, IDocumentPartitionerExtension,
		IDocumentPartitionerExtension2, IDocumentPartitionerExtension3 {

	private static final Logger log = Logger.getLogger(DocumentPartitioner.class);
	
	/**
	 * The position category this partitioner uses to store the document's partitioning information.
	 * 
	 * @since 2.2
	 */
	protected static final String CONTENT_TYPES_CATEGORY = "__content_types_category"; //$NON-NLS-1$
	/**
	 * The partitioner's scanner
	 * 
	 * @since 2.2
	 */
	protected final IPartitionTokenScanner fScanner;
	/**
	 * The legal content types of this partitioner
	 * 
	 * @since 2.2
	 */
	protected final String[] fLegalContentTypes;
	/**
	 * The partitioner's document
	 * 
	 * @since 2.2
	 */
	protected IDocument fDocument;
	/**
	 * The position updater used to for the default updating of partitions
	 * 
	 * @since 2.2
	 */
	protected final DefaultPositionUpdater fPositionUpdater;
	/**
	 * The offset at which the first changed partition starts
	 * 
	 * @since 2.2
	 */
	protected int fStartOffset;
	/**
	 * The offset at which the last changed partition ends
	 * 
	 * @since 2.2
	 */
	protected int fEndOffset;
	/**
	 * The offset at which a partition has been deleted
	 * 
	 * @since 2.2
	 */
	protected int fDeleteOffset;
	/**
	 * The position category this partitioner uses to store the document's partitioning information.
	 * 
	 * @since 2.2
	 */
	protected final String fPositionCategory;
	/**
	 * The active document rewrite session.
	 * 
	 * @since 2.2
	 */
	protected DocumentRewriteSession fActiveRewriteSession;
	/**
	 * Flag indicating whether this partitioner has been initialized.
	 * 
	 * @since 2.2
	 */
	protected boolean fIsInitialized = false;
	/**
	 * The cached positions from our document, so we don't create a new array every time someone requests partition
	 * information.
	 * 
	 * @since 2.2
	 */
	protected Position[] fCachedPositions = null;
	/**
	 * Debug option for cache consistency checking.
	 * 
	 * @since 2.2
	 */
	protected static final boolean CHECK_CACHE_CONSISTENCY = "true".equalsIgnoreCase(Platform.getDebugOption("org.eclipse.jface.text/debug/FastPartitioner/PositionCache")); //$NON-NLS-1$//$NON-NLS-2$;

	/**
	 * Creates a new partitioner that uses the given scanner and may return partitions of the given legal content types.
	 * 
	 * @param scanner
	 *            the scanner this partitioner is supposed to use
	 * @param legalContentTypes
	 *            the legal content types of this partitioner
	 * @since 2.2
	 */
	public DocumentPartitioner(IPartitionTokenScanner scanner, String[] legalContentTypes) {
		fScanner = scanner;
		fLegalContentTypes = TextUtilities.copy(legalContentTypes);
		fPositionCategory = CONTENT_TYPES_CATEGORY + hashCode();
		fPositionUpdater = new DefaultPositionUpdater(fPositionCategory);
	}

	@Inject
	public DocumentPartitioner(IPartitionTokenScanner scanner, ITokenTypeToPartitionTypeMapper mapper) {
		this(scanner, mapper.getSupportedPartitionTypes());
	}

	/*
	 * @see org.eclipse.jface.text.IDocumentPartitionerExtension2#getManagingPositionCategories()
	 */
	/**
	 * @since 2.2
	 */
	@Override
	public String[] getManagingPositionCategories() {
		return new String[] { fPositionCategory };
	}

	/*
	 * @see org.eclipse.jface.text.IDocumentPartitioner#connect(org.eclipse.jface.text.IDocument)
	 */
	/**
	 * @since 2.2
	 */
	@Override
	public final void connect(IDocument document) {
		connect(document, false);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized void connect(IDocument document, boolean delayInitialization) {
		Assert.isNotNull(document);
		Assert.isTrue(!document.containsPositionCategory(fPositionCategory));

		fDocument = document;
		fDocument.addPositionCategory(fPositionCategory);

		fIsInitialized = false;
		if (!delayInitialization)
			checkInitialization();
	}

	/**
	 * Calls {@link #initialize()} if the receiver is not yet initialized.
	 * 
	 * @since 2.2
	 */
	protected final void checkInitialization() {
		if (!fIsInitialized)
			initialize();
	}

	/**
	 * Performs the initial partitioning of the partitioner's document.
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	protected void initialize() {
		fIsInitialized = true;
		clearPositionCache();
		fScanner.setRange(fDocument, 0, fDocument.getLength());

		try {
			IToken token = fScanner.nextToken();
			while (!token.isEOF()) {

				String contentType = getTokenContentType(token);

				if (isSupportedContentType(contentType)) {
					TypedPosition p = new TypedPosition(fScanner.getTokenOffset(), fScanner.getTokenLength(),
							contentType);
					fDocument.addPosition(fPositionCategory, p);
				}

				token = fScanner.nextToken();
			}
		} catch (BadLocationException x) {
			// cannot happen as offsets come from scanner
		} catch (BadPositionCategoryException x) {
			// cannot happen if document has been connected before
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized void disconnect() {

		Assert.isTrue(fDocument.containsPositionCategory(fPositionCategory));

		try {
			fDocument.removePositionCategory(fPositionCategory);
		} catch (BadPositionCategoryException x) {
			// can not happen because of Assert
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized void documentAboutToBeChanged(DocumentEvent e) {
		if (fIsInitialized) {

			Assert.isTrue(e.getDocument() == fDocument);

			fStartOffset = -1;
			fEndOffset = -1;
			fDeleteOffset = -1;
		}
	}

	/*
	 * @see IDocumentPartitioner#documentChanged(DocumentEvent)
	 */
	/**
	 * @since 2.2
	 */
	@Override
	public synchronized final boolean documentChanged(DocumentEvent e) {
		if (fIsInitialized) {
			IRegion region = documentChanged2(e);
			return (region != null);
		}
		return false;
	}

	/**
	 * Helper method for tracking the minimal region containing all partition changes. If <code>offset</code> is smaller
	 * than the remembered offset, <code>offset</code> will from now on be remembered. If <code>offset  + length</code>
	 * is greater than the remembered end offset, it will be remembered from now on.
	 * 
	 * @param offset
	 *            the offset
	 * @param length
	 *            the length
	 */
	private void rememberRegion(int offset, int length) {
		// remember start offset
		if (fStartOffset == -1)
			fStartOffset = offset;
		else if (offset < fStartOffset)
			fStartOffset = offset;

		// remember end offset
		int endOffset = offset + length;
		if (fEndOffset == -1)
			fEndOffset = endOffset;
		else if (endOffset > fEndOffset)
			fEndOffset = endOffset;
	}

	/**
	 * Remembers the given offset as the deletion offset.
	 * 
	 * @param offset
	 *            the offset
	 */
	private void rememberDeletedOffset(int offset) {
		fDeleteOffset = offset;
	}

	/**
	 * Creates the minimal region containing all partition changes using the remembered offset, end offset, and deletion
	 * offset.
	 * 
	 * @return the minimal region containing all the partition changes
	 */
	private IRegion createRegion() {
		if (fDeleteOffset == -1) {
			if (fStartOffset == -1 || fEndOffset == -1)
				return null;
			return new Region(fStartOffset, fEndOffset - fStartOffset);
		} else if (fStartOffset == -1 || fEndOffset == -1) {
			return new Region(fDeleteOffset, 0);
		} else {
			int offset = Math.min(fDeleteOffset, fStartOffset);
			int endOffset = Math.max(fDeleteOffset, fEndOffset);
			return new Region(offset, endOffset - offset);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized IRegion documentChanged2(DocumentEvent e) {

		if (!fIsInitialized)
			return null;

		try {
			Assert.isTrue(e.getDocument() == fDocument);

			Position[] category = getPositions();

			int reparseStart = e.getOffset();
			String contentType = null;
			int newLength = e.getText() == null ? 0 : e.getText().length();
			int oldPosition = fDocument.computeIndexInCategory(fPositionCategory, reparseStart);
			if (oldPosition > 0) {
				Position oldPartition = category[oldPosition - 1];
				if (oldPartition.offset + oldPartition.length > reparseStart)
					reparseStart = category[oldPosition - 1].offset;
			}
			fScanner.setPartialRange(fDocument, reparseStart, fDocument.getLength() - reparseStart, /* ignore contentType */
					null, reparseStart);

			int behindLastScannedPosition = reparseStart;
			IToken token = fScanner.nextToken();
			int actualReparseStart = fScanner.getTokenOffset();
			int partitionStart = -1;
			int first = fDocument.computeIndexInCategory(fPositionCategory, actualReparseStart);
			if (first > 0) {
				TypedPosition partition = (TypedPosition) category[first - 1];
				if (partition.includes(actualReparseStart)) {
					partitionStart = partition.getOffset();
					contentType = partition.getType();
					if (e.getOffset() == partition.getOffset() + partition.getLength())
						actualReparseStart = partitionStart;
					--first;
				} else if (actualReparseStart == e.getOffset()
						&& actualReparseStart == partition.getOffset() + partition.getLength()) {
					partitionStart = partition.getOffset();
					contentType = partition.getType();
					actualReparseStart = partitionStart;
					--first;
				} else {
					partitionStart = partition.getOffset() + partition.getLength();
					contentType = IDocument.DEFAULT_CONTENT_TYPE;
					
					if (actualReparseStart != partitionStart) {
						String message = String.format(
								"Detected unexpected state in document partitioner. Please file a bug with the following information attached:%n" +
								"Document content after the event was applied:%n" +
								">>>%s<<<%n" +
								"Document event: %s", fDocument.get(), String.valueOf(e));
						log.error(message);
					}
				}
			}

			fScanner.setPartialRange(fDocument, actualReparseStart, fDocument.getLength() - actualReparseStart, contentType, 
					actualReparseStart);
			behindLastScannedPosition = actualReparseStart;
			token = fScanner.nextToken();

			fPositionUpdater.update(e);
			for (int i = first; i < category.length; i++) {
				Position p = category[i];
				if (p.isDeleted) {
					rememberDeletedOffset(e.getOffset());
					break;
				}
			}
			clearPositionCache();
			category = getPositions();

			while (!token.isEOF()) {

				contentType = getTokenContentType(token);

				if (!isSupportedContentType(contentType)) {
					token = fScanner.nextToken();
					continue;
				}

				int start = fScanner.getTokenOffset();
				int length = fScanner.getTokenLength();

				behindLastScannedPosition = start + length;
				int lastScannedPosition = behindLastScannedPosition - 1;

				// remove all affected positions
				while (first < category.length) {
					TypedPosition p = (TypedPosition) category[first];
					if (lastScannedPosition >= p.offset + p.length
							|| (p.overlapsWith(start, length) && (!fDocument.containsPosition(fPositionCategory, start,
									length) || !contentType.equals(p.getType())))) {

						rememberRegion(p.offset, p.length);
						fDocument.removePosition(fPositionCategory, p);
						++first;

					} else
						break;
				}

				// if position already exists and we have scanned at least the
				// area covered by the event, we are done
				if (fDocument.containsPosition(fPositionCategory, start, length)) {
					if (lastScannedPosition >= e.getOffset() + newLength)
						return createRegion();
					++first;
				} else {
					// insert the new type position
					try {
						fDocument.addPosition(fPositionCategory, new TypedPosition(start, length, contentType));
						rememberRegion(start, length);
					} catch (BadPositionCategoryException x) {
					} catch (BadLocationException x) {
					}
				}

				token = fScanner.nextToken();
			}

			first = fDocument.computeIndexInCategory(fPositionCategory, behindLastScannedPosition);

			clearPositionCache();
			category = getPositions();
			TypedPosition p;
			while (first < category.length) {
				p = (TypedPosition) category[first++];
				fDocument.removePosition(fPositionCategory, p);
				rememberRegion(p.offset, p.length);
			}

		} catch (BadPositionCategoryException x) {
			// should never happen on connected documents
		} catch (BadLocationException x) {
		} finally {
			clearPositionCache();
		}

		return createRegion();
	}

	/**
	 * Returns the position in the partitoner's position category which is close to the given offset. This is, the
	 * position has either an offset which is the same as the given offset or an offset which is smaller than the given
	 * offset. This method profits from the knowledge that a partitioning is a ordered set of disjoint position.
	 * <p>
	 * May be extended or replaced by subclasses.
	 * </p>
	 * 
	 * @param offset
	 *            the offset for which to search the closest position
	 * @return the closest position in the partitioner's category
	 * @since 2.2
	 */
	protected TypedPosition findClosestPosition(int offset) {

		try {

			int index = fDocument.computeIndexInCategory(fPositionCategory, offset);
			Position[] category = getPositions();

			if (category.length == 0)
				return null;

			if (index < category.length) {
				if (offset == category[index].offset)
					return (TypedPosition) category[index];
			}

			if (index > 0)
				index--;

			return (TypedPosition) category[index];

		} catch (BadPositionCategoryException x) {
		} catch (BadLocationException x) {
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be replaced or extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized String getContentType(int offset) {
		checkInitialization();

		TypedPosition p = findClosestPosition(offset);
		if (p != null && p.includes(offset))
			return p.getType();

		return IDocument.DEFAULT_CONTENT_TYPE;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be replaced or extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized ITypedRegion getPartition(int offset) {
		checkInitialization();

		try {

			Position[] category = getPositions();

			if (category == null || category.length == 0)
				return new TypedRegion(0, fDocument.getLength(), IDocument.DEFAULT_CONTENT_TYPE);

			int index = fDocument.computeIndexInCategory(fPositionCategory, offset);

			if (index < category.length) {

				TypedPosition next = (TypedPosition) category[index];

				if (offset == next.offset)
					return new TypedRegion(next.getOffset(), next.getLength(), next.getType());

				if (index == 0)
					return new TypedRegion(0, next.offset, IDocument.DEFAULT_CONTENT_TYPE);

				TypedPosition previous = (TypedPosition) category[index - 1];
				if (previous.includes(offset))
					return new TypedRegion(previous.getOffset(), previous.getLength(), previous.getType());

				int endOffset = previous.getOffset() + previous.getLength();
				return new TypedRegion(endOffset, next.getOffset() - endOffset, IDocument.DEFAULT_CONTENT_TYPE);
			}

			TypedPosition previous = (TypedPosition) category[category.length - 1];
			if (previous.includes(offset)) {
				return new TypedRegion(previous.getOffset(), previous.getLength(), previous.getType());
			}
			
			if (isOpenSingleLineCommentPartition(previous, offset)) {
				return new TypedRegion(previous.getOffset(), previous.getLength() + 1, previous.getType());
			}

			int endOffset = previous.getOffset() + previous.getLength();
			return new TypedRegion(endOffset, fDocument.getLength() - endOffset, IDocument.DEFAULT_CONTENT_TYPE);

		} catch (BadPositionCategoryException x) {
		} catch (BadLocationException x) {
		}

		return new TypedRegion(0, fDocument.getLength(), IDocument.DEFAULT_CONTENT_TYPE);
	}

	private boolean isOpenSingleLineCommentPartition(TypedPosition position, int offset) throws BadLocationException {
		if (position.isDeleted()) {
			return false;
		}
		int endOffset = position.getOffset() + position.getLength();
		if (offset != endOffset) {
			return false;
		}
		if (!TerminalsTokenTypeToPartitionMapper.SL_COMMENT_PARTITION.equals(position.getType())) {
			return false;
		}
		int line = fDocument.getLineOfOffset(offset - 1);
		return fDocument.getLineDelimiter(line) == null;
	}

	/*
	 * @see IDocumentPartitioner#computePartitioning(int, int)
	 */
	/**
	 * @since 2.2
	 */
	@Override
	public final ITypedRegion[] computePartitioning(int offset, int length) {
		return computePartitioning(offset, length, false);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be replaced or extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public String[] getLegalContentTypes() {
		return TextUtilities.copy(fLegalContentTypes);
	}

	/**
	 * Returns whether the given type is one of the legal content types.
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @param contentType
	 *            the content type to check
	 * @return <code>true</code> if the content type is a legal content type
	 * @since 2.2
	 */
	protected boolean isSupportedContentType(String contentType) {
		if (contentType != null) {
			for (int i = 0; i < fLegalContentTypes.length; i++) {
				if (fLegalContentTypes[i].equals(contentType))
					return true;
			}
		}

		return false;
	}

	/**
	 * Returns a content type encoded in the given token. If the token's data is not <code>null</code> and a string it
	 * is assumed that it is the encoded content type.
	 * <p>
	 * May be replaced or extended by subclasses.
	 * </p>
	 * 
	 * @param token
	 *            the token whose content type is to be determined
	 * @return the token's content type
	 * @since 2.2
	 */
	protected String getTokenContentType(IToken token) {
		Object data = token.getData();
		if (data instanceof String)
			return (String) data;
		return null;
	}

	/* zero-length partition support */

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be replaced or extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public String getContentType(int offset, boolean preferOpenPartitions) {
		return getPartition(offset, preferOpenPartitions).getType();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be replaced or extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized ITypedRegion getPartition(int offset, boolean preferOpenPartitions) {
		ITypedRegion region = getPartition(offset);
		if (preferOpenPartitions) {
			if (region.getOffset() == offset && !region.getType().equals(IDocument.DEFAULT_CONTENT_TYPE)) {
				if (offset > 0) {
					region = getPartition(offset - 1);
					if (region.getType().equals(IDocument.DEFAULT_CONTENT_TYPE))
						return region;
				}
				return new TypedRegion(offset, 0, IDocument.DEFAULT_CONTENT_TYPE);
			}
		}
		return region;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be replaced or extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public synchronized ITypedRegion[] computePartitioning(int offset, int length, boolean includeZeroLengthPartitions) {
		checkInitialization();
		List<ITypedRegion> list = new ArrayList<ITypedRegion>();

		try {

			int endOffset = offset + length;

			Position[] category = getPositions();

			TypedPosition previous = null, current = null;
			int start, end, gapOffset;
			Position gap = new Position(0);

			int startIndex = getFirstIndexEndingAfterOffset(category, offset);
			int endIndex = getFirstIndexStartingAfterOffset(category, endOffset);
			for (int i = startIndex; i < endIndex; i++) {

				current = (TypedPosition) category[i];

				gapOffset = (previous != null) ? previous.getOffset() + previous.getLength() : 0;
				gap.setOffset(gapOffset);
				gap.setLength(current.getOffset() - gapOffset);
				if ((includeZeroLengthPartitions && overlapsOrTouches(gap, offset, length))
						|| (gap.getLength() > 0 && gap.overlapsWith(offset, length))) {
					start = Math.max(offset, gapOffset);
					end = Math.min(endOffset, gap.getOffset() + gap.getLength());
					list.add(new TypedRegion(start, end - start, IDocument.DEFAULT_CONTENT_TYPE));
				}

				if (current.overlapsWith(offset, length)) {
					start = Math.max(offset, current.getOffset());
					end = Math.min(endOffset, current.getOffset() + current.getLength());
					list.add(new TypedRegion(start, end - start, current.getType()));
				}

				previous = current;
			}

			if (previous != null) {
				gapOffset = previous.getOffset() + previous.getLength();
				gap.setOffset(gapOffset);
				int gapLength = fDocument.getLength() - gapOffset;
				if(gapLength < 0) {
					clearPositionCache();
					return new TypedRegion[0];
				} else {
					gap.setLength(gapLength);
					if ((includeZeroLengthPartitions && overlapsOrTouches(gap, offset, length))
							|| (gap.getLength() > 0 && gap.overlapsWith(offset, length))) {
						start = Math.max(offset, gapOffset);
						end = Math.min(endOffset, fDocument.getLength());
						list.add(new TypedRegion(start, end - start, IDocument.DEFAULT_CONTENT_TYPE));
					}
				}
			}

			if (list.isEmpty())
				list.add(new TypedRegion(offset, length, IDocument.DEFAULT_CONTENT_TYPE));

		} catch (BadPositionCategoryException ex) {
			// Make sure we clear the cache
			clearPositionCache();
		} catch (RuntimeException ex) {
			// Make sure we clear the cache
			clearPositionCache();
			throw ex;
		}

		TypedRegion[] result = new TypedRegion[list.size()];
		list.toArray(result);
		return result;
	}

	/**
	 * Returns <code>true</code> if the given ranges overlap with or touch each other.
	 * 
	 * @param gap
	 *            the first range
	 * @param offset
	 *            the offset of the second range
	 * @param length
	 *            the length of the second range
	 * @return <code>true</code> if the given ranges overlap with or touch each other
	 */
	private boolean overlapsOrTouches(Position gap, int offset, int length) {
		return gap.getOffset() <= offset + length && offset <= gap.getOffset() + gap.getLength();
	}

	/**
	 * Returns the index of the first position which ends after the given offset.
	 * 
	 * @param positions
	 *            the positions in linear order
	 * @param offset
	 *            the offset
	 * @return the index of the first position which ends after the offset
	 */
	private int getFirstIndexEndingAfterOffset(Position[] positions, int offset) {
		int i = -1, j = positions.length;
		while (j - i > 1) {
			int k = (i + j) >> 1;
			Position p = positions[k];
			if (p.getOffset() + p.getLength() > offset)
				j = k;
			else
				i = k;
		}
		return j;
	}

	/**
	 * Returns the index of the first position which starts at or after the given offset.
	 * 
	 * @param positions
	 *            the positions in linear order
	 * @param offset
	 *            the offset
	 * @return the index of the first position which starts after the offset
	 */
	private int getFirstIndexStartingAfterOffset(Position[] positions, int offset) {
		int i = -1, j = positions.length;
		while (j - i > 1) {
			int k = (i + j) >> 1;
			Position p = positions[k];
			if (p.getOffset() >= offset)
				j = k;
			else
				i = k;
		}
		return j;
	}

	/*
	 * @see org.eclipse.jface.text.IDocumentPartitionerExtension3#startRewriteSession(org.eclipse.jface.text.DocumentRewriteSession)
	 */
	/**
	 * @since 2.2
	 */
	@Override
	public void startRewriteSession(DocumentRewriteSession session) throws IllegalStateException {
		if (fActiveRewriteSession != null)
			throw new IllegalStateException();
		fActiveRewriteSession = session;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public void stopRewriteSession(DocumentRewriteSession session) {
		if (fActiveRewriteSession == session)
			flushRewriteSession();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * May be extended by subclasses.
	 * </p>
	 * 
	 * @since 2.2
	 */
	@Override
	public DocumentRewriteSession getActiveRewriteSession() {
		return fActiveRewriteSession;
	}

	/**
	 * Flushes the active rewrite session.
	 * 
	 * @since 2.2
	 */
	protected synchronized final void flushRewriteSession() {
		fActiveRewriteSession = null;

		// remove all position belonging to the partitioner position category
		try {
			fDocument.removePositionCategory(fPositionCategory);
		} catch (BadPositionCategoryException x) {
		}
		fDocument.addPositionCategory(fPositionCategory);

		fIsInitialized = false;
	}

	/**
	 * Clears the position cache. Needs to be called whenever the positions have been updated.
	 * 
	 * @since 2.2
	 */
	protected final void clearPositionCache() {
		if (fCachedPositions != null) {
			fCachedPositions = null;
		}
	}

	/**
	 * Returns the partitioners positions.
	 * 
	 * @return the partitioners positions
	 * @throws BadPositionCategoryException
	 *             if getting the positions from the document fails
	 * @since 2.2
	 */
	protected synchronized final Position[] getPositions() throws BadPositionCategoryException {
		if (fCachedPositions == null) {
			fCachedPositions = fDocument.getPositions(fPositionCategory);
		} else if (CHECK_CACHE_CONSISTENCY) {
			Position[] positions = fDocument.getPositions(fPositionCategory);
			int len = Math.min(positions.length, fCachedPositions.length);
			for (int i = 0; i < len; i++) {
				if (!positions[i].equals(fCachedPositions[i]))
					System.err
							.println("FastPartitioner.getPositions(): cached position is not up to date: from document: " + toString(positions[i]) + " in cache: " + toString(fCachedPositions[i])); //$NON-NLS-1$ //$NON-NLS-2$
			}
			for (int i = len; i < positions.length; i++)
				System.err
						.println("FastPartitioner.getPositions(): new position in document: " + toString(positions[i])); //$NON-NLS-1$
			for (int i = len; i < fCachedPositions.length; i++)
				System.err
						.println("FastPartitioner.getPositions(): stale position in cache: " + toString(fCachedPositions[i])); //$NON-NLS-1$
		}
		return fCachedPositions;
	}

	/**
	 * Pretty print a <code>Position</code>.
	 * 
	 * @param position
	 *            the position to format
	 * @return a formatted string
	 */
	private String toString(Position position) {
		return "P[" + position.getOffset() + "+" + position.getLength() + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
