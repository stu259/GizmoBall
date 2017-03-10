package controller.buildListeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

import model.IModel;
import view.IDisplay;

public class BindListener implements ActionListener,  MouseInputListener, KeyListener {

	private IModel model;
	private BuildListener buildListener;
	private int x,y;
	private IDisplay display;


	public BindListener(IModel m, BuildListener bl, IDisplay d) {
		model=m;
		buildListener=bl;
		display =d;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		buildListener.setMouseListener(this);	
		display.changeText("Select Gizmo to Bind");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x=e.getX()/display.getScale();
		y=e.getY()/display.getScale();
		buildListener.setKeyListener(this);	
		display.changeText("Press Key to Bind");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		model.keyConnectGizmo(x,y, Character.toString(e.getKeyChar()));	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		display.changeText("Select Gizmo to Bind");
		
	}
	
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		buildListener.setKeyListener(null);
		display.changeText("Select Button");
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
