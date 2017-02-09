package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BuildBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BuildBoard() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setSize(500,500);
		setBackground(Color.white);

	}

	public void paintComponent(Graphics g) {
		int width = getSize().width;
		int height = getSize().height ;
		int gridsize = 20;
		super.paintComponent(g);

//		int htOfRow = height / (20);
//		for (int i = 1; i <= 20; i++) {
//			g.drawLine( i * htOfRow,0, i * htOfRow,width);
//		}

		int wdOfRow = width / (20);
		for (int i = 1; i <= 20; i++) {
			g.drawLine(i * wdOfRow,0,i * wdOfRow,height);
		}

	}
}
