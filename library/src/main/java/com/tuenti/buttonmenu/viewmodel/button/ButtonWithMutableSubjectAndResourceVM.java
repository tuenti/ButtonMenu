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
 * ButtonVM implementation. This entity can be used by different components to represent a simple button that can
 * change this subject and image resource associated. The component that use this implementation must declare the
 * interface ButtonVM.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class ButtonWithMutableSubjectAndResourceVM extends SimpleButtonVM implements MutableSubjectButtonVM,
		MutableResourceButtonVM {

	private int imageId;
	private int imageResourceId;
	private String subject;
	private int subjectResourceId;


	public ButtonWithMutableSubjectAndResourceVM(int layoutId, boolean enabled,
			int clickableResId, int[]
			enableDisableResIds, ButtonCommand actionCommand, int imageResourceId,
			int subjectResourceId) {
		super(layoutId, enabled, clickableResId, enableDisableResIds, actionCommand);
		this.imageResourceId = imageResourceId;
		this.subjectResourceId = subjectResourceId;
	}

	@Override
	public int getImageResourceId() {
		return imageId;
	}

	@Override
	public void setImageResourceId(final int imageId) {
		this.imageId = imageId;
		notifyImageResourceChanged();
	}

	@Override
	public int getResIdToChangeResource() {
		return imageResourceId;
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

	private void notifyImageResourceChanged() {
		ButtonVMListener buttonVMListener = getListener();
		buttonVMListener.onImageResourceChanged(this.imageId, this);
	}

	private void notifySubjectChanged() {
		ButtonVMListener buttonVMListener = getListener();
		buttonVMListener.onSubjectChanged(this.subject, this);
	}

}
