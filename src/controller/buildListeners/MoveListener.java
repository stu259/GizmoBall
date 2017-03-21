package controller.buildListeners;

import model.IModel;
import view.IDisplay;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MoveListener implements ActionListener, MouseInputListener {

	private IModel model;
	private BuildListener buildListener;
	private IDisplay display;
	private double x, y;
	private boolean isClicked;

	public MoveListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;
		isClicked = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Gizmo to Move");
		isClicked = false;
	}

	public void mousePressed(MouseEvent e) {
		if (isClicked == false) {
			x = (double) e.getX() / (double) display.getScale();
			y = (double) e.getY() / (double) display.getScale();

			if (model.containsGizmo(x, y)==null) {
				display.errorPopup("No Gizmo Selected");
				display.changeText("Select Gizmo to Move");
				isClicked = false;
			} else {
				isClicked = true;
				display.changeText("Select New Location");
			}
		} else if (isClicked == true) {
			double newX = (double) e.getX() / (double) display.getScale();
			double newY = (double) e.getY() / (double) display.getScale();
			Boolean moved = false;
			System.out.println(x + " " + y + " " + newX + " " + newY);
			if (model.containsGizmo(x, y)=="g") {
				moved = (model.moveGizmo((int) x, (int) y, (int) newX, (int) newY));
			} else if(model.containsGizmo(x, y)=="b") {
				moved = (model.moveBall(x, y, newX, newY));
			}
			if (!moved) {
				display.errorPopup("Move action invalid. First click a ball or gizmo. Second click on a empty space.");
			}

			isClicked = false;
			display.changeText("Select Gizmo to Move");
		}

	}

	public void mouseReleased(MouseEvent e) {
		if (isClicked == false) {
			display.changeText("Select Gizmo to Move");
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
