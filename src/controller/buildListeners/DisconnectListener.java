package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class DisconnectListener implements ActionListener, MouseInputListener {

	private IModel model;
	private BuildListener buildListener;
	private IDisplay display;

	public DisconnectListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display =d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Gizmo to disconect");
	}

	public void mousePressed(MouseEvent e) {
		model.disconnectGizmo(e.getX()/display.getScale(),e.getY()/display.getScale());
		model.removeKeyPress(e.getX()/display.getScale(),e.getY()/display.getScale());
	}

	public void mouseReleased(MouseEvent e) {
		display.changeText("Select Gizmo to disconect");
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