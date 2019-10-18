/**
 * Copyright (c) 2017, 2019 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.wizard.template;

import com.google.common.annotations.Beta;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;
import org.eclipse.xtext.ui.wizard.template.ProjectTemplateProcessor;

/**
 * Annotation to define a project template that is selected by the user in the new project wizard and declared in a
 * language specific ProjectTemplateProvider.
 * 
 * @author Arne Deutsch - Initial contribution and API
 * @since 2.14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Active(ProjectTemplateProcessor.class)
@Beta
public @interface ProjectTemplate {
  /**
   * Label of the project template presented to the user in the list of templates. The label is written to a file
   * "messages.properties" and read from there at runtime. This way it is possible to internationalize using i18n.
   */
  public String label();
  /**
   * Path to the icon of the project template presented to the user in the list of templates.
   */
  public String icon();
  /**
   * Description of the project template presented to the user in a text field in the new project wizard. It is
   * rendered inside a FormText widget and has to conform to a simplified HTML format. Example:
   * 
   * <pre>
   * &lt;p&gt;&lt;b&gt;Hello World&lt;/b&gt;&lt;/p&gt;
   * &lt;p>This is a parameterized hello world for MyDsl. You can set a parameter to modify the content
   * in the generated file and a parameter to set the package the file is created in.&lt;/p&gt;
   * </pre>
   * 
   * The description is written to a file "messages.properties" and read from there at runtime. This way it is
   * possible to internationalize using i18n.
   */
  public String description();
}
