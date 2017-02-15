package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
import view.FlipperBoard;
import view.IDisplay;

public class FlipperListener implements KeyListener {
private Model m;
private char ch= 'd';
	
	public FlipperListener(Model m) {
		this.m = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		ch=e.getKeyChar();
		System.out.println(ch);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
