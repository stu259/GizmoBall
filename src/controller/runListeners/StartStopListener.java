package controller.runListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.IModel;

public class StartStopListener implements ActionListener {
	
	IModel model;
	Timer timer;
	
	public StartStopListener(IModel model) {
		this.model = model;
		timer = new Timer(25, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == timer){
			model.moveBalls();
		}else if(e.getActionCommand().toLowerCase().equals("start")){
			timer.start();
		}else if(e.getActionCommand().toLowerCase().equals("stop")){
			timer.stop();
		}
		
	}

}
