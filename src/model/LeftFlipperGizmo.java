package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class LeftFlipperGizmo extends Gizmo{
	
	private int angularVel;
	private boolean rotating;
	
	public LeftFlipperGizmo (int x1, int y1) {
		super(x1, y1);
		size = 2; //flipper has predefined size of 2
		coef = 0.95;
		angularVel = 1080;
		rotating = false;
		makeLeftFlipper();
	}
	
	private void makeLeftFlipper(){
		double radius = (double) size/8;
		int y2 = this.getEndY();
		
		corners.add(new Circle(x + (radius), y + (radius), radius));
		corners.add(new Circle(x + (radius), y2 - (radius), radius));
		lines.add(new LineSegment(x + (2 * radius), y + (radius), x + (2 * radius), y2 - (radius)));
		lines.add(new LineSegment(x, y + (radius), x, y2 - (radius)));
		
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
		return "leftflipper";
	}
}
