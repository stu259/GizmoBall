package prototypes.flipper;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MagicKeyListener;
import controller.buildListeners.AbsorberListener;
import controller.buildListeners.FlipperListener;
import model.Model;
import prototypes.FlipperBoard;

public class FlipperGui {
	private JFrame frame;
	private JButton button;
	private FlipperBoard board;
	private Model model;

	public FlipperGui(Model m) {
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
		frame.addKeyListener(new MagicKeyListener(new FlipperListener(model, board)));
		//frame.addKeyListener(new MagicKeyListener(new AbsorberListener(model, board)));
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