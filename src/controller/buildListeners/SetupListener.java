package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IDisplay;

public class SetupListener implements ActionListener {
private IDisplay display;
	
	public SetupListener(IDisplay display) {
		this.display=display;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			display.build().setup();	
	}
}