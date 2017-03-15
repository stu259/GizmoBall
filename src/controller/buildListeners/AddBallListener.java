package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class AddBallListener implements ActionListener, MouseInputListener {

	private IModel model;
	private IDisplay display;
	private BuildListener buildListener;
	double x, y;
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
		int[] max = new int[2];
		int[] min = new int[2];
		int[] init = new int[2];
		message[0] = "Velocity x";
		message[1] = "Velocity y";
		max[0]=10;
		max[1]=10;
		min[0]=-10;
		min[1]=-10;
		init[0]=0;
		init[0]=0;
		velocity = display.inputPopup(message,min,max,init);
		if (velocity != null) {
			if (!model.addBall(x, y, velocity[0], velocity[1]))
			{
				display.errorPopup("You can't add a ball on top of an existing gizmo");
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
