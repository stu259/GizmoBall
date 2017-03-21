package controller.buildListeners;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class DeleteListener implements ActionListener, MouseInputListener {

	private IModel model;
	private BuildListener buildListener;
	private IDisplay display;
	double x;
	double y;

	public DeleteListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Gizmo to Delete");
	}

	public void mousePressed(MouseEvent e) {
		x = (double) e.getX() / (double) display.getScale();
		y = (double) e.getY() / (double) display.getScale();
		if (model.containsGizmo(x, y) == null) {
			display.errorPopup("No Gizmo Selected");
		} else if (model.containsGizmo(x, y)=="g") {
			model.deleteGizmo(e.getX() / display.getScale(), e.getY() / display.getScale());
		} else {
			model.deleteBall(x, y);
		}

	}

	public void mouseReleased(MouseEvent e) {
		display.changeText("Select Gizmo to Delete");
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
