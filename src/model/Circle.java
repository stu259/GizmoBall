package model;

public class Circle extends Gizmo{
	public Circle (int x1, int y1, String color) {
		super(x1, y1, color);
		coef = 1.0;
		size = 1; //circle has predefined size of 1
	}
}
