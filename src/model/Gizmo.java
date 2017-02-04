package model;

import java.awt.Color;

public class Gizmo implements IGizmo{

	protected int x, y, angle, size;
	protected double coef;
	protected Color color;
	
	public Gizmo(int x, int y){
		this.x = x;
		this.y= y;
		this.color = Color.BLUE;
		angle = 0;  //default angle facing upward
	}
	
	@Override
	public int getStartX() {
		return x;
	}

	@Override
	public int getStartY() {
		return y;
	}

	@Override
	public int getEndX() {
		return x+size;
	}

	@Override
	public int getEndY() {
		return y+size;
	}
	
	@Override
	public void newPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void rotate() {
		if(angle == 270) angle = 0;
		else angle += 90;
	}
	
	
	@Override
	public void changeColor(Color color) {
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
	public Color getColor() {
		return color;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public Gizmo copy(){
		return this.copy();
	}
}
