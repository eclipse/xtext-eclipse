/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.refactoring.participant;

import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * @author koehnlein - Initial contribution and API
 */
@Singleton
@SuppressWarnings("all")
public abstract class AbstractParticipantStrategyRegistry<T extends Object> {
  private static Logger LOG = Logger.getLogger(AbstractParticipantStrategyRegistry.class);
  
  protected abstract String getExtensionPointID();
  
  private List<T> strategies;
  
  public List<? extends T> getStrategies() {
    List<T> _elvis = null;
    if (this.strategies != null) {
      _elvis = this.strategies;
    } else {
      ArrayList<T> _xblockexpression = null;
      {
        final IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(this.getExtensionPointID());
        final ArrayList<T> strategies = CollectionLiterals.<T>newArrayList();
        for (final IConfigurationElement configurationElement : configurationElements) {
          try {
            Object _createExecutableExtension = configurationElement.createExecutableExtension("class");
            strategies.add(((T) _createExecutableExtension));
          } catch (final Throwable _t) {
            if (_t instanceof CoreException) {
              final CoreException e = (CoreException)_t;
              AbstractParticipantStrategyRegistry.LOG.error("Error instantiating participant strategy", e);
            } else {
              throw Exceptions.sneakyThrow(_t);
            }
          }
        }
        _xblockexpression = strategies;
      }
      _elvis = _xblockexpression;
    }
    return _elvis;
  }
}
