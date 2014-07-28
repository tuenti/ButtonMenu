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

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test created to check the correctness of ButtonWithCounterVM.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class ButtonWithCounterVMTest extends ButtonVMTest {

	private static final int DEFAULT_COUNTER_VALUE = 3;
	private static final int DEFAULT_COUNTER_WIDGET_ID = 4;
	private static final int ANY_OTHER_COUNTER_VALUE = 9;

	@Override
	protected ButtonVM getButtonVM() {
		return new ButtonWithCounterVM(DEFAULT_LAYOUT_ID, DEFAULT_ENABLED_VALUE,
				DEFAULT_CLICKABLE_RES_ID,
				DEFAULT_ENABLE_DISABLES_RES_IDS, mockedButtonCommand, DEFAULT_COUNTER_VALUE,
				DEFAULT_COUNTER_WIDGET_ID);
	}

	@Test
	public void shouldContainsDefaultCounterId() {
		ButtonWithCounterVM buttonWithCounterVM = (ButtonWithCounterVM) buttonVM;

		assertEquals(DEFAULT_COUNTER_VALUE, buttonWithCounterVM.getCounterValue());
	}

	@Test
	public void shouldContainsDefaultCounterWidgetResourceId() {
		ButtonWithCounterVM buttonWithCounterVM = (ButtonWithCounterVM) buttonVM;

		assertEquals(DEFAULT_COUNTER_WIDGET_ID, buttonWithCounterVM.getCounterWidgetId());
	}

	@Test
	public void shouldChangeCounterValue() {
		ButtonWithCounterVM buttonWithCounterVM = (ButtonWithCounterVM) buttonVM;

		buttonWithCounterVM.setCounterValue(ANY_OTHER_COUNTER_VALUE);

		assertEquals(ANY_OTHER_COUNTER_VALUE, buttonWithCounterVM.getCounterValue());
	}

	@Test
	public void shouldNotifyButtonVMListener() {
		ButtonWithCounterVM buttonWithCounterVM = (ButtonWithCounterVM) buttonVM;
		buttonWithCounterVM.registerListener(mockedButtonVMListener);

		buttonWithCounterVM.setCounterValue(ANY_OTHER_COUNTER_VALUE);

		verify(mockedButtonVMListener, times(1)).onCounterValueChanged(ANY_OTHER_COUNTER_VALUE, buttonWithCounterVM);
	}

}
