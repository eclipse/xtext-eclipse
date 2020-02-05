/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.xbase.ui.tests.editor;

import org.eclipse.xtext.xbase.ui.tests.editor.ContentAssistInBlockTest;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class ContentAssistFinallyTest6 extends ContentAssistInBlockTest {
  @Override
  protected String getPrefix() {
    return "try { var x = 0 } catch(Exception ex) {} finally {";
  }
  
  @Override
  protected String getSuffix() {
    return "}";
  }
}
