package controller.runListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;

public class RunListener implements KeyListener {

	private IModel model;

	public RunListener(IModel m) {
		model = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Integer s=e.getKeyCode();
		model.keyPressed(s.toString() + " down");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Integer s=e.getKeyCode();
		model.keyPressed(s.toString() + " up");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
