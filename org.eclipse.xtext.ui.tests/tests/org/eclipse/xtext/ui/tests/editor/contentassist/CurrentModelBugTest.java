/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.ui.testing.util.ResourceLoadHelper;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.editor.contentassist.IContentProposalProvider;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.ui.tests.ui.internal.TestsActivator;
import org.eclipse.xtext.ui.tests.editor.contentassist.domainModelTest.DomainModelTestPackage;
import org.eclipse.xtext.ui.tests.editor.contentassist.ui.DomainModelTestLanguageUiModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.ui.contentassist.DomainModelTestLanguageProposalProvider;
import org.eclipse.xtext.util.Modules2;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class CurrentModelBugTest extends AbstractXtextTests implements ResourceLoadHelper {

	private EClass expectedClass;

	private String expectedClassName;
	
	@Override
	public void tearDown() throws Exception {
		expectedClass = null;
		expectedClassName = null;
		super.tearDown();
	}
	
	private ISetup getSetup() {
		return new DomainModelTestLanguageStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules2.mixin(new DomainModelTestLanguageRuntimeModule(), new DomainModelTestLanguageUiModule(TestsActivator.getInstance()){

					@Override
					public Class<? extends IContentProposalProvider> bindIContentProposalProvider() {
						return MockedProposals.class;
					}
					
					@SuppressWarnings("unused")
					public CurrentModelBugTest bindCurrentModelBugTest() {
						return CurrentModelBugTest.this;
					}
				}, new SharedStateModule()));
			}
		};
	}
	
	public void verify(ContentAssistContext contentAssistContext) {
		EObject currentModel = contentAssistContext.getCurrentModel();
		assertEquals(currentModel.toString(), expectedClass, currentModel.eClass());
		if (expectedClassName != null) {
			assertTrue(contentAssistContext.getCurrentModel() instanceof org.eclipse.xtext.ui.tests.editor.contentassist.domainModelTest.Class);
			assertEquals(expectedClassName, ((org.eclipse.xtext.ui.tests.editor.contentassist.domainModelTest.Class) contentAssistContext.getCurrentModel()).getName());
		}
	}
	
	@Test public void testBug283857_01() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
    	newBuilder(getSetup()).append("class Name {\n" + 
    			"attr \n" + 
    			"\n" + 
    			"}\n" + 
    			"").assertTextAtCursorPosition("attr ", 5, "Name");
	}
	
	@Test public void testBug283857_02() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.MODEL;
    	newBuilder(getSetup()).append("class Name {\n" + 
    			"attr \n" + 
    			"\n" + 
    			"}\n" + 
    			"").assertTextAtCursorPosition(0, "class", "datatype", "import");
    }
	
	@Test public void testBug283857_03() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
    	newBuilder(getSetup()).append("class Name {\n" + 
    			"attr \n" + 
    			"\n" + 
    			"}\n" + 
    			"").assertTextAtCursorPosition(6, "Name");
    }
	
	@Test public void testBug283857_04() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.MODEL;
    	newBuilder(getSetup()).append("class ").assertText("Name");
    }
	
	@Test public void testBug283857_05() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.MODEL;
    	newBuilder(getSetup()).append("class Name {\n" + 
    			"attr Name: \n" + 
    			"\n" + 
    			"}\n" + 
    			"").assertText("class", "datatype");
    }
	
	@Test public void testBug284381_01() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
		expectedClassName = "myClass";
		newBuilder(getSetup()).append("class myClass {\n" + 
				"   // ContentAssistContext.getCurrentModel() == myClass -> OK\n" + 
				"  class mySubClass1 {\n" + 
				"   // ContentAssistContext.getCurrentModel() == mySubClass1 -> OK\n" + 
				"  }\n" + 
				"\n" + 
				"  // ContentAssistContext.getCurrentModel() ==  mySubClass1 -> expected myClass\n" + 
				"\n" + 
				"  class mySubClass2 {\n" + 
				"  }\n" + 
				"  // ContentAssistContext.getCurrentModel() ==  mySubClass2 -> expected myClass\n" + 
				"\n" + 
				"}\n" + 
				"").assertTextAtCursorPosition(" // ContentAssistContext.getCurrentModel() == myClass -> OK", "attr", "ref", "class", "}");
	}
	
	@Test public void testBug284381_02() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
		expectedClassName = "mySubClass1";
		newBuilder(getSetup()).append("class myClass {\n" + 
				"   // ContentAssistContext.getCurrentModel() == myClass\n" + 
				"  class mySubClass1 {\n" + 
				"   // ContentAssistContext.getCurrentModel() == mySubClass1 -> OK\n" + 
				"  }\n" + 
				"\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass1 -> expected myClass\n" + 
				"\n" + 
				"  class mySubClass2 {\n" + 
				"  }\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass2 -> expected myClass\n" + 
				"\n" + 
				"}\n" + 
				"").assertTextAtCursorPosition(" // ContentAssistContext.getCurrentModel() == mySubClass1 -> OK", "attr", "ref", "class", "}");
	}
	
	@Test public void testBug284381_03() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
		expectedClassName = "myClass";
		newBuilder(getSetup()).append("class myClass {\n" + 
				"   // ContentAssistContext.getCurrentModel() == myClass -> OK\n" + 
				"  class mySubClass1 {\n" + 
				"   // ContentAssistContext.getCurrentModel() == mySubClass1 -> OK\n" + 
				"  }\n" + 
				"\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass1 -> expected myClass\n" + 
				"\n" + 
				"  class mySubClass2 {\n" + 
				"  }\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass2 -> expected myClass\n" + 
				"\n" + 
				"}\n" + 
				"").assertTextAtCursorPosition(" // ContentAssistContext.getCurrentModel() ==  mySubClass1 -> expected myClass", "class", "}");
	}
	
	@Test public void testBug284381_04() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
		expectedClassName = "myClass";
		newBuilder(getSetup()).append("class myClass {\n" + 
				"   // ContentAssistContext.getCurrentModel() == myClass\n" + 
				"  class mySubClass1 {\n" + 
				"   // ContentAssistContext.getCurrentModel() == mySubClass1 -> OK\n" + 
				"  }\n" + 
				"\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass1 -> expected myClass\n" + 
				"\n" + 
				"  class mySubClass2 {\n" + 
				"  }\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass2 -> expected myClass\n" + 
				"\n" + 
				"}\n" + 
				"").assertTextAtCursorPosition(" // ContentAssistContext.getCurrentModel() ==  mySubClass2 -> expected myClass", "class", "}");
	}
	
	@Test public void testBug284381_05() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
		expectedClassName = "mySubClass1";
		newBuilder(getSetup()).append("class myClass {\n" + 
				"   // ContentAssistContext.getCurrentModel() == myClass\n" + 
				"  class mySubClass1 {\n" + 
				"   // ContentAssistContext.getCurrentModel() == mySubClass1 -> OK\n" + 
				"  }\n" + 
				"\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass1 -> expected myClass\n" + 
				"\n" + 
				"  class mySubClass2 {\n" + 
				"  }\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass2 -> expected myClass\n" + 
				"\n" + 
				"}\n" + 
				"").assertTextAtCursorPosition(" class mySubClass1", 7, "Name");
	}
	
	@Test public void testBug284381_06() throws Exception {
		expectedClass = DomainModelTestPackage.Literals.CLASS;
		expectedClassName = "myClass";
		newBuilder(getSetup()).append("class myClass {\n" + 
				"   // ContentAssistContext.getCurrentModel() == myClass\n" + 
				"  class mySubClass1 {\n" + 
				"   // ContentAssistContext.getCurrentModel() == mySubClass1 -> OK\n" + 
				"  }\n" + 
				"\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass1 -> expected myClass\n" + 
				"\n" + 
				"  class mySubClass2 {\n" + 
				"  }\n" + 
				"   // ContentAssistContext.getCurrentModel() ==  mySubClass2 -> expected myClass\n" + 
				"\n" + 
				"}\n" + 
				"").assertTextAtCursorPosition("class mySubClass1", "attr", "ref", "class", "}");
	}

	protected ContentAssistProcessorTestBuilder newBuilder(ISetup standAloneSetup) throws Exception {
		with(standAloneSetup);
		return new ContentAssistProcessorTestBuilder(getInjector(), this);
	}

	public static class MockedProposals extends DomainModelTestLanguageProposalProvider {
		@Inject
		private CurrentModelBugTest test;
		
		@Override
		public void completeAssignment(Assignment assignment, ContentAssistContext contentAssistContext,
				ICompletionProposalAcceptor acceptor) {
			test.verify(contentAssistContext);
			super.completeAssignment(assignment, contentAssistContext, acceptor);
		}
	}

}
