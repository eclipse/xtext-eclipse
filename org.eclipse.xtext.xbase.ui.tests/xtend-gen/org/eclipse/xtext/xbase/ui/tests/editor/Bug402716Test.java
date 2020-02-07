/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.xbase.ui.tests.editor;

import org.eclipse.xtext.xbase.ui.tests.editor.AbstractXbaseContentAssistBugTest;
import org.junit.Test;

/**
 * TODO resolve type parameters in display strings
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class Bug402716Test extends AbstractXbaseContentAssistBugTest {
  @Test
  public void testFilterType() throws Exception {
    this.newBuilder().append("#[].filter").assertProposal("filter()").withDisplayString("filter(Class<T> type) : Iterable<T> - IterableExtensions");
  }
  
  @Test
  public void testFilterLambda() throws Exception {
    this.newBuilder().append("#[].filter").assertProposal("filter[]").withDisplayString("filter((T)=>Boolean predicate) : Iterable<T> - IterableExtensions");
  }
  
  @Test
  public void testMapIterable() throws Exception {
    this.newBuilder().append("(#[] as Iterable<String>).map").assertProposal("map[]").withDisplayString("map((T)=>R transformation) : Iterable<R> - IterableExtensions");
  }
  
  @Test
  public void testMapListLiteral() throws Exception {
    this.newBuilder().append("#[].map").assertProposal("map[]").withDisplayString("map((T)=>R transformation) : List<R> - ListExtensions");
  }
  
  @Test
  public void testMapNewArrayList() throws Exception {
    this.newBuilder().append("newArrayList.map").assertProposal("map[]").withDisplayString("map((T)=>R transformation) : List<R> - ListExtensions");
  }
}
