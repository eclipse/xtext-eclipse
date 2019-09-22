/**
 * Copyright (c) 2016, 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.domainmodel.ui;

import com.google.inject.Binder;
import com.google.inject.name.Names;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.example.domainmodel.ui.AbstractDomainmodelUiModule;
import org.eclipse.xtext.example.domainmodel.ui.autoedit.FantasticEditStrategyProvider;
import org.eclipse.xtext.example.domainmodel.ui.linking.DomainmodelLinkingDiagnosticMessageProvider;
import org.eclipse.xtext.example.domainmodel.ui.navigation.DomainmodelHyperlinkHelper;
import org.eclipse.xtext.example.domainmodel.ui.outline.FilterOperationsContribution;
import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.outline.actions.IOutlineContribution;

/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings("all")
public class DomainmodelUiModule extends AbstractDomainmodelUiModule {
  public DomainmodelUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
  
  public Class<? extends ILinkingDiagnosticMessageProvider> bindILinkingDiagnosticMessageProvider() {
    return DomainmodelLinkingDiagnosticMessageProvider.class;
  }
  
  @Override
  public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
    return DomainmodelHyperlinkHelper.class;
  }
  
  @Override
  public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
    return FantasticEditStrategyProvider.class;
  }
  
  public void configureFilterOperationsOutlineContribution(final Binder binder) {
    binder.<IOutlineContribution>bind(IOutlineContribution.class).annotatedWith(Names.named("FilterOperationsContribution")).to(
      FilterOperationsContribution.class);
  }
}
