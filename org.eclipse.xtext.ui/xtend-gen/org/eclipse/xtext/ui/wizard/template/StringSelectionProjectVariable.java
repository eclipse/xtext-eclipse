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
public final class StringSelectionProjectVariable extends ProjectVariable {
  private String[] possibleValues;
  
  private String value;
  
  public StringSelectionProjectVariable(final String label, final String[] possibleValues, final String description) {
    super(label, description);
    this.possibleValues = possibleValues;
    this.value = possibleValues[0];
  }
  
  public String[] getPossibleValues() {
    return this.possibleValues;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public String setValue(final String value) {
    return this.value = value;
  }
  
  @Override
  public String toString() {
    return this.value;
  }
}
