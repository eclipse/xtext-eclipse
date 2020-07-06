/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.internal.Activator;
import org.eclipse.xtext.resource.IResourceDescription.Delta;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @author Jan Koehnlein
 */
@Singleton
public class RegistryBuilderParticipant implements IXtextBuilderParticipant {

	private static final String PARTICIPANT = "participant"; //$NON-NLS-1$

	private static final String EXTENSION_POINT_ID = PARTICIPANT;

	private static final String ATT_CLASS = "class"; //$NON-NLS-1$
	
	private static final String ATT_FILE_EXTENSIONS = "fileExtensions"; //$NON-NLS-1$
	
	private static final Splitter FILE_EXTENSION_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

	private static final Logger readerLog = Logger.getLogger(BuilderParticipantReader.class);

	@Inject
	private IExtensionRegistry extensionRegistry;

	private volatile ImmutableList<IXtextBuilderParticipant> participants;

	private Map<String, IXtextBuilderParticipant> classToParticipant;

	@Override
	public void build(IBuildContext buildContext, IProgressMonitor monitor) throws CoreException {
		ImmutableList<IXtextBuilderParticipant> participants = getParticipants();
		if (participants.isEmpty())
			return;
		SubMonitor progress = SubMonitor.convert(monitor, participants.size());
		progress.subTask(Messages.RegistryBuilderParticipant_InvokingBuildParticipants);
		for (IXtextBuilderParticipant participant : participants) {
			if (progress.isCanceled())
				throw new OperationCanceledException();
			participant.build(buildContext, progress.split(1));
		}
		if (progress.isCanceled())
			throw new OperationCanceledException();
	}

	public ImmutableList<IXtextBuilderParticipant> getParticipants() {
		ImmutableList<IXtextBuilderParticipant> result = participants;
		if (participants == null) {
			result = initParticipants();
		}
		return result;
	}

	protected synchronized ImmutableList<IXtextBuilderParticipant> initParticipants() {
		ImmutableList<IXtextBuilderParticipant> result = participants;
		if (result == null) {
			if (classToParticipant == null) {
				classToParticipant = Maps.newHashMap();
				Activator activator = Activator.getDefault();
				if (activator != null) {
					String pluginID = activator.getBundle().getSymbolicName();
					String extensionPointID = EXTENSION_POINT_ID;
					BuilderParticipantReader reader = new BuilderParticipantReader(extensionRegistry, pluginID, extensionPointID);
					reader.readRegistry();
				}
			}
			result = ImmutableList.copyOf(classToParticipant.values());
			participants = result;
		}
		return result;
	}

	public class BuilderParticipantReader extends RegistryReader {

		public BuilderParticipantReader(IExtensionRegistry pluginRegistry, String pluginID, String extensionPointID) {
			super(pluginRegistry, pluginID, extensionPointID);
		}

		@Override
		protected boolean readElement(IConfigurationElement element, boolean add) {
			if (element.getName().equals(PARTICIPANT)) {
				String className = element.getAttribute(ATT_CLASS);
				if (className == null) {
					logMissingAttribute(element, ATT_CLASS);
				} else if (add) {
					IXtextBuilderParticipant participant = new DeferredBuilderParticipant(element);
					if (classToParticipant.containsKey(className)) {
						readerLog.warn("The builder participant '" + className + "' was registered twice."); //$NON-NLS-1$ //$NON-NLS-2$
					}
					classToParticipant.put(className, participant);
					participants = null;
					return true;
				} else {
					classToParticipant.remove(className);
					participants = null;
					return true;
				}
			}
			return false;
		}

		@Override
		protected void logError(IConfigurationElement element, String text) {
			doLogError(element, text);
		}
	}
	
	private static void doLogError(IConfigurationElement element, String text) {
		IExtension extension = element.getDeclaringExtension();
		readerLog.error("Plugin " + extension.getContributor().getName() + ", extension " + extension.getExtensionPointUniqueIdentifier()); //$NON-NLS-1$ //$NON-NLS-2$
		readerLog.error(text);
	}

	private static class NoOpBuilderParticipant implements IXtextBuilderParticipant {
		@Override
		public void build(IBuildContext context, IProgressMonitor monitor) throws CoreException {
		}
	}
	
	public static class DeferredBuilderParticipant implements IXtextBuilderParticipant {
		private final IConfigurationElement element;
		private final ImmutableList<String> handledFileExtensions;
		
		private IXtextBuilderParticipant delegate;

		public DeferredBuilderParticipant(IConfigurationElement element) {
			this.element = element;
			String fileExtensionsAtt = Strings.nullToEmpty(element.getAttribute(ATT_FILE_EXTENSIONS));
			this.handledFileExtensions = ImmutableList.copyOf(FILE_EXTENSION_SPLITTER.split(fileExtensionsAtt));
		}

		@Override
		public void build(IBuildContext context, IProgressMonitor monitor) throws CoreException {
			getDelegate(context).build(context, monitor);
		}

		private IXtextBuilderParticipant getDelegate(IBuildContext context) {
			if (!interestedIn(context)) {
				return new NoOpBuilderParticipant();
			}
			return getDelegate();
		}
		
		public IXtextBuilderParticipant getDelegate() {
			if (delegate == null) {
				initDelegate();
			}
			return delegate;
		}

		private synchronized void initDelegate() {
			if (delegate != null)
				return;
			try {
				Object participant = element.createExecutableExtension(ATT_CLASS);
				if (participant instanceof IXtextBuilderParticipant) {
					delegate = (IXtextBuilderParticipant) participant;
				} else {
					doLogError(element, element.getAttribute(ATT_CLASS)
							+ " did not yield an instance of IXtextBuilderParticipant but " + //$NON-NLS-1$
							participant.getClass().getName());
				}
			} catch (CoreException e) {
				doLogError(element, e.getMessage());
			} catch (NoClassDefFoundError e) {
				doLogError(element, e.getMessage());
			}
			if (delegate == null) {
				delegate = new NoOpBuilderParticipant();
			}
		}

		private boolean interestedIn(IBuildContext context) {
			List<String> extensions = getHandledFileExtensions();
			if (extensions.isEmpty()) {
				return true;
			}
			for (Delta change : context.getDeltas()) {
				String fileExtension = change.getUri().fileExtension();
				if (extensions.contains(fileExtension)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Ask the participant whether it is interesting in the given file extension.
		 * Does not initialize the delegate or the injector of the contributing language.
		 */
		public boolean isParticipating(String fileExtension) {
			List<String> extensions = getHandledFileExtensions();
			return extensions.isEmpty() || extensions.contains(fileExtension);
		}
		
		private List<String> getHandledFileExtensions() {
			return handledFileExtensions;
		}

	}

}
