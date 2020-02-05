/*******************************************************************************
 * Copyright (c) 2009, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.ui.shared.Access;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.util.EmfFormatter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class BuilderUtil {

	public static String print(ImmutableList<Delta> deltas) {
		int i = 1;
		String buff = "Deltas : \n";
		for (Delta delta : deltas) {
			buff+= "Delta "+i+"["+delta.getUri()+"]: {\n";
			buff+= " old : "+toString(delta.getOld())+"\n";
			buff+= " new : "+toString(delta.getNew())+"\n";
			buff+= "}\n\n";
			i++;
		}
		return buff;
	}

	public static String toString(IResourceDescription desc) {
		if (desc==null)
			return "null";
		return EmfFormatter.objToStr(desc, new EStructuralFeature[0]);
	}

	public static String toString(IResourceDescriptions index) {
		StringBuffer buff = new StringBuffer();
		for (IResourceDescription desc : index.getAllResourceDescriptions()) {
			buff.append(EmfFormatter.objToStr(desc, new EStructuralFeature[0]));
		}
		return buff.toString();
	}

	public static boolean indexContainsElement(final String fileUri, final String eObjectName) {
		IResourceDescriptions descriptions = getBuilderState();
		URI uri = URI.createURI("platform:/resource"+fileUri);
		IResourceDescription description = descriptions.getResourceDescription(uri);
		if (description!=null) {
			return description.getExportedObjects(EcorePackage.Literals.EOBJECT, QualifiedName.create(eObjectName), false).iterator().hasNext();
		}
		return false;
	}

	public static IBuilderState getBuilderState() {
		return Access.getIBuilderState().get();
	}

	public static int countResourcesInIndex() {
		Collection<IResourceDescription> forIterable = Lists.newArrayList(getBuilderState().getAllResourceDescriptions());
		return forIterable.size();
	}

	public static void assertNumberOfMarkers(IFile file, int numberOfMarkers) throws CoreException {
		IMarker[] markers = file.findMarkers(EValidator.MARKER, true, 1);
		assertEquals(IResourcesSetupUtil.printMarker(markers), numberOfMarkers, markers.length);
	}

	public static Set<IReferenceDescription> getIncomingReferences(URI uri) {
		Set<IReferenceDescription> desc = Sets.newHashSet();
		Iterable<IResourceDescription> descriptions = getBuilderState().getAllResourceDescriptions();
		for (IResourceDescription res : descriptions) {
			Iterable<IReferenceDescription> descriptions2 = res.getReferenceDescriptions();
			for (IReferenceDescription ref : descriptions2) {
				if (uri.hasFragment()) {
					if (ref.getTargetEObjectUri().equals(uri))
						desc.add(ref);
				} else {
					if (ref.getTargetEObjectUri().trimFragment().equals(uri.trimFragment()))
						desc.add(ref);
				}
			}
		}
		return desc;
	}
	
	public static Set<IReferenceDescription> getContainedReferences(URI uri) {
		Set<IReferenceDescription> desc = Sets.newHashSet();
		Iterable<IResourceDescription> descriptions = getBuilderState().getAllResourceDescriptions();
		for (IResourceDescription res : descriptions) {
			Iterable<IReferenceDescription> descriptions2 = res.getReferenceDescriptions();
			for (IReferenceDescription ref : descriptions2) {
				if (uri.hasFragment()) {
					if (ref.getSourceEObjectUri().equals(uri))
						desc.add(ref);
				} else {
					if (ref.getSourceEObjectUri().trimFragment().equals(uri.trimFragment()))
						desc.add(ref);
				}
			}
		}
		return desc;
	}
}
