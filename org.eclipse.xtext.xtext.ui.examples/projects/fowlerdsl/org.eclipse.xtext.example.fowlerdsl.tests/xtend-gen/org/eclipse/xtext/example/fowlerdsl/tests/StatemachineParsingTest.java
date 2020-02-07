/**
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.fowlerdsl.tests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Statemachine;
import org.eclipse.xtext.example.fowlerdsl.tests.StatemachineInjectorProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author miklossy - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(StatemachineInjectorProvider.class)
@SuppressWarnings("all")
public class StatemachineParsingTest {
  @Inject
  @Extension
  private ParseHelper<Statemachine> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("events");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("doorClosed\t\tD1CL");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("drawerOpened\tD2OP");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("lightOn\t\t\tL1ON");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("doorOpened\t\tD1OP");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("panelClosed\t\tPNCL");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      _builder.newLine();
      _builder.append("resetEvents");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("doorOpened");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      _builder.newLine();
      _builder.append("commands");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("unlockPanel PNUL");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("lockPanel\tNLK");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("lockDoor\tD1LK");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("unlockDoor\tD1UL");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      _builder.newLine();
      _builder.append("state idle");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("actions {unlockDoor lockPanel}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("doorClosed => active");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      _builder.newLine();
      _builder.append("state active");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("drawerOpened => waitingForLight");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("lightOn\t\t => waitingForDrawer");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      _builder.newLine();
      _builder.append("state waitingForLight");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("lightOn => unlockedPanel");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      _builder.newLine();
      _builder.append("state waitingForDrawer");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("drawerOpened => unlockedPanel");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      _builder.newLine();
      _builder.append("state unlockedPanel");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("actions {unlockPanel lockDoor}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("panelClosed => idle");
      _builder.newLine();
      _builder.append("end");
      _builder.newLine();
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
