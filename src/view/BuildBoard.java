package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BuildBoard extends JPanel {

	public BuildBoard() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setSize(new Dimension(500, 500));
		setBackground(Color.white);

	}

	public void paintComponent(Graphics g) {
		int width = getSize().width;
		int height = getSize().height ;
		int gridsize = 20;
		super.paintComponent(g);

		int htOfRow = height / (gridsize);
		for (int i = 1; i <= gridsize; i++) {
			g.drawLine(0, i * htOfRow, width, i * htOfRow);
		}

		int wdOfRow = width / (gridsize);
		for (int i = 1; i <= gridsize; i++) {
			g.drawLine(i * wdOfRow, 0, i * wdOfRow, height);
		}

	}
}
