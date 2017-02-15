package view;

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

import controller.buildListeners.FlipperListener;
import model.Model;


public class TestingGUI {
	private FlipperListener fl;
	private JFrame frame;
	private FlipperBoard board;
	private Model model;

	public TestingGUI(Model m) {
		model=m;
		createAndShowGUI();
		
	}

	public void createAndShowGUI() {

		frame = new JFrame("Flipper Prototype");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Board is passed the Model so it can act as Observer
		board = new FlipperBoard(800, 1000, model);
		
		Container cp = frame.getContentPane();
		frame.setFocusable(true);
		frame.addKeyListener(new FlipperListener(model));
		frame.add(board, BorderLayout.CENTER);					

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
