package controller.buildListeners;

import view.IDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationsListener implements ActionListener {

	private IDisplay display;
	
	public OperationsListener(IDisplay display){
		this.display = display;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		display.build();
		//call build operations()
	}

}
