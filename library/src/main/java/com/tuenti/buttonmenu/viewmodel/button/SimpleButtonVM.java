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
 * Base ButtonVM implementation. This entity can be used by different components to represent a simple button that can't
 * change and contains an button command associated.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class SimpleButtonVM implements ButtonVM {

	private final int layoutId;
	private boolean enabled;
	private final int clickableResId;
	private final int[] enableDisableResIds;
	private ButtonCommand buttonCommand;
	private ButtonVMListener listener;

	public SimpleButtonVM() {
		this(0, 0, null);
	}

	public SimpleButtonVM(int layoutId, int clickableResId, ButtonCommand buttonCommand) {
		this(layoutId, true, clickableResId, new int[]{clickableResId}, buttonCommand);
	}

	public SimpleButtonVM(int layoutId, boolean enabled, int clickableResId, int[] enableDisableResIds,
			ButtonCommand buttonCommand) {
		this.layoutId = layoutId;
		this.enabled = enabled;
		this.clickableResId = clickableResId;
		this.enableDisableResIds = enableDisableResIds;
		this.buttonCommand = buttonCommand;
		this.listener = new NullButtonVMListener();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void disable() {
		final boolean enabled = false;
		setEnabled(enabled);
		notifyIsEnabledListener(enabled);
	}

	@Override
	public void enable() {
		final boolean enabled = true;
		setEnabled(enabled);
		notifyIsEnabledListener(enabled);
	}

	@Override
	public int getLayoutId() {
		return layoutId;
	}

	@Override
	public ButtonCommand getButtonCommand() {
		return buttonCommand;
	}

	@Override
	public void registerListener(ButtonVMListener listener) {
		this.listener = listener;
	}

	@Override
	public void unregisterListener() {
		this.listener = new NullButtonVMListener();
	}

	@Override
	public int getClickableResId() {
		return clickableResId;
	}

	@Override
	public int[] getViewIdsToEnableOrDisable() {
		return enableDisableResIds;
	}

	@Override
	public void setButtonCommand(ButtonCommand buttonCommand) {
		this.buttonCommand = buttonCommand;
	}

	protected ButtonVMListener getListener() {
		return listener;
	}

	private void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private void notifyIsEnabledListener(final boolean enabled) {
		this.listener.onEnablePropertyChanged(enabled, this);
	}

}
