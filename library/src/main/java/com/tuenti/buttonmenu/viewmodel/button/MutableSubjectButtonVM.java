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
 * Interface created to represent a ButtonVM that supports a subject. This interface have to be implemented by each
 * item added to ButtonMenuVM that use a subject inside the button.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public interface MutableSubjectButtonVM extends ButtonVM {

	/**
	 * @return the subject stored in the ButtonVM
	 */
	String getSubject();

	/**
	 * Change the subject stored in the view model and notify the ButtonVMListener the change.
	 *
	 * @param subject changed.
	 */
	void setSubject(final String subject);

	/**
	 * @return an integer associated to the resource id that it's going to store the image change.
	 */
	int getResIdToInsertSubject();


}
