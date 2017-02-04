package model;

import java.awt.Color;

public class SquareGizmo extends Gizmo{
	public SquareGizmo (int x1, int y1, Color color) {
		super(x1, y1, color);
		size = 1; //sqaure has predefined size of 1
		coef = 1.0;
	}
}