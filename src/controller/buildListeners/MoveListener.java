package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class MoveListener implements ActionListener, MouseInputListener {

	private IModel model;
	private BuildListener buildListener;
	private IDisplay display;
	private int x;
	private int y;
	private boolean isClicked;

	public MoveListener(IModel m, BuildListener bL, IDisplay d) {
		model = m;
		buildListener = bL;
		display = d;
		isClicked=false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
		display.changeText("Select Gizmo");
	}

	public void mousePressed(MouseEvent e) {
		if (isClicked ==false) {
			x = e.getX()/display.getScale();
			y = e.getY()/display.getScale();
			isClicked= true;
			display.changeText("Select New Location");
		} 
		else if (isClicked == true){
			model.moveGizmo(x, y, e.getX()/display.getScale(),e.getY()/display.getScale());
			isClicked = false;
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		if (isClicked ==false) {
			buildListener.setMouseListener(null);
			display.changeText("Select Button");
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
