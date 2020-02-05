/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.ecore;

import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ecore.EcoreRuntimeModule;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	private static final Logger logger = Logger.getLogger(Activator.class);
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.xtext.ui.ecore"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private Injector injector;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}
	
	public Injector getInjector() {
		return injector;
	}
	
	private void initializeEcoreInjector() {
		injector = Guice.createInjector(
				Modules.override(Modules.override(new EcoreRuntimeModule())
				.with(new EcoreUiModule(plugin)))
				.with(new SharedStateModule()));
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		try {
			initializeEcoreInjector();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		injector = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
