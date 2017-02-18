package model;

import java.awt.Color;

public class TriangleGizmo extends Gizmo{
	public TriangleGizmo (int x1, int y1) {
		super(x1, y1);
		coef = 1.0;
		size = 1; //triangle has predefined size of 1
	}
	
	@Override
	public String gizmoType() {
		return "triangle";
	}
}
