package controller.runListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
import prototypes.FlipperBoard;

public class AbsorberListener implements KeyListener {
	private Model m;
	private FlipperBoard b;	
		public AbsorberListener(Model m, FlipperBoard b) {
			this.b=b;
			this.m = m;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			b.triggered=false;
			if (e.getKeyCode() == KeyEvent.VK_UP){
				System.out.println("Key Pressed");
				m.triggerAbsorber();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Key Released");
			b.triggered=false;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}