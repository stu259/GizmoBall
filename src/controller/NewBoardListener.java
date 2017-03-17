package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import view.IDisplay;

public class NewBoardListener implements ActionListener {
	
	private IDisplay display;
	private IModel model;
	private TimerListener timer;

	public NewBoardListener(IDisplay d, IModel m, TimerListener t) {
		display=d;
		model=m;
		timer =t;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.clear();
		timer.stopTimer();
		display.changeMode("build");
	}

}
