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
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {
		timer.start();
		model.moveBalls();
	}
}