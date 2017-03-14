package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.IModel;

public class TimerListener implements ActionListener {
	Timer timer;
	IModel model;

	public TimerListener(IModel m) {
		model=m;
		timer = new Timer(25, this);
	}

	public void startTimer() {
		timer.start();
	}

	public void stopTimer() {
		timer.stop();
	}

	public Timer getTimer() {
		return timer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			model.moveBalls();
		} else if (e.getActionCommand().toLowerCase().equals("start")) {
			timer.start();
		} else if (e.getActionCommand().toLowerCase().equals("stop")) {
			timer.stop();
		}

	}

}
