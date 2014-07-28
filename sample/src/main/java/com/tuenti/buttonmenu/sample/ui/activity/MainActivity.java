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

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.tuenti.buttonmenu.ButtonMenu;
import com.tuenti.buttonmenu.animator.ObjectAnimatorFactory;
import com.tuenti.buttonmenu.animator.ScrollAnimator;
import com.tuenti.buttonmenu.sample.R;
import com.tuenti.buttonmenu.sample.ui.Agenda;
import com.tuenti.buttonmenu.sample.ui.Contact;
import com.tuenti.buttonmenu.sample.ui.buttonmenu.CustomButtonMenuVM;
import com.tuenti.buttonmenu.sample.ui.renderer.ContactRenderer;
import com.tuenti.buttonmenu.sample.ui.renderer.ContactRendererBuilder;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.ButtonMenuVM;

public class MainActivity extends Activity {

	private final ButtonMenuVM buttonMenuVM = new CustomButtonMenuVM();

	private ButtonMenu button_menu;
	private ListView lv_contacts;

	private Agenda agenda = new Agenda();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapGui();
		hookListeners();
		initializeButtonMenu();
		initializeContactsList();
	}

	private void mapGui() {
		button_menu = (ButtonMenu) findViewById(R.id.button_menu);
		lv_contacts = (ListView) findViewById(R.id.lv_contacts);
	}

	private void hookListeners() {
		lv_contacts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				openSecondActivity();
			}
		});
	}

	private void openSecondActivity() {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}

	/**
	 * Set the ButtonMenuVM implementation to the ButtonMenu custom view and initialize it.
	 */
	private void initializeButtonMenu() {
		button_menu = (ButtonMenu) findViewById(R.id.button_menu);
		button_menu.setButtonMenuVM(buttonMenuVM);
		button_menu.initialize();
	}

	private void initializeContactsList() {
		List<Renderer<Contact>> prototypes = new ArrayList<Renderer<Contact>>();
		prototypes.add(new ContactRenderer(this));
		ContactRendererBuilder contactRendererBuilder = new ContactRendererBuilder(prototypes);

		RendererAdapter<Contact> adapter = new RendererAdapter<Contact>(getLayoutInflater(), contactRendererBuilder, agenda);
		lv_contacts.setAdapter(adapter);

		initializeScrollAnimator();
	}

	/**
	 * Initialize ScrollAnimator to work with ButtonMenu custom view, the ListView used in this sample and an
	 * animation duration of 200 milliseconds.
	 */
	private void initializeScrollAnimator() {
		ScrollAnimator scrollAnimator = new ScrollAnimator(button_menu, new ObjectAnimatorFactory());
		scrollAnimator.configureListView(lv_contacts);
		scrollAnimator.setDurationInMillis(300);
	}
}
