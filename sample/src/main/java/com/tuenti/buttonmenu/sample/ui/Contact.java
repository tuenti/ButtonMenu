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

/**
 * Contains all the information needed of a contact.
 */
public class Contact {

	private final String name;
	private final String lastDate;
	private final String lastMessage;
	private final String avatar;

	public Contact(String name, String lastDate, String lastMessage, String avatar) {
		this.name = name;
		this.lastDate = lastDate;
		this.lastMessage = lastMessage;
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public String getLastDate() {
		return lastDate;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public String getAvatar() {
		return avatar;
	}
}
