package org.eclipse.xtext.ui.refactoring.participant;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.List;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.ltk.core.refactoring.resource.MoveResourceChange;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.xtext.formatting2.regionaccess.ITextReplacement;
import org.eclipse.xtext.ide.serializer.IEmfResourceChange;
import org.eclipse.xtext.ide.serializer.ITextDocumentChange;
import org.eclipse.xtext.ui.refactoring.impl.StatusWrapper;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ChangeConverter implements IAcceptor<IEmfResourceChange> {
  private CompositeChange currentChange;
  
  private StatusWrapper status;
  
  private Predicate<Change> changeFilter;
  
  @Inject(optional = true)
  private IWorkspace workspace;
  
  public Predicate<Change> initialize(final String name, final StatusWrapper status, final Predicate<Change> changeFilter) {
    Predicate<Change> _xblockexpression = null;
    {
      CompositeChange _compositeChange = new CompositeChange(name);
      this.currentChange = _compositeChange;
      this.status = status;
      _xblockexpression = this.changeFilter = changeFilter;
    }
    return _xblockexpression;
  }
  
  @Override
  public void accept(final IEmfResourceChange emfResourceChange) {
    this.doConvert(emfResourceChange);
  }
  
  public Change getChange() {
    int _length = this.currentChange.getChildren().length;
    boolean _tripleEquals = (_length == 0);
    if (_tripleEquals) {
      return null;
    } else {
      return this.currentChange;
    }
  }
  
  protected void _doConvert(final IEmfResourceChange change) {
    this.handleUriChange(change);
  }
  
  protected void _doConvert(final ITextDocumentChange change) {
    this.handleReplacements(change);
    this.handleUriChange(change);
  }
  
  protected void handleReplacements(final ITextDocumentChange change) {
    int _size = change.getReplacements().size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      final Function1<ITextReplacement, ReplaceEdit> _function = (ITextReplacement replacement) -> {
        int _offset = replacement.getOffset();
        int _length = replacement.getLength();
        String _replacementText = replacement.getReplacementText();
        return new ReplaceEdit(_offset, _length, _replacementText);
      };
      final List<ReplaceEdit> textEdits = ListExtensions.<ITextReplacement, ReplaceEdit>map(change.getReplacements(), _function);
      final IFile file = this.toFile(change.getNewURI());
      final MultiTextEdit textEdit = new MultiTextEdit();
      textEdit.addChildren(((TextEdit[])Conversions.unwrapArray(textEdits, TextEdit.class)));
      String _lastSegment = change.getOldURI().lastSegment();
      final TextFileChange textFileChange = new TextFileChange(_lastSegment, file);
      textFileChange.setSaveMode(TextFileChange.FORCE_SAVE);
      textFileChange.setEdit(textEdit);
      textFileChange.setTextType(change.getOldURI().fileExtension());
      this.addChange(textFileChange);
    }
  }
  
  protected void handleUriChange(final IEmfResourceChange change) {
    URI _newURI = change.getNewURI();
    URI _oldURI = change.getOldURI();
    boolean _notEquals = (!Objects.equal(_newURI, _oldURI));
    if (_notEquals) {
      String _lastSegment = change.getNewURI().lastSegment();
      String _lastSegment_1 = change.getOldURI().lastSegment();
      boolean _equals = Objects.equal(_lastSegment, _lastSegment_1);
      if (_equals) {
        final IFile newFile = this.toFile(change.getNewURI());
        final IContainer newContainer = newFile.getParent();
        final IFile oldFile = this.toFile(change.getOldURI());
        final MoveResourceChange ltkChange = new MoveResourceChange(oldFile, newContainer);
        this.addChange(ltkChange);
      }
    }
  }
  
  protected void addChange(final Change change) {
    boolean _apply = this.changeFilter.apply(change);
    if (_apply) {
      this.currentChange.add(change);
    }
  }
  
  protected IFile toFile(final URI uri) {
    IWorkspaceRoot _root = this.workspace.getRoot();
    String _platformString = uri.toPlatformString(true);
    Path _path = new Path(_platformString);
    return _root.getFile(_path);
  }
  
  protected void doConvert(final IEmfResourceChange change) {
    if (change instanceof ITextDocumentChange) {
      _doConvert((ITextDocumentChange)change);
      return;
    } else if (change != null) {
      _doConvert(change);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(change).toString());
    }
  }
}
