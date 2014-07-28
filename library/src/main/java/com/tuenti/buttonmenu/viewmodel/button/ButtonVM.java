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
 * Interface created to represent a ButtonVM. This interface have to be implemented by each item added to
 * ButtonMenuVM implementations.
 * <p/>
 * This view model contains information for each item in a ButtonMenu. Contains the presentation state for each item
 * and have to be used to modify the item.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public interface ButtonVM {

	/**
	 * Method used to check if the item should be shown as enabled or disabled.
	 *
	 * @return true if enabled; otherwise false;
	 */
	boolean isEnabled();

	/**
	 * Disable the item and notify the ButtonVMListener.
	 */
	void disable();

	/**
	 * Enable the item and notify the ButtonVMListener.
	 */
	void enable();

	/**
	 * Method used by the ButtonMenu to inflate the view when a new item it's added to this component.
	 *
	 * @return an integer that represents the R.layout identifier.
	 */
	int getLayoutId();

	/**
	 * Method used by the ButtonMenu to execute a command with the onClickListener for one item has been executed.
	 * This method can return a NULL ActionCommand if the implementation doesn't contains one.
	 *
	 * @return an ActionCommand implementation or null.
	 */
	ButtonCommand getButtonCommand();

	/**
	 * Register a listener to notify some changes like the enable or disable change.
	 *
	 * @param listener to register.
	 */
	void registerListener(ButtonVMListener listener);

	/**
	 * Un-register a listener from the listener collection.
	 */
	void unregisterListener();

	/**
	 * Method used by the ButtonMenu to hook the View.OnClickListenerMethod.
	 *
	 * @return an integer that represents the R.id identifier.
	 */
	int getClickableResId();

	/**
	 * Method used by the ButtonMenu to retrieve information about which widgets must be enabled or disabled when the
	 * client code execute a disable or enable methods.
	 *
	 * @return a integer array that represents the R.id identifier to be disabled or enabled.
	 */
	int[] getViewIdsToEnableOrDisable();

	/**
	 * Method used to update the action command stored in a ButtonMenu.
	 *
	 * @param actionCommand
	 */
	void setButtonCommand(ButtonCommand actionCommand);
}
