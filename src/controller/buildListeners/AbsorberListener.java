package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.MouseInputListener;

import model.IGizmo;
import model.LeftFlipperGizmo;
import model.Model;
import model.RightFlipperGizmo;
import view.FlipperBoard;

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