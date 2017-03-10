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
		int[] max = new int[1];
		int[] min = new int[1];
		int[] init = new int[1];
		message[0]="Gravity";
		max[0]=50;
		min[0]=-50;
		init[0]=(int)model.getGravity();
		model.setGravity(display.inputPopup(message,min,max,init)[0]);		
	}


}
