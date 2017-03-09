package model;

import java.awt.Color;

import physics.Circle;
import physics.LineSegment;

public class RightFlipperGizmo extends Gizmo{
	
	private int angularVel;
	private boolean rotating;
	
	public RightFlipperGizmo (int x1, int y1) {
		super(x1, y1);
		size = 2; //flipper has predefined size of 2
		coef = 0.95;
		angularVel = -1080;
		rotating = false;
		makeRightFlipper();
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
	
	public void rotateOnPivot(){
		rotating = !rotating;
	}
	
	public boolean rotatingOnPivot(){
		return rotating;
	}
	
	/*
	 * Returns the value of angularVel
	 */
	public int getAngularVel(){
		return angularVel;
	}
	
	@Override
	public String gizmoType() {
		return "rightflipper";
	}
}
