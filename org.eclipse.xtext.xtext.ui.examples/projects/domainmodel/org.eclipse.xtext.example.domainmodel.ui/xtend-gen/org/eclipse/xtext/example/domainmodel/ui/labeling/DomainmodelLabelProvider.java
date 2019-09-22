/**
 * Copyright (c) 2016, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.domainmodel.ui.labeling;

import com.google.inject.Inject;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmArrayType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmUpperBound;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.example.domainmodel.domainmodel.Operation;
import org.eclipse.xtext.example.domainmodel.domainmodel.Property;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;

/**
 * Provides labels for a EObjects.
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
@SuppressWarnings("all")
public class DomainmodelLabelProvider extends XbaseLabelProvider {
  @Inject
  public DomainmodelLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  @Override
  protected Object doGetImage(final Object element) {
    if (((element instanceof EObject) && (!(element instanceof JvmIdentifiableElement)))) {
      StringConcatenation _builder = new StringConcatenation();
      String _name = ((EObject) element).eClass().getName();
      _builder.append(_name);
      _builder.append(".gif");
      return _builder.toString();
    }
    return super.doGetImage(element);
  }
  
  public String text(final Property property) {
    StringBuilder builder = new StringBuilder();
    builder.append(Strings.notNull(property.getName()));
    builder.append(" : ");
    this.append(builder, property.getType());
    return builder.toString();
  }
  
  public String text(final Operation operation) {
    StringBuilder builder = new StringBuilder();
    builder.append(Strings.notNull(operation.getName()));
    builder.append("(");
    boolean isFirst = true;
    EList<JvmFormalParameter> _params = operation.getParams();
    for (final JvmFormalParameter param : _params) {
      {
        if ((!isFirst)) {
          builder.append(", ");
        }
        isFirst = false;
        this.append(builder, param.getParameterType());
      }
    }
    builder.append(") : ");
    this.append(builder, operation.getType());
    return builder.toString();
  }
  
  protected void append(final StringBuilder builder, final JvmTypeReference typeRef) {
    if ((typeRef instanceof JvmParameterizedTypeReference)) {
      final JvmType type = ((JvmParameterizedTypeReference)typeRef).getType();
      this.append(builder, type);
      EList<JvmTypeReference> arguments = ((JvmParameterizedTypeReference)typeRef).getArguments();
      boolean _isEmpty = arguments.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        builder.append("<");
        Iterator<JvmTypeReference> iterator = arguments.iterator();
        while (iterator.hasNext()) {
          {
            JvmTypeReference jvmTypeReference = iterator.next();
            this.append(builder, jvmTypeReference);
            boolean _hasNext = iterator.hasNext();
            if (_hasNext) {
              builder.append(",");
            }
          }
        }
        builder.append(">");
      }
    } else {
      if ((typeRef instanceof JvmWildcardTypeReference)) {
        builder.append("?");
        Iterator<JvmTypeConstraint> iterator_1 = ((JvmWildcardTypeReference)typeRef).getConstraints().iterator();
        while (iterator_1.hasNext()) {
          {
            JvmTypeConstraint constraint = iterator_1.next();
            if ((constraint instanceof JvmUpperBound)) {
              builder.append(" extends ");
            } else {
              builder.append(" super ");
            }
            this.append(builder, constraint.getTypeReference());
            boolean _hasNext = iterator_1.hasNext();
            if (_hasNext) {
              builder.append(" & ");
            }
          }
        }
      } else {
        if ((typeRef instanceof JvmGenericArrayTypeReference)) {
          this.append(builder, ((JvmGenericArrayTypeReference)typeRef).getType());
        }
      }
    }
  }
  
  protected void append(final StringBuilder builder, final JvmType type) {
    if ((type instanceof JvmArrayType)) {
      this.append(builder, ((JvmArrayType)type).getComponentType());
      builder.append("[]");
    } else {
      builder.append(Strings.notNull(type.getSimpleName()));
    }
  }
}
