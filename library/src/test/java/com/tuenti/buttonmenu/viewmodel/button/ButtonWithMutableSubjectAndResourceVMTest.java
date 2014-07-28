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
 * Test created to check the correctness of ButtonWithMutableSubjectAndResourceVM.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class ButtonWithMutableSubjectAndResourceVMTest extends ButtonWithMutableSubjectVMTest {

	private static final int DEFAULT_IMAGE_RESOURCE_ID = 11;
	private static final int ANY_IMAGE_ID = 12;

	@Override
	protected ButtonVM getButtonVM() {
		return new ButtonWithMutableSubjectAndResourceVM(DEFAULT_LAYOUT_ID, DEFAULT_ENABLED_VALUE,
				DEFAULT_CLICKABLE_RES_ID, DEFAULT_ENABLE_DISABLES_RES_IDS, mockedButtonCommand, DEFAULT_IMAGE_RESOURCE_ID,
				DEFAULT_SUBJECT_WIDGET_ID);
	}

	@Test
	public void shouldContainsDefaultResourceWidgetResourceId() {
		MutableResourceButtonVM buttonWithMutableResource = (MutableResourceButtonVM) buttonVM;

		assertEquals(DEFAULT_IMAGE_RESOURCE_ID, buttonWithMutableResource.getResIdToChangeResource());
	}

	@Test
	public void shouldChangeSubjectValue() {
		MutableResourceButtonVM buttonWithMutableResource = (MutableResourceButtonVM) buttonVM;

		buttonWithMutableResource.setImageResourceId(ANY_IMAGE_ID);

		assertEquals(ANY_IMAGE_ID, buttonWithMutableResource.getImageResourceId());
	}

	@Test
	public void shouldNotifyButtonVMListener() {
		MutableResourceButtonVM buttonWithMutableResource = (MutableResourceButtonVM) buttonVM;
		buttonWithMutableResource.registerListener(mockedButtonVMListener);

		buttonWithMutableResource.setImageResourceId(ANY_IMAGE_ID);

		verify(mockedButtonVMListener).onImageResourceChanged(ANY_IMAGE_ID, buttonWithMutableResource);
	}

}
