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

package com.tuenti.buttonmenu.viewmodel.button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test created to check the correctness of ButtonVM implementation. Every ButtonVM implementation should pass this
 * tests.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public abstract class ButtonVMTest {

	protected static final int DEFAULT_LAYOUT_ID = 0;
	protected static final boolean DEFAULT_ENABLED_VALUE = true;
	protected static final int DEFAULT_CLICKABLE_RES_ID = 1;
	protected static final int[] DEFAULT_ENABLE_DISABLES_RES_IDS = {2};

	protected ButtonVM buttonVM;

	@Mock
	protected ButtonCommand mockedButtonCommand;
	@Mock
	protected ButtonVMListener mockedButtonVMListener;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		buttonVM = getButtonVM();
	}

	@Test
	public void shouldContainsDefaultLayoutId() {
		assertEquals(DEFAULT_LAYOUT_ID, buttonVM.getLayoutId());
	}

	@Test
	public void shouldBeEnabledByDefault() {
		assertEquals(DEFAULT_ENABLED_VALUE, buttonVM.isEnabled());
	}

	@Test
	public void shouldContainDefaultClickableResId() {
		assertEquals(DEFAULT_CLICKABLE_RES_ID, buttonVM.getClickableResId());
	}

	@Test
	public void shouldContainsDefaultEnableDisableResIds() {
		assertEquals(DEFAULT_ENABLE_DISABLES_RES_IDS, buttonVM.getViewIdsToEnableOrDisable());
	}

	@Test
	public void shouldHaveDefaultActionCommandSet() {
		assertEquals(mockedButtonCommand, buttonVM.getButtonCommand());
	}

	@Test
	public void shouldDisableSimpleButtonVM() {
		buttonVM.disable();

		assertFalse(buttonVM.isEnabled());
	}

	@Test
	public void shouldEnableSimpleButtonVM() {
		buttonVM.disable();

		buttonVM.enable();

		assertTrue(buttonVM.isEnabled());
	}

	@Test
	public void shouldNotifyButtonVMListenerWhenEnable() {
		buttonVM.registerListener(mockedButtonVMListener);

		buttonVM.enable();

		verify(mockedButtonVMListener, times(1)).onEnablePropertyChanged(true, buttonVM);
	}

	@Test
	public void shouldNotifyButtonVMListenerWhenDisable() {
		buttonVM.registerListener(mockedButtonVMListener);

		buttonVM.disable();

		verify(mockedButtonVMListener).onEnablePropertyChanged(false, buttonVM);
	}

	protected abstract ButtonVM getButtonVM();

}
