/*******************************************************************************
 * Copyright (c) 2016, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.arithmetics.ui.editor.hierarchy

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.IGlobalServiceProvider
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.ui.editor.findrefs.EditorResourceAccess
import org.eclipse.xtext.ui.editor.hierarchy.AbstractOpenHierarchyHandler
import org.eclipse.xtext.ui.editor.hierarchy.DeferredHierarchyBuilder

class OpenCallHierarchyHandler extends AbstractOpenHierarchyHandler {

	val static HIERARCHY_VIEW_PART_ID = 'org.eclipse.xtext.example.arithmetics.ui.ArithmeticsCallHierarchy'

	@Inject
	extension IGlobalServiceProvider

	@Inject
	EditorResourceAccess resourceAccess

	override protected getHierarchyViewPartID() {
		HIERARCHY_VIEW_PART_ID
	}

	override protected createHierarchyBuilder(EObject target) {
		val xtextCallHierarchyBuilder = target.findService(ArithmeticsCallHierarchyBuilder)
		xtextCallHierarchyBuilder.resourceAccess = resourceAccess
		xtextCallHierarchyBuilder.indexData = target.findService(IResourceDescriptions)

		val deferredHierarchyBuilder = target.findService(DeferredHierarchyBuilder)
		deferredHierarchyBuilder.hierarchyBuilder = xtextCallHierarchyBuilder
		return deferredHierarchyBuilder
	}

}
