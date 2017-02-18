package model;

import java.awt.Color;

public class LeftFlipperGizmo extends Gizmo{
	
	private int angularVel;
	
	public LeftFlipperGizmo (int x1, int y1) {
		super(x1, y1);
		size = 2; //flipper has predefined size of 2
		coef = 0.95;
		angularVel = 1080;
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
