/**
 * Copyright (c) 2018, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.domainmodel.ui.tests;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelFactory;
import org.eclipse.xtext.example.domainmodel.ui.tests.DomainmodelUiInjectorProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.ui.IImageHelper;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.ui.labeling.XbaseImages2;
import org.eclipse.xtext.xtype.XtypeFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author miklossy - Initial contribution and API
 */
@RunWith(XtextRunner.class)
@InjectWith(DomainmodelUiInjectorProvider.class)
@SuppressWarnings("all")
public class LabelProviderTest {
  @Inject
  private ILabelProvider labelProvider;
  
  @Inject
  private IImageHelper imageHelper;
  
  @Inject
  private XbaseImages2 xbaseImages;
  
  @Extension
  private final DomainmodelFactory _domainmodelFactory = DomainmodelFactory.eINSTANCE;
  
  @Extension
  private final XtypeFactory _xtypeFactory = XtypeFactory.eINSTANCE;
  
  @Test
  public void package_image() {
    this.hasImage(this._domainmodelFactory.createPackageDeclaration(), "PackageDeclaration.gif");
  }
  
  @Test
  public void import_section_image() {
    this.hasImage(this._xtypeFactory.createXImportSection(), this.xbaseImages.forImportContainer());
  }
  
  @Test
  public void import_declaration_image() {
    this.hasImage(this._xtypeFactory.createXImportDeclaration(), this.xbaseImages.forImport());
  }
  
  @Test
  public void entity_image() {
    this.hasImage(this._domainmodelFactory.createEntity(), "Entity.gif");
  }
  
  @Test
  public void property_image() {
    this.hasImage(this._domainmodelFactory.createProperty(), "Property.gif");
  }
  
  @Test
  public void operation_image() {
    this.hasImage(this._domainmodelFactory.createOperation(), "Operation.gif");
  }
  
  private void hasImage(final EObject eObject, final String image) {
    final Image actual = this.labelProvider.getImage(eObject);
    final Image expected = this.imageHelper.getImage(image);
    Assert.assertEquals(expected, actual);
  }
  
  private void hasImage(final EObject eObject, final ImageDescriptor descriptor) {
    final Image actual = this.labelProvider.getImage(eObject);
    final Image expected = this.imageHelper.getImage(descriptor);
    Assert.assertEquals(expected, actual);
  }
}
