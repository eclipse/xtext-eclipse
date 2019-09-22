/**
 * Copyright (c) 2016, 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.arithmetics.ui.editor.hierarchy;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.example.arithmetics.arithmetics.AbstractDefinition;
import org.eclipse.xtext.example.arithmetics.arithmetics.ArithmeticsPackage;
import org.eclipse.xtext.example.arithmetics.ui.editor.hierarchy.ArithmeticsCallHierarchyNodeLocationProvider;
import org.eclipse.xtext.ide.editor.hierarchy.DefaultCallHierarchyBuilder;
import org.eclipse.xtext.ide.editor.hierarchy.IHierarchyNodeLocationProvider;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

@SuppressWarnings("all")
public class ArithmeticsCallHierarchyBuilder extends DefaultCallHierarchyBuilder {
  @Inject
  private ArithmeticsCallHierarchyNodeLocationProvider arithmeticsCallHierarchyNodeLocationProvider;
  
  @Override
  protected IHierarchyNodeLocationProvider getHierarchyNodeLocationProvider() {
    return this.arithmeticsCallHierarchyNodeLocationProvider;
  }
  
  @Override
  protected IEObjectDescription findDeclaration(final URI objectURI) {
    final IEObjectDescription description = this.getDescription(objectURI);
    EClass _eClass = null;
    if (description!=null) {
      _eClass=description.getEClass();
    }
    boolean _isDefinition = this.isDefinition(_eClass);
    if (_isDefinition) {
      return description;
    }
    final IUnitOfWork<IEObjectDescription, EObject> _function = (EObject object) -> {
      return this.getDescription(EcoreUtil2.<AbstractDefinition>getContainerOfType(object, AbstractDefinition.class));
    };
    return this.<IEObjectDescription>readOnly(objectURI, _function);
  }
  
  @Override
  protected boolean filterReference(final IReferenceDescription reference) {
    return ((reference != null) && this.isDefinition(reference.getEReference().getEType()));
  }
  
  protected boolean isDefinition(final EClassifier type) {
    return this.isAssignable(ArithmeticsPackage.Literals.ABSTRACT_DEFINITION, type);
  }
}
