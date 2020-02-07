/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.editor.hyperlinking;

import com.google.common.base.Objects;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.JFaceTextUtil;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.MultipleHyperlinkPresenter;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.util.ReflectExtensions;

/**
 * The MultipleHyperLinkPresenter only shows the hyper link text when more then one hyper link exists.
 * This is not always desired as some hyperlinks might have special meaning, and without showing the user what the hyperlink does
 * only simple go to declaration hyperlinks should be treated like that.
 * 
 * Thus this class introduces a protocol for IHyperLinks to make sure their label is shown even if they are the only one.
 * To do so the property IHyperLink#getTypeLabel needs to be set to the constant SHOW_ALWAYS.
 * 
 * @author Sven Efftinge - Initial contribution and API
 * 
 * @since 2.8
 */
@FinalFieldsConstructor
@SuppressWarnings("all")
public class SingleHoverShowingHyperlinkPresenter implements InvocationHandler {
  private static final Logger log = Logger.getLogger(SingleHoverShowingHyperlinkPresenter.class);
  
  @Extension
  private ReflectExtensions reflect = new ReflectExtensions();
  
  /**
   * constant text needs to set into IHyperLink#getTypeLabel (which doesn't seem to be used otherwise)
   * if a hyperlink's label should be shown even if it's the only hyper link.
   */
  public static final String SHOW_ALWAYS = "SHOW_ALWAYS";
  
  private final MultipleHyperlinkPresenter delegate;
  
  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
    if (((method.getName().startsWith("showHyperlinks") && (args.length >= 1)) && (args[0] instanceof IHyperlink[]))) {
      Object _get = args[0];
      final IHyperlink[] nullsafe = this.makeNullsafe(((IHyperlink[]) _get));
      int _length = nullsafe.length;
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        args[0] = nullsafe;
        final Object result = method.invoke(this.delegate, args);
        final IHyperlink[] activeHyperlinks = nullsafe;
        int _length_1 = activeHyperlinks.length;
        boolean _equals = (_length_1 == 1);
        if (_equals) {
          final IHyperlink singleHyperlink = activeHyperlinks[0];
          String _typeLabel = singleHyperlink.getTypeLabel();
          boolean _equals_1 = Objects.equal(SingleHoverShowingHyperlinkPresenter.SHOW_ALWAYS, _typeLabel);
          if (_equals_1) {
            final int start = singleHyperlink.getHyperlinkRegion().getOffset();
            int _length_2 = singleHyperlink.getHyperlinkRegion().getLength();
            final int end = (start + _length_2);
            Region _region = new Region(start, (end - start));
            this.reflect.set(this.delegate, "fSubjectRegion", _region);
            this.reflect.set(this.delegate, "fCursorOffset", Integer.valueOf(JFaceTextUtil.getOffsetForCursorLocation(this.reflect.<ITextViewer>get(this.delegate, "fTextViewer"))));
            Object _get_1 = this.reflect.<Object>get(this.delegate, "fManager");
            ((AbstractInformationControlManager) _get_1).showInformation();
          }
        }
        return result;
      }
      return null;
    }
    return method.invoke(this.delegate, args);
  }
  
  /**
   * @since 2.9
   */
  protected IHyperlink[] makeNullsafe(final IHyperlink[] arr) {
    final Function1<IHyperlink, Boolean> _function = (IHyperlink it) -> {
      return Boolean.valueOf(((it == null) || (it.getHyperlinkRegion() == null)));
    };
    boolean _exists = IterableExtensions.<IHyperlink>exists(((Iterable<IHyperlink>)Conversions.doWrapArray(arr)), _function);
    if (_exists) {
      final ArrayList<IHyperlink> list = CollectionLiterals.<IHyperlink>newArrayList();
      final Consumer<IHyperlink> _function_1 = (IHyperlink it) -> {
        if (((it != null) && (it.getHyperlinkRegion() != null))) {
          list.add(it);
        } else {
          Class<? extends IHyperlink> _class = null;
          if (it!=null) {
            _class=it.getClass();
          }
          String _name = null;
          if (_class!=null) {
            _name=_class.getName();
          }
          String _plus = ("Filtered invalid hyperlink: " + _name);
          SingleHoverShowingHyperlinkPresenter.log.warn(_plus);
        }
      };
      ((List<IHyperlink>)Conversions.doWrapArray(arr)).forEach(_function_1);
      return list.<IHyperlink>toArray(new IHyperlink[list.size()]);
    }
    return arr;
  }
  
  public SingleHoverShowingHyperlinkPresenter(final MultipleHyperlinkPresenter delegate) {
    super();
    this.delegate = delegate;
  }
}
