package controller;

import jUnit.InvalidLineException;
import view.IViewError;

public class ErrorMessage {

	IViewError display;
	Boolean test = false;;

	public void error(String message) throws InvalidLineException {
		if (!test) {
			display.errorPopup(message);
		} else {
			throw new InvalidLineException();
		}

	}

	public void giveDisplay(IViewError d) {
		display = d;
	}

	public void setTest(Boolean t) {
		test = t;
	}

}
