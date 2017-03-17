package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class ConnectListener implements ActionListener, MouseInputListener {

	private IModel model;
	private IDisplay display;
	private BuildListener buildListener;
	private int x;
	private int y;
	private boolean isClicked;

	public ConnectListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;
		isClicked = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Gizmo 1");
	}

	public void mousePressed(MouseEvent e) {
		if (isClicked == false) {
			x = e.getX() / display.getScale();
			y = e.getY() / display.getScale();
			isClicked = true;
			display.changeText("Select Gizmo 2");

		} else if (isClicked == true) {
			model.connectGizmo(x, y, e.getX() / display.getScale(), e.getY() / display.getScale());
			isClicked = false;
		}

	}

	public void mouseReleased(MouseEvent e) {
		if (isClicked == false) {
			display.changeText("Select Gizmo 1");
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

}
