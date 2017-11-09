/**
 * Copyright (c) 2017 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.wizard.template;

import org.eclipse.xtext.ui.wizard.template.ProjectVariable;

@SuppressWarnings("all")
public final class BooleanProjectVariable extends ProjectVariable {
  private boolean value;
  
  public BooleanProjectVariable(final String label, final boolean defaultValue, final String description) {
    super(label, description);
    this.value = defaultValue;
  }
  
  public boolean getValue() {
    return this.value;
  }
  
  public boolean setValue(final boolean value) {
    return this.value = value;
  }
  
  @Override
  public String toString() {
    return Boolean.valueOf(this.value).toString();
  }
}
