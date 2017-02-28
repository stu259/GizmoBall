package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import controller.BuildListener;
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
		isClicked=false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		if (isClicked ==false) {
			x = e.getX()/display.getScale();
			y = e.getY()/display.getScale();
			isClicked= true;
		} 
		else if (isClicked == true){
			model.addAbsorber(x, y, e.getX()/display.getScale(), e.getY()/display.getScale());
			//checked the values passing are correct.
			isClicked = false;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getClickCount() > 1) {
			buildListener.setMouseListener(null);
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
