/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.refactoring.participant

import org.eclipse.core.runtime.Platform
import java.util.List
import com.google.inject.Singleton
import org.eclipse.core.runtime.CoreException
import org.apache.log4j.Logger

/**
 * @author koehnlein - Initial contribution and API
 * @since 2.13
 */
@Singleton
abstract class AbstractParticipantStrategyRegistry<T> {
	
	static Logger LOG = Logger.getLogger(AbstractParticipantStrategyRegistry)
	
	protected abstract def String getExtensionPointID()
	
	private List<T> strategies
	
	def List<? extends T> getStrategies() {
		return strategies ?: {
			val configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointID);
			val strategies= <T>newArrayList
			for (configurationElement : configurationElements) {
				try {
					strategies += configurationElement.createExecutableExtension('class') as T
				} catch (CoreException e) {
					LOG.error("Error instantiating participant strategy", e);
				}
			}
			strategies
		}
	}
}