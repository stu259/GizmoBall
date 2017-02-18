package model;

import java.awt.Color;

import physics.Vect;

public class AbsorberGizmo extends Gizmo{

	int x, y, ex, ey;
	Vect velo;
	
	public AbsorberGizmo(int x, int y, int ex, int ey) {
		super(x, y);
		this.ex = ex;
		this.ey = ey;
		velo = new Vect(0,-25);
		color = Color.ORANGE;
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
