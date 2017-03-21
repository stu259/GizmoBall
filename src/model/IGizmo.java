package model;

import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public interface IGizmo {
	/**
	 * The initialised position of the gizmo
	 */
	public void newPosition(int x, int y);
	
	/**
	 * Rotates the gizmo 90 degrees clockwise
	 */
	public void rotate();
	
	/**
	 * Returns the starting X coordinate
	 */
	public int getStartX();
	
	/**
	 * Returns the starting Y coordinate
	 */
	public int getStartY();
	
	/**
	 * Returns the ending X coordinate
	 */
	public int getEndX();
	
	/**
	 * Returns the ending Y coordinate
	 */
	public int getEndY();
	
	/**
	 * Returns the size of the gizmo
	 */
	public int getSize();
	
	/**
	 * Returns the coefficient of reflection
	 */
	public double getCoef();
	
	/**
	 * Returns the angle that the gizmo is pointing in
	 */
	public int getRotation();

	/**
	 * Returns a copy of the gizmo for passing between methods 
	 */
	public Gizmo copy();
	
	/**
	 * Sets the key
	 */
	public void setKey(String k);
	
	/**
	 * gets the key
	 */
	public String getKey();
	
	/**
	 * sets trigger to opposite of current logical value
	 */
	public void trigger();
	
	/**
	 * returns if gizmo is triggered or not
	 */
	public boolean triggered();
	
	/**
	 * returns type of gizmo as string
	 */
	public String gizmoType();
	
	/**
	 * clears list of incoming connections
	 */
	public void clearIncomingConnections();
	
	/**
	 * removes a specific incoming connection
	 */
	public void removeIncomingConnection(IGizmo gizmo);

	/**
	 * adds gizmo to incoming connection list
	 */
	public void addIncomingConnection(IGizmo gizmoConnected);

	/**
	 * returns list of incoming connections
	 */
	public List<IGizmo> getIncomingConnections();

	/**
	 * sets the outgoing connection to given gizmo
	 */
	public void addOutgoingConnection(IGizmo connectedGizmo);

	/**
	 * returns list of all outgoing connections
	 */
	public List<IGizmo> getOutgoingConnections();

	/**
	 * clears all outgoing connections
	 */
	void clearOutgoingConnections();

	/**
	 * removes a specific outgoing connection
	 */
	void removeOutgoingConnection(IGizmo gizmo);
	
	/**
	 * sets the key that is assigned to the gizmo (parameter)
	 */
	public void setKeyboardPress(String k);

	/**
	 * returns the key that has been assigned to the gizmo
	 */
	String getKeyboardPress();

	/**
	 * Returns the center of the gizmo as a vector
	 */
	Vect getCenter();

	/**
	 * returns all of the LineSegments associated with gizmo
	 */
	List<LineSegment> getLines();

	/**
	 * returns all of the Circles associated with gizmo
	 */
	List<Circle> getCorners();

	/**
	 * sets whether or not the gizmo is rotating on its pivot
	 */
	void rotateOnPivot(boolean state);

	/**
	 * returns whether or not the gizmo is rotating on pivot
	 */
	boolean isRotatingOnPivot();
	
	/**
	 * returns the pivot point of the gizmo
	 */
	Vect getPivotPoint();
	
	/**
	 * sets the corner(s) of the gizmo to given list
	 */
	void setCorners(List<Circle> corners);

	/**
	 * sets the linesegements of the gizmo to given list
	 */
	void setLines(List<LineSegment> lines);

	/**
	 * returns the angular velocity of the gizmo
	 */
	double getAngularVel();

	/**
	 * set the angular velocity of the gizmo
	 */
	void setAngularVel(double angularVel);

	/**
	 * returns current angle of the gizmo
	 */
	double getCurrentAngle();

	/**
	 * returns whether or not the gizmo has been hit
	 */
	boolean isHit();

	/**
	 * reduces the hit count of the gizmo
	 */
	void cooldownHit();
	
	/**
	 * set the hit number to the given parameter
	 */
	void setHit(int hitNo);
}
