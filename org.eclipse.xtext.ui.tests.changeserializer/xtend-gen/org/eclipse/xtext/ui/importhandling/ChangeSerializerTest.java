/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.importhandling;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Collection;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.ide.serializer.IEmfResourceChange;
import org.eclipse.xtext.ide.serializer.impl.ChangeSerializer;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.InMemoryURIHandler;
import org.eclipse.xtext.ui.importhandling.ChangeSerializerTestHelper;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration;
import org.eclipse.xtext.ui.tests.changeserializer.tests.ChangeSerializerInjectorProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(ChangeSerializerInjectorProvider.class)
@SuppressWarnings("all")
public class ChangeSerializerTest {
  @Inject
  private Provider<ChangeSerializer> serializerProvider;
  
  @Inject
  @Extension
  private ChangeSerializerTestHelper _changeSerializerTestHelper;
  
  @Test
  public void testRenameGlobal1() {
    final InMemoryURIHandler fs = new InMemoryURIHandler();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package pkg1");
    _builder.newLine();
    _builder.newLine();
    _builder.append("element Foo {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Pair<String, String> _mappedTo = Pair.<String, String>of("inmemory:/file1.chgser", _builder.toString());
    this._changeSerializerTestHelper.operator_add(fs, _mappedTo);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package pkg2");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import pkg1.Foo");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("element Bar {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("ref Foo");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("inmemory:/file2.chgser", _builder_1.toString());
    this._changeSerializerTestHelper.operator_add(fs, _mappedTo_1);
    final ResourceSet rs = this._changeSerializerTestHelper.createResourceSet(fs);
    final PackageDeclaration model = this._changeSerializerTestHelper.<PackageDeclaration>contents(rs, "inmemory:/file1.chgser", PackageDeclaration.class);
    final ChangeSerializer serializer = this.serializerProvider.get();
    serializer.beginRecordChanges(model.eResource());
    model.setName("newpackage");
    Assert.assertEquals(1, model.eResource().getResourceSet().getResources().size());
    Collection<IEmfResourceChange> _endRecordChangesToTextDocuments = this._changeSerializerTestHelper.endRecordChangesToTextDocuments(serializer);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("---------------- inmemory:/file1.chgser (syntax: <offset|text>) ----------------");
    _builder_2.newLine();
    _builder_2.append("package <8:4|newpackage>");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("element Foo {");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("--------------------------------------------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("8 4 \"pkg1\" -> \"newpackage\"");
    _builder_2.newLine();
    _builder_2.append("---------------- inmemory:/file2.chgser (syntax: <offset|text>) ----------------");
    _builder_2.newLine();
    _builder_2.append("package pkg2");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("import <21:8|newpackage.Foo>");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("element Bar {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("ref Foo");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("--------------------------------------------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("21 8 \"pkg1.Foo\" -> \"newpackage.Foo\"");
    _builder_2.newLine();
    this._changeSerializerTestHelper.operator_tripleEquals(_endRecordChangesToTextDocuments, _builder_2);
  }
  
  @Test
  public void testMoveToNewPackage() {
    final InMemoryURIHandler fs = new InMemoryURIHandler();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package pkg1");
    _builder.newLine();
    _builder.newLine();
    _builder.append("element Foo {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Pair<String, String> _mappedTo = Pair.<String, String>of("inmemory:/file1.chgser", _builder.toString());
    this._changeSerializerTestHelper.operator_add(fs, _mappedTo);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package pkg1");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("element Bar {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("ref Foo");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("inmemory:/file2.chgser", _builder_1.toString());
    this._changeSerializerTestHelper.operator_add(fs, _mappedTo_1);
    final ResourceSet rs = this._changeSerializerTestHelper.createResourceSet(fs);
    final PackageDeclaration model = this._changeSerializerTestHelper.<PackageDeclaration>contents(rs, "inmemory:/file1.chgser", PackageDeclaration.class);
    final ChangeSerializer serializer = this.serializerProvider.get();
    serializer.beginRecordChanges(model.eResource());
    model.setName("newpackage");
    Assert.assertEquals(1, model.eResource().getResourceSet().getResources().size());
    Collection<IEmfResourceChange> _endRecordChangesToTextDocuments = this._changeSerializerTestHelper.endRecordChangesToTextDocuments(serializer);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("---------------- inmemory:/file1.chgser (syntax: <offset|text>) ----------------");
    _builder_2.newLine();
    _builder_2.append("package <8:4|newpackage>");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("element Foo {");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("--------------------------------------------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("8 4 \"pkg1\" -> \"newpackage\"");
    _builder_2.newLine();
    _builder_2.append("---------------- inmemory:/file2.chgser (syntax: <offset|text>) ----------------");
    _builder_2.newLine();
    _builder_2.append("<0:39|package pkg1");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("import newpackage.Foo");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("element Bar {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("ref Foo");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append(">");
    _builder_2.newLine();
    _builder_2.append("--------------------------------------------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("0 39 \"package pkg1\\n\\nele...\" -> \"package pkg1\\n\\nimp...\"");
    _builder_2.newLine();
    this._changeSerializerTestHelper.operator_tripleEquals(_endRecordChangesToTextDocuments, _builder_2);
  }
  
  @Test
  public void testMoveIntoLocalPackage() {
    final InMemoryURIHandler fs = new InMemoryURIHandler();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("element Foo {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    Pair<String, String> _mappedTo = Pair.<String, String>of("inmemory:/file1.chgser", _builder.toString());
    this._changeSerializerTestHelper.operator_add(fs, _mappedTo);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package pkg1");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import other.Foo");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("element Bar {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("ref Foo");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("inmemory:/file2.chgser", _builder_1.toString());
    this._changeSerializerTestHelper.operator_add(fs, _mappedTo_1);
    final ResourceSet rs = this._changeSerializerTestHelper.createResourceSet(fs);
    final PackageDeclaration model = this._changeSerializerTestHelper.<PackageDeclaration>contents(rs, "inmemory:/file1.chgser", PackageDeclaration.class);
    final ChangeSerializer serializer = this.serializerProvider.get();
    serializer.beginRecordChanges(model.eResource());
    model.setName("pkg1");
    Assert.assertEquals(1, model.eResource().getResourceSet().getResources().size());
    Collection<IEmfResourceChange> _endRecordChangesToTextDocuments = this._changeSerializerTestHelper.endRecordChangesToTextDocuments(serializer);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("---------------- inmemory:/file1.chgser (syntax: <offset|text>) ----------------");
    _builder_2.newLine();
    _builder_2.append("package <8:5|pkg1>");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("element Foo {");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("--------------------------------------------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("8 5 \"other\" -> \"pkg1\"");
    _builder_2.newLine();
    _builder_2.append("---------------- inmemory:/file2.chgser (syntax: <offset|text>) ----------------");
    _builder_2.newLine();
    _builder_2.append("package pkg1<12:20|");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append(">element Bar {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("ref Foo");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("--------------------------------------------------------------------------------");
    _builder_2.newLine();
    _builder_2.append("12 20 \"\\n\\nimport other.Foo\\n\\n\" -> \"\\n\\n\"");
    _builder_2.newLine();
    this._changeSerializerTestHelper.operator_tripleEquals(_endRecordChangesToTextDocuments, _builder_2);
  }
}
