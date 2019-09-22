/**
 * Copyright (c) 2013, 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.fowlerdsl.ui.quickfix;

import org.eclipse.xtext.example.fowlerdsl.validation.StatemachineValidator;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Custom quickfixes.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
@SuppressWarnings("all")
public class StatemachineQuickfixProvider extends DefaultQuickfixProvider {
  @Fix(StatemachineValidator.INVALID_NAME)
  public void convertNameToFirstLowerCase(final Issue issue, final IssueResolutionAcceptor acceptor) {
    String _firstLower = StringExtensions.toFirstLower(IterableExtensions.<String>head(((Iterable<String>)Conversions.doWrapArray(issue.getData()))));
    String _plus = ("Change to \'" + _firstLower);
    String _plus_1 = (_plus + "\'.");
    String _firstLower_1 = StringExtensions.toFirstLower(IterableExtensions.<String>head(((Iterable<String>)Conversions.doWrapArray(issue.getData()))));
    String _plus_2 = ("Change to \'" + _firstLower_1);
    String _plus_3 = (_plus_2 + "\'.");
    final IModification _function = (IModificationContext it) -> {
      final String firstLetter = it.getXtextDocument().get((issue.getOffset()).intValue(), 1);
      it.getXtextDocument().replace((issue.getOffset()).intValue(), 1, firstLetter.toLowerCase());
    };
    acceptor.accept(issue, _plus_1, _plus_3, "upcase.png", _function);
  }
}
