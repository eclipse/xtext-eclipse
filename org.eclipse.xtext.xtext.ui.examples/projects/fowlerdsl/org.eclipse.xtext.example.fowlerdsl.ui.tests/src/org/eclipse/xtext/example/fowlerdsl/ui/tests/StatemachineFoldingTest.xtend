/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.fowlerdsl.ui.tests

import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.testing.AbstractFoldingTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author miklossy - Initial contribution and API
 */
@RunWith(XtextRunner)
@InjectWith(StatemachineUiInjectorProvider)
class StatemachineFoldingTest extends AbstractFoldingTest {

	@Test def events() {
		'''
			[>events
				doorClosed   D1CL
				drawerOpened D2OP
				lightOn      L1ON
				doorOpened   D1OP
				panelClosed  PNCL
			end<]
		'''.testFoldingRegions
	}

	@Test def resetEvents() {
		'''
			[>resetEvents
				doorOpened
				doorClosed
			end<]
		'''.testFoldingRegions
	}

	@Test def commands() {
		'''
			[>commands
				unlockPanel PNUL
				lockPanel   NLK
				lockDoor    D1LK
				unlockDoor  D1UL
			end<]
		'''.testFoldingRegions
	}

	@Test def state001() {
		'''
			[>state idle
			end<]
		'''.testFoldingRegions
	}

	@Test def state002() {
		'''
			[>state idle
				
				
				
			end<]
		'''.testFoldingRegions
	}

	@Test def complex() {
		'''
			[>events
				doorClosed   D1CL
				drawerOpened D2OP
				lightOn      L1ON
				doorOpened   D1OP
				panelClosed  PNCL
			end<]
			
			[>resetEvents
				doorOpened
				doorClosed
			end<]
			
			[>commands
				unlockPanel PNUL
				lockPanel   NLK
				lockDoor    D1LK
				unlockDoor  D1UL
			end<]
			
			[>state idle
				actions {unlockDoor lockPanel}
				doorClosed => active
			end<]
			
			[>state active
				drawerOpened => waitingForLight
				lightOn      => waitingForDrawer
			end<]
			
			[>state waitingForLight
				lightOn => unlockedPanel
			end<]
			
			[>state waitingForDrawer
				drawerOpened => unlockedPanel
			end<]
			
			[>state unlockedPanel
				actions {unlockPanel lockDoor}
				panelClosed => idle
			end<]
		'''.testFoldingRegions
	}
}