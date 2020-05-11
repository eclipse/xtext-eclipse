/**
 * Copyright (c) 2017, 2020 TypeFox GmbH (http://www.typefox.io) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.tests.editor.quickfix;

import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.testing.AbstractWorkbenchTest;
import org.eclipse.xtext.ui.tests.editor.quickfix.AbstractQuickfixTest;
import org.eclipse.xtext.ui.tests.quickfix.ui.tests.QuickfixCrossrefTestLanguageUiInjectorProvider;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(QuickfixCrossrefTestLanguageUiInjectorProvider.class)
@SuppressWarnings("all")
public class CompositeQuickfixTest extends AbstractQuickfixTest {
  @Test
  public void testSimpleFixMultipleMarkers() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\"bad doc\"");
    _builder.newLine();
    _builder.append("Foo { ref Bor }");
    _builder.newLine();
    _builder.append("\"bad doc\"");
    _builder.newLine();
    _builder.append("Bor { }");
    _builder.newLine();
    final IFile resource = this.dslFile(_builder);
    final IMarker[] markers = this.getMarkers(resource);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("<0<\"bad doc\">0>");
    _builder_1.newLine();
    _builder_1.append("Foo { ref Bor }");
    _builder_1.newLine();
    _builder_1.append("<1<\"bad doc\">1>");
    _builder_1.newLine();
    _builder_1.append("Bor { }");
    _builder_1.newLine();
    _builder_1.append("---------------");
    _builder_1.newLine();
    _builder_1.append("0: message=multiFixableIssue2");
    _builder_1.newLine();
    _builder_1.append("1: message=multiFixableIssue2");
    _builder_1.newLine();
    this.assertContentsAndMarkers(resource, markers, _builder_1);
    this.applyQuickfixOnMultipleMarkers(markers);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("\"not bad doc\"");
    _builder_2.newLine();
    _builder_2.append("Foo { ref Bor }");
    _builder_2.newLine();
    _builder_2.append("\"not bad doc\"");
    _builder_2.newLine();
    _builder_2.append("Bor { }");
    _builder_2.newLine();
    _builder_2.append("---------------");
    _builder_2.newLine();
    _builder_2.append("(no markers found)");
    _builder_2.newLine();
    this.assertContentsAndMarkers(resource, _builder_2);
  }
  
  @Test
  public void testSimpleSingleMarker() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\"bad doc\"");
    _builder.newLine();
    _builder.append("Foo { ref Bor }");
    _builder.newLine();
    _builder.append("\"bad doc\"");
    _builder.newLine();
    _builder.append("Bor { }");
    _builder.newLine();
    final IFile resource = this.dslFile(_builder);
    final IMarker[] markers = this.getMarkers(resource);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("<0<\"bad doc\">0>");
    _builder_1.newLine();
    _builder_1.append("Foo { ref Bor }");
    _builder_1.newLine();
    _builder_1.append("<1<\"bad doc\">1>");
    _builder_1.newLine();
    _builder_1.append("Bor { }");
    _builder_1.newLine();
    _builder_1.append("---------------");
    _builder_1.newLine();
    _builder_1.append("0: message=multiFixableIssue2");
    _builder_1.newLine();
    _builder_1.append("1: message=multiFixableIssue2");
    _builder_1.newLine();
    this.assertContentsAndMarkers(resource, markers, _builder_1);
    final Function1<IMarker, Integer> _function = (IMarker it) -> {
      try {
        Object _attribute = it.getAttribute(IMarker.CHAR_START);
        return ((Integer) _attribute);
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    final IMarker firstMarker = IterableExtensions.<IMarker>head(IterableExtensions.<IMarker, Integer>sortBy(((Iterable<IMarker>)Conversions.doWrapArray(markers)), _function));
    this.applyQuickfixOnSingleMarkers(firstMarker);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("\"not bad doc\"");
    _builder_2.newLine();
    _builder_2.append("Foo { ref Bor }");
    _builder_2.newLine();
    _builder_2.append("<0<\"bad doc\">0>");
    _builder_2.newLine();
    _builder_2.append("Bor { }");
    _builder_2.newLine();
    _builder_2.append("---------------");
    _builder_2.newLine();
    _builder_2.append("0: message=multiFixableIssue2");
    _builder_2.newLine();
    this.assertContentsAndMarkers(resource, _builder_2);
  }
  
  @Test
  public void testSimpleQuickAssist() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\"bad doc\"");
    _builder.newLine();
    _builder.append("Foo { ref Bor }");
    _builder.newLine();
    _builder.append("\"bad doc\"");
    _builder.newLine();
    _builder.append("Bor { }");
    _builder.newLine();
    final IFile resource = this.dslFile(_builder);
    final XtextEditor editor = this.openEditor(resource);
    final ICompletionProposal[] proposals = this.computeQuickAssistProposals(editor, 1);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Multi fix 2");
    final Function1<ICompletionProposal, String> _function = (ICompletionProposal it) -> {
      return it.getDisplayString();
    };
    Assert.assertEquals(_builder_1.toString(), IterableExtensions.join(ListExtensions.<ICompletionProposal, String>map(((List<ICompletionProposal>)Conversions.doWrapArray(proposals)), _function), "\n"));
    IterableExtensions.<ICompletionProposal>head(((Iterable<ICompletionProposal>)Conversions.doWrapArray(proposals))).apply(editor.getDocument());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("\"not bad doc\"");
    _builder_2.newLine();
    _builder_2.append("Foo { ref Bor }");
    _builder_2.newLine();
    _builder_2.append("\"bad doc\"");
    _builder_2.newLine();
    _builder_2.append("Bor { }");
    _builder_2.newLine();
    Assert.assertEquals(_builder_2.toString(), editor.getDocument().get());
  }
  
  @Test
  public void testMultiFixMultipleMarkers() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("c {\tbadname { foo {} } }");
    _builder.newLine();
    _builder.append("a {\tbadname { bar {} } }");
    _builder.newLine();
    _builder.append("b {\tbadname { baz {} } }");
    _builder.newLine();
    final IFile resource = this.dslFile(_builder);
    final IMarker[] markers = this.getMarkers(resource);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("<0<c>0> {\tbadname { foo {} } }");
    _builder_1.newLine();
    _builder_1.append("<1<a>1> {\tbadname { bar {} } }");
    _builder_1.newLine();
    _builder_1.append("<2<b>2> {\tbadname { baz {} } }");
    _builder_1.newLine();
    _builder_1.append("---------------------------------");
    _builder_1.newLine();
    _builder_1.append("0: message=badNameInSubelements");
    _builder_1.newLine();
    _builder_1.append("1: message=badNameInSubelements");
    _builder_1.newLine();
    _builder_1.append("2: message=badNameInSubelements");
    _builder_1.newLine();
    this.assertContentsAndMarkers(resource, markers, _builder_1);
    this.applyQuickfixOnMultipleMarkers(markers);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("newElement { } c {\tgoodname { foo {} } }");
    _builder_2.newLine();
    _builder_2.append("newElement { } a {\tgoodname { bar {} } }");
    _builder_2.newLine();
    _builder_2.append("newElement { } b {\tgoodname { baz {} } }");
    _builder_2.newLine();
    _builder_2.append("-------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("(no markers found)");
    _builder_2.newLine();
    this.assertContentsAndMarkers(resource, _builder_2);
  }
  
  @Test
  public void testMultiFixSingleMarker() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("c {\tbadname { foo {} } }");
    _builder.newLine();
    _builder.append("a {\tbadname { bar {} } }");
    _builder.newLine();
    final IFile resource = this.dslFile(_builder);
    final IMarker[] markers = this.getMarkers(resource);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("<0<c>0> {\tbadname { foo {} } }");
    _builder_1.newLine();
    _builder_1.append("<1<a>1> {\tbadname { bar {} } }");
    _builder_1.newLine();
    _builder_1.append("---------------------------------");
    _builder_1.newLine();
    _builder_1.append("0: message=badNameInSubelements");
    _builder_1.newLine();
    _builder_1.append("1: message=badNameInSubelements");
    _builder_1.newLine();
    this.assertContentsAndMarkers(resource, markers, _builder_1);
    final Function1<IMarker, Integer> _function = (IMarker it) -> {
      try {
        Object _attribute = it.getAttribute(IMarker.CHAR_START);
        return ((Integer) _attribute);
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    final IMarker firstMarker = IterableExtensions.<IMarker>head(IterableExtensions.<IMarker, Integer>sortBy(((Iterable<IMarker>)Conversions.doWrapArray(markers)), _function));
    this.applyQuickfixOnSingleMarkers(firstMarker);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("newElement { } c {\tgoodname { foo {} } }");
    _builder_2.newLine();
    _builder_2.append("<0<a>0> {\tbadname { bar {} } }");
    _builder_2.newLine();
    _builder_2.append("-------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("0: message=badNameInSubelements");
    _builder_2.newLine();
    this.assertContentsAndMarkers(resource, _builder_2);
  }
  
  @Test
  public void testMultiQuickAssist() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("c {\tbadname { foo {} } }");
    _builder.newLine();
    _builder.append("a {\tbadname { bar {} } }");
    _builder.newLine();
    final IFile resource = this.dslFile(_builder);
    final XtextEditor editor = this.openEditor(resource);
    final ICompletionProposal[] proposals = this.computeQuickAssistProposals(editor, 1);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Fix Bad Names");
    final Function1<ICompletionProposal, String> _function = (ICompletionProposal it) -> {
      return it.getDisplayString();
    };
    Assert.assertEquals(_builder_1.toString(), IterableExtensions.join(ListExtensions.<ICompletionProposal, String>map(((List<ICompletionProposal>)Conversions.doWrapArray(proposals)), _function), "\n"));
    IterableExtensions.<ICompletionProposal>head(((Iterable<ICompletionProposal>)Conversions.doWrapArray(proposals))).apply(editor.getDocument());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("newElement { } c {\tgoodname { foo {} } }");
    _builder_2.newLine();
    _builder_2.append("a {\tbadname { bar {} } }");
    _builder_2.newLine();
    Assert.assertEquals(_builder_2.toString(), editor.getDocument().get());
  }
  
  @Test
  public void testNoCrossRef() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("fixable_a {\tref fixable_b }");
    _builder.newLine();
    _builder.append("fixable_b {\tref fixable_a }");
    _builder.newLine();
    final IFile resource = this.dslFile(_builder);
    final XtextEditor editor = this.openEditor(resource);
    final ICompletionProposal[] proposals = this.computeQuickAssistProposals(editor, 1);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("rename fixable");
    final Function1<ICompletionProposal, String> _function = (ICompletionProposal it) -> {
      return it.getDisplayString();
    };
    Assert.assertEquals(_builder_1.toString(), IterableExtensions.join(ListExtensions.<ICompletionProposal, String>map(((List<ICompletionProposal>)Conversions.doWrapArray(proposals)), _function), "\n"));
    IterableExtensions.<ICompletionProposal>head(((Iterable<ICompletionProposal>)Conversions.doWrapArray(proposals))).apply(editor.getDocument());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("fixedName {\tref fixable_b }");
    _builder_2.newLine();
    _builder_2.append("fixable_b {\tref fixable_a }");
    _builder_2.newLine();
    Assert.assertEquals(_builder_2.toString(), editor.getDocument().get());
  }
  
  @Test
  public void testTextualMultiModification() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("lowercase_a {}");
      _builder.newLine();
      _builder.append("lowercase_b {}");
      _builder.newLine();
      _builder.append("lowercase_c {}");
      _builder.newLine();
      _builder.append("lowercase_d {}");
      _builder.newLine();
      _builder.append("lowercase_e {}");
      _builder.newLine();
      _builder.append("lowercase_f {}");
      _builder.newLine();
      final IFile resource = this.dslFile(_builder);
      IEditorPart _openEditor = IDE.openEditor(AbstractWorkbenchTest.getActivePage(), resource);
      final XtextEditor xtextEditor = ((XtextEditor) _openEditor);
      final IMarker[] markers = this.getMarkers(resource);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("<0<lowercase_a>0> {}");
      _builder_1.newLine();
      _builder_1.append("<1<lowercase_b>1> {}");
      _builder_1.newLine();
      _builder_1.append("<2<lowercase_c>2> {}");
      _builder_1.newLine();
      _builder_1.append("<3<lowercase_d>3> {}");
      _builder_1.newLine();
      _builder_1.append("<4<lowercase_e>4> {}");
      _builder_1.newLine();
      _builder_1.append("<5<lowercase_f>5> {}");
      _builder_1.newLine();
      _builder_1.append("--------------------");
      _builder_1.newLine();
      _builder_1.append("0: message=lowercase");
      _builder_1.newLine();
      _builder_1.append("1: message=lowercase");
      _builder_1.newLine();
      _builder_1.append("2: message=lowercase");
      _builder_1.newLine();
      _builder_1.append("3: message=lowercase");
      _builder_1.newLine();
      _builder_1.append("4: message=lowercase");
      _builder_1.newLine();
      _builder_1.append("5: message=lowercase");
      _builder_1.newLine();
      this.assertContentsAndMarkers(resource, markers, _builder_1);
      this.applyQuickfixOnMultipleMarkers(markers);
      xtextEditor.doSave(null);
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("LOWERCASE_A_LOWERCASE_A {}");
      _builder_2.newLine();
      _builder_2.append("LOWERCASE_B_LOWERCASE_B {}");
      _builder_2.newLine();
      _builder_2.append("LOWERCASE_C_LOWERCASE_C {}");
      _builder_2.newLine();
      _builder_2.append("LOWERCASE_D_LOWERCASE_D {}");
      _builder_2.newLine();
      _builder_2.append("LOWERCASE_E_LOWERCASE_E {}");
      _builder_2.newLine();
      _builder_2.append("LOWERCASE_F_LOWERCASE_F {}");
      _builder_2.newLine();
      _builder_2.append("--------------------------");
      _builder_2.newLine();
      _builder_2.append("(no markers found)");
      _builder_2.newLine();
      this.assertContentsAndMarkers(resource, _builder_2);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
