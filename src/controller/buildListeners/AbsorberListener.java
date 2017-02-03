package controller.buildListeners;
import java.util.HashSet;
import java.util.Set;
import controller.BuildListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class AbsorberListener implements ActionListener, MouseInputListener {
/**
 * <pre>
 *           0..*     0..*
 * AbsorberListener ------------------------> BuildListener
 *           absorberListener        &gt;       buildListener
 * </pre>
 */
private Set<BuildListener> buildListener;

public Set<BuildListener> getBuildListener() {
   if (this.buildListener == null) {
this.buildListener = new HashSet<BuildListener>();
   }
   return this.buildListener;
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
