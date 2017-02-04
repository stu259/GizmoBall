package model;

import java.awt.Color;

public interface IGizmo {
	/*
	 * The initialised position of the gizmo
	 */
	public void newPosition(int x, int y);
	
	/*
	 * Rotates the gizmo 90 degrees clockwise
	 */
	public void rotate();
	
	/*
	 * Returns the starting X coordinate
	 */
	public int getStartX();
	
	/*
	 * Returns the starting Y coordinate
	 */
	public int getStartY();
	
	/*
	 * Returns the ending X coordinate
	 */
	public int getEndX();
	
	/*
	 * Returns the ending Y coordinate
	 */
	public int getEndY();
	
	/*
	 * Returns the size of the gizmo
	 */
	public int getSize();
	
	/*
	 * Changes the colour of the gizmo
	 */
	public void changeColor(Color color);
	
	/*
	 * Returns the color of the gizmo
	 */
	public Color getColor();
	
	/*
	 * Returns the coefficient of reflection
	 */
	public double getCof();
	
	/*
	 * Returns the angle that the gizmo is pointing in
	 */
	public int getRotation();

	/*
	 * Returns a copy of the gizmo for passing between methods 
	 */
	public Gizmo copy();
}
