package controller;

import view.IViewError;

public class ErrorMessage {
	
	IViewError display;

	public void error(String message) {
		display.errorPopup(message);		
	}
	
	public void giveDisplay(IViewError d){
		display = d;
	}

}
