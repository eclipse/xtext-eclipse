/**
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.homeautomation.ui.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.example.homeautomation.ruleEngine.Device;
import org.eclipse.xtext.example.homeautomation.ruleEngine.Rule;
import org.eclipse.xtext.example.homeautomation.ruleEngine.State;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
@SuppressWarnings("all")
public class RuleEngineLabelProvider extends XbaseLabelProvider {
  @Inject
  public RuleEngineLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final Rule rule) {
    String _xblockexpression = null;
    {
      EObject _eContainer = rule.getDeviceState().eContainer();
      final Device device = ((Device) _eContainer);
      String _description = rule.getDescription();
      String _plus = (_description + " when ");
      String _name = device.getName();
      String _plus_1 = (_plus + _name);
      String _plus_2 = (_plus_1 + ".");
      String _name_1 = rule.getDeviceState().getName();
      _xblockexpression = (_plus_2 + _name_1);
    }
    return _xblockexpression;
  }
  
  public String image(final Rule rule) {
    return "rule.gif";
  }
  
  public String image(final Device device) {
    return "device.gif";
  }
  
  public String image(final State state) {
    return "state.gif";
  }
}
