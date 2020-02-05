/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.actions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public abstract class AbstractToggleActionContributor {

	protected static class InternalToggleAction extends Action {

		private final AbstractToggleActionContributor contribution;

		protected InternalToggleAction(AbstractToggleActionContributor contribution) {
			this.contribution = contribution;
			setId(contribution.getPreferenceKey());
			setChecked(contribution.isPropertySet());
		}

		@Override
		public void run() {
			boolean newState = !contribution.isPropertySet();
			setChecked(newState);
			contribution.toggle();
		}
	}

	private static final Logger logger = Logger.getLogger(AbstractToggleActionContributor.class);

	@Inject
	private IPreferenceStoreAccess preferenceStoreAccess;

	private IPropertyChangeListener propertyChangeListener;

	private Action action;

	private IPreferenceStore preferenceStore;

	public abstract String getPreferenceKey();

	protected abstract void stateChanged(boolean newState);

	protected boolean isPropertySet() {
		return preferenceStoreAccess.getPreferenceStore().getBoolean(getPreferenceKey());
	}

	protected IPreferenceStoreAccess getPreferenceStoreAccess() {
		return preferenceStoreAccess;
	}

	protected void toggle() {
		boolean newState = !isPropertySet();
		IPreferenceStore store = preferenceStoreAccess.getWritablePreferenceStore();
		store.setValue(getPreferenceKey(), newState);
		if (store instanceof IPersistentPreferenceStore) {
			try {
				((IPersistentPreferenceStore) store).save();
			} catch (IOException e) {
				// log and ignore
				logger.debug(e.getMessage(), e);
			}
		}
		// Prevent sending state change event twice, already called by the propertyChangeListener
		if (propertyChangeListener == null)
			stateChanged(newState);
	}

	/**
	 * Subclasses must set text, image, description, tooltip etc. here.
	 */
	protected abstract void configureAction(Action action);

	protected Action getAction() {
		if (action == null) {
			action = new InternalToggleAction(this);
			configureAction(action);
		}
		return action;
	}

	public void initialize(IPreferenceStoreAccess preferenceStoreAccess) {
		preferenceStoreAccess.getWritablePreferenceStore().setDefault(getPreferenceKey(), getPreferenceDefaultValue());
	}

	/**
	 * @since 2.2
	 */
	protected boolean getPreferenceDefaultValue() {
		return false;
	}

	protected void addPropertyChangeListener() {
		propertyChangeListener = new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (getPreferenceKey().equals(event.getProperty()) && event.getOldValue() != event.getNewValue()) {
					boolean newValue = Boolean.parseBoolean(event.getNewValue().toString());
					stateChanged(newValue);
					getAction().setChecked(newValue);
				}
			}
		};
		preferenceStore = preferenceStoreAccess.getPreferenceStore();
		preferenceStore.addPropertyChangeListener(propertyChangeListener);
	}

	protected void removePropertyChangeListener() {
		if (preferenceStore != null)
			preferenceStore.removePropertyChangeListener(propertyChangeListener);
	}

}
