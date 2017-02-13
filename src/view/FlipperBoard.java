package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Ball;
import model.Gizmo;
import model.IGizmo;
import model.Model;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public  class FlipperBoard extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;

	public FlipperBoard(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		IGizmo gizmo = new Gizmo (5,5);
		gm.addGizmo("leftflipper",gizmo.getKey(),10,10);
		g2.setColor(gizmo.getColor());
		int x =  gizmo.getStartX();
		int y =  gizmo.getStartY();
		int width = 30;
		System.out.println(width);
		g2.fillRect(x, y, 2*width, width/2);
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			repaint();
		}
	
}
