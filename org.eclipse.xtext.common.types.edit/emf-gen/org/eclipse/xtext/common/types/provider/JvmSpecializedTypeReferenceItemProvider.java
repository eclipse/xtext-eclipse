/**
 * Copyright (c) 2011-2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.common.types.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.xtext.common.types.JvmSpecializedTypeReference;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.TypesPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.xtext.common.types.JvmSpecializedTypeReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JvmSpecializedTypeReferenceItemProvider extends JvmTypeReferenceItemProvider
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JvmSpecializedTypeReferenceItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
	{
		if (itemPropertyDescriptors == null)
		{
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	{
		if (childrenFeatures == null)
		{
			super.getChildrenFeatures(object);
			childrenFeatures.add(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child)
	{
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		return getString("_UI_JvmSpecializedTypeReference_type");
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		updateChildren(notification);

		switch (notification.getFeatureID(JvmSpecializedTypeReference.class))
		{
			case TypesPackage.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmParameterizedTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmGenericArrayTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmWildcardTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmAnyTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmMultiTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmDelegateTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmSynonymTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmUnknownTypeReference()));

		newChildDescriptors.add
			(createChildParameter
				(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT,
				 TypesFactory.eINSTANCE.createJvmInnerTypeReference()));
	}

}
