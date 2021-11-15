/*******************************************************************************
 * Copyright (c) 2021 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.contentassist;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.contentassist.antlr.DelegatingContentAssistContextFactory.StatefulFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
@Singleton
public class ContentAssistContextFactoryWithSharedPool extends AbstractContentAssistContextFactory {
	
	/**
	 * Register in UI module via
	 * <code>
	public Provider<? extends SharedExecutorServiceAccess> provideSharedExecutorServiceAccess() {
		return Access.provider(SharedExecutorServiceAccess.class);
	}
	 * </code>
	 */
	@Singleton
	public static class SharedExecutorServiceAccess {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		ExecutorService pool() {
			return pool;
		}
	}

	private final Provider<StatefulFactory> statefulFactoryProvider;

	private final ExecutorService pool;
	
	@Inject
	public ContentAssistContextFactoryWithSharedPool(Provider<StatefulFactory> statefulFactoryProvider, SharedExecutorServiceAccess access) {
		this.pool = access.pool();
		this.statefulFactoryProvider = statefulFactoryProvider;
	}
	
	public Provider<? extends StatefulFactory> getStatefulFactoryProvider() {
		return statefulFactoryProvider;
	}
	
	protected ExecutorService getPool() {
		return pool;
	}
	
	@Override
	public ContentAssistContext[] create(ITextViewer viewer, int offset, XtextResource resource) {
		StatefulFactory factory = getStatefulFactoryProvider().get();
		factory.setPool(pool);
		return factory.create(viewer, offset, resource);
	}

}