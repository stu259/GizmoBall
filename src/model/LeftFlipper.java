package model;

public class LeftFlipper extends Gizmo{
	
	private int angularVel;
	
	public LeftFlipper (int x1, int y1, String color) {
		super(x1, y1, color);
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
}
