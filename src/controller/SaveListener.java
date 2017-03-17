package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import model.IModel;
import view.IDisplay;

public class SaveListener implements ActionListener {

	private IDisplay d;
	private IModel m;

	public SaveListener(IDisplay display, IModel model) {
		d = display;
		m = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File f = d.saveDialog();
		if (f != null) {
			m.save(f);
		}

	}

}
