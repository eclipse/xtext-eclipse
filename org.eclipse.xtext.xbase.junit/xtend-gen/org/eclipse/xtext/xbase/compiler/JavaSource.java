/**
 * Copyright (c) 2015, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.xbase.compiler;

import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * @since 2.9
 */
@Data
@SuppressWarnings("all")
public class JavaSource {
  private final String fileName;
  
  private final String code;
  
  public JavaSource(final String fileName, final String code) {
    super();
    this.fileName = fileName;
    this.code = code;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.fileName== null) ? 0 : this.fileName.hashCode());
    return prime * result + ((this.code== null) ? 0 : this.code.hashCode());
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    JavaSource other = (JavaSource) obj;
    if (this.fileName == null) {
      if (other.fileName != null)
        return false;
    } else if (!this.fileName.equals(other.fileName))
      return false;
    if (this.code == null) {
      if (other.code != null)
        return false;
    } else if (!this.code.equals(other.code))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("fileName", this.fileName);
    b.add("code", this.code);
    return b.toString();
  }
  
  @Pure
  public String getFileName() {
    return this.fileName;
  }
  
  @Pure
  public String getCode() {
    return this.code;
  }
}
