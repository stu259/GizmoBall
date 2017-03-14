package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class LeftFlipperGizmo extends Gizmo{
	
	private int currentAngle, maxAngle;
	
	public LeftFlipperGizmo (int x1, int y1) {
		super(x1, y1);
		size = 2; //flipper has predefined size of 2
		coef = 0.95;
		makeLeftFlipper();
		currentAngle = 0;
		maxAngle = 90;
		this.setAngularVel(-1080);
	}

	private void makeLeftFlipper(){
		double radius = (double) size/8;
		int y2 = this.getEndY();
		
		corners.add(new Circle(x + (radius), y + (radius), radius));
		corners.add(new Circle(x + (radius), y2 - (radius), radius));
		lines.add(new LineSegment(x + (2 * radius), y + (radius), x + (2 * radius), y2 - (radius)));
		lines.add(new LineSegment(x, y + (radius), x, y2 - (radius)));
		
	}
	
	@Override
	public Vect getPivotPoint() {
		return corners.get(0).getCenter();
	}
	
	public int getMaxAngle() {
		return maxAngle;
	}

	public void setMaxAngle(int maxAngle) {
		this.maxAngle = maxAngle;
	}
	
	public int getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(int currentAngle) {
		this.currentAngle = currentAngle;
	}
	

	
	@Override
	public String gizmoType() {
		return "leftflipper";
	}
}
