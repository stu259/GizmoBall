package controller.runListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;

public class RunListener implements KeyListener{

	public IModel model;
	
	public RunListener (IModel m){
		model =m;
	}	

	public void keyPressed(KeyEvent e) {
		//model.trigger
	}

	public void keyReleased(KeyEvent e) {
		//model.untrigger
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
