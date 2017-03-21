package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class LeftFlipperGizmo extends Gizmo{
	
	private double currentAngle, maxAngle;
	
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
	
	public double getMaxAngle() {
		return maxAngle;
	}

	public void setMaxAngle(int maxAngle) {
		this.maxAngle = maxAngle;
	}
	
	@Override
	public double getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(double currentAngle) {
		this.currentAngle = currentAngle;
	}
	
	@Override
	public String gizmoType() {
		return "leftflipper";
	}
	
	@Override
	public void redraw(){
		lines.clear();
		corners.clear();
		makeLeftFlipper();
		int currentRot = this.getRotation();
		this.angle = 0;
		for(int i=0; i < currentRot/90; i++)
			this.rotate();
	}
}
