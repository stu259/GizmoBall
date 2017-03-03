package controller.runListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.TimerListener;
import model.IModel;

public class StartStopListener implements ActionListener {
	
	IModel model;
	TimerListener timer;
	
	public StartStopListener(IModel model, TimerListener t) {
		this.model = model;
		timer = t;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getSource() == timer.getTimer()){
			model.moveBalls();
		}else if(e.getActionCommand().toLowerCase().equals("start")){
			timer.startTimer();

		}else if(e.getActionCommand().toLowerCase().equals("stop")){
			timer.stopTimer();
		}
		
	}

}
