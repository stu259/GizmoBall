package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Model;

public class PrototypesTimer implements ActionListener {

	private Timer timer;
	private Model model;

	public PrototypesTimer(Model m) {
		model = m;
		timer = new Timer(50, this);
//		this.moveBalls();
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {
		timer.start();
		System.out.println("Moving the big hairy balls! c==3");
		model.moveBalls();
	}
//	
//	public void moveBalls(){
//		System.out.println("Moving the big hairy balls! c==3");
//		model.moveBalls();
//	}
}