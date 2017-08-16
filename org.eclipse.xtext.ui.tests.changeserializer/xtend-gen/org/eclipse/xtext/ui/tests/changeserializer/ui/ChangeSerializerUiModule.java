/**
 * generated by Xtext unspecified
 */
package org.eclipse.xtext.ui.tests.changeserializer.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveParticipantStrategy;
import org.eclipse.xtext.ui.tests.changeserializer.ui.AbstractChangeSerializerUiModule;
import org.eclipse.xtext.ui.tests.changeserializer.ui.refactoring.ChangeSerializerMoveParticipantStrategy;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
@SuppressWarnings("all")
public class ChangeSerializerUiModule extends AbstractChangeSerializerUiModule {
  public Class<? extends XtextMoveParticipantStrategy> bindXtextMoveParticipantStrategy() {
    return ChangeSerializerMoveParticipantStrategy.class;
  }
  
  public ChangeSerializerUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
