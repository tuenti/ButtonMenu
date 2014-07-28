/*
 * Copyright (c) Tuenti Technologies S.L. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tuenti.buttonmenu.viewmodel.buttonmenu;


import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tuenti.buttonmenu.viewmodel.button.ButtonVM;

/**
 * Test created to check the correctness of SimpleButtonMenuVM.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class SimpleButtonMenuVMTest {

	private SimpleButtonMenuVM simpleButtonMenuVM;

	@Mock
	private ButtonVM mockedButtonVM;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initializeSimpleButtonMenuVM();
	}

	@Test
	public void shouldBeEmptyAfterConstruction() {
		assertTrue(simpleButtonMenuVM.getButtonVMs().isEmpty());
	}


	@Test
	public void shouldContainsAllTheAddedButtonVM() {
		simpleButtonMenuVM.addItem(mockedButtonVM);

		Set<ButtonVM> buttonVMs = simpleButtonMenuVM.getButtonVMs();

		assertEquals(1, buttonVMs.size());
		assertTrue(buttonVMs.contains(mockedButtonVM));
	}

	@Test
	public void shouldContainsNoRepeatedElements() {
		simpleButtonMenuVM.addItem(mockedButtonVM);
		simpleButtonMenuVM.addItem(mockedButtonVM);

		Set<ButtonVM> buttonVMs = simpleButtonMenuVM.getButtonVMs();

		assertEquals(1, buttonVMs.size());
		assertTrue(buttonVMs.contains(mockedButtonVM));
	}

	private void initializeSimpleButtonMenuVM() {
		simpleButtonMenuVM = new SimpleButtonMenuVM();
	}


}
