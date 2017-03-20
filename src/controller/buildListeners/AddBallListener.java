package controller.buildListeners;

import model.IModel;
import view.IDisplay;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class AddBallListener implements ActionListener, MouseInputListener {

	double x, y;
	private IModel model;
	private IDisplay display;
	private BuildListener buildListener;
	private double[] velocity;

	public AddBallListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Ball Location");
	}

	public void mousePressed(MouseEvent e) {
		x = (double) e.getX() / (double) display.getScale();
		y = (double) e.getY() / (double) display.getScale();
	}

	public void mouseReleased(MouseEvent e) {
		display.changeText("Enter Ball Velocity");
		String[] message = new String[2];
		double[] max = new double[2];
		double[] min = new double[2];
		double[] init = new double[2];
		message[0] = "Velocity x";
		message[1] = "Velocity y";
		max[0] = 10;
		max[1] = 10;
		min[0] = -10;
		min[1] = -10;
		init[0] = 0;
		init[0] = 0;
		velocity = display.inputPopup(message, min, max, init);
		if (velocity != null) {
			if (!model.addBall(x, y, velocity[0], velocity[1])) {
				display.errorPopup("You can't add a ball on top of an existing gizmo. \n"
						+ "Keep in mind flippers take up 4 grid positions.");
			}
		}
		display.changeText("Select Ball Location");
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
