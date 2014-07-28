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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import com.tuenti.buttonmenu.viewmodel.button.ButtonVM;

/**
 * Main implementation of ButtonMenuVM. This entity contain the business logic associated to store ButtonVM's.
 * This class it's going to be extended by GroupConversationButtonMenuVm and IndividualConversationButtonMenuVm. This
 * entity has been created to avoid duplicate code.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class SimpleButtonMenuVM implements ButtonMenuVM {

	private final Set<ButtonVM> buttonVMs;
	private ButtonMenuVMListener listener;

	public SimpleButtonMenuVM() {
		this(new LinkedList<ButtonVM>());
	}

	public SimpleButtonMenuVM(ButtonVM buttonVM) {
		this.buttonVMs = new LinkedHashSet<ButtonVM>();
		this.buttonVMs.add(buttonVM);
	}

	public SimpleButtonMenuVM(Collection<ButtonVM> buttons) {
		this.buttonVMs = new LinkedHashSet<ButtonVM>();
		this.buttonVMs.addAll(buttons);
	}

	@Override
	public Set<ButtonVM> getButtonVMs() {
		return buttonVMs;
	}

	@Override
	public void enable() {
		for (ButtonVM buttonVM : buttonVMs) {
			buttonVM.enable();
		}
	}

	@Override
	public void disable() {
		for (ButtonVM buttonVM : buttonVMs) {
			buttonVM.disable();
		}
	}

	public void addItem(final ButtonVM buttonVM) {
		if (buttonVMs.add(buttonVM)) {
			notifyItemAdded(buttonVM);
		}
	}

	public void removeItem(final ButtonVM buttonVM) {
		if (buttonVMs.remove(buttonVM)) {
			notifyItemRemoved(buttonVM);
		}
	}

	@Override
	public void registerListener(ButtonMenuVMListener listener) {
		this.listener = listener;
	}

	@Override
	public void unregisterListener(ButtonMenuVMListener listener) {
		if (this.listener == listener) {
			this.listener = null;
		}
	}

	private void notifyItemAdded(ButtonVM buttonVM) {
		if (this.listener != null) {
			this.listener.onButtonVMAdded(buttonVM);
		}
	}

	private void notifyItemRemoved(ButtonVM buttonVM) {
		if (this.listener != null) {
			this.listener.onButtonVMRemoved(buttonVM);
		}
	}
}
