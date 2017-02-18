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
import model.IDrawableBall;
import model.IDrawableGizmo;
import model.DrawableGizmo;
import model.IGizmo;
import model.LeftFlipperGizmo;
import model.Model;
import model.RightFlipperGizmo;
import model.SquareGizmo;
import model.TriangleGizmo;

public class FlipperPrototype extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height, scale;
	protected Model gm;
	public boolean triggered;

	public FlipperPrototype(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		scale = w / 20;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		List<IDrawableGizmo> gizmoDrawables = gm.drawableGizmo();
		List<IDrawableBall> ballDrawables = gm.drawableBall();
		
		for (IDrawableGizmo gizmo : gizmoDrawables) {
			
			int x1 = gizmo.getStartX() * scale;
			int y1 = gizmo.getStartY() * scale;
			int x2 = gizmo.getEndX() * scale;
			int y2 = gizmo.getEndY() * scale;

			Color color = Color.BLUE;
			
			if (gizmo.getGizmoType().toLowerCase().equals("square")) {
				g2.setColor(color);
				g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo.getGizmoType().toLowerCase().equals("circle")) {
				g2.setColor(color);
				g2.fillOval(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo.getGizmoType().toLowerCase().equals("absorber")) {
				g2.setColor(color);
				g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo.getGizmoType().toLowerCase().equals("triangle")) {
				g2.setColor(color);

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

			} else if (gizmo.getGizmoType().toLowerCase().equals("rightflipper")) {
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
			} else if (gizmo.getGizmoType().toLowerCase().equals("leftflipper")) {
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

		for (IDrawableBall ball : ballDrawables) {
			double radius = ball.getRadius();

			int x1 = (int) ((ball.getX() - radius) * scale);
			int y1 = (int) ((ball.getY() - radius) * scale);
			// int x2 = (int) (ball.getX() + radius) * scale;
			// int y2 = (int) (ball.getY() + radius) * scale;

			int width = (int) ((2 * radius) * scale);

			g2.fillOval(x1, y1, width, width);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
