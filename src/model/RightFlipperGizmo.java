package model;

import java.awt.Color;

public class RightFlipperGizmo extends Gizmo{
	
	private int angularVel;
	
	public RightFlipperGizmo (int x1, int y1, Color color) {
		super(x1, y1, color);
		size = 2; //flipper has predefined size of 2
		coef = 0.95;
		angularVel = -1080;
	}
	
	/*
	 * Returns the value of angularVel
	 */
	public int getAngularVel(){
		return angularVel;
	}
}
