package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import model.IDrawableModel;
import model.IModel;

public class TimerListener implements ActionListener, Observer {
	Timer timer;
	IDrawableModel model;

	public TimerListener(IDrawableModel m) {
		model = m;
		model.addObserver(this);

		timer = new Timer(5, this);
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
			model.tick();
		} else if (e.getActionCommand().toLowerCase().equals("start")) {
			timer.start();
		} else if (e.getActionCommand().toLowerCase().equals("stop")) {
			timer.stop();
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		if (timer.isRunning()) {
			timer.restart();
		}

	}

}
