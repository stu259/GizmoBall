package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
			if (!m.load(f)) {
				d.errorPopup("Invalid File Format");
			}else{
				d.load();
			}
		} else {
			d.errorPopup("No File Selected");
		}
	}

}
