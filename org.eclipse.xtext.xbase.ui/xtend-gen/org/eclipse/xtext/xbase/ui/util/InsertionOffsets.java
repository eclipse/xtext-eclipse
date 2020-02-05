/**
 * Copyright (c) 2014, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.xbase.ui.util;

import com.google.common.base.Objects;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author Anton Kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class InsertionOffsets {
  public int before(final EObject element) {
    return NodeModelUtils.findActualNodeFor(element).getOffset();
  }
  
  public int after(final EObject element) {
    int _xblockexpression = (int) 0;
    {
      final ICompositeNode node = NodeModelUtils.findActualNodeFor(element);
      _xblockexpression = node.getEndOffset();
    }
    return _xblockexpression;
  }
  
  public int inEmpty(final EObject element) {
    int _xblockexpression = (int) 0;
    {
      final ICompositeNode node = NodeModelUtils.findActualNodeFor(element);
      final Function1<ILeafNode, Boolean> _function = (ILeafNode it) -> {
        String _text = it.getText();
        return Boolean.valueOf(Objects.equal(_text, "{"));
      };
      final ILeafNode openingBraceNode = IterableExtensions.<ILeafNode>findFirst(node.getLeafNodes(), _function);
      int _xifexpression = (int) 0;
      if ((openingBraceNode != null)) {
        int _offset = openingBraceNode.getOffset();
        _xifexpression = (_offset + 1);
      } else {
        _xifexpression = node.getEndOffset();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
