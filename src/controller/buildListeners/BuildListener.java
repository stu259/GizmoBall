package controller.buildListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class BuildListener implements KeyListener, MouseInputListener  {

	private MouseInputListener mouseListener;
	private KeyListener keyListener;


	public void setKeyListener(KeyListener k) {
		keyListener = k;
	}

	public void setMouseListener(MouseInputListener m) {
		mouseListener = m;
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
		if (mouseListener != null) {
			mouseListener.mouseExited(e);
		}

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
