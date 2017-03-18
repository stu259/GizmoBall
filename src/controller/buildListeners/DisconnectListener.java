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
	private int x,y;

	public DisconnectListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Gizmo to disconnect");
	}

	public void mousePressed(MouseEvent e) {
		x= e.getX()/display.getScale();
		y = e.getY()/display.getScale();
		if (!model.containsGizmo(x, y)) {
			display.errorPopup("No Gizmo Selected");
			display.changeText("Select Gizmo to disconnect");
		} else {
			model.disconnectGizmo(x,y);
			model.removeKeyPress(x,y);
		}
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