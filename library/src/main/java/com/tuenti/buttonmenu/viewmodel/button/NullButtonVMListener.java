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
 * Null Object pattern implementation used to avoid null checks in ButtonVM.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
class NullButtonVMListener implements ButtonVMListener {

	@Override
	public void onEnablePropertyChanged(boolean enabled, ButtonVM buttonVM) {
		//Empty
	}

	@Override
	public void onCounterValueChanged(int counterValue, CounterButtonVM buttonVM) {
		//Empty
	}

	@Override
	public void onImageResourceChanged(int imageResourceId, MutableResourceButtonVM buttonVM) {
		//Empty
	}

	@Override
	public void onSubjectChanged(String subject, MutableSubjectButtonVM buttonVm) {
		//Empty
	}
}
