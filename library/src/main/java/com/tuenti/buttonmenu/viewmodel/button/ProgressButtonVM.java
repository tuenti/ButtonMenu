package com.tuenti.buttonmenu.viewmodel.button;

/**
 * Interface created to represent a ButtonVM that supports a progressBar. This interface have to be
 * implemented by each
 * item added to ButtonMenuVM that use a progressBar inside the button.
 *
 * @author "Iñaki Villar Algaba" <inaki.seri@gmail.com>
 */
public interface ProgressButtonVM extends ButtonVM {

	/**
	 * Change the state starting loading in the view model and notify the ButtonVMListener the
	 * change.
	 */
	public void startProgress();

	/**
	 * Change the state finishing loading in the view model and notify the ButtonVMListener the
	 * change
	 */
	public void finishProgress();

	/**
	 * @return the resource id associated to the progress
	 */
	public int getProgress();

	/**
	 * @return the resource id associated to the image.
	 */
	public int getImage();
}
