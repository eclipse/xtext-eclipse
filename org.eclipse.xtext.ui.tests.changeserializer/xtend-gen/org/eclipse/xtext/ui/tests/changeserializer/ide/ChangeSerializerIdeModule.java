/**
 * generated by Xtext unspecified
 */
package org.eclipse.xtext.ui.tests.changeserializer.ide;

import org.eclipse.xtext.ide.refactoring.XtextMoveResourceStrategy;
import org.eclipse.xtext.ui.tests.changeserializer.ide.AbstractChangeSerializerIdeModule;
import org.eclipse.xtext.ui.tests.changeserializer.ide.refactoring.ChangeSerializerMoveResourceStrategy;

/**
 * Use this class to register ide components.
 */
@SuppressWarnings("all")
public class ChangeSerializerIdeModule extends AbstractChangeSerializerIdeModule {
  public Class<? extends XtextMoveResourceStrategy> bindXtextMoveParticipantStrategy() {
    return ChangeSerializerMoveResourceStrategy.class;
  }
}
