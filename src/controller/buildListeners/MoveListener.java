package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import controller.BuildListener;
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
		boolean isClicked=false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 1 && isClicked ==false) {
			x = e.getX()/display.getScale();
			y = e.getY()/display.getScale();
			isClicked= true;
		} 
		else if (e.getClickCount() == 1 && isClicked == true){
			model.moveGizmo(x, y, e.getX()/display.getScale(),e.getY()/display.getScale());
			isClicked = false;
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getClickCount() > 1) {
			buildListener.setMouseListener(null);
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
