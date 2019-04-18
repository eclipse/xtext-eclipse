/*******************************************************************************
 * Copyright (c) 2010, 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui;

import com.google.inject.Injector;
import org.eclipse.core.runtime.Platform;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.eclipse.xtext.xbase.ui.internal.XbaseActivator;
import org.osgi.framework.Bundle;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class XtypeExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Platform.getBundle(XbaseActivator.PLUGIN_ID);
	}
	
	@Override
	protected Injector getInjector() {
		XbaseActivator activator = XbaseActivator.getInstance();
		return activator != null ? activator.getInjector(XbaseActivator.ORG_ECLIPSE_XTEXT_XBASE_XTYPE) : null;
	}

}
