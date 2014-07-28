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


package com.tuenti.buttonmenu.sample.ui.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;
import com.tuenti.buttonmenu.sample.R;
import com.tuenti.buttonmenu.sample.ui.Contact;

/**
 * ContactRenderer implementation created to show a Contact information inside a ListView row.
 *
 * If you want to lear more about how to use Renderers take a look to this project:
 * https://github.com/pedrovgs/Renderers
 *
 * @author Pedro Vicente Gómez Sánchez
 */

public class ContactRenderer extends Renderer<Contact> {

	private final Context context;

	private TextView tv_name;
	private TextView tv_date;
	private TextView tv_last_message;
	private ImageView iv_avatar;

	public ContactRenderer(Context context) {
		this.context = context;
	}

	@Override
	protected View inflate(LayoutInflater inflater, ViewGroup parent) {
		return inflater.inflate(R.layout.contact_row, parent, false);
	}

	@Override
	protected void setUpView(View rootView) {
		tv_name = (TextView) rootView.findViewById(R.id.tv_name);
		tv_date = (TextView) rootView.findViewById(R.id.tv_date);
		tv_last_message = (TextView) rootView.findViewById(R.id.tv_last_message);
		iv_avatar = (ImageView) rootView.findViewById(R.id.iv_avatar);
	}

	@Override
	protected void hookListeners(View rootView) {
		//Empty
	}

	@Override
	public void render() {
		Contact contact = getContent();
		Picasso.with(context).load(contact.getAvatar()).into(iv_avatar);
		tv_name.setText(contact.getName());
		tv_date.setText(contact.getLastDate());
		tv_last_message.setText(contact.getLastMessage());
	}
}
