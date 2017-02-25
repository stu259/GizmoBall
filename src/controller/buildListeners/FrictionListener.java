package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.BuildListener;
import model.IModel;
import view.IDisplay;

public class FrictionListener implements ActionListener {
	
	private IModel model;
	private IDisplay display;
	private int[] friction;

	public FrictionListener(IModel m,  IDisplay d) {
		model = m;
		display =d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] message = new String[2];
		message[0]="Friction mu";
		message[1]="Friction mu2";
		friction = display.inputPopup(message);
		model.setFriction(friction[0], friction[1]);		
	}

}
