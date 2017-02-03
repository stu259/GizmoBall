package controller.buildListeners;
import java.util.HashSet;
import java.util.Set;
import controller.BuildListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;

public class DoNothingKeyListener implements KeyListener {
/**
    * <pre>
    *           0..*     0..*
    * DoNothingKeyListener ------------------------> BuildListener
    *           doNothingKeyListener        &gt;       buildListener
    * </pre>
    */
   private Set<BuildListener> buildListener;
   
   public Set<BuildListener> getBuildListener() {
      if (this.buildListener == null) {
         this.buildListener = new HashSet<BuildListener>();
      }
      return this.buildListener;
   }
   

	public DoNothingKeyListener(IModel model) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
