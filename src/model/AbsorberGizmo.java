package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class AbsorberGizmo extends Gizmo{

	int ex, ey;
	Vect velo;
	
	public AbsorberGizmo(int x, int y, int ex, int ey) {
		super(x, y);
		this.ex = ex;
		this.ey = ey;
		velo = new Vect(0,-50);
		makeAbsorber();
		coef = 1;
	}

	private void makeAbsorber(){
		lines.add(new LineSegment(x, y, ex, y));	//top
		lines.add(new LineSegment(ex, y, ex, ey));	//right
		lines.add(new LineSegment(x, y, x, ey));	//left
		lines.add(new LineSegment(x, ey, ex, ey));	//bottom
		corners.add(new Circle(x, y, 0));
		corners.add(new Circle(x, ey, 0));
		corners.add(new Circle(ex, y, 0));
		corners.add(new Circle(ex, ey, 0));
	}
	
	/**
	 * 
	 * @return exit velocity of the ball if touched
	 */
	public Vect getExitVeloicty(){
		return velo;
	}
	
	@Override
	public int getEndX(){
		return ex;
	}
	
	@Override
	public int getEndY(){
		return ey;
	}
	
	public void newPosition(int x, int y, int ex, int ey){
		this.x = x;
		this.y = y;
		this.ex = ex;
		this.ey = ey;
	}
	
	@Override
	public void rotate(){
		return;
	}
	
	@Override
	public String gizmoType() {
		return "absorber";
	}
}
