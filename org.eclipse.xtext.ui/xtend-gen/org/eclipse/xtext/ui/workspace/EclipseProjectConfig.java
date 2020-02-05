/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.workspace;

import java.util.Set;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.ui.workspace.EclipseProjectConfigProvider;
import org.eclipse.xtext.ui.workspace.EclipseWorkspaceConfig;
import org.eclipse.xtext.workspace.IProjectConfig;
import org.eclipse.xtext.workspace.ISourceFolder;
import org.eclipse.xtext.workspace.IWorkspaceConfig;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

@Data
@SuppressWarnings("all")
public class EclipseProjectConfig implements IProjectConfig {
  private final IProject project;
  
  private final EclipseProjectConfigProvider projectConfigProvider;
  
  @Override
  public String getName() {
    return this.project.getName();
  }
  
  @Override
  public URI getPath() {
    String _name = this.getName();
    String _plus = ("/" + _name);
    String _plus_1 = (_plus + "/");
    return URI.createPlatformResourceURI(_plus_1, true);
  }
  
  @Override
  public Set<? extends ISourceFolder> getSourceFolders() {
    return CollectionLiterals.<ISourceFolder>emptySet();
  }
  
  @Override
  public ISourceFolder findSourceFolderContaining(final URI member) {
    final Function1<ISourceFolder, Boolean> _function = (ISourceFolder sourceFolder) -> {
      return Boolean.valueOf(sourceFolder.contains(member));
    };
    return IterableExtensions.findFirst(this.getSourceFolders(), _function);
  }
  
  @Override
  public IWorkspaceConfig getWorkspaceConfig() {
    IWorkspaceRoot _root = this.project.getWorkspace().getRoot();
    return new EclipseWorkspaceConfig(_root, this.projectConfigProvider);
  }
  
  @Override
  public String toString() {
    return this.project.toString();
  }
  
  public EclipseProjectConfig(final IProject project, final EclipseProjectConfigProvider projectConfigProvider) {
    super();
    this.project = project;
    this.projectConfigProvider = projectConfigProvider;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.project== null) ? 0 : this.project.hashCode());
    return prime * result + ((this.projectConfigProvider== null) ? 0 : this.projectConfigProvider.hashCode());
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
    EclipseProjectConfig other = (EclipseProjectConfig) obj;
    if (this.project == null) {
      if (other.project != null)
        return false;
    } else if (!this.project.equals(other.project))
      return false;
    if (this.projectConfigProvider == null) {
      if (other.projectConfigProvider != null)
        return false;
    } else if (!this.projectConfigProvider.equals(other.projectConfigProvider))
      return false;
    return true;
  }
  
  @Pure
  public IProject getProject() {
    return this.project;
  }
  
  @Pure
  public EclipseProjectConfigProvider getProjectConfigProvider() {
    return this.projectConfigProvider;
  }
}
