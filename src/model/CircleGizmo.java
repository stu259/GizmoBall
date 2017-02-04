package model;
import java.awt.Color;

import physics.Circle;

public class CircleGizmo extends Gizmo{
	
	private Circle circle;
	
	public CircleGizmo (int x1, int y1) {
		super(x1, y1);
		coef = 1.0;
		size = 1; //circle has predefined size of 1
		circle = new Circle(x1, y1, size/2);
	}
	
	public double getRadius(){
		return size/2;
	}
}
