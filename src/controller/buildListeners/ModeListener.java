package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IDisplay;

public class ModeListener implements ActionListener{
	
	private IDisplay display;
	private String mode;

	public ModeListener(IDisplay display, String m) {
		this.display=display;
		mode=m;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		display.changeMode(mode);
	}
	

}
