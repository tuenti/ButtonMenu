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
 * ButtonWithProgressVM implementation based on SimpleButtonVM. This entity can be used by different components to
 * represent a simple button with a progressBar associated.
 *
 * @author "Iñaki Villar Algaba" <inaki.seri@gmail.com>
 */
public class ButtonWithProgressVM extends SimpleButtonVM implements ProgressButtonVM {

	private int imageViewId;
	private int progressViewId;
	private boolean loading;

	public ButtonWithProgressVM(int layoutId, boolean enabled, int clickableResId,
			int[] enableDisableResIds, ButtonCommand actionCommand, int imageViewId,
			int progressBarId) {
		super(layoutId, enabled, clickableResId, enableDisableResIds, actionCommand);
		this.imageViewId = imageViewId;
		this.progressViewId = progressBarId;
		this.loading = false;
	}

	@Override
	public void showLoading() {
		loading = true;
		notifyShowProgress();
	}

	@Override
	public void closeLoading() {
		loading = false;
		notifyCloseProgress();
	}

	@Override
	public int getProgress() {
		return progressViewId;
	}

	@Override
	public int getImage() {
		return imageViewId;
	}

	@Override
	public boolean isLoading() {
		return loading;
	}

	private void notifyShowProgress() {
		ButtonVMListener buttonVMListener = getListener();
		buttonVMListener.onProgressValueChanged(true, this);
	}

	private void notifyCloseProgress() {
		ButtonVMListener buttonVMListener = getListener();
		buttonVMListener.onProgressValueChanged(false, this);
	}
}
