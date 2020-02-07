/**
 * Copyright (c) 2014, 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.example.domainmodel.formatting2;

import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.example.domainmodel.domainmodel.AbstractElement;
import org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel;
import org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelPackage;
import org.eclipse.xtext.example.domainmodel.domainmodel.Entity;
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature;
import org.eclipse.xtext.example.domainmodel.domainmodel.Operation;
import org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration;
import org.eclipse.xtext.example.domainmodel.domainmodel.Property;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XBasicForLoopExpression;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XCollectionLiteral;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XDoWhileExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XForLoopExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XPostfixOperation;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XSynchronizedExpression;
import org.eclipse.xtext.xbase.XThrowExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XWhileExpression;
import org.eclipse.xtext.xbase.formatting2.XbaseFormatter;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xtype.XFunctionTypeRef;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;

/**
 * @author Moritz Eysholdt - Initial implementation and API
 */
@SuppressWarnings("all")
public class DomainmodelFormatter extends XbaseFormatter {
  protected void _format(final DomainModel domainmodel, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.setNewLines(0, 0, 1);
      it.noSpace();
    };
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.<DomainModel>append(document.<DomainModel>prepend(domainmodel, _function), _function_1);
    this.format(domainmodel.getImportSection(), document);
    EList<AbstractElement> _elements = domainmodel.getElements();
    for (final AbstractElement element : _elements) {
      this.format(element, document);
    }
  }
  
  protected void _format(final PackageDeclaration pkg, @Extension final IFormattableDocument document) {
    final ISemanticRegion open = this.textRegionExtensions.regionFor(pkg).keyword("{");
    final ISemanticRegion close = this.textRegionExtensions.regionFor(pkg).keyword("}");
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(pkg).feature(DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME), _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.append(open, _function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_2);
    EList<AbstractElement> _elements = pkg.getElements();
    for (final AbstractElement element : _elements) {
      {
        document.<AbstractElement>format(element);
        final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
          it.setNewLines(1, 1, 2);
        };
        document.<AbstractElement>append(element, _function_3);
      }
    }
  }
  
  protected void _format(final Entity entity, @Extension final IFormattableDocument document) {
    final ISemanticRegion open = this.textRegionExtensions.regionFor(entity).keyword("{");
    final ISemanticRegion close = this.textRegionExtensions.regionFor(entity).keyword("}");
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(entity).feature(DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME), _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    document.<JvmParameterizedTypeReference>surround(entity.getSuperType(), _function_1);
    final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.append(open, _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    document.<ISemanticRegion, ISemanticRegion>interior(open, close, _function_3);
    this.format(entity.getSuperType(), document);
    EList<Feature> _features = entity.getFeatures();
    for (final Feature feature : _features) {
      {
        document.<Feature>format(feature);
        final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
          it.setNewLines(1, 1, 2);
        };
        document.<Feature>append(feature, _function_4);
      }
    }
  }
  
  protected void _format(final Property property, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(property).keyword(":"), _function);
    document.<JvmTypeReference>format(property.getType());
  }
  
  protected void _format(final Operation operation, @Extension final IFormattableDocument document) {
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    document.append(this.textRegionExtensions.regionFor(operation).keyword("op"), _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    document.surround(this.textRegionExtensions.regionFor(operation).keyword("("), _function_1);
    boolean _isEmpty = operation.getParams().isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      List<ISemanticRegion> _keywords = this.textRegionExtensions.regionFor(operation).keywords(",");
      for (final ISemanticRegion comma : _keywords) {
        final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
          it.noSpace();
        };
        final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
          it.oneSpace();
        };
        document.append(document.prepend(comma, _function_2), _function_3);
      }
      EList<JvmFormalParameter> _params = operation.getParams();
      for (final JvmFormalParameter params : _params) {
        document.<JvmFormalParameter>format(params);
      }
      final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      document.prepend(this.textRegionExtensions.regionFor(operation).keyword(")"), _function_4);
    }
    JvmTypeReference _type = operation.getType();
    boolean _tripleNotEquals = (_type != null);
    if (_tripleNotEquals) {
      final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      document.append(this.textRegionExtensions.regionFor(operation).keyword(")"), _function_5);
      final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_7 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      document.<JvmTypeReference>append(document.<JvmTypeReference>prepend(operation.getType(), _function_6), _function_7);
      document.<JvmTypeReference>format(operation.getType());
    } else {
      final Procedure1<IHiddenRegionFormatter> _function_8 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      document.append(this.textRegionExtensions.regionFor(operation).keyword(")"), _function_8);
    }
    document.<XExpression>format(operation.getBody());
  }
  
  public void format(final Object entity, final IFormattableDocument document) {
    if (entity instanceof JvmTypeParameter) {
      _format((JvmTypeParameter)entity, document);
      return;
    } else if (entity instanceof JvmFormalParameter) {
      _format((JvmFormalParameter)entity, document);
      return;
    } else if (entity instanceof XtextResource) {
      _format((XtextResource)entity, document);
      return;
    } else if (entity instanceof XAssignment) {
      _format((XAssignment)entity, document);
      return;
    } else if (entity instanceof XBinaryOperation) {
      _format((XBinaryOperation)entity, document);
      return;
    } else if (entity instanceof XDoWhileExpression) {
      _format((XDoWhileExpression)entity, document);
      return;
    } else if (entity instanceof XFeatureCall) {
      _format((XFeatureCall)entity, document);
      return;
    } else if (entity instanceof XMemberFeatureCall) {
      _format((XMemberFeatureCall)entity, document);
      return;
    } else if (entity instanceof XPostfixOperation) {
      _format((XPostfixOperation)entity, document);
      return;
    } else if (entity instanceof XWhileExpression) {
      _format((XWhileExpression)entity, document);
      return;
    } else if (entity instanceof XFunctionTypeRef) {
      _format((XFunctionTypeRef)entity, document);
      return;
    } else if (entity instanceof JvmGenericArrayTypeReference) {
      _format((JvmGenericArrayTypeReference)entity, document);
      return;
    } else if (entity instanceof JvmParameterizedTypeReference) {
      _format((JvmParameterizedTypeReference)entity, document);
      return;
    } else if (entity instanceof JvmWildcardTypeReference) {
      _format((JvmWildcardTypeReference)entity, document);
      return;
    } else if (entity instanceof Entity) {
      _format((Entity)entity, document);
      return;
    } else if (entity instanceof Operation) {
      _format((Operation)entity, document);
      return;
    } else if (entity instanceof PackageDeclaration) {
      _format((PackageDeclaration)entity, document);
      return;
    } else if (entity instanceof Property) {
      _format((Property)entity, document);
      return;
    } else if (entity instanceof XBasicForLoopExpression) {
      _format((XBasicForLoopExpression)entity, document);
      return;
    } else if (entity instanceof XBlockExpression) {
      _format((XBlockExpression)entity, document);
      return;
    } else if (entity instanceof XCastedExpression) {
      _format((XCastedExpression)entity, document);
      return;
    } else if (entity instanceof XClosure) {
      _format((XClosure)entity, document);
      return;
    } else if (entity instanceof XCollectionLiteral) {
      _format((XCollectionLiteral)entity, document);
      return;
    } else if (entity instanceof XConstructorCall) {
      _format((XConstructorCall)entity, document);
      return;
    } else if (entity instanceof XForLoopExpression) {
      _format((XForLoopExpression)entity, document);
      return;
    } else if (entity instanceof XIfExpression) {
      _format((XIfExpression)entity, document);
      return;
    } else if (entity instanceof XInstanceOfExpression) {
      _format((XInstanceOfExpression)entity, document);
      return;
    } else if (entity instanceof XReturnExpression) {
      _format((XReturnExpression)entity, document);
      return;
    } else if (entity instanceof XSwitchExpression) {
      _format((XSwitchExpression)entity, document);
      return;
    } else if (entity instanceof XSynchronizedExpression) {
      _format((XSynchronizedExpression)entity, document);
      return;
    } else if (entity instanceof XThrowExpression) {
      _format((XThrowExpression)entity, document);
      return;
    } else if (entity instanceof XTryCatchFinallyExpression) {
      _format((XTryCatchFinallyExpression)entity, document);
      return;
    } else if (entity instanceof XTypeLiteral) {
      _format((XTypeLiteral)entity, document);
      return;
    } else if (entity instanceof XVariableDeclaration) {
      _format((XVariableDeclaration)entity, document);
      return;
    } else if (entity instanceof JvmTypeConstraint) {
      _format((JvmTypeConstraint)entity, document);
      return;
    } else if (entity instanceof DomainModel) {
      _format((DomainModel)entity, document);
      return;
    } else if (entity instanceof XExpression) {
      _format((XExpression)entity, document);
      return;
    } else if (entity instanceof XImportDeclaration) {
      _format((XImportDeclaration)entity, document);
      return;
    } else if (entity instanceof XImportSection) {
      _format((XImportSection)entity, document);
      return;
    } else if (entity instanceof EObject) {
      _format((EObject)entity, document);
      return;
    } else if (entity == null) {
      _format((Void)null, document);
      return;
    } else if (entity != null) {
      _format(entity, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(entity, document).toString());
    }
  }
}
