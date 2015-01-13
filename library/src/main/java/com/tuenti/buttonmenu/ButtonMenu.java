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

package com.tuenti.buttonmenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuenti.buttonmenu.viewmodel.button.ButtonCommand;
import com.tuenti.buttonmenu.viewmodel.button.ButtonVM;
import com.tuenti.buttonmenu.viewmodel.button.ButtonVMListener;
import com.tuenti.buttonmenu.viewmodel.button.ButtonWithProgressVM;
import com.tuenti.buttonmenu.viewmodel.button.CounterButtonVM;
import com.tuenti.buttonmenu.viewmodel.button.MutableResourceButtonVM;
import com.tuenti.buttonmenu.viewmodel.button.MutableSubjectButtonVM;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.ButtonMenuVM;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.ButtonMenuVM.ButtonMenuVMListener;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.OnButtonCommandExecuted;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Base custom view created extending LinearLayout. This class works as the core of ButtonMenu library.
 * <p/>
 * Add a ButtonMenuVM implementation to this component to show a list of buttons.
 *
 * @author "Pedro Vicente Gómez Sánchez" <pgomez@tuenti.com>
 */
public class ButtonMenu extends LinearLayout implements ButtonVMListener, ButtonMenuVMListener {

	private static final int MIN_WEIGHT_SUM = 0;
	static final float WEIGHT_SUM = 100.0f;

	private Map<ButtonVM, View> items;
	private ButtonMenuVM buttonMenuVM;
	private OnButtonCommandExecuted onButtonCommandExecutedListener;

	public ButtonMenu(Context context) {
		super(context);
		initializeView();
	}

