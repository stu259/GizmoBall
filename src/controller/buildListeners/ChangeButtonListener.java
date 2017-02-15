package controller.buildListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IDisplay;

public class ChangeButtonListener implements ActionListener {

	private IDisplay d;
	private String b;
	
	public ChangeButtonListener(IDisplay display, String buttons) {
		d=display;
		b=buttons;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			d.changeBuildButtons(b);
	}

}