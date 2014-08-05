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
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.verify;

/**
 * Test created to check the correctness of ButtonWithProgressVM.
 *
 * @author "Iñaki Villar Algaba" <inaki.seri@gmail.com>
 */
public class ButtonWithProgressVMTest extends ButtonVMTest {

	protected static final int DEFAULT_IMAGE_ID = 5;
	protected static final int DEFAULT_PROGRESS_IMAGE_ID = 6;

	@Override
	protected ButtonVM getButtonVM() {
		return new ButtonWithProgressVM(DEFAULT_LAYOUT_ID, DEFAULT_ENABLED_VALUE,
				DEFAULT_CLICKABLE_RES_ID,
				DEFAULT_ENABLE_DISABLES_RES_IDS, mockedButtonCommand, DEFAULT_IMAGE_ID,
				DEFAULT_PROGRESS_IMAGE_ID);
	}

	@Test
	public void shouldContainsDefaultSubjectWidgetResourceId() {
		ButtonWithProgressVM buttonWithProgressVM = (ButtonWithProgressVM) buttonVM;

		assertEquals(DEFAULT_IMAGE_ID, buttonWithProgressVM.getImage());
		assertEquals(DEFAULT_PROGRESS_IMAGE_ID, buttonWithProgressVM.getProgress());
	}

	@Test
	public void shouldChangeProgressStartValue() {
		ButtonWithProgressVM buttonWithMutableSubjectVM = (ButtonWithProgressVM) buttonVM;

		buttonWithMutableSubjectVM.showLoading();

		assertTrue(buttonWithMutableSubjectVM.isLoading());
	}

	@Test
	public void shouldChangeProgressFinishValue() {
		ButtonWithProgressVM buttonWithMutableSubjectVM = (ButtonWithProgressVM) buttonVM;

		buttonWithMutableSubjectVM.closeLoading();

		assertFalse(buttonWithMutableSubjectVM.isLoading());
	}

	@Test
	public void shouldNotifyButtonVMListener() {
		ButtonWithProgressVM buttonWithProgressVM = (ButtonWithProgressVM) buttonVM;
		buttonWithProgressVM.registerListener(mockedButtonVMListener);

		buttonWithProgressVM.showLoading();

		verify(mockedButtonVMListener).onProgressValueChanged(true,
				buttonWithProgressVM);
	}
}
