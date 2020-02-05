/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.contentassist

import org.eclipse.jface.text.contentassist.ICompletionProposal
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal
import org.eclipse.xtext.ui.editor.contentassist.ContentProposalPriorities
import org.eclipse.xtext.common.types.JvmField
import org.eclipse.xtext.common.types.JvmExecutable
import org.eclipse.xtext.xbase.scoping.batch.IIdentifiableElementDescription
import org.eclipse.xtext.xbase.scoping.batch.SimpleIdentifiableElementDescription
import org.eclipse.xtext.xbase.scoping.batch.StaticFeatureDescriptionWithTypeLiteralReceiver

/**
 * @author Sven Efftinge - Initial contribution and API
 */
class XbaseContentProposalPriorities extends ContentProposalPriorities {
	
	override adjustCrossReferencePriority(ICompletionProposal proposal, String prefix) {
		if(proposal instanceof ConfigurableCompletionProposal) {
			switch desc : proposal.getAdditionalData(XbaseProposalProvider.DESCRIPTION_KEY) {
				SimpleIdentifiableElementDescription case proposal.replacementString != 'this' && proposal.replacementString != 'super': {
					adjustPriority(proposal, prefix, 570)
					return;
				}
				StaticFeatureDescriptionWithTypeLiteralReceiver: {
					adjustPriority(proposal,prefix,560)
				}
				IIdentifiableElementDescription : {
					switch desc.elementOrProxy {
						JvmField : {
							adjustPriority(proposal, prefix, 550)
							return;
						}
						JvmExecutable : {
							adjustPriority(proposal, prefix, 520)
							return;
						}
					}
				}
			}
		}
		super.adjustCrossReferencePriority(proposal, prefix)
	}
	
}