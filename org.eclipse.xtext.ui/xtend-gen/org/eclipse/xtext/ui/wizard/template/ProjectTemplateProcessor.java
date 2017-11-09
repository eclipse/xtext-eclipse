/**
 * Copyright (c) 2017 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.wizard.template;

import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtext.ui.wizard.template.AbstractProjectTemplate;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * Generate some code to simplify implementation of project templates.
 * 
 * <ol>
 * <li>Automatically extend AbstractProjectTemplate</li>
 * </ol>
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
@SuppressWarnings("all")
public class ProjectTemplateProcessor extends AbstractClassProcessor {
  @Override
  public void doTransform(final MutableClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    annotatedClass.setExtendedClass(context.newTypeReference(AbstractProjectTemplate.class));
  }
}
