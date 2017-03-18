package model;

import java.io.File;
import java.util.List;

public interface IModel {
	/*
	 * checks if there is room for that gizmo. then adds it to the board.
	 */
	public boolean addGizmo(String gizmo, int x, int y);

	// /*
	// * adds one ball to board
	// */
	// public void addBall(Ball ball);

	/*
	 * Adds a ball to the board using the coordinates and velocity given
	 */
	public boolean addBall(double x, double y, double velx, double vely);

	/*
	 * Rotates the gizmo at the given x and y coordinates
	 */
	public boolean rotateGizmo(int x, int y);

	/*
	 * Removes a gizmo at the given coordinate
	 */
	public void deleteGizmo(int x, int y);

	/*
	 * Removes all gizmos from the board
	 */
	public void clear();

	/*
	 * Sets friction
	 */
	public void setFriction(double f, double fTwo);

	/*
	 * Sets the gravity
	 */
	public void setGravity(double g);

	/*
	 * Moves gizmo
	 */
	public boolean moveGizmo(int gizmoX, int gizmoY, int newX, int newY);

	/*
	 * Calls methods when switched to run mode
	 */
	public void runMode();

	/**
	 * Saves current game state
	 */
	public void save(File f);

	/**
	 * Load in Game
	 * 
	 * @param f
	 * @return
	 * @throws InvalidGizmoException
	 */
	public void load(File f);

	/**
	 * Creates a Drawable object so that the view can obtain information of the
	 * gizmo to draw without direct coupling/possible manipulation of the Gizmo
	 * object itself in the Model.
	 * 
	 * @param gizmo
	 * @return Drawable Object
	 */
	public List<IDrawableGizmo> drawableGizmos();

	public List<IDrawableBall> drawableBalls();

	/*
	 * Disconnects key actions bound to the given gizmo
	 */
	void removeKeyPress(int x, int y);

	boolean addAbsorber(int x, int y, int ex, int ey);

	void connectGizmo(int x1, int y1, int x2, int y2);

	void disconnectGizmo(int x, int y);

	void keyConnectGizmo(int x, int y, String k);
	
	public double[] getFriction();
	
	public double getGravity();

	public void tick();
	
	public void keyPressed(String k);
	
	public boolean containsGizmo(double x, double y);

	public void deleteBall(double x, double y);

	public Boolean moveBall(double x, double y, double newX, double newY);

	public void resetBalls();
	
}