/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tasks

import com.google.inject.Inject
import java.util.List
import org.apache.log4j.Logger
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IMarker
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.tasks.ITaskFinder
import org.eclipse.xtext.tasks.Task
import org.eclipse.xtext.ui.markers.IMarkerContributor
import org.eclipse.xtext.tasks.Priority

/**
 * @author Stefan Oehme - Initial contribution and API
 * @since 2.6
 */
class TaskMarkerContributor implements IMarkerContributor {
	static val LOG = Logger.getLogger(TaskMarkerContributor);

	@Inject
	TaskMarkerCreator markerCreator

	@Inject
	ITaskFinder taskFinder

	@Inject
	TaskMarkerTypeProvider typeProvider

	override updateMarkers(IFile file, Resource resource, IProgressMonitor monitor) {
		try {
			val tasks = taskFinder.findTasks(resource);
			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			val markers = file.findMarkers(TaskMarkerTypeProvider.XTEXT_TASK_TYPE, true, IResource.DEPTH_ZERO);
			
			// Avoid recreation of existing markers. This can result in a build loop.
			val hasDelta = tasks.exists[t|!markers.exists[m|match(t,m)]] || markers.exists[m|!tasks.exists[t|match(t,m)]]
			if (hasDelta) {
				deleteMarkers(file, monitor);
				createTaskMarkers(file, tasks, monitor);
			}
		} catch (CoreException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	private def boolean match (Task task, IMarker marker) {
		val attributes = marker.getAttributes(#[IMarker.LOCATION, IMarker.PRIORITY, IMarker.MESSAGE, IMarker.LINE_NUMBER, IMarker.CHAR_START, IMarker.CHAR_END, IMarker.USER_EDITABLE]);
		return 
		   attributes.get(0) == "line " + task.lineNumber
		&& attributes.get(1) == getPriority(task.tag.priority)
		&& attributes.get(2) == task.fullText
		&& attributes.get(3) == task.lineNumber
		&& attributes.get(4)  == task.offset
		&& attributes.get(5) == task.offset + task.totalLength
		&& attributes.get(6) == false
	}

	private def int getPriority(Priority priority) {
		switch(priority) {
			case HIGH:
				return IMarker.PRIORITY_HIGH
			case NORMAL:
				return IMarker.PRIORITY_NORMAL
			case LOW:
				return IMarker.PRIORITY_LOW
			default:
				return IMarker.PRIORITY_NORMAL
		}
	}

	protected def createTaskMarkers(IFile file, List<Task> tasks, IProgressMonitor monitor) throws CoreException {
		for (task : tasks) {
			markerCreator.createMarker(task, file, typeProvider.getMarkerType(task));
		}
	}

	override deleteMarkers(IFile file, IProgressMonitor monitor) {
		file.deleteMarkers(TaskMarkerTypeProvider.XTEXT_TASK_TYPE, true, IResource.DEPTH_ZERO)
	}

}
