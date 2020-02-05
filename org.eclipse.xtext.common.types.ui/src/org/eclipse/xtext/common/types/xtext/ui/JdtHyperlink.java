/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.common.types.xtext.ui;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.PartInitException;
import org.eclipse.xtext.ui.editor.hyperlinking.AbstractHyperlink;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class JdtHyperlink extends AbstractHyperlink {

	private static final Logger logger = Logger.getLogger(JdtHyperlink.class);
	
	private IJavaElement javaElement;

	@Override
	public void open() {
		try {
			JavaUI.openInEditor(javaElement);
		}
		catch (PartInitException e) {
			logger.debug("PartInitException; javaElement: " + javaElement.toString(), e);
		}
		catch (JavaModelException e) {
			logger.debug("JavaModelException; javaElement: " + javaElement.toString(), e);
		}
	}

	public void setJavaElement(IJavaElement javaElement) {
		this.javaElement = javaElement;
	}

	public IJavaElement getJavaElement() {
		return javaElement;
	}

}
