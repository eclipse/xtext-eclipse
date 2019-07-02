/**
 * Copyright (c) 2015, 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xtext.ui.wizard.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * @author dhuebner - Initial contribution and API
 */
@SuppressWarnings("all")
public class StatusWidget extends Composite {
  private Link link;
  
  private Label imageLabel;
  
  private Procedure0 quickFix = ((Procedure0) () -> {
  });
  
  private int severity = IMessageProvider.NONE;
  
  public StatusWidget(final Composite parent, final int style) {
    super(parent, style);
    this.createControls();
  }
  
  protected void createControls() {
    GridLayout _gridLayout = new GridLayout(2, false);
    this.setLayout(_gridLayout);
    Label _label = new Label(this, SWT.NONE);
    this.imageLabel = _label;
    this.imageLabel.setText("   ");
    GridData _gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
    final Procedure1<GridData> _function = (GridData it) -> {
      it.widthHint = 15;
    };
    GridData _doubleArrow = ObjectExtensions.<GridData>operator_doubleArrow(_gridData, _function);
    this.imageLabel.setLayoutData(_doubleArrow);
    Link _link = new Link(this, SWT.NONE);
    this.link = _link;
    GridData _gridData_1 = new GridData(GridData.FILL_HORIZONTAL);
    final Procedure1<GridData> _function_1 = (GridData it) -> {
      it.heightHint = 30;
    };
    GridData _doubleArrow_1 = ObjectExtensions.<GridData>operator_doubleArrow(_gridData_1, _function_1);
    this.link.setLayoutData(_doubleArrow_1);
    this.link.setFont(this.getFont());
    this.link.setText("\n\n\n");
    this.link.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(final SelectionEvent e) {
        super.widgetSelected(e);
        StatusWidget.this.quickFix.apply();
      }
    });
  }
  
  public Procedure0 clearStatus() {
    final Procedure0 _function = () -> {
    };
    final Procedure0 _function_1 = () -> {
    };
    return this.setStatus(IMessageProvider.NONE, "\n\n\n", _function, _function_1);
  }
  
  public Procedure0 setStatus(final int severity, final String text, final Procedure0 quickFix, final Procedure0 callback) {
    Procedure0 _xblockexpression = null;
    {
      this.severity = severity;
      this.setVisible((severity != IMessageProvider.NONE));
      this.imageLabel.setImage(this.imageFor(severity));
      this.link.setText(text);
      final Matcher matcher = Pattern.compile("<a>(.*)</a>").matcher(text.trim());
      this.link.setToolTipText(matcher.replaceAll("$1"));
      final Procedure0 _function = () -> {
        quickFix.apply();
        callback.apply();
      };
      _xblockexpression = this.quickFix = _function;
    }
    return _xblockexpression;
  }
  
  public Procedure0 addStatus(final int severity, final String text) {
    Procedure0 _xifexpression = null;
    boolean _isEmpty = this.link.getText().trim().isEmpty();
    if (_isEmpty) {
      final Procedure0 _function = () -> {
      };
      final Procedure0 _function_1 = () -> {
      };
      _xifexpression = this.setStatus(severity, text, _function, _function_1);
    } else {
      String _text = this.link.getText();
      String _plus = (_text + "\n");
      String _plus_1 = (_plus + text);
      this.link.setText(_plus_1);
      final Matcher matcher = Pattern.compile("<a>(.*)</a>").matcher(text.trim());
      this.link.setToolTipText(matcher.replaceAll("$1"));
      if ((severity > this.severity)) {
        this.severity = severity;
        this.setVisible((severity != IMessageProvider.NONE));
        this.imageLabel.setImage(this.imageFor(severity));
      }
    }
    return _xifexpression;
  }
  
  public int getSevertity() {
    return this.severity;
  }
  
  private Image imageFor(final int type) {
    Image _switchResult = null;
    switch (type) {
      case IMessageProvider.NONE:
        _switchResult = null;
        break;
      case IMessageProvider.INFORMATION:
        _switchResult = JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
        break;
      case IMessageProvider.WARNING:
        _switchResult = JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
        break;
      case IMessageProvider.ERROR:
        _switchResult = JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
        break;
    }
    return _switchResult;
  }
}
