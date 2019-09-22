/**
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.domainmodel.ui.quickfix;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature;
import org.eclipse.xtext.example.domainmodel.validation.IssueCodes;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.ui.quickfix.XbaseQuickfixProvider;

/**
 * Custom quickfixes.
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
@SuppressWarnings("all")
public class DomainmodelQuickfixProvider extends XbaseQuickfixProvider {
  @Fix(IssueCodes.INVALID_TYPE_NAME)
  public void fixTypeName(final Issue issue, final IssueResolutionAcceptor acceptor) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Capitalize name of \'");
    String _get = issue.getData()[0];
    _builder.append(_get);
    _builder.append("\'");
    final IModification _function = (IModificationContext context) -> {
      IXtextDocument xtextDocument = context.getXtextDocument();
      String firstLetter = xtextDocument.get((issue.getOffset()).intValue(), 1);
      xtextDocument.replace((issue.getOffset()).intValue(), 1, Strings.toFirstUpper(firstLetter));
    };
    acceptor.accept(issue, "Capitalize name", _builder.toString(), 
      "upcase.png", _function);
  }
  
  @Fix(IssueCodes.INVALID_FEATURE_NAME)
  public void fixFeatureName(final Issue issue, final IssueResolutionAcceptor acceptor) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Uncapitalize name of \'");
    String _get = issue.getData()[0];
    _builder.append(_get);
    _builder.append("\'");
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      ((Feature) element).setName(Strings.toFirstLower(issue.getData()[0]));
    };
    acceptor.accept(issue, "Uncapitalize name", _builder.toString(), 
      "upcase.png", _function);
  }
  
  @Fix(IssueCodes.MISSING_TYPE)
  public void createReferenceType(final Issue issue, final IssueResolutionAcceptor acceptor) {
    this.createLinkingIssueResolutions(issue, acceptor);
  }
}
