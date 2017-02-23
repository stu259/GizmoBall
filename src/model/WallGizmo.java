package model;

import physics.LineSegment;

public class WallGizmo extends Gizmo {

	int x, y, ex, ey;
	
	public WallGizmo(int x, int y, int ex, int ey) {
		super(x, y);
		this.ex = ex;
		this.ey = ey;
	}

	public int getEndX(){
		return ex;
	}
	
	public int getEndY(){
		return ey;
	}
	
	
	
}
