/*
* generated by Xtext
*/
package org.eclipse.xtext.ui.tests.editor.contentassist.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.tests.editor.contentassist.ui.contentassist.AbstractBacktrackingContentAssistTestLanguageProposalProvider;
/**
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#content-assist
 * on how to customize the content assistant.
 */
public class BacktrackingContentAssistTestLanguageProposalProvider extends AbstractBacktrackingContentAssistTestLanguageProposalProvider {

	
	@Override
	public void complete_Identifier(EObject model, RuleCall ruleCall, ContentAssistContext context,
			ICompletionProposalAcceptor acceptor) {
		complete_ID(model, ruleCall, context, acceptor);
		super.complete_Identifier(model, ruleCall, context, acceptor);
	}
	
}
