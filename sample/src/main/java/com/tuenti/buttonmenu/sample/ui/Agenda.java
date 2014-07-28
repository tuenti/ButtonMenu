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

package com.tuenti.buttonmenu.sample.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pedrogomez.renderers.AdapteeCollection;

/**
 * Class created to contain Contact instances.
 */
public class Agenda implements AdapteeCollection<Contact> {

	private List<Contact> contacts = new ArrayList<Contact>();

	/*
	 * Add fake info to the contacts collection to be used in the sample.
	 */
	public Agenda() {
		add(new Contact("Jamie Torres ", "16:40", "Can you call me?", "http://api.randomuser.me/portraits/men/30.jpg"));
		add(new Contact("Rachel Phillips", "16:59", "Ey! Do you have something to do today?",
				"http://api.randomuser.me/portraits/women/49.jpg"));
		add(new Contact("Keith Gilbert", "18:15", "jajajaja ;)", "http://api.randomuser.me/portraits/men/79.jpg"));
		add(new Contact("Joaquin José", "18:50", "ok! See you tomorrow :)",
				"http://api.randomuser.me/portraits/men/58.jpg"));
		add(new Contact("David Saint", "18:50", "ok! See you tomorrow :)",
				"http://api.randomuser.me/portraits/men/11.jpg"));
		add(new Contact("Dawn Moreno ", "yesterday", "........ are you kidding?",
				"http://api.randomuser.me/portraits/women/66" +
						".jpg"));
		add(new Contact("Kina Gregory ", "yesterday", "Do you have the last preproduction version?",
				"http://api.randomuser.me/portraits/women/89.jpg"));
		add(new Contact("Jake Gómez", "yesterday", "This library is awesome!!", "http://api.randomuser.me/portraits/men/60" +
				".jpg"));
		add(new Contact("Leticia Jackson ", "two days ago", "I miss you :_(",
				"http://api.randomuser.me/portraits/women/39.jpg"));
		add(new Contact("Eva Rodriguez", "four days ago", "Ok, have a nice day! ;)",
				"http://api.randomuser.me/portraits/women/7.jpg"));
		add(new Contact("Jamie Torres ", "16:40", "Can you call me?", "http://api.randomuser.me/portraits/men/30.jpg"));
		add(new Contact("Rachel Phillips", "16:59", "Ey! Do you have something to do today?",
				"http://api.randomuser.me/portraits/women/49.jpg"));
		add(new Contact("Keith Gilbert", "18:15", "jajajaja ;)", "http://api.randomuser.me/portraits/men/79.jpg"));
		add(new Contact("Joaquin José", "18:50", "ok! See you tomorrow :)",
				"http://api.randomuser.me/portraits/men/58.jpg"));
		add(new Contact("David Saint", "18:50", "ok! See you tomorrow :)",
				"http://api.randomuser.me/portraits/men/11.jpg"));
		add(new Contact("Dawn Moreno ", "yesterday", "........ are you kidding?",
				"http://api.randomuser.me/portraits/women/66" +
						".jpg"));
		add(new Contact("Kina Gregory ", "yesterday", "Do you have the last preproduction version?",
				"http://api.randomuser.me/portraits/women/89.jpg"));
		add(new Contact("Jake Gómez", "yesterday", "This library is awesome!!", "http://api.randomuser.me/portraits/men/60" +
				".jpg"));
		add(new Contact("Leticia Jackson ", "two days ago", "I miss you :_(",
				"http://api.randomuser.me/portraits/women/39.jpg"));
		add(new Contact("Eva Rodriguez", "four days ago", "Ok, have a nice day! ;)",
				"http://api.randomuser.me/portraits/women/7.jpg"));
	}

	@Override
	public int size() {
		return contacts.size();
	}

	@Override
	public Contact get(int index) {
		return contacts.get(index);
	}

	@Override
	public void add(Contact element) {
		contacts.add(element);
	}

	@Override
	public void remove(Contact element) {
		contacts.remove(element);
	}

	@Override
	public void addAll(Collection<Contact> elements) {
		contacts.addAll(elements);
	}

	@Override
	public void removeAll(Collection<Contact> elements) {
		contacts.removeAll(elements);
	}
}
