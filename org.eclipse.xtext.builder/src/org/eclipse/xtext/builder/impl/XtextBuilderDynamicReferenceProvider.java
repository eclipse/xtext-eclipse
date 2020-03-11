/*******************************************************************************
 * Copyright (c) 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IDynamicReferenceProvider;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

/**
 * @author cdietrich - Initial contribution and API
 */
public class XtextBuilderDynamicReferenceProvider implements IDynamicReferenceProvider {

	@Override
	public List<IProject> getDependentProjects(IBuildConfiguration buildConfiguration) throws CoreException {
		return Collections.emptyList();
	}

}
