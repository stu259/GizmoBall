package model;

public class Triangle extends Gizmo{
	public Triangle (int x1, int y1, String color) {
		super(x1, y1, color);
		coef = 1.0;
		size = 1; //triangle has predefined size of 1
	}
}
