package controller.buildListeners;

import model.IModel;
import view.BuildBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener{

	private IModel model;
	
	public ClearListener(IModel m) {
		model=m;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.clear();
	}


}
