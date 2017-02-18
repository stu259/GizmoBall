package controller.buildListeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.IGizmo;
import model.LeftFlipperGizmo;
import model.Model;
import model.RightFlipperGizmo;
import view.FlipperPrototype;
import view.IDisplay;

public class FlipperListener implements KeyListener {
private Model m;
private FlipperPrototype b;	
	public FlipperListener(Model m, FlipperPrototype b) {
		this.b=b;
		this.m = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		b.triggered=false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			System.out.println("Key Pressed");
			List <IGizmo> gizmos = m.getGizmos();
			for (IGizmo gizmo:gizmos){
				if (gizmo instanceof LeftFlipperGizmo || gizmo instanceof RightFlipperGizmo){
					//m.moveFlipper(gizmo);
					b.triggered=true;
					b.repaint();
				}
			}
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
