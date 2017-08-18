/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.refactoring.participant;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@Data
@SuppressWarnings("all")
public class ResourceMove {
  private final URI oldURI;
  
  private final URI newURI;
  
  public ResourceMove(final URI oldURI, final URI newURI) {
    super();
    this.oldURI = oldURI;
    this.newURI = newURI;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.oldURI== null) ? 0 : this.oldURI.hashCode());
    result = prime * result + ((this.newURI== null) ? 0 : this.newURI.hashCode());
    return result;
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
    ResourceMove other = (ResourceMove) obj;
    if (this.oldURI == null) {
      if (other.oldURI != null)
        return false;
    } else if (!this.oldURI.equals(other.oldURI))
      return false;
    if (this.newURI == null) {
      if (other.newURI != null)
        return false;
    } else if (!this.newURI.equals(other.newURI))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("oldURI", this.oldURI);
    b.add("newURI", this.newURI);
    return b.toString();
  }
  
  @Pure
  public URI getOldURI() {
    return this.oldURI;
  }
  
  @Pure
  public URI getNewURI() {
    return this.newURI;
  }
}
