package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RunBoard extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;

	public RunBoard(int w, int h) {
		width = w;
		height = h;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.gray);
	}
	
	

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
