/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.ui.internal;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Collections;
import java.util.Map;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.example.arithmetics.ArithmeticsRuntimeModule;
import org.eclipse.xtext.example.arithmetics.ide.ArithmeticsIdeModule;
import org.eclipse.xtext.example.arithmetics.ui.ArithmeticsUiModule;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ArithmeticsActivator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "org.eclipse.xtext.example.arithmetics.ui";
	private static final String PLUGIN_ID_XTEXT_IDE = "org.eclipse.xtext.ide";
	public static final String ORG_ECLIPSE_XTEXT_EXAMPLE_ARITHMETICS_ARITHMETICS = "org.eclipse.xtext.example.arithmetics.Arithmetics";
	
	private static final Logger logger = Logger.getLogger(ArithmeticsActivator.class);
	
	private static ArithmeticsActivator INSTANCE;
	
	private Map<String, Injector> injectors = Collections.synchronizedMap(Maps.<String, Injector> newHashMapWithExpectedSize(1));
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		injectors.clear();
		INSTANCE = null;
		super.stop(context);
	}
	
	public static ArithmeticsActivator getInstance() {
		return INSTANCE;
	}
	
	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}
	
	protected Injector createInjector(String language) {
		try {
			com.google.inject.Module runtimeModule = getRuntimeModule(language);
			com.google.inject.Module ideModule = getIdeModule(language);
			com.google.inject.Module sharedStateModule = getSharedStateModule();
			com.google.inject.Module uiModule = getUiModule(language);
			com.google.inject.Module mergedModule = null;
			if (ideModule != null) {
				mergedModule = Modules2.mixin(runtimeModule, ideModule, sharedStateModule, uiModule);
			} else {
				// backward compatibility
				mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, uiModule);
			}
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			logger.error("Failed to create injector for " + language);
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for " + language, e);
		}
	}
	
	protected com.google.inject.Module getRuntimeModule(String grammar) {
		if (ORG_ECLIPSE_XTEXT_EXAMPLE_ARITHMETICS_ARITHMETICS.equals(grammar)) {
			return new ArithmeticsRuntimeModule();
		}
		throw new IllegalArgumentException(grammar);
	}
	
	protected com.google.inject.Module getIdeModule(String grammar) {
		if (ORG_ECLIPSE_XTEXT_EXAMPLE_ARITHMETICS_ARITHMETICS.equals(grammar)) {
			// check for Xtext >= 2.11
			return (Platform.getBundle(PLUGIN_ID_XTEXT_IDE) != null) ? new ArithmeticsIdeModule() : null;
		}
		throw new IllegalArgumentException(grammar);
	}

	protected com.google.inject.Module getUiModule(String grammar) {
		if (ORG_ECLIPSE_XTEXT_EXAMPLE_ARITHMETICS_ARITHMETICS.equals(grammar)) {
			return new ArithmeticsUiModule(this);
		}
		throw new IllegalArgumentException(grammar);
	}
	
	protected com.google.inject.Module getSharedStateModule() {
		return new SharedStateModule();
	}
	
	
}
