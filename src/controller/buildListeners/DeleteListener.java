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

	public DeleteListener(IModel m,  BuildListener bL, IDisplay d) {
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
		model.deleteGizmo(e.getX()/display.getScale(),e.getY()/display.getScale());
	}

	public void mouseReleased(MouseEvent e) {
			buildListener.setMouseListener(null);
			display.changeText("Select Button");
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
