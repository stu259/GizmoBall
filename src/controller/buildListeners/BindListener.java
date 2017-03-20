package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class BindListener implements ActionListener, MouseInputListener, KeyListener {

	private IModel model;
	private BuildListener buildListener;
	private int x, y;
	private IDisplay display;

	public BindListener(IModel m, BuildListener bl, IDisplay d) {
		model = m;
		buildListener = bl;
		display = d;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		buildListener.setMouseListener(this);
		display.changeText("Select Gizmo to Bind");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX() / display.getScale();
		y = e.getY() / display.getScale();
		if (!model.containsGizmo(x, y)) {
			display.errorPopup("No Gizmo Selected");
			display.changeText("Select Gizmo to Bind");
		} else {
			buildListener.setKeyListener(this);
			display.changeText("Press Key to Bind");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Integer s = e.getKeyCode();
		model.keyConnectGizmo(x, y, s.toString() + " down");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		display.changeText("Press key to bind");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		Integer s = arg0.getKeyCode();
		model.keyConnectGizmo(x, y, s.toString() + " up");
		buildListener.setKeyListener(new NoActionKeyListener());
		display.changeText("Select Gizmo to Bind");

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
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
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
