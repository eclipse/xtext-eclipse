/*******************************************************************************
 * Copyright (c) 2010, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.findrefs;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.IGlobalServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;

/**
 * This class must neither be renamed nor moved.
 * 
 * @author Jan K�hnlein - Initial contribution and API
 * @author Peter Friese
 */
public class FindReferencesHandler extends AbstractHandler {

	@Inject
	protected EObjectAtOffsetHelper eObjectAtOffsetHelper;

	@Inject
	protected IGlobalServiceProvider globalServiceProvider;

	private static final Logger LOG = Logger.getLogger(FindReferencesHandler.class);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			XtextEditor editor = EditorUtils.getActiveXtextEditor(event);
			if (editor != null) {
				final ITextSelection selection = (ITextSelection) editor.getSelectionProvider().getSelection();
				editor.getDocument().priorityReadOnly(new IUnitOfWork.Void<XtextResource>() {
					@Override
					public void process(XtextResource state) throws Exception {
						EObject target = eObjectAtOffsetHelper.resolveElementAt(state, selection.getOffset());
						findReferences(target);
					}
				});
			}
		} catch (Exception e) {
			LOG.error(Messages.FindReferencesHandler_3, e);
		}
		return null;
	}

	protected void findReferences(EObject target) {
		ReferenceQueryExecutor queryExecutor = getQueryExecutor(target);
		if(queryExecutor != null) 
			queryExecutor.execute();
	}

	protected ReferenceQueryExecutor getQueryExecutor(EObject target) {
		if (target == null) {
			return null;
		}
		URI targetURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(target);
		if(targetURI != null) {
			ReferenceQueryExecutor queryExecutor = globalServiceProvider.findService(targetURI.trimFragment(), ReferenceQueryExecutor.class);
			if (queryExecutor != null) {
				queryExecutor.init(target);
				return queryExecutor;
			}
		}
		return null;
	}
}
