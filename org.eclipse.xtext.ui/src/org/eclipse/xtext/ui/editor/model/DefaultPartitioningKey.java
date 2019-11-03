/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.model;

import org.eclipse.jface.text.IDocumentExtension3;

/**
 * Use the default partitioning. This is discouraged.
 * 
 * @since 2.20
 */
public class DefaultPartitioningKey extends PartitioningKey {

	public DefaultPartitioningKey() {
		super(IDocumentExtension3.DEFAULT_PARTITIONING);
	}
	
}
