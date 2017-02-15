package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IDisplay;

public class BackListener implements ActionListener {
private IDisplay display;
	
	public BackListener(IDisplay display) {
		this.display=display;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		display.changeBuildButtons("main");
	}
}