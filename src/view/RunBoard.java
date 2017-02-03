package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RunBoard extends JPanel {

	private static final long serialVersionUID = 1L;

	public RunBoard() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setMinimumSize(new Dimension(500, 500));
		setBackground(Color.gray);
	}

}
