package model;

import java.awt.Color;
import java.util.List;

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
	
	/*
	 * Sets the key
	 */
	public void setKey(String k);
	
	/*
	 * gets the key
	 */
	public String getKey();
	
	public String gizmoType();
	
	public void clearIncomingConnections();

	public void clearOutgoingConnection();
	
	public void removeIncomingConnection(IGizmo gizmo);

	void addIncomingConnection(IGizmo gizmoConnected);

	List<IGizmo> getIncomingConnections();

	void setOutgoingConnection(IGizmo connectedGizmo);

	IGizmo getOutgoingConnection();

	void setKeyboardPress(String k);

	String getKeyboardPress();
}
