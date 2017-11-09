/*******************************************************************************
 * Copyright (c) 2017 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.wizard.template

import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.TransformationContext

/**
 * Generate some code to simplify implementation of project templates.
 * 
 * <ol>
 * <li>Automatically extend AbstractProjectTemplate</li>
 * </ol> 
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
class ProjectTemplateProcessor extends AbstractClassProcessor {

	override doTransform(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
		annotatedClass.extendedClass = AbstractProjectTemplate.newTypeReference
	}

}
