package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class AddAbsorberListener implements ActionListener, MouseInputListener {

	private IModel model;
	private BuildListener buildListener;
	private IDisplay display;
	private int x;
	private int y;
	private boolean isClicked;

	public AddAbsorberListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;
		isClicked = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Absorber Top Left Location");
	}

	public void mousePressed(MouseEvent e) {
		if (isClicked == false) {
			x = e.getX() / display.getScale();
			y = e.getY() / display.getScale();
			isClicked = true;
			display.changeText("Select Absorber Bottom Right Location");
		} else if (isClicked == true) {
			System.out.println(e.getY() / display.getScale());
			if (!model.addAbsorber(x, y, e.getX() / display.getScale() + 1, e.getY() / display.getScale() + 1)){
				display.errorPopup("Absorber cannot overlap existing gizmos");
			}
			// checked the values passing are correct.
			isClicked = false;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (isClicked == true) {
			display.changeText("Select Absorber Top Left Location");
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}
}
