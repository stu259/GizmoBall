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
		model.keyPressed(s.toString() + "pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Integer s=e.getKeyCode();
		model.keyPressed(s.toString() + "released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
