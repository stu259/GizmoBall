package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.buildListeners.FlipperListener;
import model.AbsorberGizmo;
import model.Ball;
import model.CircleGizmo;
import model.Gizmo;
import model.IGizmo;
import model.LeftFlipperGizmo;
import model.Model;
import model.RightFlipperGizmo;
import model.SquareGizmo;
import model.TriangleGizmo;

public class FlipperBoard extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height, scale;
	protected Model gm;
	public boolean triggered;

	public FlipperBoard(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		scale = w / 20;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.gray);
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		List<IGizmo> gizmos = gm.getGizmos();
		List<Ball> balls = gm.getBalls();

		for (IGizmo gizmo : gizmos) {
			int x1 = gizmo.getStartX() * scale;
			int y1 = gizmo.getStartY() * scale;
			int x2 = gizmo.getEndX() * scale;
			int y2 = gizmo.getEndY() * scale;

			if (gizmo instanceof SquareGizmo) {
				g2.setColor(gizmo.getColor());
				g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo instanceof CircleGizmo) {
				g2.setColor(gizmo.getColor());
				g2.fillOval(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo instanceof AbsorberGizmo) {
				g2.setColor(gizmo.getColor());
				g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo instanceof TriangleGizmo) {
				g2.setColor(gizmo.getColor());

				switch (gizmo.getRotation()) {
				case 0:
					g2.fillPolygon(new int[] { x1, x2, x1 }, new int[] { y2, y1, y1 }, 3);
					break;
				case 90:
					g2.fillPolygon(new int[] { x2, x2, x1 }, new int[] { y2, y1, y1 }, 3);
					break;
				case 180:
					g2.fillPolygon(new int[] { x2, x1, x2 }, new int[] { y1, y2, y2 }, 3);
					break;
				case 270:
					g2.fillPolygon(new int[] { x1, x1, x2 }, new int[] { y1, y2, y2 }, 3);
					break;
				}

			} else if (gizmo instanceof RightFlipperGizmo) {
				RoundRectangle2D rf = new RoundRectangle2D.Double(x1 + 2 * scale - ((x2 - x1) / 4), y1, (x2 - x1) / 4,
						(y2 - y1), (y2 - y1) / 4, (y2 - y1) / 4);
				if (!triggered) {
					g2.fill(rf);

				} else {
					AffineTransform transform = new AffineTransform();
					transform.rotate(Math.toRadians(90), rf.getX() + 6, rf.getY() + 6);
					Shape transformed = transform.createTransformedShape(rf);
					g2.fill(transformed);
				}
			} else if (gizmo instanceof LeftFlipperGizmo) {
				RoundRectangle2D rf = new RoundRectangle2D.Double(x1, y1, (x2 - x1) / 4, (y2 - y1), (y2 - y1) / 4,
						(y2 - y1) / 4);
				if (!triggered) {
					g2.fill(rf);

				} else {

					AffineTransform transform = new AffineTransform();
					transform.rotate(Math.toRadians(-90), rf.getX() + 6, rf.getY() + 6);
					Shape transformed = transform.createTransformedShape(rf);
					g2.fill(transformed);
				}
			}
		}

		for (Ball ball : balls) {
			double radius = ball.getRadius();

			int x1 = (int) ((ball.getX() - radius) * scale);
			int y1 = (int) ((ball.getY() - radius) * scale);
			// int x2 = (int) (ball.getX() + radius) * scale;
			// int y2 = (int) (ball.getY() + radius) * scale;

			int width = (int) ((2 * radius) * scale);

			g2.fillOval(x1, y1, width, width);
		}
	}

	// int x1 = 250;
	// int y1 = 250;
	// int vx = (int) ((Math.random() * 10.0) + 10.0);
	// int vy = (int) ((Math.random() * 10.0) + 10.0);
	// int radius = 6;
	// Ball ball = new Ball(x1,y1,vx,vy);
	// gm.addBall(ball);
	//
	// vx += (int) ((Math.random() * 10.0) - 5.0);
	// vx = -vx;
	// vy += (int) ((Math.random() * 10.0) - 5.0);
	// vy = -vy;
	//
	// g2.setColor(ball.getColor());
	// g2.fillOval(x1-radius, y1-radius, radius+radius, radius+radius);

	// //Circle
	// IGizmo c = new Gizmo (400,100);
	// int xc = c.getStartX();
	// int yc = c.getStartY();
	// gm.addGizmo("circle",c.getKey(),xc,yc);
	// g2.setColor(c.getColor());
	// int widthc = 30;
	// g2.fillOval(xc, yc, widthc, widthc);

	// //Square
	// IGizmo s = new Gizmo (220,400);
	// int xs = s.getStartX();
	// int ys = s.getStartY();
	// gm.addGizmo("square",s.getKey(),xs,ys);
	// g2.setColor(s.getColor());
	// int widths = 30;
	// g2.fillRect(xs, ys, widths, widths);

	// //Left Flipper
	// IGizmo lf = new Gizmo (50,220);
	// int xlf = lf.getStartX();
	// int ylf = lf.getStartY();
	// gm.addGizmo("leftflipper",lf.getKey(),xlf,ylf);
	// g2.setColor(lf.getColor());
	// int widthlf = 30;
	// g2.fillRect(xlf, ylf, 2*widthlf, widthlf/2);
	//
	// //Right Flipper
	// IGizmo rf = new Gizmo (375,220);
	// int xrf = rf.getStartX();
	// int yrf = rf.getStartY();
	// gm.addGizmo("rightflipper",rf.getKey(),xrf,yrf);
	// g2.setColor(rf.getColor());
	// int widthrf = 30;
	// g2.fillRect(xrf, yrf, 2*widthrf, widthrf/2);
	//

	//
	//// //Triagnle
	//// IGizmo t = new Gizmo (100,400);
	//// int xt = t.getStartX();
	//// int yt = t.getStartY();
	//// gm.addGizmo("triangle",t.getKey(),xt,yt);
	//// g2.setColor(t.getColor());
	//// int widtht = 30;
	//// g2.fillPolygon();
	//

	//
	//

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
