package prototypes.absorber;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.MagicKeyListener;
import controller.runListeners.AbsorberListener;
import controller.runListeners.FlipperListener;
import model.Model;
import prototypes.FlipperBoard;

public class AbsorberGui {
	private JFrame frame;
	private JButton button;
	private FlipperBoard board;
	private Model model;

	public AbsorberGui(Model m) {
		model = m;
		createAndShowGUI();

	}

	public void createAndShowGUI() {

		frame = new JFrame("Prototype Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Board is passed the Model so it can act as Observer
		board = new FlipperBoard(800, 800, model);

		Container cp = frame.getContentPane();
		frame.setFocusable(true);
		frame.addKeyListener(new MagicKeyListener(new AbsorberListener(model, board)));
		frame.add(board, BorderLayout.CENTER);

		frame.pack();
		frame.setLocationRelativeTo(null);
		// frame.setResizable(false);
		frame.setVisible(true);
	}

	public FlipperBoard getBoard() {
		return board;
	}
}
