/**
 * Copyright (c) 2014, 2020 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.builder.debug;

import com.google.inject.ImplementedBy;
import org.eclipse.xtext.builder.debug.XtextBuildConsole;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@ImplementedBy(XtextBuildConsole.Logger.class)
public interface IBuildLogger {
	void log(Object o);
}
