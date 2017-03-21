package model;

import java.io.File;
import java.util.List;

import jUnit.InvalidLineException;

public interface IModel {
	/*
	 * checks if there is room for that gizmo. then adds it to the board.
	 */
	public boolean addGizmo(String gizmo, int x, int y);

	// /*
	// * adds one ball to board
	// */
	// public void addBall(Ball ball);

	/**
	 * Adds a ball to the board using the coordinates and velocity given
	 */
	public boolean addBall(double x, double y, double velx, double vely);

	/**
	 * Rotates the gizmo at the given x and y coordinates
	 */
	public boolean rotateGizmo(int x, int y);

	/**
	 * Removes a gizmo at the given coordinate
	 */
	public boolean deleteGizmo(int x, int y);

	/**
	 * Removes all gizmos from the board
	 */
	public void clear();

	/**
	 * Sets friction
	 */
	public void setFriction(double f, double fTwo);

	/**
	 * Sets the gravity
	 */
	public void setGravity(double g);

	/**
	 * Moves gizmo
	 */
	public boolean moveGizmo(int gizmoX, int gizmoY, int newX, int newY);

	/**
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
	 * @throws InvalidLineException 
	 * @throws InvalidGizmoException
	 */
	public void load(File f) throws InvalidLineException;

	/**
	 * Creates a Drawable object so that the view can obtain information of the
	 * gizmo to draw without direct coupling/possible manipulation of the Gizmo
	 * object itself in the Model.
	 * 
	 * @param gizmo
	 * @return Drawable Object
	 */
	public List<IDrawableGizmo> drawableGizmos();

	/***
	 * Creates a Drawable object so that the view can obtain information of the
	 * gizmo to draw without direct coupling/possible manipulation of the Ball
	 * object itself in the Model.
	 * @return list of DrawableBall objects
	 */
	public List<IDrawableBall> drawableBalls();

	/***
	 * Adds absorber with starting and ending coordinates
	 * @param x
	 * @param y
	 * @param ex
	 * @param ey
	 * @return true if gizmo can be added, false otherwise
	 */
	public boolean addAbsorber(int x, int y, int ex, int ey);

	/***
	 * connects gizmo(x1,y1) to gizmo(x2,y2)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return if connection is possible or not
	 */
	public boolean connectGizmo(int x1, int y1, int x2, int y2);

	/***
	 * disconnects gizmo(x,y)
	 * @param x
	 * @param y
	 * @return if the disconnection was successful
	 */
	public boolean disconnectGizmo(int x, int y);

	/***
	 * connects a gizmo at (x,y) with the key k
	 * @param x
	 * @param y
	 * @param k
	 * @return if the connection was successful or not
	 */
	public boolean keyConnectGizmo(int x, int y, String k);
	
	/***
	 * @return friction of the game itself
	 */
	public double[] getFriction();
	
	/***
	 * @return gravity of the gizmoball game
	 */
	public double getGravity();

	/***
	 * move to the next frame of gameplay
	 */
	public void tick();
	
	/***
	 * For each gizmo connected to k, trigger them
	 * @param key
	 */
	public void keyPressed(String k);
	
	/***
	 * Checks if (x,y) is a gizmo or a ball
	 * @param x
	 * @param y
	 * @return String "g" if gizmo or "b" if ball at (x,y)
	 */
	public String containsGizmo(double x, double y);

	/***
	 * Deletes a ball at the given coordinates
	 * @param x
	 * @param y
	 * @return whether or not ball deletion was possible/successful
	 */
	public boolean deleteBall(double x, double y);

	/***
	 * moves a ball at (x,y) to (newX, newY)
	 * @param x
	 * @param y
	 * @param newX
	 * @param newY
	 * @return whether or not the movement of the ball is possible
	 */
	public boolean moveBall(double x, double y, double newX, double newY);

	/***
	 * Moves ball to original position
	 * Unpauses ball
	 * Sets ball to unabsorbed
	 */
	public void resetBalls();
	
}