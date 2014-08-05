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
 * Interface created to work as a listener for ButtonVM. This interface contains some methods called when the client
 * code interacts with a ButtonVM.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public interface ButtonVMListener {

	/**
	 * Method executed when the enable() or disable() method it's executed in a ButtonVM.
	 *
	 * @param enabled boolean represents the new ButtonVM state.
	 * @param buttonVM that has suffer the change.
	 */
	void onEnablePropertyChanged(final boolean enabled, final ButtonVM buttonVM);

	/**
	 * Method executed when the counter value has changed in a CounterButtonVM.
	 *
	 * @param counterValue new counterValue change in the view model.
	 * @param buttonVM that has suffer the change.
	 */
	void onCounterValueChanged(final int counterValue, final CounterButtonVM buttonVM);

	/**
	 * Method executed when the image resource has changed in a ImageResourceChangeVM.
	 *
	 * @param imageResourceId new image identifier.
	 * @param buttonVM that has suffer the change.
	 */
	void onImageResourceChanged(final int imageResourceId, final MutableResourceButtonVM buttonVM);

	/**
	 * Method executed when the subject value change in the view model.
	 *
	 * @param subject new subject value.
	 * @param buttonVm that has suffer the change.
	 */
	void onSubjectChanged(final String subject, final MutableSubjectButtonVM buttonVm);

	/**
	 * Method executed when the progress value change in the view model.
	 *
	 * @param loading  new state of progressbar loading
	 * @param buttonVm that has suffer the change.
	 */
	void onProgressValueChanged(boolean loading, final ButtonWithProgressVM buttonVm);
}
