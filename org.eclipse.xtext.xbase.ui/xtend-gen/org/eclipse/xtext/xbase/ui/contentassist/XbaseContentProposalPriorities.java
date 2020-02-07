/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.xbase.ui.contentassist;

import com.google.common.base.Objects;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtext.common.types.JvmExecutable;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentProposalPriorities;
import org.eclipse.xtext.xbase.scoping.batch.IIdentifiableElementDescription;
import org.eclipse.xtext.xbase.scoping.batch.SimpleIdentifiableElementDescription;
import org.eclipse.xtext.xbase.scoping.batch.StaticFeatureDescriptionWithTypeLiteralReceiver;
import org.eclipse.xtext.xbase.ui.contentassist.XbaseProposalProvider;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("all")
public class XbaseContentProposalPriorities extends ContentProposalPriorities {
  @Override
  public void adjustCrossReferencePriority(final ICompletionProposal proposal, final String prefix) {
    if ((proposal instanceof ConfigurableCompletionProposal)) {
      Object _additionalData = ((ConfigurableCompletionProposal)proposal).getAdditionalData(XbaseProposalProvider.DESCRIPTION_KEY);
      final Object desc = _additionalData;
      boolean _matched = false;
      if (desc instanceof SimpleIdentifiableElementDescription) {
        if (((!Objects.equal(((ConfigurableCompletionProposal)proposal).getReplacementString(), "this")) && (!Objects.equal(((ConfigurableCompletionProposal)proposal).getReplacementString(), "super")))) {
          _matched=true;
          this.adjustPriority(proposal, prefix, 570);
          return;
        }
      }
      if (!_matched) {
        if (desc instanceof StaticFeatureDescriptionWithTypeLiteralReceiver) {
          _matched=true;
          this.adjustPriority(proposal, prefix, 560);
        }
      }
      if (!_matched) {
        if (desc instanceof IIdentifiableElementDescription) {
          _matched=true;
          JvmIdentifiableElement _elementOrProxy = ((IIdentifiableElementDescription)desc).getElementOrProxy();
          boolean _matched_1 = false;
          if (_elementOrProxy instanceof JvmField) {
            _matched_1=true;
            this.adjustPriority(proposal, prefix, 550);
            return;
          }
          if (!_matched_1) {
            if (_elementOrProxy instanceof JvmExecutable) {
              _matched_1=true;
              this.adjustPriority(proposal, prefix, 520);
              return;
            }
          }
        }
      }
    }
    super.adjustCrossReferencePriority(proposal, prefix);
  }
}
