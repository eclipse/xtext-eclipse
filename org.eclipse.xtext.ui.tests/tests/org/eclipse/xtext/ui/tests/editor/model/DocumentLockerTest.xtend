/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.model

import com.google.inject.Provider
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.parser.antlr.Lexer
import org.eclipse.xtext.parser.antlr.internal.InternalXtextLexer
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.ui.editor.model.DocumentTokenSource
import org.eclipse.xtext.ui.editor.model.IXtextDocument
import org.eclipse.xtext.ui.editor.model.XtextDocument
import org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.util.concurrent.CancelableUnitOfWork
import org.junit.Test

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Jan Koehnlein
 */
class DocumentLockerTest extends AbstractXtextDocumentTest {
	
	@Test def void testNoUpdateContentProcessOnReentrant(){
		val s = newArrayList
		val document = new XtextDocument(createTokenSource, createTextEditComposer, outdatedStateManager, operationCanceledManager) {
			override protected boolean updateContentBeforeRead() {
				s += 'x'
			}
		}
		document.input = new XtextResource => [
			new XtextResourceSet().resources += it
		]
		assertEquals(0, s.size)
		val size1 = document.readOnly [
			assertEquals(1, s.size)
			val size2 = document.readOnly [
				assertEquals(1, s.size)
				val size3 = document.readOnly [
					assertEquals(1, s.size)
				]
				assertTrue("Assert was not executed due to null resource in readOnly", size3)
			]
			assertTrue("Assert was not executed due to null resource in readOnly", size2)
		]
		assertTrue("Assert was not executed due to null resource in readOnly", size1)
		assertEquals(1, s.size)
	}

	@Test def void testModifySetsOutdatedFalse() {
		val document = new XtextDocument(createTokenSource, createTextEditComposer, outdatedStateManager, operationCanceledManager)
		val resource = new XtextResource => [
			new XtextResourceSet().resources += it
		]
		document.input = resource
		val modify1 = document.internalModify(Boolean.FALSE) [
			assertFalse(document.cancelIndicator.isCanceled())
			null
		]
		assertNull("Failed to modify resource due to null resource in readOnly", modify1)
		val indicator = document.cancelIndicator
		assertFalse(indicator.isCanceled())
		document.set("fupp");
		assertTrue(indicator.isCanceled())
		val modify2 = document.internalModify(Boolean.FALSE) [
			assertFalse(document.cancelIndicator.isCanceled())
			null
		]
		assertNull("Failed to modify resource due to null resource in readOnly", modify2)
	}
	
	@Test def void testPriorityReadOnlyCancelsReaders() {
		val document = new XtextDocument(createTokenSource(), null, outdatedStateManager, operationCanceledManager)
		document.input = new XtextResource => [
			new XtextResourceSet().resources += it
		]
		val boolean[] check = newBooleanArrayOfSize(1) 
		val thread = new Thread([
			val success = document.readOnly(Boolean.FALSE, new CancelableUnitOfWork<Object, XtextResource>() {
				override Object exec(XtextResource state, CancelIndicator cancelIndicator) throws Exception {
					check.set(0,true)
					val wait = 4000;
					var i = 0;
					while (!cancelIndicator.isCanceled) {
						Thread.sleep(10l)
						if (i > wait)
							throw new InterruptedException();
						i = i + 1;
					}
					return null;
				}
			})
			assertNull("Failed to read resource due to null resource in readOnly", success)
		])
		thread.start
		while (!check.get(0)) {
			Thread.sleep(1)
		}
		val success = document.priorityReadOnly(Boolean.FALSE)[
			null
		]
		assertNull("Failed to read resource due to null resource in priorityReadOnly", success)
		assertFalse(thread.interrupted)
	}
	
	@Test def void testReadOnlyDoesntCancelReaders() {
		val document = new XtextDocument(createTokenSource(), null, outdatedStateManager, operationCanceledManager)
		document.input = new XtextResource => [
			new XtextResourceSet().resources += it
		]
		val cancelIndicators = newArrayList
		document.addReaderCancelationListener(cancelIndicators)
		assertTrue(cancelIndicators.empty)
		val success1 = document.readOnly(Boolean.FALSE)[null]
		assertNull("Failed to read resource due to null resource in readOnly", success1)
		assertTrue(cancelIndicators.empty)
		val success2 = document.readOnly(Boolean.FALSE)[null]
		assertNull("Failed to read resource due to null resource in readOnly", success2)
		assertTrue(cancelIndicators.empty)
	}
	
	private def DocumentTokenSource createTokenSource() {
		val tokenSource = new DocumentTokenSource
		tokenSource.lexer = new Provider<Lexer>() {
			override get() {
				new InternalXtextLexer()
			}
		}
		tokenSource
	}
	
	private def createTextEditComposer() {
		new ITextEditComposer() {
			override beginRecording(Resource resource) {
			}
			
			override endRecording() {
				null
			}
			
			override getTextEdit() {
				null
			}
		}
	}
	
	private def addReaderCancelationListener(IXtextDocument document, List<CancelIndicator> cancelIndicators) {
		document.addModelListener [
			val CancelableUnitOfWork<Boolean, XtextResource> work = [
				state, cancelIndicator | 
				assertFalse(cancelIndicator.isCanceled)
				cancelIndicators += cancelIndicator
			]
			val workResult = document.readOnly(null, work)
			assertNotNull("Failed to read resource due to null resource in readOnly", workResult)
		]
	} 
}