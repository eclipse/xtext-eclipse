/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.templates;

import org.eclipse.osgi.util.NLS;

/**
 * @author dhuebner - Initial contribution and API
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.xtext.xbase.ui.templates.messages"; //$NON-NLS-1$
	public static String ImportsVariableResolver_0;
	public static String ImportsVariableResolver_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
