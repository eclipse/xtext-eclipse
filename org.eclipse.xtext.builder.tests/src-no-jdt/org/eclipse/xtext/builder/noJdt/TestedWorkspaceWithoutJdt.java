/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.noJdt;

import org.eclipse.core.resources.IProject;
import org.eclipse.xtext.builder.TestedWorkspace;
import org.eclipse.xtext.builder.impl.ProjectOpenedOrClosedListener;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.shared.contribution.ISharedStateContributionRegistry;

import com.google.common.annotations.Beta;
import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * 
 * @since 2.17
 */
@Beta
public class TestedWorkspaceWithoutJdt extends TestedWorkspace {

	@Inject
	public TestedWorkspaceWithoutJdt(ISharedStateContributionRegistry contributions) {
		this(contributions.getSingleContributedInstance(ProjectOpenedOrClosedListener.class));
	}

	public TestedWorkspaceWithoutJdt(ProjectOpenedOrClosedListener closedProjectTaskProcessor) {
		super(closedProjectTaskProcessor);
	}
	
	public IProject createEmptyXtextProject(String string) {
		return run((monitory)-> {
			IProject project = createProject(string);
			addNature(project, XtextProjectHelper.NATURE_ID);
			return project;
		});
	}
}
