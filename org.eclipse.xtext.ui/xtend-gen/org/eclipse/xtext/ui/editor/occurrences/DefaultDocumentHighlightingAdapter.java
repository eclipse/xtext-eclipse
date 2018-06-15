/**
 * Copyright (c) 2018 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.editor.occurrences;

import com.google.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.lsp4j.DocumentHighlight;
import org.eclipse.lsp4j.DocumentHighlightKind;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextDocumentPositionParams;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.builder.MonitorBasedCancelIndicator;
import org.eclipse.xtext.ide.server.Document;
import org.eclipse.xtext.ide.server.occurrences.IDocumentHighlightService;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.occurrences.DefaultOccurrenceComputer;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.util.internal.Log;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * An adapter to compute IDE document highlighting to Eclipse occurrence computing
 * 
 * @since 2.15
 * @author Titouan Vervack - Initial contribution and API
 */
@Log
@SuppressWarnings("all")
public class DefaultDocumentHighlightingAdapter extends DefaultOccurrenceComputer {
  @Inject
  private IDocumentHighlightService highlightService;
  
  @Override
  public Map<Annotation, Position> createAnnotationMap(final XtextEditor editor, final ITextSelection selection, final SubMonitor monitor) {
    final IUnitOfWork<Map<Annotation, Position>, XtextResource> _function = (XtextResource resource) -> {
      final String contents = this.getContents(resource);
      if ((contents == null)) {
        return null;
      }
      final Document document = new Document(Integer.valueOf(0), contents);
      String _string = resource.getURI().toString();
      final TextDocumentIdentifier textDocumentIdentifier = new TextDocumentIdentifier(_string);
      try {
        final org.eclipse.lsp4j.Position position = document.getPosition(selection.getOffset());
        final TextDocumentPositionParams positionParams = new TextDocumentPositionParams(textDocumentIdentifier, position);
        final MonitorBasedCancelIndicator cancelIndicator = new MonitorBasedCancelIndicator(monitor);
        final List<? extends DocumentHighlight> highlights = this.highlightService.getDocumentHighlights(document, resource, positionParams, cancelIndicator);
        return this.createAnnotationMap(document, highlights);
      } catch (final Throwable _t) {
        if (_t instanceof IndexOutOfBoundsException) {
          final IndexOutOfBoundsException e = (IndexOutOfBoundsException)_t;
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Failed to mark occurrences at index ");
          int _offset = selection.getOffset();
          _builder.append(_offset);
          _builder.append(". Document length was ");
          int _length = document.getContents().length();
          _builder.append(_length);
          DefaultDocumentHighlightingAdapter.LOG.warn(_builder, e);
          return null;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    };
    final Supplier<Map<Annotation, Position>> _function_1 = () -> {
      return null;
    };
    final Map<Annotation, Position> annotationMap = editor.getDocument().<Map<Annotation, Position>>tryReadOnly(_function, _function_1);
    Map<Annotation, Position> _elvis = null;
    if (annotationMap != null) {
      _elvis = annotationMap;
    } else {
      Map<Annotation, Position> _createAnnotationMap = super.createAnnotationMap(editor, selection, monitor);
      _elvis = _createAnnotationMap;
    }
    return _elvis;
  }
  
  protected String getContents(final XtextResource r) {
    IParseResult _parseResult = r.getParseResult();
    ICompositeNode _rootNode = null;
    if (_parseResult!=null) {
      _rootNode=_parseResult.getRootNode();
    }
    String _text = null;
    if (_rootNode!=null) {
      _text=_rootNode.getText();
    }
    return _text;
  }
  
  protected Map<Annotation, Position> createAnnotationMap(final Document document, final List<? extends DocumentHighlight> highlights) {
    final HashMap<Annotation, Position> annotationMap = new HashMap<Annotation, Position>();
    for (final DocumentHighlight highlight : highlights) {
      {
        final int start = document.getOffSet(highlight.getRange().getStart());
        final int end = document.getOffSet(highlight.getRange().getEnd());
        final int length = (end - start);
        final Position position = new Position(start, length);
        final String range = document.getSubstring(highlight.getRange());
        String _transformToType = this.transformToType(highlight.getKind());
        final Annotation annotation = new Annotation(_transformToType, false, range);
        annotationMap.put(annotation, position);
      }
    }
    return annotationMap;
  }
  
  protected String transformToType(final DocumentHighlightKind kind) {
    String _switchResult = null;
    if (kind != null) {
      switch (kind) {
        case Read:
          _switchResult = DefaultOccurrenceComputer.OCCURRENCE_ANNOTATION_TYPE;
          break;
        case Write:
          _switchResult = DefaultOccurrenceComputer.DECLARATION_ANNOTATION_TYPE;
          break;
        default:
          _switchResult = null;
          break;
      }
    } else {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  private final static Logger LOG = Logger.getLogger(DefaultDocumentHighlightingAdapter.class);
}
