package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.buildListeners.FlipperListener;
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
		IGizmo gizmo = new Gizmo (220,220);
		int x =  gizmo.getStartX();
		int y =  gizmo.getStartY();
		gm.addGizmo("leftflipper",gizmo.getKey(),x,y);
		g2.setColor(gizmo.getColor());
		int width = 30;
		g2.fillRect(x, y, 2*width, width/2);
		
		//stuff for the ball
//		int x1 = 250;
//		int y1 = 250;
//		int vx = (int) ((Math.random() * 10.0) + 10.0);
//		int vy = (int) ((Math.random() * 10.0) + 10.0);
//		int radius = 6;
//		Ball ball = new Ball(x1,y1,vx,vy);
//		gm.addBall(ball);
//		
//        vx += (int) ((Math.random() * 10.0) - 5.0);
//        vx = -vx;
//        vy += (int) ((Math.random() * 10.0) - 5.0);
//        vy = -vy;
//		
//        x += vx;
//        if (x <= radius) {
//            x = radius;
//            vx = -vx;
//        }
//        if (x >= 500 - radius) {
//            x = 500 - radius;
//            vx = -vx;
//        }
//
//        y += vy;
//        if (y <= radius) {
//            y = radius;
//            vy = -vy;
//        }
//        if (y >= 500 - radius) {
//            y = 500 - radius;
//            vy = -vy;
//        }
//		g2.setColor(ball.getColor());
//
//		int width1 = 10;
//		g2.fillOval(x1-radius, y1-radius, radius+radius, radius+radius);
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			repaint();
		}
	
}
