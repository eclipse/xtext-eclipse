/**
 * Copyright (c) 2013, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.domainmodel.tests;

import com.google.inject.Inject;
import java.lang.reflect.Method;
import org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel;
import org.eclipse.xtext.example.domainmodel.tests.DomainmodelInjectorProvider;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.testing.OnTheFlyJavaCompiler2;
import org.eclipse.xtext.xbase.testing.evaluation.AbstractXbaseEvaluationTest;
import org.junit.runner.RunWith;

/**
 * Xbase integration test.
 * 
 * runs all Xbase tests from {@link AbstractXbaseEvaluationTest} in the context of an
 * entity operation.
 * 
 * Unsupported features can be disabled by overriding the respective test method.
 * 
 * @author Sven Efftinge
 */
@RunWith(XtextRunner.class)
@InjectWith(DomainmodelInjectorProvider.class)
@SuppressWarnings("all")
public class XbaseIntegrationTest extends AbstractXbaseEvaluationTest {
  @Inject
  private OnTheFlyJavaCompiler2 javaCompiler;
  
  @Inject
  @Extension
  private ParseHelper<DomainModel> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  private JvmModelGenerator generator;
  
  @Override
  protected Object invokeXbaseExpression(final String expression) {
    try {
      Object _xblockexpression = null;
      {
        final DomainModel model = this._parseHelper.parse((("entity Foo { op doStuff() : Object { " + expression) + " } } "));
        this._validationTestHelper.assertNoErrors(model);
        final InMemoryFileSystemAccess fsa = new InMemoryFileSystemAccess();
        this.generator.doGenerate(model.eResource(), fsa);
        final CharSequence concatenation = fsa.getTextFiles().values().iterator().next();
        final Class<?> clazz = this.javaCompiler.compileToClass("Foo", concatenation.toString());
        final Object foo = clazz.getDeclaredConstructor().newInstance();
        final Method method = clazz.getDeclaredMethod("doStuff");
        _xblockexpression = method.invoke(foo);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
