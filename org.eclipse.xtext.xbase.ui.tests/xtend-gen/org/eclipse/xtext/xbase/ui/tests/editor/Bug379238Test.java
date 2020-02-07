/**
 * Copyright (c) 2013, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.xbase.ui.tests.editor;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.ui.editor.hover.html.XtextBrowserInformationControlInput;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.ui.tests.AbstractXbaseUITestCase;
import org.eclipse.xtext.xbase.ui.tests.editor.TestingXbaseHoverProvider;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Holger Schill - Initial contribution and API
 */
@SuppressWarnings("all")
public class Bug379238Test extends AbstractXbaseUITestCase {
  @Inject
  private ParseHelper<XConstructorCall> parseHelper;
  
  @Inject
  private TestingXbaseHoverProvider hoverProvider;
  
  @Test
  public void testXConstructorWithNullConstructorNoException() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new()");
    _builder.newLine();
    final XConstructorCall constructorCall = this.parseHelper.parse(_builder);
    final XtextBrowserInformationControlInput result = this.hoverProvider.getHoverInfo(constructorCall);
    Assert.assertNull(result);
  }
}
