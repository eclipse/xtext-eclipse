package org.eclipse.xtext.example.fowlerdsl.ui.tests;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.example.fowlerdsl.ui.tests.StatemachineUiInjectorProvider;
import org.eclipse.xtext.example.fowlerdsl.validation.StatemachineValidator;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.AbstractQuickfixTest;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author miklossy - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(StatemachineUiInjectorProvider.class)
@SuppressWarnings("all")
public class StatemachineQuickfixTest extends AbstractQuickfixTest {
  @Test
  public void fix_invalid_reset_event() {
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
    _builder.append("foo");
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
    _builder_1.append("resetEvents");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    AbstractQuickfixTest.Quickfix _quickfix = new AbstractQuickfixTest.Quickfix("Change to \'doorClosed\'", "Change to \'doorClosed\'", _builder_1.toString());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("events");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorClosed   D1CL");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("drawerOpened D2OP");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("lightOn      L1ON");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorOpened   D1OP");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("panelClosed  PNCL");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("resetEvents");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorOpened");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_1 = new AbstractQuickfixTest.Quickfix("Change to \'doorOpened\'", "Change to \'doorOpened\'", _builder_2.toString());
    StringConcatenation _builder_3 = new StringConcatenation();
    _builder_3.append("events");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("doorClosed   D1CL");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("drawerOpened D2OP");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("lightOn      L1ON");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("doorOpened   D1OP");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("panelClosed  PNCL");
    _builder_3.newLine();
    _builder_3.append("end");
    _builder_3.newLine();
    _builder_3.newLine();
    _builder_3.append("resetEvents");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("drawerOpened");
    _builder_3.newLine();
    _builder_3.append("end");
    _builder_3.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_2 = new AbstractQuickfixTest.Quickfix("Change to \'drawerOpened\'", "Change to \'drawerOpened\'", _builder_3.toString());
    StringConcatenation _builder_4 = new StringConcatenation();
    _builder_4.append("events");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("doorClosed   D1CL");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("drawerOpened D2OP");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("lightOn      L1ON");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("doorOpened   D1OP");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("panelClosed  PNCL");
    _builder_4.newLine();
    _builder_4.append("end");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("resetEvents");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("lightOn");
    _builder_4.newLine();
    _builder_4.append("end");
    _builder_4.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_3 = new AbstractQuickfixTest.Quickfix("Change to \'lightOn\'", "Change to \'lightOn\'", _builder_4.toString());
    StringConcatenation _builder_5 = new StringConcatenation();
    _builder_5.append("events");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("doorClosed   D1CL");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("drawerOpened D2OP");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("lightOn      L1ON");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("doorOpened   D1OP");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("panelClosed  PNCL");
    _builder_5.newLine();
    _builder_5.append("end");
    _builder_5.newLine();
    _builder_5.newLine();
    _builder_5.append("resetEvents");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("panelClosed");
    _builder_5.newLine();
    _builder_5.append("end");
    _builder_5.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_4 = new AbstractQuickfixTest.Quickfix("Change to \'panelClosed\'", "Change to \'panelClosed\'", _builder_5.toString());
    this.testQuickfixesOn(_builder, Diagnostic.LINKING_DIAGNOSTIC, _quickfix, _quickfix_1, _quickfix_2, _quickfix_3, _quickfix_4);
  }
  
  @Test
  public void fix_invalid_state_action() {
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
    _builder.append("actions {foo}");
    _builder.newLine();
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
    _builder_1.append("actions {lockDoor}");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    AbstractQuickfixTest.Quickfix _quickfix = new AbstractQuickfixTest.Quickfix("Change to \'lockDoor\'", "Change to \'lockDoor\'", _builder_1.toString());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("commands");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("unlockPanel PNUL");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("lockPanel   NLK");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("lockDoor    D1LK");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("unlockDoor  D1UL");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("state idle");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("actions {lockPanel}");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_1 = new AbstractQuickfixTest.Quickfix("Change to \'lockPanel\'", "Change to \'lockPanel\'", _builder_2.toString());
    StringConcatenation _builder_3 = new StringConcatenation();
    _builder_3.append("commands");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("unlockPanel PNUL");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("lockPanel   NLK");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("lockDoor    D1LK");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("unlockDoor  D1UL");
    _builder_3.newLine();
    _builder_3.append("end");
    _builder_3.newLine();
    _builder_3.newLine();
    _builder_3.append("state idle");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("actions {unlockDoor}");
    _builder_3.newLine();
    _builder_3.append("end");
    _builder_3.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_2 = new AbstractQuickfixTest.Quickfix("Change to \'unlockDoor\'", "Change to \'unlockDoor\'", _builder_3.toString());
    StringConcatenation _builder_4 = new StringConcatenation();
    _builder_4.append("commands");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("unlockPanel PNUL");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("lockPanel   NLK");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("lockDoor    D1LK");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("unlockDoor  D1UL");
    _builder_4.newLine();
    _builder_4.append("end");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("state idle");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("actions {unlockPanel}");
    _builder_4.newLine();
    _builder_4.append("end");
    _builder_4.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_3 = new AbstractQuickfixTest.Quickfix("Change to \'unlockPanel\'", "Change to \'unlockPanel\'", _builder_4.toString());
    this.testQuickfixesOn(_builder, Diagnostic.LINKING_DIAGNOSTIC, _quickfix, _quickfix_1, _quickfix_2, _quickfix_3);
  }
  
  @Test
  public void fix_invalid_state_name() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("commands");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("lockPanel\tNLK");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("unlockDoor\tD1UL");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("state Idle");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("actions {unlockDoor lockPanel}");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("commands");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("lockPanel\tNLK");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("unlockDoor\tD1UL");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state idle");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("actions {unlockDoor lockPanel}");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    AbstractQuickfixTest.Quickfix _quickfix = new AbstractQuickfixTest.Quickfix("Change to \'idle\'.", "Change to \'idle\'.", _builder_1.toString());
    this.testQuickfixesOn(_builder, StatemachineValidator.INVALID_NAME, _quickfix);
  }
  
  @Test
  public void fix_invalid_transition_event() {
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
    _builder.append("foo => active");
    _builder.newLine();
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
    _builder_1.append("doorClosed => active");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state active");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    AbstractQuickfixTest.Quickfix _quickfix = new AbstractQuickfixTest.Quickfix("Change to \'doorClosed\'", "Change to \'doorClosed\'", _builder_1.toString());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("events");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorClosed   D1CL");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("drawerOpened D2OP");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("lightOn      L1ON");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorOpened   D1OP");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("panelClosed  PNCL");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("state idle");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorOpened => active");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("state active");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_1 = new AbstractQuickfixTest.Quickfix("Change to \'doorOpened\'", "Change to \'doorOpened\'", _builder_2.toString());
    StringConcatenation _builder_3 = new StringConcatenation();
    _builder_3.append("events");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("doorClosed   D1CL");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("drawerOpened D2OP");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("lightOn      L1ON");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("doorOpened   D1OP");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("panelClosed  PNCL");
    _builder_3.newLine();
    _builder_3.append("end");
    _builder_3.newLine();
    _builder_3.newLine();
    _builder_3.append("state idle");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("drawerOpened => active");
    _builder_3.newLine();
    _builder_3.append("end");
    _builder_3.newLine();
    _builder_3.newLine();
    _builder_3.append("state active");
    _builder_3.newLine();
    _builder_3.append("end");
    _builder_3.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_2 = new AbstractQuickfixTest.Quickfix("Change to \'drawerOpened\'", "Change to \'drawerOpened\'", _builder_3.toString());
    StringConcatenation _builder_4 = new StringConcatenation();
    _builder_4.append("events");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("doorClosed   D1CL");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("drawerOpened D2OP");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("lightOn      L1ON");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("doorOpened   D1OP");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("panelClosed  PNCL");
    _builder_4.newLine();
    _builder_4.append("end");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("state idle");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("lightOn => active");
    _builder_4.newLine();
    _builder_4.append("end");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("state active");
    _builder_4.newLine();
    _builder_4.append("end");
    _builder_4.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_3 = new AbstractQuickfixTest.Quickfix("Change to \'lightOn\'", "Change to \'lightOn\'", _builder_4.toString());
    StringConcatenation _builder_5 = new StringConcatenation();
    _builder_5.append("events");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("doorClosed   D1CL");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("drawerOpened D2OP");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("lightOn      L1ON");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("doorOpened   D1OP");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("panelClosed  PNCL");
    _builder_5.newLine();
    _builder_5.append("end");
    _builder_5.newLine();
    _builder_5.newLine();
    _builder_5.append("state idle");
    _builder_5.newLine();
    _builder_5.append("\t");
    _builder_5.append("panelClosed => active");
    _builder_5.newLine();
    _builder_5.append("end");
    _builder_5.newLine();
    _builder_5.newLine();
    _builder_5.append("state active");
    _builder_5.newLine();
    _builder_5.append("end");
    _builder_5.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_4 = new AbstractQuickfixTest.Quickfix("Change to \'panelClosed\'", "Change to \'panelClosed\'", _builder_5.toString());
    this.testQuickfixesOn(_builder, Diagnostic.LINKING_DIAGNOSTIC, _quickfix, _quickfix_1, _quickfix_2, _quickfix_3, _quickfix_4);
  }
  
  @Test
  public void fix_invalid_transition_state() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("events");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorClosed   D1CL");
    _builder.newLine();
    _builder.append("end");
    _builder.newLine();
    _builder.newLine();
    _builder.append("state idle");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("doorClosed => foo");
    _builder.newLine();
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
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state idle");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("doorClosed => active");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("state active");
    _builder_1.newLine();
    _builder_1.append("end");
    _builder_1.newLine();
    AbstractQuickfixTest.Quickfix _quickfix = new AbstractQuickfixTest.Quickfix("Change to \'active\'", "Change to \'active\'", _builder_1.toString());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("events");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorClosed   D1CL");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("state idle");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("doorClosed => idle");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("state active");
    _builder_2.newLine();
    _builder_2.append("end");
    _builder_2.newLine();
    AbstractQuickfixTest.Quickfix _quickfix_1 = new AbstractQuickfixTest.Quickfix("Change to \'idle\'", "Change to \'idle\'", _builder_2.toString());
    this.testQuickfixesOn(_builder, Diagnostic.LINKING_DIAGNOSTIC, _quickfix, _quickfix_1);
  }
}
