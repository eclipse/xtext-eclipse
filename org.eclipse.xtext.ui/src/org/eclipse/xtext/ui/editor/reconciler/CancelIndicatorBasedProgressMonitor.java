/**
 * Copyright (c) 2014, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.editor.reconciler;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.xtext.util.CancelIndicator;

/**
 * @author Sven Efftinge - Initial contribution and API
 * 
 * @since 2.7
 */
public class CancelIndicatorBasedProgressMonitor implements IProgressMonitor {

	private final IProgressMonitor doDelegate = new NullProgressMonitor();

	private final CancelIndicator cancelIndicator;

	public CancelIndicatorBasedProgressMonitor(final CancelIndicator cancelIndicator) {
		this.cancelIndicator = cancelIndicator;
	}

	@Override
	public boolean isCanceled() {
		return cancelIndicator.isCanceled();
	}

	@Override
	public void beginTask(String name, int totalWork) {
		doDelegate.beginTask(name, totalWork);
	}

	@Override
	public void done() {
		doDelegate.done();
	}

	@Override
	public void internalWorked(double work) {
		doDelegate.internalWorked(work);
	}

	@Override
	public void setCanceled(boolean value) {
		doDelegate.setCanceled(value);
	}

	@Override
	public void setTaskName(String name) {
		doDelegate.setTaskName(name);
	}

	@Override
	public void subTask(String name) {
		doDelegate.subTask(name);
	}

	@Override
	public void worked(int work) {
		doDelegate.worked(work);
	}
}
