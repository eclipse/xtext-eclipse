/*******************************************************************************
 * Copyright (c) 2010, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xtext.launcher;

import static org.eclipse.xtext.xtext.launcher.SelectionUtil.*;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;

/**
 * @author Peter Friese - Initial contribution and API
 */
public class WorkflowLaunchUtils {

	protected static final String MWE2_FILE_EXTENSION = "mwe2";
	protected static final String MWE2_LAUNCH_SHORCUT_ID = "org.eclipse.emf.mwe2.launch.shortcut1";
	
	protected static final Logger logger = Logger.getLogger(WorkflowLaunchUtils.class);
	
	private static final boolean mwe2Available;
	
	static {
		boolean b = true;
		try {
			Class.forName("org.eclipse.emf.mwe2.launch.runtime.Mwe2Launcher", false, WorkflowLaunchUtils.class.getClassLoader());
		} catch(ClassNotFoundException e) {
			b = false;
		}
		mwe2Available = b;
	}

	public static IResource workflowFileFor(ExecutionEvent event) {
		IFile file = getSelectedFile(event);
		return workflowFileFor(file);
	}

	public static boolean workflowFileAvailableFor(IEditorPart activeEditor) {
		return (workflowFileFor(activeEditor) != null);
	}

	public static IResource workflowFileFor(IEditorPart editor) {
		IFile file = getSelectedFile(editor);
		return workflowFileFor(file);
	}

	public static IResource workflowFileFor(ISelection selection) {
		IFile file = getSelectedFile(selection);
		return workflowFileFor(file);
	}

	public static boolean workflowFileAvailableForGrammarFile(IResource resource) {
		return mwe2Available && (workflowFileFor(resource) != null);
	}

	public static IResource workflowFileFor(IResource resource) {
		if (resource == null) {
			return null;
		}
		IContainer parent = resource.getParent();
		IPath locationWithoutFileExtension = parent.getProjectRelativePath().append("Generate" + resource.getName()).removeFileExtension();
		IResource workflowFile = resource.getProject().findMember(locationWithoutFileExtension
				.addFileExtension(MWE2_FILE_EXTENSION));
		return workflowFile;
	}

	/**
	 * Find launch shortcut in the extension registry. Using this introspective approach to avoid an explicit
	 * dependency to <code>org.eclipse.emf.mwe2.launch</code> bundle.
	 */
	protected static ILaunchShortcut findLaunchShortcut() throws CoreException {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"org.eclipse.debug.ui.launchShortcuts");
		for (IConfigurationElement configurationElement : config) {
			String id = configurationElement.getAttribute("id");
			if (MWE2_LAUNCH_SHORCUT_ID.equals(id)) {
				Object executableExtension = configurationElement.createExecutableExtension("class");
				if (executableExtension instanceof ILaunchShortcut) {
					ILaunchShortcut launchShortcut = (ILaunchShortcut) executableExtension;
					return launchShortcut;
				}
			}
		}
		return null;
	}

	public static void runWorkflow(IResource workflowFile, String mode) {
		try {
			ILaunchShortcut launchShortcut = findLaunchShortcut();
			if (launchShortcut != null) {
				ISelection selection = new StructuredSelection(workflowFile);
				launchShortcut.launch(selection, mode);
			}
		} catch (CoreException e) {
			logger.error("Could not delegate to MWE2 launch shortcut.", e);
			e.printStackTrace();
		}
	}

}
