package model;

import java.io.File;

public interface IModel {
	/*
	 * checks if there is room for that gizmo.
	 * then adds it to the board.
	 */
	public boolean addGizmo(IGizmo gizmo, String key);
	public boolean addGizmo(String gizmo, String key, int x, int y);
	
	/*
	 * adds one ball to board
	 */
	public void addBall(Ball ball);
	
	/*
	 * adds an absorber to the 
	 */
	public boolean addAbsorber(String key, int x, int y, int ex, int ey);
	
	/*
	 * Rotates the given Gizmo by rotating its line segments
	 * and corners in the model
	 */
	public void rotateGizmo(String key);
	
	/*
	 * Connects gizmos to trigger on another
	 */
	public boolean connectGizmo(IGizmo gizmo1, IGizmo gizmo2);
	
	/*
	 * Disconnects connected gizmos
	 */
	public boolean disconnectGizmo(IGizmo gizmo);
	
	/*
	 * Connects key actions to given gizmo
	 */
	public void keyConnectGizmo(IGizmo gizmo, String key);
	
	/*
	 * Disconnects key actions bound to the given gizmo
	 */
	public void removeKey(IGizmo gizmo);
	
	/*
	 * Removes a given gizmo from the board
	 */
    public void deleteGizmo(String key);
    
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
    public boolean moveGizmo(int x, int y, IGizmo gizmo);
    
    /*
     * Calls methods when switched to run mode
     */
	public void runMode();
	
	/*
	 * Moves balls based on their direction and collisions
	 */
	public void moveBalls();
	
	/*
	 * Resumes all balls from paused state
	 */
	public void resume();
	
	/*
	 * Puts all balls into paused state
	 */
	public void pause();
	
	/**
	 * Saves current game state
	 */
	public void save(File f);
	
	/**
	 * Load in Game
	 * @param f 
	 * @return 
	 */
	public boolean load(File f);

	
}