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

import com.tuenti.buttonmenu.sample.R;
import com.tuenti.buttonmenu.viewmodel.button.ButtonVM;
import com.tuenti.buttonmenu.viewmodel.button.SimpleButtonVM;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.SimpleButtonMenuVM;

/**
 * ButtonMenu implementation used in the sample project. This class has been created to encapsulate the
 * implementation of this ButtonMenu and to group the initialization if ButtonVM implementations out of activities
 * and fragments.
 */
public class CustomButtonMenuVM extends SimpleButtonMenuVM {

	/*
	 * Every ButtonVM implementation could be moved to a different file extending SimpleButtonVM if needed.
	 */
	private final ButtonVM moment = new SimpleButtonVM(R.layout.moment_button, R.id.moment, null);
	private final ButtonVM photo = new SimpleButtonVM(R.layout.photo_button, R.id.photo, null);
	private final ButtonVM contact = new SimpleButtonVM(R.layout.contact_button, R.id.contact, null);

	public CustomButtonMenuVM() {
		super();
		addItem(moment);
		addItem(photo);
		addItem(contact);
	}
}
