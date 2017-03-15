package controller.runListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;

public class RunListener implements KeyListener {

	private IModel model;

	public RunListener(IModel m) {
		model = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		Integer s=e.getKeyCode();
		model.keyPressed(s.toString());
		System.out.println(s.toString());
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		Integer s=e.getKeyCode();
//		model.keyPressed(s.toString());
//		System.out.println(s.toString());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
