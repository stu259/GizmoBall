package controller.buildListeners;

import model.IModel;
import view.BuildBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener{

	private IModel model;
	private BuildBoard buildBoard;
	
	public ClearListener(IModel m, BuildBoard bB) {
		model=m;	
		buildBoard=bB;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.clear();
		buildBoard.repaint();
	}


}
