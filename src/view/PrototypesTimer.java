package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Model;

public class PrototypesTimer implements ActionListener {
	private FlipperBoard board;
	private Timer timer;
	private Model model;

	public PrototypesTimer(Model m,FlipperBoard b) {
		board=b;
		model = m;
		timer = new Timer(100, this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {
		timer.start();
		model.moveBalls();
	}
}