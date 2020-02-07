/**
 * Copyright (c) 2016, 2018 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.domainmodel.ui.editor.hierarchy;

import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.xtext.ui.editor.hierarchy.DefaultCallHierarchyViewPart;
import org.eclipse.xtext.xbase.lib.Pair;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class AssociationHierarchyViewPart extends DefaultCallHierarchyViewPart {
  @Override
  protected Pair<String, ColumnLayoutData>[] getLocationColumnDescriptions() {
    ColumnWeightData _columnWeightData = new ColumnWeightData(60);
    Pair<String, ColumnLayoutData> _mappedTo = Pair.<String, ColumnLayoutData>of("Line", _columnWeightData);
    ColumnWeightData _columnWeightData_1 = new ColumnWeightData(300);
    Pair<String, ColumnLayoutData> _mappedTo_1 = Pair.<String, ColumnLayoutData>of("Property", _columnWeightData_1);
    return new Pair[] { _mappedTo, _mappedTo_1 };
  }
}
