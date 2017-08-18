/**
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ui.refactoring.participant;

import org.eclipse.xtext.ui.refactoring.participant.AbstractParticipantStrategyRegistry;
import org.eclipse.xtext.ui.refactoring.participant.XtextMoveParticipantStrategy;

@SuppressWarnings("all")
public class XtextMoveParticipantStrategyRegistry extends AbstractParticipantStrategyRegistry<XtextMoveParticipantStrategy> {
  @Override
  protected String getExtensionPointID() {
    return "org.eclipse.xtext.ui.moveParticipantStrategy";
  }
}
