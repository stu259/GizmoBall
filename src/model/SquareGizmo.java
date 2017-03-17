package model;

import physics.Circle;
import physics.LineSegment;

public class SquareGizmo extends Gizmo{
	public SquareGizmo (int x1, int y1) {
		super(x1, y1);
		size = 1; //sqaure has predefined size of 1
		coef = 1.0;
		makeSquare();
	}
	
	private void makeSquare(){
		int x2 = this.getEndX();
		int y2 = this.getEndY();
		
		lines.add(new LineSegment(x, y, x, y2));
		lines.add(new LineSegment(x, y2, x2, y2));
		lines.add(new LineSegment(x2, y2, x2, y));
		lines.add(new LineSegment(x2, y, x, y));
		corners.add(new Circle(x, y, 0));
		corners.add(new Circle(x, y2, 0));
		corners.add(new Circle(x2, y, 0));
		corners.add(new Circle(x2, y2, 0));
	}
	
	@Override
	public String gizmoType() {
		return "square";
	}
	
	@Override
	public void redraw(){
		lines.clear();
		corners.clear();
		makeSquare();
	}
}