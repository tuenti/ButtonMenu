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

package com.tuenti.buttonmenu.animator;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * ObjectAnimator factory created to provide ObjectAnimator objects used in ScrollAnimator to perform push in / push
 * out animations.
 *
 * @author Pedro Vicente Gómez Sánchez <pgomez@tuenti.com>
 */
public class ObjectAnimatorFactory {

	public ObjectAnimator getObjectAnimator(View animatedView, String animationType, int translationY) {
		return ObjectAnimator.ofFloat(animatedView, animationType, translationY);
	}
}
