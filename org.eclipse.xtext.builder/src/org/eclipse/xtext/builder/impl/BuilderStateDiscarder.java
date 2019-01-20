/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import java.util.Map;

import org.eclipse.core.resources.IProject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @since 2.17
 */
public class BuilderStateDiscarder {

	public boolean forgetLastBuildState(final Iterable<IProject> toUpdate, Map<String, String> builderArguments) {
		if (isHandledBuildFlag(builderArguments)) {
			for(IProject project: toUpdate) {
				XtextBuilder builder = BuildManagerAccess.findBuilder(project);
				if (builder != null) {
					builder.forgetLastBuiltState();
				}
			}
			return true;
		}
		return false;
	}

	protected boolean isHandledBuildFlag(Map<String, String> builderArguments) {
		return IBuildFlag.FORGET_BUILD_STATE_ONLY.isSet(builderArguments) || IBuildFlag.RECOVERY_BUILD.isSet(builderArguments);
	}
	
}
