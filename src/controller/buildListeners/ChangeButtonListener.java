package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IDisplay;

public class ChangeButtonListener implements ActionListener {

	private IDisplay d;
	
	public ChangeButtonListener(IDisplay display) {
		d=display;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			d.changeBuildButtons(e.getActionCommand());
	}

}