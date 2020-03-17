/**
 * Copyright (c) 2017, 2020 TypeFox GmbH (http://www.typefox.io) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.refactoring2.rename;

import java.util.Optional;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

import com.google.inject.ImplementedBy;

/**
 * Provides the Simple name of the passed {@code EObject} instance. A Simple name is the value that is associated with the attribute having
 * name {@code name} and type {@code String}
 * 
 * @author koehnlein - Initial contribution and API
 */
@ImplementedBy(ISimpleNameProvider.DefaultImpl.class)
public interface ISimpleNameProvider {

	class DefaultImpl implements ISimpleNameProvider {

		@Override
		public String getSimpleName(EObject target) {
			if (target != null) {
				Optional<EAttribute> nameAttribute = getNameEAttribute(target);
				if (nameAttribute.isPresent()) {
					return (String) target.eGet(nameAttribute.get());
				}
			}
			return null;
		}

		@Override
		public boolean canRename(EObject target) {
			if (target != null) {
				return getNameEAttribute(target).isPresent();
			}
			return false;
		}

		protected Optional<EAttribute> getNameEAttribute(EObject target) {
			return target.eClass() //
					.getEAllAttributes() //
					.stream() //
					.filter(eAttribute -> eAttribute.getName() == "name" //
							&& eAttribute.getEType() == EcorePackage.Literals.ESTRING) //
					.findFirst();
		}
	}

	/**
	 * @return the Simple name of the passed {@code EObject} instance
	 */
	String getSimpleName(EObject target);

	/**
	 * Checks if the passed {@code EObject} instance has an attribute having name {@code name} and type {@code String}
	 * 
	 * @return {@code true} if the passed {@code EObject} instance can be renamed, else returns false
	 */
	boolean canRename(EObject target);
}
