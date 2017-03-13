package model;

import java.io.File;
import java.util.List;

public interface IModel {
	/*
	 * checks if there is room for that gizmo. then adds it to the board.
	 */
	public boolean addGizmo(IGizmo gizmo, String key);

	public boolean addGizmo(String gizmo, String key, int x, int y);

	/*
	 * Generates a random key if only given gizmo type and coordinates
	 */
	public boolean addGizmo(String gizmo, int x, int y);

	// /*
	// * adds one ball to board
	// */
	// public void addBall(Ball ball);

	/*
	 * Adds a ball to the board, given a key and coordinates
	 */
	public boolean addBall(String key, double x, double y, double velx, double vely);

	public boolean addBall(double x, double y, double velx, double vely);

	/*
	 * adds an absorber to the
	 */
	public boolean addAbsorber(String key, int x, int y, int ex, int ey);

	/*
	 * Rotates the gizmo at the given x and y coordinates
	 */
	public boolean rotateGizmo(int x, int y);

	/*
	 * Rotates the given Gizmo by rotating its line segments and corners in the
	 * model
	 */
	public void rotateGizmo(String key);

	/*
	 * Connects gizmos to trigger on another
	 */
	public void connectGizmo(IGizmo gizmo1, IGizmo gizmo2);

	/*
	 * Disconnects connected gizmos
	 */
	public void disconnectGizmo(IGizmo gizmo);

	/*
	 * Connects key actions to given gizmo
	 */
	public void keyConnectGizmo(IGizmo gizmo, String key);

	/*
	 * Removes a given gizmo from the board
	 */
	public void deleteGizmo(String key);

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

	/*
	 * Moves balls based on their direction and collisions
	 */
	public void moveBalls();

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
	public boolean load(File f);

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

	public void triggerAbsorber();

	/*
	 * Disconnects key actions bound to the given gizmo
	 */
	void removeKeyPress(IGizmo gizmo);

	boolean addAbsorber(int x, int y, int ex, int ey);

	void connectGizmo(int x1, int y1, int x2, int y2);

	void disconnectGizmo(int x, int y);

	void keyConnectGizmo(int x, int y, String k);

	public List<IGizmo> getGizmos();
	
	public double[] getFriction();
	
	public double getGravity();

}