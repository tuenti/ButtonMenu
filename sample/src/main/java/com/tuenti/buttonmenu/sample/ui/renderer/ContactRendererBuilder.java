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

import java.util.Collection;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import com.tuenti.buttonmenu.sample.ui.Contact;

/**
 * RendererBuilder implementation created to map Contacts to Renderer<Contact> implementations.
 * <p/>
 * *
 * If you want to lear more about how to use Renderers take a look to this project:
 * https://github.com/pedrovgs/Renderers
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class ContactRendererBuilder extends RendererBuilder<Contact> {

	public ContactRendererBuilder(Collection<Renderer<Contact>> prototypes) {
		super(prototypes);
	}

	@Override
	protected Class getPrototypeClass(Contact content) {
		return ContactRenderer.class;
	}
}
