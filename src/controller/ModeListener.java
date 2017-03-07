package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import view.IDisplay;

public class ModeListener implements ActionListener{
	
	private IDisplay display;
	private TimerListener timer;

	public ModeListener(IDisplay d) {
		display=d;
	}
	public ModeListener(IDisplay d, TimerListener t) {
		display =d;
		timer=t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		display.changeMode(e.getActionCommand());
		if(e.getActionCommand()=="build"){
			timer.stopTimer();
		}
	}
	

}
