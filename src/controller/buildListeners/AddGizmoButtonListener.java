package controller.buildListeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IDisplay;

public class AddGizmoButtonListener implements ActionListener {
	
	private IDisplay display;
	
	public AddGizmoButtonListener(IDisplay display){
		this.display = display;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		display.build();
		//call build gizmos()
	}

}
