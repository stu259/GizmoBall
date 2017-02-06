package controller.buildListeners;
import java.util.HashSet;
import java.util.Set;
import controller.BuildListener;
import model.IGizmo;
import model.IModel;
import view.IDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class DeleteListener implements ActionListener {
	
	private IDisplay display;
	private IModel model;
	
	public DeleteListener(IDisplay display,IModel model) {
		this.display=display;
		this.model=model;		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
