/*******************************************************************************
 * Copyright (c) 2018 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.occurrences

import com.google.inject.Inject
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.core.runtime.SubMonitor
import org.eclipse.jface.text.ITextSelection
import org.eclipse.jface.text.Position
import org.eclipse.jface.text.source.Annotation
import org.eclipse.lsp4j.DocumentHighlight
import org.eclipse.lsp4j.DocumentHighlightKind
import org.eclipse.lsp4j.TextDocumentIdentifier
import org.eclipse.lsp4j.TextDocumentPositionParams
import org.eclipse.xtext.builder.MonitorBasedCancelIndicator
import org.eclipse.xtext.ide.server.Document
import org.eclipse.xtext.ide.server.occurrences.IDocumentHighlightService
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.XtextEditor
import org.eclipse.xtext.util.internal.Log

/**
 * An adapter to compute IDE document highlighting to Eclipse occurrence computing
 * 
 * @since 2.15
 * @author Titouan Vervack - Initial contribution and API
 */
@Log
class DefaultDocumentHighlightingAdapter extends DefaultOccurrenceComputer {

	@Inject IDocumentHighlightService highlightService

	override createAnnotationMap(XtextEditor editor, ITextSelection selection, SubMonitor monitor) {
		val annotationMap = editor.document.tryReadOnly(
			[ resource |
				val contents = getContents(resource)
				if(contents === null) return null

				val document = new Document(0, contents)
				val textDocumentIdentifier = new TextDocumentIdentifier(resource.URI.toString)
				try {
					val position = document.getPosition(selection.offset)
					val positionParams = new TextDocumentPositionParams(textDocumentIdentifier, position)
					val cancelIndicator = new MonitorBasedCancelIndicator(monitor)

					val highlights = highlightService.getDocumentHighlights(document, resource, positionParams, cancelIndicator)
					return document.createAnnotationMap(highlights)
				} catch (IndexOutOfBoundsException e) {
					LOG.warn('''Failed to mark occurrences at index «selection.offset». Document length was «document.contents.length»''',e)
					return null
				}
			],
			[null]
		)
		return annotationMap ?: super.createAnnotationMap(editor, selection, monitor)
	}

	protected def String getContents(XtextResource r) {
		r.parseResult?.rootNode?.text
	}

	protected def Map<Annotation, Position> createAnnotationMap(Document document,
		List<? extends DocumentHighlight> highlights) {
		val annotationMap = new HashMap
		for (highlight : highlights) {
			val start = document.getOffSet(highlight.range.start)
			val end = document.getOffSet(highlight.range.end)
			val length = end - start

			val position = new Position(start, length)
			val range = document.getSubstring(highlight.range)
			val annotation = new Annotation(highlight.kind.transformToType, false, range)
			annotationMap.put(annotation, position)
		}
		return annotationMap
	}

	protected def String transformToType(DocumentHighlightKind kind) {
		switch kind {
			case Read: OCCURRENCE_ANNOTATION_TYPE
			case Write: DECLARATION_ANNOTATION_TYPE
			default: null
		}
	}
}
