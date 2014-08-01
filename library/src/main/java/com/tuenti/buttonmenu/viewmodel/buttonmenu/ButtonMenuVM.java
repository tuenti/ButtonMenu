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

package com.tuenti.buttonmenu.viewmodel.buttonmenu;

import java.util.Set;

import com.tuenti.buttonmenu.viewmodel.button.ButtonVM;


/**
 * Interface created to represent a ButtonMenu view model with some ButtonVM inside.
 * This view model supports the addition and removal of ButtonVM items.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public interface ButtonMenuVM {

	/**
	 * @return a list of ButtonVM stored in this view model.
	 */
	Set<ButtonVM> getButtonVMs();

	/**
	 * Registers a ButtonMenuVMListener item that will be notified when a ButtonVM is
	 * added to/removed from the ButtonMenuVM.
	 *
	 * @param ButtonMenuVMListener to register.
	 */
	void registerListener(ButtonMenuVMListener listener);

	/**
	 * Un-registers a ButtonMenuVMListener.
	 *
	 * @param ButtonMenuVMListener to un-register.
	 */
	void unregisterListener(ButtonMenuVMListener listener);

	/**
	 * Enable every ButtonVM in the ButtonMenuVM.
	 */
	void enable();

	/**
	 * Disable every ButtonVM in the ButtonMenuVM.
	 */
	void disable();

	/**
	 * Interface created to represent a listener for the addition/removal of ButtonVM items.
	 */
	interface ButtonMenuVMListener {

		/**
		 * Notifies of a ButtonVM addition.
		 *
		 * @param buttonVM to notify it's addition.
		 */
		void onButtonVMAdded(ButtonVM buttonVM);

		/**
		 * Notifies of a ButtonVM removal.
		 *
		 * @param buttonVM to notify it's removal.
		 */
		void onButtonVMRemoved(ButtonVM buttonVM);
	}
}
