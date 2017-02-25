package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.event.MouseInputListener;

import controller.buildListeners.AddGizmosListener;
import controller.buildListeners.DoNothingKeyListener;
import model.IModel;
import view.IDisplay;

public class BuildListener implements IListener {

	private IModel model;
	private IDisplay view;
	private MouseInputListener mouseListener;
	private KeyListener keyListener;

	public BuildListener(IModel m, IDisplay d) {
		model = m;
		view = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void setKeyListener(KeyListener k) {
		keyListener = k;
	}

	public void setMouseListener(MouseInputListener m) {
		mouseListener = m;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyListener.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyListener.keyReleased(e);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseListener != null) {
			mouseListener.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (mouseListener != null) {
			mouseListener.mouseReleased(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
