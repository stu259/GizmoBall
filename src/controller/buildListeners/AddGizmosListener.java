package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class AddGizmosListener implements ActionListener, MouseInputListener {

	private IModel model;
	private IDisplay display;
	private String gizmo;
	private BuildListener buildListener;

	public AddGizmosListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		gizmo = e.getActionCommand();
		display.changeText("Select Gizmo Location");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX() / display.getScale();
		if (gizmo == "rightflipper") {
			x--;
		}
		model.addGizmo(gizmo, x, e.getY() / display.getScale());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		display.changeText("Select Gizmo Location");
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
