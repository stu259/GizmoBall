package controller.buildListeners;
import java.util.HashSet;
import java.util.Set;
import controller.BuildListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.IModel;

public class AddGizmosListener implements ActionListener, MouseInputListener {
/**
 * <pre>
 *           0..*     0..*
 * AddGizmosListener ------------------------> BuildListener
 *           addGizmosListener        &gt;       buildListener1
 * </pre>
 */
private Set<BuildListener> buildListener1;

public Set<BuildListener> getBuildListener1() {
   if (this.buildListener1 == null) {
this.buildListener1 = new HashSet<BuildListener>();
   }
   return this.buildListener1;
}



	public AddGizmosListener(IModel model, String Gizmo) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
