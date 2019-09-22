/**
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.homeautomation.validation;

import com.google.common.base.Objects;
import java.util.HashSet;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.example.homeautomation.jvmmodel.RuleEngineJvmModelInferrer;
import org.eclipse.xtext.example.homeautomation.ruleEngine.Declaration;
import org.eclipse.xtext.example.homeautomation.ruleEngine.Device;
import org.eclipse.xtext.example.homeautomation.ruleEngine.Model;
import org.eclipse.xtext.example.homeautomation.ruleEngine.Rule;
import org.eclipse.xtext.example.homeautomation.ruleEngine.RuleEnginePackage;
import org.eclipse.xtext.example.homeautomation.validation.AbstractRuleEngineValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class RuleEngineValidator extends AbstractRuleEngineValidator {
  @Inject
  @Extension
  private IJvmModelAssociations _iJvmModelAssociations;
  
  @Check
  public void checkUniqueDeclarations(final Model model) {
    final HashSet<String> deviceNames = CollectionLiterals.<String>newHashSet();
    final HashSet<String> ruleDescriptions = CollectionLiterals.<String>newHashSet();
    EList<Declaration> _declarations = model.getDeclarations();
    for (final Declaration decl : _declarations) {
      if ((decl instanceof Device)) {
        boolean _add = deviceNames.add(((Device)decl).getName());
        boolean _not = (!_add);
        if (_not) {
          this.error("Device names must be unique.", decl, RuleEnginePackage.Literals.DEVICE__NAME);
        }
      } else {
        if ((decl instanceof Rule)) {
          final String methodName = RuleEngineJvmModelInferrer.getRuleMethodName(((Rule)decl));
          boolean _add_1 = ruleDescriptions.add(methodName);
          boolean _not_1 = (!_add_1);
          if (_not_1) {
            this.error("Rule descriptions must be unique.", decl, RuleEnginePackage.Literals.RULE__DESCRIPTION);
          }
        }
      }
    }
  }
  
  @Check
  public void checkStatesNotEmpty(final Device device) {
    boolean _isEmpty = device.getStates().isEmpty();
    if (_isEmpty) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("The device \"");
      String _name = device.getName();
      _builder.append(_name);
      _builder.append("\" must have at least one state.");
      this.error(_builder.toString(), device, RuleEnginePackage.Literals.DEVICE__NAME);
    }
  }
  
  @Check
  public void checkUniqueStates(final Device device) {
    final HashSet<String> stateNames = CollectionLiterals.<String>newHashSet();
    EList<org.eclipse.xtext.example.homeautomation.ruleEngine.State> _states = device.getStates();
    for (final org.eclipse.xtext.example.homeautomation.ruleEngine.State state : _states) {
      boolean _add = stateNames.add(state.getName());
      boolean _not = (!_add);
      if (_not) {
        this.error("State names must be unique.", state, RuleEnginePackage.Literals.STATE__NAME);
      }
    }
  }
  
  @Check
  public void checkRuleDescriptionNotEmpty(final Rule rule) {
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(rule.getDescription());
    if (_isNullOrEmpty) {
      this.error("A rule description must not be empty.", rule, RuleEnginePackage.Literals.RULE__DESCRIPTION);
    }
  }
  
  @Check
  public void checkRuleRecursion(final XFeatureCall featureCall) {
    final Rule containingRule = EcoreUtil2.<Rule>getContainerOfType(featureCall, Rule.class);
    if (((((containingRule != null) && (featureCall.getFeature() instanceof JvmOperation)) && Objects.equal(featureCall.getConcreteSyntaxFeatureName(), "fire")) && (featureCall.getFeatureCallArguments().size() == 1))) {
      final XExpression argument = IterableExtensions.<XExpression>head(featureCall.getFeatureCallArguments());
      if ((argument instanceof XAbstractFeatureCall)) {
        final EObject sourceElem = this._iJvmModelAssociations.getPrimarySourceElement(((XAbstractFeatureCall)argument).getFeature());
        org.eclipse.xtext.example.homeautomation.ruleEngine.State _deviceState = containingRule.getDeviceState();
        boolean _equals = Objects.equal(sourceElem, _deviceState);
        if (_equals) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Firing the same device state that triggers the rule \"");
          String _description = containingRule.getDescription();
          _builder.append(_description);
          _builder.append("\" may lead to endless recursion.");
          this.warning(_builder.toString(), featureCall, XbasePackage.Literals.XFEATURE_CALL__FEATURE_CALL_ARGUMENTS, 0);
        }
      }
    }
  }
}
