/**
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.domainmodel.ui.tests;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.example.domainmodel.ui.tests.DomainmodelUiInjectorProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractOutlineTest;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(DomainmodelUiInjectorProvider.class)
@SuppressWarnings("all")
public class OutlineTest extends AbstractOutlineTest {
  @Test
  public void testOutline() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("name : String");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op doStuff(String x) : String {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return x + \' \' + this.name");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Foo");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("name : String");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("doStuff(String) : String");
      _builder_1.newLine();
      this.assertAllLabels(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testOutlineWithPackage() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package mypackage {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("entity Foo {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("name : String");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("op doStuff(String x) : String {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("return x + \' \' + this.name");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("mypackage");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("Foo");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("name : String");
      _builder_1.newLine();
      _builder_1.append("    ");
      _builder_1.append("doStuff(String) : String");
      _builder_1.newLine();
      this.assertAllLabels(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testOutlineWithInheritance() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity A {}");
      _builder.newLine();
      _builder.append("entity B extends A {}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("A");
      _builder_1.newLine();
      _builder_1.append("B extends A");
      _builder_1.newLine();
      this.assertAllLabels(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
