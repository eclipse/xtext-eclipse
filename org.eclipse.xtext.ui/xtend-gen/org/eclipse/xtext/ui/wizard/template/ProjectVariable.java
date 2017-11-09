/**
 * Copyright (c) 2017 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.wizard.template;

/**
 * Part of a project template the variable defines the UI for the user to configure the generated files. Is generated
 * by reflection by fields annotated with @Variable from classes annotated with @Template. There is a fixed set of
 * subclasses to represent ProjectVariables of various types (boolean, string ...).
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
@SuppressWarnings("all")
public abstract class ProjectVariable {
  private String id;
  
  private final String label;
  
  private final String description;
  
  protected boolean enabled;
  
  ProjectVariable(final String label, final String description) {
    this.label = label;
    this.description = description;
    this.enabled = true;
  }
  
  String setId(final String value) {
    return this.id = value;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getLabel() {
    return this.label;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public boolean setEnabled(final boolean value) {
    return this.enabled = value;
  }
}
