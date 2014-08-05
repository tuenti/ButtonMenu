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

package com.tuenti.buttonmenu.sample.ui.buttonmenu;

import android.content.Context;

import com.tuenti.buttonmenu.viewmodel.button.ButtonVM;
import com.tuenti.buttonmenu.viewmodel.button.ButtonWithMutableSubjectAndResourceVM;
import com.tuenti.buttonmenu.viewmodel.button.ButtonWithProgressVM;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.SimpleButtonMenuVM;
import com.tuenti.buttonmenu.viewmodel.button.SimpleButtonVM;
import com.tuenti.buttonmenu.sample.R;
import com.tuenti.buttonmenu.sample.ui.buttonmenu.command.ShowToastCommand;

/**
 * ButtonMenu implementation used in the sample project. This class has been created to encapsulate the
 * implementation of this ButtonMenu and to group the initialization out of activities of fragments.
 */
public class SecondButtonMenuVM extends SimpleButtonMenuVM {

	private final Context context;

	/*
	 * Every ButtonVM implementation could be moved to a different file extending SimpleButtonVM
	 */
	private final ButtonVM moment;
	private final ButtonWithMutableSubjectAndResourceVM photo;
	private final ButtonWithProgressVM loading;

	public SecondButtonMenuVM(Context context) {
		super();
		this.context = context;
		moment = new SimpleButtonVM(R.layout.moment_button, true, R.id.moment, new int[]{R.id.moment},
				new ShowToastCommand(context, "Moment button clicked"));

		photo = new ButtonWithMutableSubjectAndResourceVM(R.layout.photo_with_subtitle_button, true, R.id.photo,
				new int[]{R.id.photo}, new ShowToastCommand(context, "Photo button clicked"), R.id.photo,
				R.id.photo);

		loading = new ButtonWithProgressVM(R.layout.progress_button, true,
				R.id.refresh, new int[] { R.id.refresh, R.id.progress },
				new ShowToastCommand(context,"Progress button clicked"), R.id.refresh,
				R.id.progress);

		addItem(moment);
		addItem(photo);
		addItem(loading);
	}

	public boolean isPhotoButtonEnabled() {
		return photo.isEnabled();
	}

	public void enablePhotoButton() {
		photo.enable();
	}

	public void disablePhotoButton() {
		photo.disable();
	}

	public void changePhotoIcon() {
		if (photo.getImageResourceId() == R.drawable.photo_icon) {
			photo.setImageResourceId(R.drawable.contact_icon);
		} else {
			photo.setImageResourceId(R.drawable.photo_icon);
		}
	}

	public void changePhotoIconSubtitle() {
		String takePictureSubtitle = context.getString(R.string.photo_button_subtitle);
		if (takePictureSubtitle.equals(photo.getSubject())) {
			photo.setSubject(context.getString(R.string.photo_button_second_subtitle));
		} else {
			photo.setSubject(takePictureSubtitle);
		}
	}

	public void showLoading() {
		loading.showLoading();
	}

	public void closeLoading() {
		loading.closeLoading();
	}

	public boolean isLoading(){
		return loading.isLoading();
	}
}
