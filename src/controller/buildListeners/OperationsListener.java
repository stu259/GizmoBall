package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IDisplay;

public class OperationsListener implements ActionListener {

	private IDisplay display;
	
	public OperationsListener(IDisplay display) {
		this.display=display;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		display.changeBuildButtons("operation");	
	}
}