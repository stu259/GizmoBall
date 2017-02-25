package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import controller.BuildListener;
import model.IModel;
import view.IDisplay;

public class AddGizmosListener implements ActionListener, MouseInputListener {
	
	private IModel model;
	private String gizmo;
	private BuildListener buildListener;

	public AddGizmosListener(IModel m, String g, BuildListener bL) {
		model = m;
		gizmo = g;
		buildListener = bL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);		
	}
	
	public void mousePressed(MouseEvent e) {		
		//model.addGizmo(gizmo, e.getX(), e.getY());
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
