/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.impl;

import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Lists.*;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmExecutable;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.MockJavaProjectProvider;
import org.eclipse.xtext.junit4.internal.StopwatchRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public abstract class AbstractTypeProviderPerformanceTest {

	protected abstract IJvmTypeProvider getTypeProvider();

	@Rule public StopwatchRule rule = new StopwatchRule(true);
	
	public Iterable<String> getClassNamesToLoad() throws Exception {
		List<String> resource = MockJavaProjectProvider.readResource("/org/eclipse/xtext/common/types/testSetups/files.list");
		return filter(transform(resource, new Function<String, String>() {
			@Override
			public String apply(String arg) {
				return "org.eclipse.xtext.common.types.testSetups."+arg.substring(0, arg.length()-".java.txt".length());
			}
		}), new Predicate<String>() {
			// filter out the one with the dollar
			@Override
			public boolean apply(String s) {
				return !s.contains("$");
			}
		});
	}
	
	@Test
	public void testLoadTypesAndResolveAllParameterNames() throws Exception {
		for (String name : getClassNamesToLoad()) {
			JvmDeclaredType type = loadAndResolve(name, true, true, true, true, true);
			TreeIterator<Object> iterator = EcoreUtil.getAllContents(type.eResource().getResourceSet(), true);
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (next instanceof JvmFormalParameter) {
					((JvmFormalParameter) next).getName();
				}
			}
		}
	}
	
	@Test
	public void testNoAccessOfParameterNames() throws Exception {
		for (String name : getClassNamesToLoad()) {
			loadAndResolve(name, true, true, true, true, false);
		}
	}
	
	@Test
	public void testLoadAndAccessAll() throws Exception {
		for (String name : getClassNamesToLoad()) {
			loadAndResolve(name, true, true, true, true, true);
		}
	}
	
	protected JvmDeclaredType loadAndResolve(String name, boolean accessMembers, boolean accessAnnotations, boolean accessTypeParams, boolean accessParameter, boolean accessParameterNames) {
		JvmDeclaredType type = (JvmDeclaredType) getTypeProvider().findTypeByName(name);
		EcoreUtil.resolveAll(type.eResource());
		EcoreUtil.resolveAll(type.eResource().getResourceSet());
		Assert.assertNotNull(name, type);
		
		if (accessAnnotations) {
			type.getAnnotations();
		}
		
		if (accessMembers) {
			EList<JvmMember> members = type.getMembers();
			for (JvmMember member : members) {
				if (accessAnnotations) {
					member.getAnnotations();
				}
				if (member instanceof JvmExecutable) {
					JvmExecutable operation = (JvmExecutable) member;
					if (accessParameter) {
						EList<JvmFormalParameter> parameters = operation.getParameters();
						for (JvmFormalParameter jvmFormalParameter : parameters) {
							if (accessAnnotations) {
								jvmFormalParameter.getAnnotations();
							}
							if (accessParameterNames) {
								jvmFormalParameter.getName();
							}
						}
					}
				}
			}
		}
		return type;
	}
}
