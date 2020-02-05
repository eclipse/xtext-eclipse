/**
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.homeautomation.tests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.example.homeautomation.ruleEngine.Model;
import org.eclipse.xtext.example.homeautomation.tests.RuleEngineInjectorProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(RuleEngineInjectorProvider.class)
@SuppressWarnings("all")
public class RuleEngineParsingTest {
  @Inject
  @Extension
  private ParseHelper<Model> _parseHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Device Window can be open, closed");
      _builder.newLine();
      _builder.newLine();
      _builder.append("Device Heater can be on, off, error");
      _builder.newLine();
      _builder.newLine();
      _builder.append("Rule \'Save energy\' when Window.open then");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("fire(Heater.off)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("println(\'Another penny to the piggy bank!\')");
      _builder.newLine();
      Assert.assertNotNull(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
