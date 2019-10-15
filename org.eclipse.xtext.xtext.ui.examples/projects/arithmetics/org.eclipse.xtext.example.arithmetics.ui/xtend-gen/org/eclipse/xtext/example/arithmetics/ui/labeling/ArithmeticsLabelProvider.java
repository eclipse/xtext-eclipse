/**
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.arithmetics.ui.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.example.arithmetics.arithmetics.DeclaredParameter;
import org.eclipse.xtext.example.arithmetics.arithmetics.Definition;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#label-provider
 */
@SuppressWarnings("all")
public class ArithmeticsLabelProvider extends DefaultEObjectLabelProvider {
  @Inject
  public ArithmeticsLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String text(final org.eclipse.xtext.example.arithmetics.arithmetics.Module ele) {
    return ele.getName();
  }
  
  public String text(final Definition ele) {
    String _name = ele.getName();
    final Function1<DeclaredParameter, CharSequence> _function = (DeclaredParameter it) -> {
      return it.getName();
    };
    String _join = IterableExtensions.<DeclaredParameter>join(ele.getArgs(), "(", ",", ")", _function);
    return (_name + _join);
  }
  
  public String image(final org.eclipse.xtext.example.arithmetics.arithmetics.Module ele) {
    return "home_nav.gif";
  }
}
