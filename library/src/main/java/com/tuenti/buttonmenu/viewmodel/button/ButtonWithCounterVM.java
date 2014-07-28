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
 * CounterButtonVM implementation based on SimpleButtonVM. This entity can be used by different components to
 * represent a simple button that can change the counter associated.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class ButtonWithCounterVM extends SimpleButtonVM implements CounterButtonVM {

	private int counterValue;
	private final int counterWidgetId;

	public ButtonWithCounterVM(int layoutId, boolean enabled, int clickableResId, int[] enableDisableResIds,
			ButtonCommand actionCommand,
			int counterValue, int counterWidgetId) {
		super(layoutId, enabled, clickableResId, enableDisableResIds, actionCommand);
		this.counterValue = counterValue;
		this.counterWidgetId = counterWidgetId;
	}

	@Override
	public int getCounterValue() {
		return counterValue;
	}

	@Override
	public void setCounterValue(final int counterValue) {
		this.counterValue = counterValue;
		notifyCounterListener(counterValue);
	}

	@Override
	public int getCounterWidgetId() {
		return counterWidgetId;
	}

	private void notifyCounterListener(final int counterValue) {
		getListener().onCounterValueChanged(counterValue, this);
	}

}
