package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import controller.BuildListener;
import model.IModel;

public class ConnectListener implements ActionListener, MouseInputListener {

	private IModel model;
	private BuildListener buildListener;
	private int x;
	private int y;

	public ConnectListener(IModel m, BuildListener bL) {
		model = m;
		buildListener = bL;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buildListener.setMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 1) {
			x = e.getX();
			y = e.getY();
		} else {
			//model.connectGizmo(x,y,e.getX(),e.getY()))
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
