/**
 * Copyright (c) 2017, 2020 TypeFox GmbH (http://www.typefox.io) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.editor.quickfix;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.ui.editor.model.edit.BatchModification;
import org.eclipse.xtext.ui.editor.model.edit.BatchModification.IBatchableModification;
import org.eclipse.xtext.ui.util.IssueUtil;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Pure;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * MarkerResolution which extends WorkbenchMarkerResolution and can be applied on multiple markers.
 * 
 * @author dhuebner - Initial contribution and API
 * @since 2.13
 */
public class WorkbenchMarkerResolutionAdapter extends WorkbenchMarkerResolution {

	public static class Factory {

		@Inject
		private Provider<WorkbenchMarkerResolutionAdapter> provider;

		public IMarkerResolution create(IMarker marker, IssueResolution resolution) {
			final WorkbenchMarkerResolutionAdapter resolutionFix = provider.get();
			resolutionFix.primaryResolution = resolution;
			resolutionFix.primaryMarker = marker;
			return resolutionFix;
		}
	}

	private static final Logger LOG = Logger.getLogger(WorkbenchMarkerResolutionAdapter.class);

	@Inject
	private MarkerResolutionGenerator resolutionsGenerator;

	@Inject
	private IssueUtil issueUtil;

	@Inject
	private Provider<BatchModification> batchModificationProvider;

	@Accessors
	private IssueResolution primaryResolution;

	@Accessors
	private IMarker primaryMarker;

	@Override
	public IMarker[] findOtherMarkers(final IMarker[] markers) {
		return Arrays.asList(markers).stream()
				.filter(marker -> marker != primaryMarker && issueUtil.getCode(primaryMarker) == issueUtil.getCode(marker))
				.toArray(IMarker[]::new);
	}

	@Override
	public String getLabel() {
		return primaryResolution.getLabel();
	}

	@Override
	public void run(final IMarker marker) {
		if (!marker.exists()) {
			return;
		}
		run(new IMarker[] { marker }, new NullProgressMonitor());
	}

	@Override
	public void run(final IMarker[] markers, final IProgressMonitor progressMonitor) {
		final Map<IProject, List<IMarker>> markersByProject = Arrays.asList(markers).stream() //
				.collect(Collectors.groupingBy(marker -> marker.getResource().getProject()));

		final SubMonitor monitor = SubMonitor.convert(progressMonitor);
		monitor.beginTask("Applying resolutions", markersByProject.size());

		markersByProject.entrySet().forEach(e -> {
			final BatchModification batch = batchModificationProvider.get();
			batch.setProject(e.getKey());

			final List<IMarker> markersInProject = e.getValue();
			final Stream<IssueResolution> resolutions = markersInProject.stream() //
					.map(marker -> resolution(marker)) //
					.filter(Objects::nonNull);
			cancelIfNeeded(monitor);

			final List<IBatchableModification> modifications = resolutions //
					.map(resolution -> resolution.getModification()) //
					.filter(BatchModification.IBatchableModification.class::isInstance) //
					.map(BatchModification.IBatchableModification.class::cast) //
					.collect(Collectors.toList());
			cancelIfNeeded(monitor);

			batch.apply(modifications, monitor.newChild(1));
			cancelIfNeeded(monitor);
		});

		monitor.done();
	}

	protected void cancelIfNeeded(IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
	}

	public IssueResolution resolution(final IMarker marker) {
		if (!marker.exists()) {
			return null;
		}
		final Issue issue = issueUtil.createIssue(marker);
		final List<IssueResolution> resolutions = resolutionsGenerator.getResolutionProvider().getResolutions(issue);
		final Optional<IssueResolution> issueResolution = resolutions.stream()
				.filter(resolution -> isSameResolution(resolution, primaryResolution)).findFirst();
		if (!issueResolution.isPresent()) {
			LOG.warn("Resolution missing for " + issue.getCode());
		}
		return issueResolution.get();
	}

	@Override
	public String getDescription() {
		return primaryResolution.getDescription();
	}

	@Override
	public Image getImage() {
		return resolutionsGenerator.getImage(primaryResolution);
	}

	private boolean isSameResolution(final IssueResolution issueResolution, final IssueResolution other) {
		return issueResolution != null //
				&& other != null //
				&& issueResolution.getDescription() == other.getDescription() //
				&& issueResolution.getLabel() == other.getLabel() //
				&& issueResolution.getImage() == other.getImage();
	}

	@Pure
	public IssueResolution getPrimaryResolution() {
		return primaryResolution;
	}

	public void setPrimaryResolution(final IssueResolution primaryResolution) {
		this.primaryResolution = primaryResolution;
	}

	@Pure
	public IMarker getPrimaryMarker() {
		return primaryMarker;
	}

	public void setPrimaryMarker(IMarker primaryMarker) {
		this.primaryMarker = primaryMarker;
	}
}
