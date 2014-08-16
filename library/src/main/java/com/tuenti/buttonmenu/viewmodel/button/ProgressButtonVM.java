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
 * Interface created to represent a ButtonVM that supports a progressBar. This interface have to be
 * implemented by each item added to ButtonMenuVM that use a progressBar inside the button.
 *
 * @author "Iñaki Villar Algaba" <inaki.seri@gmail.com>
 */
public interface ProgressButtonVM extends ButtonVM {

	/**
	 * Change the state starting loading in the view model and notify the ButtonVMListener the
	 * change.
	 */
	void showLoading();

	/**
	 * Change the state finishing loading in the view model and notify the ButtonVMListener the
	 * change
	 */
	void closeLoading();

	/**
	 * @return the resource id associated to the progress
	 */
	int getProgress();

	/**
	 * @return the resource id associated to the image.
	 */
	int getImage();

	/**
	 * @return the state of loading of ProgressBar.
	 */
	boolean isLoading();
}
