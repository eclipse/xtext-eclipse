/*******************************************************************************
 * Copyright (c) 2015, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.xtext.ui.XtextUiModule;

/**
 * This class is intended to be used in tests.<br>
 * 
 * Some tests in org.eclipse.xtext.xtext.ui.tests project needs to change {@link XtextUiModule}<br>
 * to customise the test setup.<br>
 * In those cases this class should be used. Never use XtextUiModule directly.
 * 
 * @author dhuebner - Initial contribution and API
 */
public class XtextUIModuleInternal extends XtextUiModule {

	public XtextUIModuleInternal(AbstractUIPlugin plugin) {
		super(plugin);
	}

}
