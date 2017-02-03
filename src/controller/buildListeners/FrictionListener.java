package controller.buildListeners;
import java.util.HashSet;
import java.util.Set;
import controller.BuildListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrictionListener implements ActionListener {
/**
 * <pre>
 *           0..*     0..*
 * FrictionListener ------------------------> BuildListener
 *           frictionListener        &gt;       buildListener
 * </pre>
 */
private Set<BuildListener> buildListener;

public Set<BuildListener> getBuildListener() {
   if (this.buildListener == null) {
this.buildListener = new HashSet<BuildListener>();
   }
   return this.buildListener;
}

/**
 * <pre>
 *           0..*     0..*
 * FrictionListener ------------------------> BuildListener
 *           frictionListener        &gt;       buildListener1
 * </pre>
 */
private Set<BuildListener> buildListener1;

public Set<BuildListener> getBuildListener1() {
   if (this.buildListener1 == null) {
this.buildListener1 = new HashSet<BuildListener>();
   }
   return this.buildListener1;
}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
