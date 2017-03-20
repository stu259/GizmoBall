package model;

import physics.Circle;

public class CircleGizmo extends Gizmo{
	
	public CircleGizmo (int x1, int y1) {
		super(x1, y1);
		coef = 1.0;
		size = 1; //circle has predefined size of 1
		makeCircle();
	}
	
	private void makeCircle(){
		double radius = this.getRadius();
		corners.add(new Circle((x + radius), (y + radius), radius));
	}
	
	public double getRadius(){
		return (double) size/2;
	}
	
	@Override
	public String gizmoType() {
		return "circle";
	}
	
	@Override
	public void redraw(){
		corners.clear();
		makeCircle();
		int currentRot = this.getRotation();
		this.angle = 0;
		for(int i=0; i < currentRot/90; i++)
			this.rotate();
	}
}
