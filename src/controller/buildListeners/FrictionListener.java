package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import view.IDisplay;

public class FrictionListener implements ActionListener {
	
	private IModel model;
	private IDisplay display;
	private double[] friction;

	public FrictionListener(IModel m,  IDisplay d) {
		model = m;
		display =d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] message = new String[2];
		double[] max = new double[2];
		double[] min = new double[2];
		double[] init = new double[2];
		message[0]="Friction mu";
		message[1]="Friction mu2";
		max[0]=.1;
		max[1]=.1;
		min[0]=0;
		min[1]=0;
		init[0]=model.getFriction()[0];
		init[1]=model.getFriction()[1];
		friction = display.inputPopup(message,min,max,init);
		model.setFriction(friction[0], friction[1]);		
	}

}
