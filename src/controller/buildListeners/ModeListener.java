package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.runListeners.StartStopListener;
import model.IModel;
import view.IDisplay;

public class ModeListener implements ActionListener{
	
	private IDisplay display;
	private String mode;
	private IModel model;

	public ModeListener(IDisplay display, String m, IModel iM) {
		this.display=display;
		mode=m;
		model = iM;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		display.changeMode(mode);
		if(mode == "run")
			model.runMode();
		
		
	}
	

}
