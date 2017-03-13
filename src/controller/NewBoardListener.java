package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import model.Model;
import view.IDisplay;

public class NewBoardListener implements ActionListener {
	
	IDisplay display;
	IModel model;

	public NewBoardListener(IDisplay d, IModel m) {
		display=d;
		model=m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.clear();
		display.changeMode("build");
	}

}
