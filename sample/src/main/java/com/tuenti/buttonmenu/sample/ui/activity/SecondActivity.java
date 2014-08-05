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

package com.tuenti.buttonmenu.sample.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tuenti.buttonmenu.ButtonMenu;
import com.tuenti.buttonmenu.sample.R;
import com.tuenti.buttonmenu.sample.ui.buttonmenu.SecondButtonMenuVM;

public class SecondActivity extends Activity {

	private final SecondButtonMenuVM buttonMenuVM = new SecondButtonMenuVM(this);

	private ButtonMenu button_menu;
	private Button bt_enable_disable_all;
	private Button bt_enable_disable_photo;
	private Button bt_change_icon;
	private Button bt_change_subtitle;
	private Button bt_start_progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		mapGui();
		hookListeners();
		initializeButtonMenu();
	}

	private void mapGui() {
		button_menu = (ButtonMenu) findViewById(R.id.button_menu);
		bt_enable_disable_all = (Button) findViewById(R.id.bt_enable_disable_all);
		bt_enable_disable_photo = (Button) findViewById(R.id.bt_enable_disable_photo);
		bt_change_icon = (Button) findViewById(R.id.bt_change_icon);
		bt_change_subtitle = (Button) findViewById(R.id.bt_change_subtitle);
		bt_start_progress = (Button) findViewById(R.id.bt_start_progress);
	}

	private void initializeButtonMenu() {
		button_menu.setButtonMenuVM(buttonMenuVM);
		button_menu.initialize();
	}

	private void hookListeners() {
		bt_enable_disable_all.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean enable = !button_menu.getChildAt(0).isEnabled();
				if (enable) {
					buttonMenuVM.enable();
				} else {
					buttonMenuVM.disable();
				}
			}
		});

		bt_enable_disable_photo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * Enable or disable just one element inside the ButtonMenuVM implementation.
				 */
				if (buttonMenuVM.isPhotoButtonEnabled()) {
					buttonMenuVM.disablePhotoButton();
				} else {
					buttonMenuVM.enablePhotoButton();
				}
			}
		});

		bt_change_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * Change the icon of one element inside the ButtonMenuVM implementation
				 */
				buttonMenuVM.changePhotoIcon();
			}
		});

		bt_change_subtitle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * Change the subtitle of one element inside the ButtonMenuVM implementation
				 */
				buttonMenuVM.changePhotoIconSubtitle();
			}
		});

		bt_start_progress.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * Start progress loading of one element inside the ButtonVM implementation
				 */
				if(buttonMenuVM.isLoading()) {
					buttonMenuVM.closeLoading();
					bt_start_progress.setText(getResources().getString(R.string.
							button_text_start_progress));
				} else {
					buttonMenuVM.showLoading();
					bt_start_progress.setText(getResources().getString(R.string
							.button_text_stop_progress));
				}
			}
		});

	}
}
