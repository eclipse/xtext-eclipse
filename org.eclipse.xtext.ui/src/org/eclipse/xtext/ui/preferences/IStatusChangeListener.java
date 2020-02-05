/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.preferences;

import org.eclipse.core.runtime.IStatus;

/**
 * 
 * Initially copied from Jdt.
 * 
 * @author Michael Clay
 * @since 2.1
 */
public interface IStatusChangeListener {

	void statusChanged(IStatus status);
}