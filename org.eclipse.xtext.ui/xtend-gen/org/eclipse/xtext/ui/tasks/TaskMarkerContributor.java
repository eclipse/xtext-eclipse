/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.tasks;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.tasks.ITaskFinder;
import org.eclipse.xtext.tasks.Priority;
import org.eclipse.xtext.tasks.Task;
import org.eclipse.xtext.ui.markers.IMarkerContributor;
import org.eclipse.xtext.ui.tasks.TaskMarkerCreator;
import org.eclipse.xtext.ui.tasks.TaskMarkerTypeProvider;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author Stefan Oehme - Initial contribution and API
 * @since 2.6
 */
@SuppressWarnings("all")
public class TaskMarkerContributor implements IMarkerContributor {
  private final static Logger LOG = Logger.getLogger(TaskMarkerContributor.class);
  
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
      final IMarker[] markers = file.findMarkers(TaskMarkerTypeProvider.XTEXT_TASK_TYPE, true, IResource.DEPTH_ZERO);
      final boolean hasDelta = (IterableExtensions.<Task>exists(tasks, ((Function1<Task, Boolean>) (Task t) -> {
        final Function1<IMarker, Boolean> _function = (IMarker m) -> {
          return Boolean.valueOf(this.match(t, m));
        };
        boolean _exists = IterableExtensions.<IMarker>exists(((Iterable<IMarker>)Conversions.doWrapArray(markers)), _function);
        return Boolean.valueOf((!_exists));
      })) || IterableExtensions.<IMarker>exists(((Iterable<IMarker>)Conversions.doWrapArray(markers)), ((Function1<IMarker, Boolean>) (IMarker m) -> {
        final Function1<Task, Boolean> _function = (Task t) -> {
          return Boolean.valueOf(this.match(t, m));
        };
        boolean _exists = IterableExtensions.<Task>exists(tasks, _function);
        return Boolean.valueOf((!_exists));
      })));
      if (hasDelta) {
        this.deleteMarkers(file, monitor);
        this.createTaskMarkers(file, tasks, monitor);
      }
    } catch (final Throwable _t) {
      if (_t instanceof CoreException) {
        final CoreException e = (CoreException)_t;
        TaskMarkerContributor.LOG.error(e.getMessage(), e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  private boolean match(final Task task, final IMarker marker) {
    try {
      final Object[] attributes = marker.getAttributes(new String[] { IMarker.LOCATION, IMarker.PRIORITY, IMarker.MESSAGE, IMarker.LINE_NUMBER, IMarker.CHAR_START, IMarker.CHAR_END, IMarker.USER_EDITABLE });
      return ((((((Objects.equal(attributes[0], ("line " + Integer.valueOf(task.getLineNumber()))) && Objects.equal(attributes[1], Integer.valueOf(this.getPriority(task.getTag().getPriority())))) && Objects.equal(attributes[2], task.getFullText())) && Objects.equal(attributes[3], Integer.valueOf(task.getLineNumber()))) && Objects.equal(attributes[4], Integer.valueOf(task.getOffset()))) && Objects.equal(attributes[5], Integer.valueOf((task.getOffset() + task.getTotalLength())))) && Objects.equal(attributes[6], Boolean.valueOf(false)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private int getPriority(final Priority priority) {
    if (priority != null) {
      switch (priority) {
        case HIGH:
          return IMarker.PRIORITY_HIGH;
        case NORMAL:
          return IMarker.PRIORITY_NORMAL;
        case LOW:
          return IMarker.PRIORITY_LOW;
        default:
          return IMarker.PRIORITY_NORMAL;
      }
    } else {
      return IMarker.PRIORITY_NORMAL;
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
