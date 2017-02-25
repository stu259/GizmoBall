package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.BuildListener;

public class BuildBoard extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;

	public BuildBoard(int w, int h) {
		width = w;
		height = h;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int width = getSize().width;
		int height = getSize().height;
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

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
