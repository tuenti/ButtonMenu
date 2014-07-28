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
 * ButtonWithMutableSubjectVM implementation based on SimpleButtonVM. This entity can be used by different components to
 * represent a simple button that can change the subject associated.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class ButtonWithMutableSubjectVM extends SimpleButtonVM implements MutableSubjectButtonVM {

	private String subject;
	private int subjectResourceId;

	public ButtonWithMutableSubjectVM(int layoutId, int clickableResId, ButtonCommand actionCommand) {
		this(layoutId, true, clickableResId, new int[]{clickableResId}, clickableResId, actionCommand);
	}

	public ButtonWithMutableSubjectVM(int layoutId, boolean enabled, int clickableResId,
			int[] enableDisableResIds, int subjectResourceId, ButtonCommand actionCommand) {
		super(layoutId, enabled, clickableResId, enableDisableResIds, actionCommand);
		this.subjectResourceId = subjectResourceId;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public void setSubject(final String subject) {
		this.subject = subject;
		notifySubjectChanged();
	}

	@Override
	public int getResIdToInsertSubject() {
		return subjectResourceId;
	}

	private void notifySubjectChanged() {
		ButtonVMListener buttonVMListener = getListener();
		buttonVMListener.onSubjectChanged(this.subject, this);
	}
}
