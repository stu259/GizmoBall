package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import view.IDisplay;

public class GravityListener implements ActionListener {
	
	private IModel model;
	private IDisplay display;

	public GravityListener(IModel m,  IDisplay d) {
		model = m;
		display =d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] message = new String[1];
		message[0]="Gravity";
		model.setGravity(display.inputPopup(message)[0]);		
	}


}
