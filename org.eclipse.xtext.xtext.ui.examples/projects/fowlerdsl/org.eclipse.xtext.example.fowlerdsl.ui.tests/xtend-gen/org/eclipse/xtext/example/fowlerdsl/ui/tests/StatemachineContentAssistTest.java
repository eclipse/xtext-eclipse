/**
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.fowlerdsl.ui.tests;

import java.util.Collections;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.example.fowlerdsl.ui.tests.StatemachineUiInjectorProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractContentAssistTest;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author miklossy - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(StatemachineUiInjectorProvider.class)
@SuppressWarnings("all")
public class StatemachineContentAssistTest extends AbstractContentAssistTest {
  private final String c = new Function0<String>() {
    @Override
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<|>");
      return _builder.toString();
    }
  }.apply();
  
  @Test
  public void empty() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(this.c);
    _builder.newLineIfNotEmpty();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("resetEvents");
    _builder_1.newLine();
    this.testContentAssistant(_builder, 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("commands", "events", "resetEvents", "state")), "resetEvents", _builder_1.toString());
  }
  
  @Test
  public void statemachine_resetEvents() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("events");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorClosed   D1CL");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("drawerOpened D2OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lightOn      L1ON");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorOpened   D1OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("panelClosed  PNCL");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("resetEvents");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(this.c, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("end");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("events");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed   D1CL");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("drawerOpened D2OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lightOn      L1ON");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorOpened   D1OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("panelClosed  PNCL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("resetEvents");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorOpened");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    this.testContentAssistant(_builder, 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("doorClosed", "drawerOpened", "lightOn", "doorOpened", "panelClosed")), "doorOpened", _builder_1.toString());
  }
  
  @Test
  public void state_actions() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("commands");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("unlockPanel PNUL");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lockPanel   NLK");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lockDoor    D1LK");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("unlockDoor  D1UL");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("state idle");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("actions {");
    _builder.append(this.c, "\t");
    _builder.append("}");
    _builder.newLineIfNotEmpty();
    _builder.append("end");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("commands");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("unlockPanel PNUL");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lockPanel   NLK");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lockDoor    D1LK");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("unlockDoor  D1UL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state idle");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("actions {unlockDoor}");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    this.testContentAssistant(_builder, 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("unlockPanel", "lockPanel", "lockDoor", "unlockDoor", "{")), "unlockDoor", _builder_1.toString());
  }
  
  @Test
  public void transition_event() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("events");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorClosed   D1CL");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("drawerOpened D2OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lightOn      L1ON");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorOpened   D1OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("panelClosed  PNCL");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("resetEvents");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorOpened doorClosed");
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
    _builder.append("lockPanel   NLK");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lockDoor    D1LK");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("unlockDoor  D1UL");
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
    _builder.append(this.c, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("end");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("events");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed   D1CL");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("drawerOpened D2OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lightOn      L1ON");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorOpened   D1OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("panelClosed  PNCL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("resetEvents");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorOpened doorClosed");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("commands");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("unlockPanel PNUL");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lockPanel   NLK");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lockDoor    D1LK");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("unlockDoor  D1UL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state idle");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("actions {unlockDoor lockPanel}");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    this.testContentAssistant(_builder, 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("Transition - Template for a Transition", "doorClosed", "drawerOpened", "lightOn", "doorOpened", "panelClosed", "end")), "doorClosed", _builder_1.toString());
  }
  
  @Test
  public void transition_state() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("events");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorClosed   D1CL");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("drawerOpened D2OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lightOn      L1ON");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorOpened   D1OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("panelClosed  PNCL");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("resetEvents");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorOpened doorClosed");
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
    _builder.append("lockPanel   NLK");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lockDoor    D1LK");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("unlockDoor  D1UL");
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
    _builder.append("lightOn      => waitingForDrawer");
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
    _builder.append("panelClosed => ");
    _builder.append(this.c, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("end");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("events");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed   D1CL");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("drawerOpened D2OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lightOn      L1ON");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorOpened   D1OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("panelClosed  PNCL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("resetEvents");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorOpened doorClosed");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("commands");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("unlockPanel PNUL");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lockPanel   NLK");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lockDoor    D1LK");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("unlockDoor  D1UL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state idle");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("actions {unlockDoor lockPanel}");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed => active");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state active");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("drawerOpened => waitingForLight");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lightOn      => waitingForDrawer");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state waitingForLight");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lightOn => unlockedPanel");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state waitingForDrawer");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("drawerOpened => unlockedPanel");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state unlockedPanel");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("actions {unlockPanel lockDoor}");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("panelClosed => idle");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    this.testContentAssistant(_builder, 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("idle", "active", "waitingForLight", "waitingForDrawer", "unlockedPanel")), "idle", _builder_1.toString());
  }
  
  @Test
  public void transition_template() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("events");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorClosed   D1CL");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("drawerOpened D2OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lightOn      L1ON");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorOpened   D1OP");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("panelClosed  PNCL");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("state idle");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(this.c, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("state active");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("events");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed   D1CL");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("drawerOpened D2OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lightOn      L1ON");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorOpened   D1OP");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("panelClosed  PNCL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state idle");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed => idle");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state active");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    this.testContentAssistant(_builder, 
      Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("doorClosed", "drawerOpened", "lightOn", "doorOpened", "panelClosed", "actions", "end", "Transition - Template for a Transition")), "Transition - Template for a Transition", _builder_1.toString());
  }
  
  private void testContentAssistant(final CharSequence text, final List<String> expectedProposals, final String proposalToApply, final String expectedContent) {
    try {
      final int cursorPosition = text.toString().indexOf(this.c);
      final String content = text.toString().replace(this.c, "");
      this.newBuilder().append(content).assertTextAtCursorPosition(cursorPosition, ((String[])Conversions.unwrapArray(expectedProposals, String.class))).applyProposal(cursorPosition, proposalToApply).expectContent(expectedContent);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
