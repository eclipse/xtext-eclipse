/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspace.ProjectOrder;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.builder.internal.Activator;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Schedules extraordinary Xtext builds, e.g. on project open/close. Avoids duplicate builds as far as possible.
 * 
 * @author Jan Koehnlein - Initial contribution and API
 */
@Singleton
@Deprecated
public class BuildScheduler {

	private final static Logger log = Logger.getLogger(BuildScheduler.class);

	private final Set<IProject> projectsScheduledForBuild = Sets.newHashSet();

	private final Map<IProject, Map<String, String>> projectBuilderArguments = Maps.newHashMap();

	@Inject
	private IWorkspace workspace;
	
	/*
	 * We funnel the scheduling through the deprecated type in case a client did override this class.
	 */
	@Inject
	private BuilderStateDiscarder builderStateDiscarder;

	public void scheduleBuildIfNecessary(final Iterable<IProject> toUpdate, IBuildFlag... buildFlags) {
		if (BuildScheduler.class.equals(getClass()) && buildFlags != null && buildFlags.length == 1) {
			if (builderStateDiscarder.forgetLastBuildState(toUpdate, toBuilderArguments(buildFlags))) {
				return;
			}
		}
		// skip all projects that are actually not considered to be buildable
		List<IProject> scheduleUs = Lists.newArrayList();
		for (IProject project : toUpdate) {
			if (isBuildable(project)) {
				scheduleUs.add(project);
			}
		}
		if (scheduleUs.isEmpty())
			return;
		// schedule the remaining projects if they have not yet been scheduled
		synchronized (projectsScheduledForBuild) {
			scheduleUs.removeAll(projectsScheduledForBuild);
			if (!scheduleUs.isEmpty()) {
				projectsScheduledForBuild.addAll(scheduleUs);
				for (IProject scheduled : scheduleUs) {
					Map<String, String> builderArguments = toBuilderArguments(buildFlags);
					if (projectBuilderArguments.containsKey(scheduled)) {
						Map<String, String> existingArguments = projectBuilderArguments.get(scheduled);
						existingArguments.keySet().retainAll(builderArguments.keySet());
					} else {
						projectBuilderArguments.put(scheduled, builderArguments);
					}
				}
				new BuildJob(Messages.BuildScheduler_JobName, scheduleUs).schedule();
			}
		}
	}

	private Map<String, String> toBuilderArguments(IBuildFlag... buildFlags) {
		Map<String, String> builderArguments = Maps.newHashMap();
		if (buildFlags != null && buildFlags.length > 0) {
			for (IBuildFlag buildFlag : buildFlags) {
				buildFlag.addToMap(builderArguments);
			}
		}
		return builderArguments;
	}

	protected boolean isBuildable(IProject project) {
		return XtextProjectHelper.hasNature(project) && XtextProjectHelper.hasBuilder(project);
	}

	@Deprecated
	protected class BuildJob extends Job {

		public BuildJob(String name, List<IProject> projects) {
			super(name);
			setRule(workspace.getRuleFactory().buildRule());
			updateTaskName(projects);
		}

		protected void updateTaskName(List<IProject> projects) {
			switch (projects.size()) {
				case 0:
					throw new IllegalArgumentException("projects may not be empty");
				case 1:
					setName(NLS.bind(Messages.BuildScheduler_SingleJobName, projects.get(0).getName()));
					break;
				case 2:
					setName(NLS.bind(Messages.BuildScheduler_TwoJobsName, projects.get(0).getName(), projects.get(1)
							.getName()));
					break;
				default:
					setName(NLS.bind(Messages.BuildScheduler_ManyJobsName, projects.get(0).getName(),
							projects.size() - 1));
					break;
			}
		}

		public BuildJob(String name, IProject project) {
			this(name, Collections.singletonList(project));
		}

		@Override
		public boolean belongsTo(Object family) {
			return ResourcesPlugin.FAMILY_AUTO_BUILD == family;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			SubMonitor progress = SubMonitor.convert(monitor, 1);
			try {
				IProject[] buildUs;
				Map<IProject, Map<String, String>> actualArguments;
				synchronized (projectsScheduledForBuild) {
					buildUs = projectsScheduledForBuild.toArray(new IProject[projectsScheduledForBuild.size()]);
					actualArguments = Maps.newHashMap(projectBuilderArguments);
					projectBuilderArguments.clear();
					projectsScheduledForBuild.clear();
				}
				if (buildUs.length != 0) {
					SubMonitor childProgress = SubMonitor.convert(progress.newChild(1), buildUs.length);
					ProjectOrder projectOrder = workspace.computeProjectOrder(buildUs);
					// #computeProjectOrder may filter closed projects and
					// thereby return an empty array
					if (projectOrder.projects.length != 0)
						updateTaskName(Arrays.asList(projectOrder.projects));
					MultiStatus result = null;
					for (IProject project : projectOrder.projects) {
						try {
							Map<String, String> potentialNewArguments = null;
							synchronized (projectsScheduledForBuild) {
								projectsScheduledForBuild.remove(project);
								potentialNewArguments = projectBuilderArguments.remove(project);
							}
							Map<String, String> currentArguments = actualArguments.get(project);
							if (potentialNewArguments != null) {
								currentArguments.keySet().retainAll(potentialNewArguments.keySet());
							}
							// project could have been modified in the meantime, so check again
							project.build(IncrementalProjectBuilder.FULL_BUILD, XtextBuilder.BUILDER_ID,
									actualArguments.get(project), childProgress.newChild(1));
						} catch (OperationCanceledException e) {
							return Status.CANCEL_STATUS;
						} catch (CoreException cex) {
							if (result == null) {
								result = new MultiStatus(Activator.getDefault().getBundle().getSymbolicName(), 1, null,
										null);
							}
							result.merge(cex.getStatus());
						} catch (Exception x) {
							log.error(x.getMessage(), x);
							if (result == null) {
								result = new MultiStatus(Activator.getDefault().getBundle().getSymbolicName(), 1, null,
										null);
							}
							result.merge(new Status(IStatus.ERROR,
									Activator.getDefault().getBundle().getSymbolicName(),
									Messages.BuildScheduler_BuildOf + Strings.notNull(project.getName())
											+ Messages.BuildScheduler_FailedEtc, x));
						}
						if (childProgress.isCanceled())
							return Status.CANCEL_STATUS;
					}
					if (result != null)
						return result;
				}
			} finally {
				if (monitor != null)
					monitor.done();
			}
			return Status.OK_STATUS;
		}
	}
}
