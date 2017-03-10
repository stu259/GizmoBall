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
		int[] max = new int[2];
		int[] min = new int[2];
		int[] init = new int[2];
		message[0]="Friction mu";
		message[1]="Friction mu2";
		max[0]=10;
		max[1]=10;
		min[0]=-10;
		min[1]=-10;
		init[0]=(int)model.getFriction()[0];
		init[1]=(int)model.getFriction()[1];
		friction = display.inputPopup(message,min,max,init);
		model.setFriction(friction[0], friction[1]);		
	}

}
