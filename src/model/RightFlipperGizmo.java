package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class RightFlipperGizmo extends Gizmo{
	
	private int currentAngle, maxAngle;
	
	public RightFlipperGizmo (int x1, int y1) {
		super(x1, y1);
		size = 2; //flipper has predefined size of 2
		coef = 0.95;
		makeRightFlipper();
		currentAngle = 0;
		maxAngle = 90;
		this.setAngularVel(1080);
	}

	private void makeRightFlipper(){		
		double radius = (double) size/8;
		int y2 = this.getEndY();
		int x2 = this.getEndX();
		
		corners.add(new Circle(x2 - (radius), y + (radius), radius));
		corners.add(new Circle(x2 - (radius), y2 - (radius), radius));
		lines.add(new LineSegment(x2, y + (radius), x2, y2 - (radius)));
		lines.add(new LineSegment(x2 - (2 * radius), y + (radius), x2 - (2 * radius), y2 - (radius)));
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
		return "rightflipper";
	}
	
	@Override
	public void redraw(){
		lines.clear();
		corners.clear();
		makeRightFlipper();
		int currentRot = this.getRotation();
		this.angle = 0;
		for(int i=0; i < currentRot/90; i++)
			this.rotate();
	}
}
