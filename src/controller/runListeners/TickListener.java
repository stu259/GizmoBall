package controller.runListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;

public class TickListener implements ActionListener {

	private IModel m;
	
	public TickListener(IModel m){
		this.m = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		m.tick();
	}

}
