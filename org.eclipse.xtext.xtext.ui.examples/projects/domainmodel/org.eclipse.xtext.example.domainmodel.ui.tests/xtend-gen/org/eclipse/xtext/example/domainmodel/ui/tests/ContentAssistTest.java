/**
 * Copyright (c) 2012, 2019 itemis AG (http://www.itemis.eu) and others.
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
import org.eclipse.xtext.ui.testing.AbstractContentAssistTest;
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jan Koehnlein - copied and adapted from Xtend
 */
@RunWith(XtextRunner.class)
@InjectWith(DomainmodelUiInjectorProvider.class)
@SuppressWarnings("all")
public class ContentAssistTest extends AbstractContentAssistTest {
  @Test
  public void testImportCompletion() {
    try {
      this.newBuilder().append("import java.util.Da").assertText("java.util.Date");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testImportCompletion_1() {
    try {
      this.newBuilder().append("import LinkedHashSet").assertText("java.util.LinkedHashSet");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testTypeCompletion() {
    try {
      this.newBuilder().append("entity Foo { bar: LinkedHashSet").assertText("java.util.LinkedHashSet");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEntityTemplateProposal() {
    try {
      ContentAssistProcessorTestBuilder _applyProposal = this.newBuilder().applyProposal("Entity - template for an Entity");
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity name {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _applyProposal.expectContent(_builder.toString());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testPackageTemplateProposal() {
    try {
      ContentAssistProcessorTestBuilder _applyProposal = this.newBuilder().applyProposal("Package - template for a Package");
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package name {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _applyProposal.expectContent(_builder.toString());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
