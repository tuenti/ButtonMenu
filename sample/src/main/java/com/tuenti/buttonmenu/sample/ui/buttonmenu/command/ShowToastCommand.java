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

package com.tuenti.buttonmenu.sample.ui.buttonmenu.command;

import android.content.Context;
import android.widget.Toast;

import com.tuenti.buttonmenu.viewmodel.button.ButtonCommand;

/**
 * ButtonCommand implementation created to show a toast with a message.
 */
public class ShowToastCommand implements ButtonCommand {

	private final Context context;
	private final String text;

	public ShowToastCommand(Context context, String text) {
		this.context = context;
		this.text = text;
	}

	@Override
	public void execute() {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
