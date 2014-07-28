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
 * Interface created to represent a ButtonVM that supports a counter. This interface have to be implemented by each
 * item added to ButtonMenuVM that use a counter inside the button.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public interface CounterButtonVM extends ButtonVM {

	/**
	 * @return an integer with the counter value stored in this ButtonVM.
	 */
	int getCounterValue();

	/**
	 * Modify the counter value stored in this ButtonVM and notify the ButtonVMListener that the value has changed.
	 *
	 * @param counterValue with the new value.
	 */
	void setCounterValue(final int counterValue);

	/**
	 * @return the identifier associated to the widget that works as counter.
	 */
	int getCounterWidgetId();

}
