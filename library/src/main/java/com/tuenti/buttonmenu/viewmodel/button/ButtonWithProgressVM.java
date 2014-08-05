package com.tuenti.buttonmenu.viewmodel.button;

/**
 * ButtonWithProgressVM implementation based on SimpleButtonVM. This entity can be used by different components to
 * represent a simple button with a progressBar associated.
 *
 * @author "Iñaki Villar Algaba" <inaki.seri@gmail.com>
 */
public class ButtonWithProgressVM extends SimpleButtonVM implements ProgressButtonVM {

	private int imageViewId;
	private int progressViewId;

	public ButtonWithProgressVM(int layoutId, boolean enabled, int clickableResId,
			int[] enableDisableResIds, ButtonCommand actionCommand, int imageViewId,
			int progressBarId) {
		super(layoutId, enabled, clickableResId, enableDisableResIds, actionCommand);
		this.imageViewId = imageViewId;
		this.progressViewId = progressBarId;
    }

	@Override
	public void startProgress() {
		notifyShowProgress();
	}

	@Override
	public void finishProgress() {
		notifyCloseProgress();
	}

	@Override
	public int getProgress() {
		return progressViewId;
	}

	@Override
	public int getImage() {
		return imageViewId;
	}

	private void notifyShowProgress() {
		ButtonVMListener buttonVMListener = getListener();
		buttonVMListener.onProgressValueChanged(true, this);
	}

	private void notifyCloseProgress() {
		ButtonVMListener buttonVMListener = getListener();
		buttonVMListener.onProgressValueChanged(false, this);
	}
}
