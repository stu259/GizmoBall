package model;

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
	 * Returns the x and y coordinates of the start position
	 */
	public int[] getStartPosition();
	
	/*
	 * Returns the x and y coordinates of the end position
	 */
	public int[] getEndPosition();
	
	/*
	 * Returns the size of the gizmo
	 */
	public int getSize();
	
	/*
	 * Changes the colour of the gizmo
	 */
	public void changeColor(String color);
	
	/*
	 * Returns the color of the gizmo
	 */
	public String getColor();
	
	/*
	 * Returns the coefficient of reflection
	 */
	public double getCof();
	
	/*
	 * Returns the angle that the gizmo is pointing in
	 */
	public int getRotation();
}
