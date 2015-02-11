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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

/**
 * Test created to check the correctness of ScrollAnimator implementation.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ScrollAnimatorTest {

	private static final int ANIMATED_VIEW_HEIGHT = 100;
	private static final String ANIMATION_TYPE_TRANSLATION_Y = "translationY";
	private static final int TOTAL_ITEM_COUNT = 10;
	private static final int ANY_FIRST_VISIBLE_ITEM = 2;
	private static final int ANY_VISIBLE_ITEM_COUNT = 3;
	private static final int ANY_SCROLL_ACTION = 0;
	private static final int HIDDEN_Y_POSITION = 0;
	private static final int SHOW_Y_POSITION = ANIMATED_VIEW_HEIGHT;

	private ScrollAnimator scrollAnimator;
	private OnScrollListener scrollListener;

	@Spy
	private ObjectAnimatorFactory objectAnimatorFactory;
	@Mock
	private ListView listView;
	@Spy
	private View animatedView;
	@Captor
	private ArgumentCaptor<OnScrollListener> scrollListenerCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		scrollAnimator = new ScrollAnimator(animatedView, objectAnimatorFactory);

		setUpListView();
	}

	@Test
	public void shouldHideViewOnScrollToBottom() {
		scrollAnimator.configureListView(listView);

		scrollToBottom();

		verify(objectAnimatorFactory).getObjectAnimator(animatedView, ANIMATION_TYPE_TRANSLATION_Y, SHOW_Y_POSITION);

	}

	@Test
	public void shouldShowViewOnScrollToBottomAndThenToTop() {
		scrollAnimator.configureListView(listView);

		scrollToBottom();
		scrollToTop();

		verify(objectAnimatorFactory).getObjectAnimator(animatedView, ANIMATION_TYPE_TRANSLATION_Y, 0);
	}

	@Test
	public void shouldNotifyAdditionalScrollListenerOnScroll() {
		OnScrollListener mockedScrollListener = mock(OnScrollListener.class);
		scrollAnimator.setAdditionalScrollListener(mockedScrollListener);
		scrollAnimator.configureListView(listView);

		scrollToAnyPosition(ANY_FIRST_VISIBLE_ITEM, ANY_VISIBLE_ITEM_COUNT, TOTAL_ITEM_COUNT);

		verify(mockedScrollListener).onScroll(listView, ANY_FIRST_VISIBLE_ITEM, ANY_VISIBLE_ITEM_COUNT, TOTAL_ITEM_COUNT);
	}

	@Test
	public void shouldNotifyAdditionalScrollListenerOnScrollStateChanged() {
		OnScrollListener mockedScrollListener = mock(OnScrollListener.class);
		scrollAnimator.setAdditionalScrollListener(mockedScrollListener);
		scrollAnimator.configureListView(listView);

		doAnyScrollAction();

		verify(mockedScrollListener).onScrollStateChanged(listView, ANY_SCROLL_ACTION);
	}

	@Test
	public void shouldReleaseListViewScrollListener() {
		scrollAnimator.configureListView(listView);

		scrollAnimator.release();

		verify(listView).setOnScrollListener(null);
	}

	@Test
	public void shouldShowAnimatedView() {
		scrollAnimator.configureListView(listView);

		scrollAnimator.showWithAnimation();

		verify(objectAnimatorFactory).getObjectAnimator(animatedView, ANIMATION_TYPE_TRANSLATION_Y, HIDDEN_Y_POSITION);
	}

	@Test
	public void shouldHideAnimatedView() {
		scrollAnimator.configureListView(listView);

		scrollAnimator.hideWithAnimation();

		verify(objectAnimatorFactory).getObjectAnimator(animatedView, ANIMATION_TYPE_TRANSLATION_Y, SHOW_Y_POSITION);
	}

	private void scrollToBottom() {
		when(listView.getFirstVisiblePosition()).thenReturn(2);
		verify(listView).setOnScrollListener(scrollListenerCaptor.capture());
		scrollListener = scrollListenerCaptor.getValue();
		scrollListener.onScroll(listView, 0, 5, TOTAL_ITEM_COUNT);
	}


	private void scrollToTop() {
		when(listView.getFirstVisiblePosition()).thenReturn(1);
		verify(listView).setOnScrollListener(scrollListenerCaptor.capture());
		scrollListener = scrollListenerCaptor.getValue();
		scrollListener.onScroll(listView, 5, 0, TOTAL_ITEM_COUNT);
	}

	private void scrollToAnyPosition(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		when(listView.getFirstVisiblePosition()).thenReturn(2);
		verify(listView).setOnScrollListener(scrollListenerCaptor.capture());
		scrollListener = scrollListenerCaptor.getValue();
		scrollListener.onScroll(listView, firstVisibleItem, visibleItemCount, totalItemCount);
	}

	private void doAnyScrollAction() {
		verify(listView).setOnScrollListener(scrollListenerCaptor.capture());
		scrollListener = scrollListenerCaptor.getValue();
		scrollListener.onScrollStateChanged(listView, ANY_SCROLL_ACTION);
	}

	private void setUpListView() {
		View topChild = mock(View.class);
		when(topChild.getHeight()).thenReturn(ANIMATED_VIEW_HEIGHT);
		when(animatedView.getHeight()).thenReturn(ANIMATED_VIEW_HEIGHT);
		when(topChild.getTop()).thenReturn(1);
		when(listView.getChildAt(0)).thenReturn(topChild);
	}
}
