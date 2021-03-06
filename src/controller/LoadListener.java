package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import jUnit.InvalidLineException;
import model.IModel;
import view.IDisplay;

public class LoadListener implements ActionListener {

	private IDisplay d;
	private IModel m;

	public LoadListener(IDisplay display, IModel model) {
		d = display;
		m = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File f = d.loadDialog();
		if (f != null) {
			try {
				m.load(f) ;
			} catch (InvalidLineException e1) {
				//Should never happen
			}
			}
	}

}
