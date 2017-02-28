package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import controller.BuildListener;
import model.IModel;
import view.IDisplay;

public class AddBallListener implements ActionListener, MouseInputListener {

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
		String[] message = new String[2];
		message[0] = "Velocity x";
		message[1] = "Velocity y";
		velocity = display.inputPopup(message);
		buildListener.setMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		model.addBall((double)e.getX() / (double)display.getScale(), (double)e.getY() / (double)display.getScale(), velocity[0], velocity[1]);
	}

	public void mouseReleased(MouseEvent e) {
		buildListener.setMouseListener(null);
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
