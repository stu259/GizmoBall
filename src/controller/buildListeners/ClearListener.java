package controller.buildListeners;
import java.util.HashSet;
import java.util.Set;
import controller.BuildListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {
/**
 * <pre>
 *           0..*     0..*
 * ClearListener ------------------------> BuildListener
 *           clearListener        &gt;       buildListener
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

}
