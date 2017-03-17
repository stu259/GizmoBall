package model;

import physics.Circle;
import physics.LineSegment;

public class TriangleGizmo extends Gizmo{
	public TriangleGizmo (int x1, int y1) {
		super(x1, y1);
		coef = 1.0;
		size = 1; //triangle has predefined size of 1
		makeTriangle();
	}
	
	private void makeTriangle(){
		int x2 = this.getEndX();
		int y2 = this.getEndY();
		lines.add(new LineSegment(x, y, x, y2)); // left edge
		lines.add(new LineSegment(x, y2, x2, y)); // hypotenuse
		lines.add(new LineSegment(x2, y, x, y)); // top edge
		corners.add(new Circle(x, y, 0));
		corners.add(new Circle(x2, y, 0));
		corners.add(new Circle(x, y2, 0));
	}
	
	@Override
	public String gizmoType() {
		return "triangle";
	}
	
	@Override
	public void redraw(){
		lines.clear();
		corners.clear();
		makeTriangle();
	}
}
