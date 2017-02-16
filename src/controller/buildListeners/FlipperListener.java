package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.IGizmo;
import model.LeftFlipperGizmo;
import model.Model;
import model.RightFlipperGizmo;
import view.FlipperBoard;
import view.IDisplay;

public class FlipperListener implements KeyListener {
private Model m;
	
	public FlipperListener(Model m) {
		this.m = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			System.out.println("Key Pressed");
			List <IGizmo> gizmos = m.getGizmos();
			for (IGizmo gizmo:gizmos){
				if (gizmo instanceof LeftFlipperGizmo){
					//System.out.println(gizmo.getKey());
					//m.rotateGizmo(gizmo.getKey());
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key Released");
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
