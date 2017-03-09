package controller.runListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;
import view.IDisplay;

public class AbsorberListener implements ActionListener,KeyListener {
	private IModel m;
	private RunListener runListener;
	private IDisplay d;
	
		public AbsorberListener(IModel m, RunListener rL, IDisplay d) {
			this.m = m;
			this.d= d; 
		    runListener = rL;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			runListener.setKeyListener(this);	
			d.changeText("Absorber Fired");
		}

		@Override
		public void keyPressed(KeyEvent e) {	
			if (e.getKeyCode() == KeyEvent.VK_UP){
				System.out.println("Key Pressed");
				m.triggerAbsorber();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			runListener.setKeyListener(null);
			System.out.println("Key Released");
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}