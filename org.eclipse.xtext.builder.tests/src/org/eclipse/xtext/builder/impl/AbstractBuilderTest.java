/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import java.util.List;

import org.eclipse.xtext.builder.TestedWorkspaceWithJDT;
import org.eclipse.xtext.builder.tests.BuilderTestLanguageInjectorProvider;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Event;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.testing.util.TargetPlatformUtil;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(BuilderTestLanguageInjectorProvider.class)
public abstract class AbstractBuilderTest extends Assert implements IResourceDescription.Event.Listener {
	
	protected final String F_EXT = ".buildertestlanguage";
	
	@Inject
	@Rule
	@Extension
	public TestedWorkspaceWithJDT workspace;

	@BeforeClass
	public static void setupTargetPlatform() throws Exception {
		TargetPlatformUtil.setTargetPlatform(AbstractBuilderTest.class);
	}
	
	private volatile List<Event> events = Lists.newArrayList();
	
	@Override
	public void descriptionsChanged(Event event) {
		this.events.add(event);
	}
	
	public List<Event> getEvents() {
		return events;
	}
}
