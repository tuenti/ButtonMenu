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

/**
 * Test created to check the correctness of SimpleButtonVM implementation.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class SimpleButtonVMTest extends ButtonVMTest {

	@Override
	protected ButtonVM getButtonVM() {
		return new SimpleButtonVM(DEFAULT_LAYOUT_ID, DEFAULT_ENABLED_VALUE, DEFAULT_CLICKABLE_RES_ID,
				DEFAULT_ENABLE_DISABLES_RES_IDS, mockedButtonCommand);
	}
}
