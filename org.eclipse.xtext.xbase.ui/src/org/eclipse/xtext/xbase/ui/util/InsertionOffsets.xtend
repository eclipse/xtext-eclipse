/*******************************************************************************
 * Copyright (c) 2014, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.util

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.util.NodeModelUtils

/**
 * @author Anton Kosyakov - Initial contribution and API
 */
class InsertionOffsets {

	def before(EObject element) {
		NodeModelUtils.findActualNodeFor(element).offset
	}

	def after(EObject element) {
		val node = NodeModelUtils.findActualNodeFor(element)
		node.endOffset
	}

	def inEmpty(EObject element) {
		val node = NodeModelUtils.findActualNodeFor(element)
		val openingBraceNode = node.leafNodes.findFirst[text == "{"]
		if (openingBraceNode !== null)
			openingBraceNode.offset + 1
		else
			node.endOffset
	}

}
