package model;

public class Gizmo implements IGizmo{

	protected int x1, y1, angle, size;
	protected double coef;
	protected String color;
	
	public Gizmo(int x1, int y1, String color){
		this.x1 = x1;
		this.y1= y1;
		this.color = color;
		angle = 0;  //default angle facing upward
	}
	
	@Override
	public void newPosition(int x, int y) {
		x1 = x;
		y1 = y;
	}

	@Override
	public void rotate() {
		if(angle == 270) angle = 0;
		else angle += 90;
	}
	
	@Override
	public int[] getStartPosition() {
		int[] pos = {x1, y1};
		return pos;
	}
	
	@Override
	public int[] getEndPosition() {
		int[] pos = {x1+size, y1+size};
		return pos;
	}
	
	@Override
	public void changeColor(String color) {
		this.color = color; 
	}

	@Override
	public double getCof() {
		return coef;
	}

	@Override
	public int getRotation() {
		return angle;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public int getSize() {
		return size;
	}
}
