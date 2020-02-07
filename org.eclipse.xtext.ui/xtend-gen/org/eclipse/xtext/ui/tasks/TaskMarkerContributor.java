/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.tasks;

import com.google.inject.Inject;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.tasks.ITaskFinder;
import org.eclipse.xtext.tasks.Task;
import org.eclipse.xtext.ui.markers.IMarkerContributor;
import org.eclipse.xtext.ui.tasks.TaskMarkerCreator;
import org.eclipse.xtext.ui.tasks.TaskMarkerTypeProvider;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * @author Stefan Oehme - Initial contribution and API
 * @since 2.6
 */
@SuppressWarnings("all")
public class TaskMarkerContributor implements IMarkerContributor {
  private static final Logger log = Logger.getLogger(TaskMarkerContributor.class);
  
  @Inject
  private TaskMarkerCreator markerCreator;
  
  @Inject
  private ITaskFinder taskFinder;
  
  @Inject
  private TaskMarkerTypeProvider typeProvider;
  
  @Override
  public void updateMarkers(final IFile file, final Resource resource, final IProgressMonitor monitor) {
    try {
      final List<Task> tasks = this.taskFinder.findTasks(resource);
      boolean _isCanceled = monitor.isCanceled();
      if (_isCanceled) {
        throw new OperationCanceledException();
      }
      this.deleteMarkers(file, monitor);
      this.createTaskMarkers(file, tasks, monitor);
    } catch (final Throwable _t) {
      if (_t instanceof CoreException) {
        final CoreException e = (CoreException)_t;
        TaskMarkerContributor.log.error(e.getMessage(), e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  protected void createTaskMarkers(final IFile file, final List<Task> tasks, final IProgressMonitor monitor) throws CoreException {
    for (final Task task : tasks) {
      this.markerCreator.createMarker(task, file, this.typeProvider.getMarkerType(task));
    }
  }
  
  @Override
  public void deleteMarkers(final IFile file, final IProgressMonitor monitor) {
    try {
      file.deleteMarkers(TaskMarkerTypeProvider.XTEXT_TASK_TYPE, true, IResource.DEPTH_ZERO);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
