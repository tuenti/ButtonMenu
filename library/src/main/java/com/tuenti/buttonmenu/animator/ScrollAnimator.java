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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;


/**
 * Animate a view with a "translationY" animation when the user scrolls a ListView. This class registers
 * a scroll listener when a ListView is associated using "configureListView" method.
 *
 * @author Pedro Vicente Gómez Sánchez <pgomez@tuenti.com>
 */
public class ScrollAnimator {

	private static final int SCROLL_DIRECTION_CHANGE_THRESHOLD = 5;
	private static final String ANIMATION_TYPE = "translationY";
	private static final int DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS = 200;
	private static final int HIDDEN_Y_POSITION = 0;
	protected static final int SCROLL_TO_TOP = -1;
	protected static final int SCROLL_TO_BOTTOM = 1;

	private final View animatedView;
	private final ObjectAnimatorFactory objectAnimatorFactory;
	private ListView listView;
	private OnScrollListener additionalScrollListener;
	private int scrollDirection = 0;
	private long durationInMillis = DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS;

	public ScrollAnimator(View animatedView, ObjectAnimatorFactory objectAnimatorFactory) {
		this.animatedView = animatedView;
		this.objectAnimatorFactory = objectAnimatorFactory;
	}

	/**
	 * Configure an additional scroll listener to be notified when the ListView scroll is updated.
	 *
	 * @param additionalScrollListener to notify.
	 */
	public void setAdditionalScrollListener(OnScrollListener additionalScrollListener) {
		this.additionalScrollListener = additionalScrollListener;
	}

	/**
	 * Associate a ListView to listen in order to perform the "translationY" animation when the ListView scroll is
	 * updated. This method is going to set a "OnScrollListener" to the ListView passed as argument.
	 *
	 * @param listView to listen.
	 */
	public void configureListView(ListView listView) {
		this.listView = listView;
		this.listView.setOnScrollListener(new OnScrollListener() {

			int scrollPosition;

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				notifyScrollToAdditionalScrollListener(view, firstVisibleItem, visibleItemCount, totalItemCount);
				View topChild = view.getChildAt(0);

				int newScrollPosition;
				if (topChild == null) {
					newScrollPosition = 0;
				} else {
					newScrollPosition = view.getFirstVisiblePosition() * topChild.getHeight() - topChild.getTop();
				}
				if (Math.abs(newScrollPosition - scrollPosition) >= SCROLL_DIRECTION_CHANGE_THRESHOLD) {
					onScrollPositionChanged(scrollPosition, newScrollPosition);
				}

				scrollPosition = newScrollPosition;
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				//Empty
			}
		});
	}

	/**
	 * Configure the "translationY" animation duration.
	 *
	 * @param duration in milliseconds.
	 */
	public void setDurationInMillis(long duration) {
		this.durationInMillis = duration;
	}

	/**
	 * Remove the scroll listener associated to the configured ListView.
	 */
	public void release() {
		listView.setOnScrollListener(null);
	}

	/**
	 * Show the animated view using a "translationY" animation.
	 */
	public void showWithAnimation() {
		showWithAnimationWithListener(null);
	}

	/**
	 * Show the animated view using a "translationY" animation and configure an AnimatorListener to be notified during
	 * the animation.
	 */
	public void showWithAnimationWithListener(Animator.AnimatorListener animatorListener) {
		scrollDirection = SCROLL_TO_TOP;
		ObjectAnimator objectAnimator = objectAnimatorFactory.getObjectAnimator(animatedView, ANIMATION_TYPE, HIDDEN_Y_POSITION);
		if (animatorListener != null) {
			objectAnimator.addListener(animatorListener);
		}
		objectAnimator.setDuration(durationInMillis);
		objectAnimator.start();
	}

	/**
	 * Hide the animated view using a "translationY" animation.
	 */
	public void hideWithAnimation() {
		hideWithAnimationWithListener(null);
	}

	/**
	 * Hide the animated view using a "translationY" animation and configure an AnimatorListener to be notified
	 * during the animation.
	 */
	public void hideWithAnimationWithListener(Animator.AnimatorListener animatorListener) {
		scrollDirection = SCROLL_TO_BOTTOM;
		ObjectAnimator objectAnimator = objectAnimatorFactory.getObjectAnimator(animatedView, ANIMATION_TYPE, animatedView.getHeight());
		if (animatorListener != null) {
			objectAnimator.addListener(animatorListener);
		}
		objectAnimator.setDuration(durationInMillis);
		objectAnimator.start();
	}

	/**
	 * Animate animated view from the current position to a "translationY" position.
	 *
	 * @param translationY final position.
	 */
	protected void animate(int translationY) {
		ObjectAnimator animator = objectAnimatorFactory.getObjectAnimator(animatedView, ANIMATION_TYPE, translationY);
		animator.setDuration(durationInMillis);
		animator.start();
	}

	private void notifyScrollToAdditionalScrollListener(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {
		if (additionalScrollListener != null) {
			additionalScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}

	/**
	 * Perform the "translateY" animation using the new scroll position and the old scroll position to show or hide
	 * the animated view.
	 *
	 * @param oldScrollPosition
	 * @param newScrollPosition
	 */
	private void onScrollPositionChanged(int oldScrollPosition, int newScrollPosition) {
		int newScrollDirection;

		if (newScrollPosition < oldScrollPosition) {
			newScrollDirection = SCROLL_TO_TOP;
		} else {
			newScrollDirection = SCROLL_TO_BOTTOM;
		}

		if (directionHasChanged(newScrollDirection)) {
			translateYAnimatedView(newScrollDirection);
		}
	}

	private boolean directionHasChanged(int newScrollDirection) {
		return newScrollDirection != scrollDirection;
	}

	private void translateYAnimatedView(int newScrollDirection) {
		scrollDirection = newScrollDirection;
		animatedView.post(new Runnable() {

			@Override
			public void run() {
				int translationY = hasScrolledToTop() ? HIDDEN_Y_POSITION : animatedView.getHeight();
				animate(translationY);
			}
		});
	}

	private boolean hasScrolledToTop() {
		return scrollDirection == SCROLL_TO_TOP;
	}
}
