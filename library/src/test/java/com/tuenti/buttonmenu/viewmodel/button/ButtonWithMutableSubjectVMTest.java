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
import static org.mockito.Mockito.verify;

/**
 * Test created to check the correctness of ButtonWithMutableSubjectVM.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class ButtonWithMutableSubjectVMTest extends ButtonVMTest {

	protected static final int DEFAULT_SUBJECT_WIDGET_ID = 4;
	private static final String ANY_SUBJECT = "pgomez@tuenti.com";

	@Override
	protected ButtonVM getButtonVM() {
		return new ButtonWithMutableSubjectVM(DEFAULT_LAYOUT_ID, DEFAULT_ENABLED_VALUE, DEFAULT_CLICKABLE_RES_ID,
				DEFAULT_ENABLE_DISABLES_RES_IDS, DEFAULT_SUBJECT_WIDGET_ID, mockedButtonCommand);
	}

	@Test
	public void shouldContainsDefaultSubjectWidgetResourceId() {
		MutableSubjectButtonVM buttonWithMutableSubjectVM = (MutableSubjectButtonVM) buttonVM;

		assertEquals(DEFAULT_SUBJECT_WIDGET_ID, buttonWithMutableSubjectVM.getResIdToInsertSubject());
	}

	@Test
	public void shouldChangeSubjectValue() {
		MutableSubjectButtonVM buttonWithMutableSubjectVM = (MutableSubjectButtonVM) buttonVM;

		buttonWithMutableSubjectVM.setSubject(ANY_SUBJECT);

		assertEquals(ANY_SUBJECT, buttonWithMutableSubjectVM.getSubject());
	}

	@Test
	public void shouldNotifyButtonVMListener() {
		MutableSubjectButtonVM buttonWithMutableSubjectVM = (MutableSubjectButtonVM) buttonVM;
		buttonWithMutableSubjectVM.registerListener(mockedButtonVMListener);

		buttonWithMutableSubjectVM.setSubject(ANY_SUBJECT);

		verify(mockedButtonVMListener).onSubjectChanged(ANY_SUBJECT, buttonWithMutableSubjectVM);
	}
}
