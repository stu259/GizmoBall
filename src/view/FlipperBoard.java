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
		//Left Flipper
		IGizmo lf = new Gizmo (50,220);
		int xlf =  lf.getStartX();
		int ylf =  lf.getStartY();
		gm.addGizmo("leftflipper",lf.getKey(),xlf,ylf);
		g2.setColor(lf.getColor());
		int widthlf = 30;
		g2.fillRect(xlf, ylf, 2*widthlf, widthlf/2);

		//Right Flipper
		IGizmo rf = new Gizmo (375,220);
		int xrf =  rf.getStartX();
		int yrf =  rf.getStartY();
		gm.addGizmo("rightflipper",rf.getKey(),xrf,yrf);
		g2.setColor(rf.getColor());
		int widthrf = 30;
		g2.fillRect(xrf, yrf, 2*widthrf, widthrf/2);

		//Square
		IGizmo s = new Gizmo (220,400);
		int xs =  s.getStartX();
		int ys =  s.getStartY();
		gm.addGizmo("square",s.getKey(),xs,ys);
		g2.setColor(s.getColor());
		int widths = 30;
		g2.fillRect(xs, ys, widths, widths);

//		//Triagnle
//		IGizmo t = new Gizmo (100,400);
//		int xt =  t.getStartX();
//		int yt =  t.getStartY();
//		gm.addGizmo("triangle",t.getKey(),xt,yt);
//		g2.setColor(t.getColor());
//		int widtht = 30;
//		g2.fillPolygon();

		//Circle
		IGizmo c = new Gizmo (400,100);
		int xc =  c.getStartX();
		int yc =  c.getStartY();
		gm.addGizmo("circle",c.getKey(),xc,yc);
		g2.setColor(c.getColor());
		int widthc = 30;
		g2.fillOval(xc, yc, widthc, widthc);


		int x1 = 250;
		int y1 = 250;
		int vx = (int) ((Math.random() * 10.0) + 10.0);
		int vy = (int) ((Math.random() * 10.0) + 10.0);
		int radius = 6;
		Ball ball = new Ball(x1,y1,vx,vy);
		gm.addBall(ball);

        vx += (int) ((Math.random() * 10.0) - 5.0);
        vx = -vx;
        vy += (int) ((Math.random() * 10.0) - 5.0);
        vy = -vy;

		g2.setColor(ball.getColor());
		g2.fillOval(x1-radius, y1-radius, radius+radius, radius+radius);
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			repaint();
		}
	
}
