package controller.buildListeners;

import model.IModel;
import view.IDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {

	private IDisplay display;
	private IModel model;
	
	public ClearListener(IDisplay display,IModel model) {
		this.display=display;
		this.model=model;	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.clear();
		display.build();
	}

}
