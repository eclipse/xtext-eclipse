/**
 * Copyright (c) 2014, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.builder.debug;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.inject.Singleton;
import java.io.PrintStream;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.xtext.builder.debug.IBuildLogger;
import org.eclipse.xtext.builder.internal.Activator;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * @author Jan Koehnlein - Initial contribution and API
 * @author Moritz Eysholdt
 */
@SuppressWarnings("all")
public class XtextBuildConsole extends IOConsole {
  public static class Factory implements IConsoleFactory {
    @Override
    public void openConsole() {
      IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
      XtextBuildConsole _xtextBuildConsole = new XtextBuildConsole();
      consoleManager.addConsoles(new IConsole[] { _xtextBuildConsole });
    }
  }
  
  @Singleton
  public static class Logger implements IBuildLogger {
    private static IBuildLogger delegate;
    
    @Override
    public void log(final Object it) {
      if ((XtextBuildConsole.Logger.delegate != null)) {
        XtextBuildConsole.Logger.delegate.log(it);
      }
      final IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
      final XtextBuildConsole console = IterableExtensions.<XtextBuildConsole>head(Iterables.<XtextBuildConsole>filter(((Iterable<?>)Conversions.doWrapArray(consoleManager.getConsoles())), XtextBuildConsole.class));
      if ((console != null)) {
        String _name = Thread.currentThread().getName();
        String _plus = ("[" + _name);
        String _plus_1 = (_plus + "] ");
        String _switchResult = null;
        boolean _matched = false;
        if (it instanceof Throwable) {
          _matched=true;
          _switchResult = Throwables.getStackTraceAsString(((Throwable)it));
        }
        if (!_matched) {
          _switchResult = it.toString();
        }
        String _plus_2 = (_plus_1 + _switchResult);
        console.println(_plus_2);
        consoleManager.showConsoleView(console);
      }
    }
    
    public IBuildLogger registerDelegate(final IBuildLogger delegate) {
      return XtextBuildConsole.Logger.delegate = delegate;
    }
  }
  
  private final PrintStream out;
  
  public XtextBuildConsole() {
    super("Xtext Build", "xtextBuildConsole", 
      ImageDescriptor.createFromURL(Activator.getDefault().getBundle().getEntry("icons/console.png")), true);
    IOConsoleOutputStream _newOutputStream = this.newOutputStream();
    PrintStream _printStream = new PrintStream(_newOutputStream, true);
    this.out = _printStream;
  }
  
  public void println(final String it) {
    this.out.println(it);
  }
}