	public ButtonMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView();
	}

	@TargetApi(VERSION_CODES.HONEYCOMB)
	public ButtonMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView();
	}

	/**
	 * Join the Activity/Fragment lifecycle to the ButtonMenu custom view lifecycle. Call this method from the
	 * initialize of your Activity/Fragment.
	 */
	public void initialize() {
		clean();
		renderButtonMenuVM();
		registerListeners();
	}

	/**
	 * Join the Activity/Fragment lifecycle to the ButtonMenu custom view lifecycle. Call this method from the
	 * release of your Activity/Fragment.
	 */
	public void release() {
		unregisterListeners();
		clean();
	}

	/**
	 * Set the view model to configure the button menu state.
	 * This method should be called before initialize method.
	 *
	 * @param buttonMenuVM used to configure the custom view
	 */
	public void setButtonMenuVM(ButtonMenuVM buttonMenuVM) {
		this.buttonMenuVM = buttonMenuVM;
	}

	/**
	 * Obtain the ButtonMenuVM implementation associated to the ButtonMenu instance.
	 */
	public ButtonMenuVM getButtonMenuVM() {
		return buttonMenuVM;
	}

	/**
	 * Attach a OnActionCommandExecutedListener to the ButtonMenu.
	 *
	 * @param onButtonCommandExecutedListener - The OnActionCommandExecutedListener to be attached.
	 */
	public void setOnButtonCommandExecutedListener(OnButtonCommandExecuted onButtonCommandExecutedListener) {
		this.onButtonCommandExecutedListener = onButtonCommandExecutedListener;
	}

	@Override
	public void onEnablePropertyChanged(boolean enabled, ButtonVM buttonVM) {
		View view = getViewForViewModel(buttonVM);
		setIsEnabled(buttonVM, view, enabled);
	}

	@Override
	public void onCounterValueChanged(int counterValue, CounterButtonVM buttonVM) {
		View view = getViewForViewModel(buttonVM);
		int counterResourceId = buttonVM.getCounterWidgetId();
		renderCounter(buttonVM, view, counterResourceId);
	}

	@Override
	public void onImageResourceChanged(int imageResourceId, MutableResourceButtonVM buttonVM) {
		View view = getViewForViewModel(buttonVM);
		int viewResourceToChange = buttonVM.getResIdToChangeResource();
		renderImageResource(view, viewResourceToChange, imageResourceId);
	}

	@Override
	public void onSubjectChanged(String subject, MutableSubjectButtonVM buttonVM) {
		View view = getViewForViewModel(buttonVM);
		int subjectResourceId = buttonVM.getResIdToInsertSubject();
		renderSubject(view, subject, subjectResourceId);
	}

	@Override
	public void onButtonVMAdded(final ButtonVM buttonVM) {
		add(buttonVM);
		addItem(buttonVM);
	}

	@Override
	public void onButtonVMRemoved(final ButtonVM buttonVM) {
		remove(buttonVM);
		removeItem(buttonVM);
	}

	@Override
	public void onProgressValueChanged(boolean loading, ButtonWithProgressVM buttonVM) {
		View view = getViewForViewModel(buttonVM);
		int imageId = buttonVM.getImage();
		int progressId = buttonVM.getProgress();
		renderProgress(view, imageId, progressId, loading);
	}


	private void renderButtonMenuVM() {
		if (buttonMenuVM != null && buttonMenuVM.getButtonVMs() != null) {
			Set<ButtonVM> buttonVMList = this.buttonMenuVM.getButtonVMs();
			for (ButtonVM buttonVM : buttonVMList) {
				addItem(buttonVM);
			}
		}
	}

	private void addItem(final ButtonVM buttonVM) {
		if (!items.containsKey(buttonVM)) {
			View view = renderItem(buttonVM);
			hookActions(view, buttonVM);
			items.put(buttonVM, view);
			updateWeight();
		}
	}

	private void removeItem(final ButtonVM buttonVM) {
		if (items.containsKey(buttonVM)) {
			items.remove(buttonVM);
			updateWeight();
		}
	}

	private void initializeView() {
		initItemsCollection();
		initWeightSum();
	}

	private void initItemsCollection() {
		items = new HashMap<ButtonVM, View>();
	}

	private LayoutInflater getLayoutInflater() {
		Context context = this.getContext();
		return LayoutInflater.from(context);
	}

	private void initWeightSum() {
		// If the weight is unset or is not valid, we set it.
		if (getWeightSum() <= MIN_WEIGHT_SUM) {
			setWeightSum(WEIGHT_SUM);
		}
	}

	private View renderItem(ButtonVM buttonVM) {
		LayoutInflater layoutInflater = getLayoutInflater();
		View view = layoutInflater.inflate(buttonVM.getLayoutId(), null);
		this.addView(view);
		if (!buttonVM.isEnabled()) {
			setIsEnabled(buttonVM, view, false);
		}
		return view;
	}

	private void hookActions(final View view, final ButtonVM buttonVM) {
		boolean isEnabled = buttonVM.isEnabled();
		view.setEnabled(isEnabled);
		View clickableView = view.findViewById(buttonVM.getClickableResId());
		if (clickableView != null) {
			clickableView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					ButtonCommand actionCommand = buttonVM.getButtonCommand();
					if (actionCommand != null) {
						notifyActionCommandListener();
						actionCommand.execute();
					}
				}
			});
		}
	}

	private void notifyActionCommandListener() {
		if (onButtonCommandExecutedListener != null) {
			onButtonCommandExecutedListener.onActionCommandExecuted();
		}
	}

	private void updateWeight() {
		Collection<View> views = items.values();
		int viewCount = views.size();
		float weight = getWeightSum() / viewCount;
		for (View view : views) {
			LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT, weight);
			view.setLayoutParams(layoutParams);
		}

	}

	private void unregisterListeners() {
		unregisterButtonMenuVMListener();
		unregisterButtonVMListener();
	}

	private void registerListeners() {
		registerButtonMenuVMListener();
		registerButtonVMListener();
	}

	private void registerButtonVMListener() {
		for (ButtonVM buttonVM : items.keySet()) {
			buttonVM.registerListener(this);
		}
	}

	private void unregisterButtonVMListener() {
		for (ButtonVM buttonVM : items.keySet()) {
			buttonVM.unregisterListener();
		}
	}

	private void registerButtonMenuVMListener() {
		if (buttonMenuVM != null) {
			buttonMenuVM.registerListener(this);
		}
	}

	private void unregisterButtonMenuVMListener() {
		buttonMenuVM.unregisterListener(this);
	}

	private void setIsEnabled(final ButtonVM buttonVM, final View view, final boolean enabled) {
		int[] viewsIdToChange = buttonVM.getViewIdsToEnableOrDisable();
		for (int viewIdToChange : viewsIdToChange) {
			View viewToChange = view.findViewById(viewIdToChange);
			if (viewToChange != null) {
				viewToChange.setEnabled(enabled);
			}
		}
		view.setEnabled(enabled);
	}

	@TargetApi(VERSION_CODES.CUPCAKE)
	private void renderImageResource(View view, int viewResourceToChange, int imageResourceId) {
		Button button = (Button) view.findViewById(viewResourceToChange);
		if (button != null) {
			button.setCompoundDrawablesWithIntrinsicBounds(0, imageResourceId, 0, 0);
		}
	}

	private void renderSubject(View view, String subject, int subjectResourceId) {
		Button button = (Button) view.findViewById(subjectResourceId);
		if (button != null) {
			button.setText(subject);
		}
	}

	private void renderCounter(CounterButtonVM viewModel, View view, int counterResourceId) {
		TextView counter = (TextView) view.findViewById(counterResourceId);
		int counterValue = viewModel.getCounterValue();
		if (counterValue <= 0) {
			counter.setVisibility(View.INVISIBLE);
		} else {
			counter.setVisibility(View.VISIBLE);
		}
		String newCounter = "" + counterValue;
		counter.setText(newCounter);
	}

	private void renderProgress(View view, int viewResourceId, int progressResourceId,
			boolean show) {
		View image = view.findViewById(viewResourceId);
		View progressBar = view.findViewById(progressResourceId);
		image.setVisibility(show ? View.GONE : View.VISIBLE);
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

    private View getViewForViewModel(final ButtonVM viewModel) {
		View view = null;
		Set<Entry<ButtonVM, View>> entries = items.entrySet();
		for (Entry<ButtonVM, View> entry : entries) {
			if (entry.getKey().equals(viewModel)) {
				view = entry.getValue();
				break;
			}
		}
		return view;
	}

	private void clean() {
		for (View view : items.values()) {
			this.removeView(view);
		}
		items = new HashMap<ButtonVM, View>();
	}

	private void add(final ButtonVM buttonVM) {
		View view = getViewForViewModel(buttonVM);
		if (view != null) {
			addView(view);
		}
	}

	private void remove(final ButtonVM buttonVM) {
		View view = getViewForViewModel(buttonVM);
		if (view != null) {
			removeView(view);
		}
	}
}
