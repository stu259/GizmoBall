package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import view.IDisplay;

public class ModeListener implements ActionListener{
	
	private IDisplay display;
	private TimerListener timer;
	private IModel model;

	public ModeListener(IDisplay d, IModel m,TimerListener t) {
		display=d;
		model =m;
		timer=t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		display.changeMode(e.getActionCommand());
		if(e.getActionCommand()=="build"){
			timer.stopTimer();
			model.resetBalls();
		}else if(e.getActionCommand()=="run"){
			model.runMode();
			display.changeText("Press a button");
		}
	}
	

}
