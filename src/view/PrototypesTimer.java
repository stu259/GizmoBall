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
		timer = new Timer(50, this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {
		timer.start();
		if (board.initial==false && board.diagonal==true)
		{
			board.diagonal=false;
			board.upright=true;
		}
		if (board.upright==false && board.diagonal==true){
			board.diagonal=false;
			board.initial=true;
		}
		model.moveBalls();
	}
}