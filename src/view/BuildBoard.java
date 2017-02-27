package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.BuildListener;
import model.IDrawableBall;
import model.IDrawableGizmo;
import model.IModel;
import model.Model;

public class BuildBoard extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int width, height, scale;
	protected Model gm;

	public BuildBoard(int w, int h, Model m) {
		m.addObserver(this);
		width = w;
		height = h;
		scale = w / 20;
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

		List<IDrawableGizmo> gizmoDrawables = gm.drawableGizmo();
		List<IDrawableBall> ballDrawables = gm.drawableBall();

		for (IDrawableGizmo gizmo : gizmoDrawables) {

			int x1 = gizmo.getStartX() * scale;
			int y1 = gizmo.getStartY() * scale;
			int x2 = gizmo.getEndX() * scale;
			int y2 = gizmo.getEndY() * scale;

			Color color = Color.BLUE;

			if (gizmo.getGizmoType().toLowerCase().equals("square")) {
				g2.setColor(color.red);
				g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo.getGizmoType().toLowerCase().equals("circle")) {
				g2.setColor(color.GREEN);
				g2.fillOval(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo.getGizmoType().toLowerCase().equals("absorber")) {
				g2.setColor(Color.PINK);
				g2.fillRect(x1, y1, x2 - x1, y2 - y1);

			} else if (gizmo.getGizmoType().toLowerCase().equals("triangle")) {
				g2.setColor(color.BLUE);
				Polygon p = new Polygon(new int[] { x1, x2, x1 }, new int[] { y2, y1, y1 }, 3);
				AffineTransform transform = new AffineTransform();
				transform.rotate(Math.toRadians(gizmo.getRotation()), p.getBounds().getCenterX(),
						p.getBounds().getCenterY());
				Shape transformed = transform.createTransformedShape(p);
				g2.fill(transformed);

			} else if (gizmo.getGizmoType().toLowerCase().equals("rightflipper")) {
				g2.setColor(color.YELLOW);
				RoundRectangle2D rf = new RoundRectangle2D.Double(x1 + 2 * scale - ((x2 - x1) / 4), y1, (x2 - x1) / 4,
						(y2 - y1), (y2 - y1) / 4, (y2 - y1) / 4);
				g2.rotate(Math.toRadians(gizmo.getRotation()));
				g2.fill(rf);

			} else if (gizmo.getGizmoType().toLowerCase().equals("leftflipper")) {
				g2.setColor(color.YELLOW);
				RoundRectangle2D lf = new RoundRectangle2D.Double(x1, y1, (x2 - x1) / 4, (y2 - y1), (y2 - y1) / 4,
						(y2 - y1) / 4);
				g2.fill(lf);

			}
		}

		for (IDrawableBall ball : ballDrawables) {
			double radius = ball.getRadius();

			g2.setColor(Color.BLUE);

			int x1 = (int) ((ball.getX() - radius) * scale);
			int y1 = (int) ((ball.getY() - radius) * scale);
			int w = (int) ((2 * radius) * scale);

			g2.fillOval(x1, y1, w, w);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

	public int getScale() {
		return scale;
	}
}
