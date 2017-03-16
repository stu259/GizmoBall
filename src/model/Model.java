package model;

import physics.*;
import physics.Geometry.VectPair;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Model extends Observable implements IModel, IdrawModel {
	private Map<String, IGizmo> gizmos;

	private double gravity = 25;
	private double frictionMU = 0.025;
	private double frictionMUTwo = 0.025;
	private double time = 0.025;

	// connections for triggering both redrawing of lines and
	private Map<LineSegment, IGizmo> linesToAbsorber;
	private Map<Circle, IGizmo> circlesToAbsorber;
	private Map<Circle, IGizmo> circlesToGizmos;
	private Map<LineSegment, IGizmo> linesToGizmos;
	private Map<String, Ball> balls;
	private Map<String, List<IGizmo>> keylistToGizmos;

	private List<LineSegment> walls;
	
	private int boardSize;

	public Model() {
		boardSize = 20;

		gizmos = new HashMap<String, IGizmo>();
		balls = new HashMap<String, Ball>();
		
		linesToAbsorber = new HashMap<LineSegment, IGizmo>();
		circlesToAbsorber = new HashMap<Circle, IGizmo>();
		linesToGizmos = new HashMap<LineSegment, IGizmo>();
		circlesToGizmos = new HashMap<Circle, IGizmo>();
		keylistToGizmos = new HashMap<String, List<IGizmo>>();
		
		walls = new ArrayList<LineSegment>();
		makeWalls(boardSize);
	}

	/**
	 * 
	 * 
	 * RUNNING CODE
	 * 
	 * 
	 */

	/*
	 * Called when all data from build mode is to be translated into the run
	 * mode Creates all line segments and circles to be processed for the
	 * physics engine
	 */
	@Override
	public void runMode() {

		// clear dataStructures
		linesToGizmos.clear();
		circlesToGizmos.clear();
		linesToAbsorber.clear();
		circlesToAbsorber.clear();

		for (String key : gizmos.keySet()) {
			IGizmo gizmo = gizmos.get(key);
			String gizmoType = gizmo.gizmoType().toLowerCase();
			
			// add method to draw gizmo
			if (gizmoType.equals("absorber"))
				makeAbsorberGizmo(gizmo.copy());
			else
				drawGizmos(gizmo);
		}
	}

	private void drawGizmos(IGizmo gizmo){
		List<LineSegment> lines = gizmo.getLines();
		List<Circle> corners = gizmo.getCorners();
		
		for(LineSegment line : lines){ // create connections for the lines -> absorber
			linesToGizmos.put(line, gizmo);
		}
		for(Circle corner : corners){  // create connections for the circles -> absorber
			System.out.println("Adding a corner");
			circlesToGizmos.put(corner, gizmo);
		}
	}
	
	private void makeAbsorberGizmo(IGizmo gizmo) {
		List<LineSegment> gizLines = gizmo.getLines();
		List<Circle> gizCorners = gizmo.getCorners();
		
		for(LineSegment line : gizLines){ // create connections for the lines -> absorber
			linesToAbsorber.put(line, gizmo);
		}
		for(Circle corner : gizCorners)  // create connections for the circles -> absorber
			circlesToAbsorber.put(corner, gizmo);
	}

	private void makeWalls(int boardSize) {
		System.out.println("Drawing walls!");
		LineSegment topWall = new LineSegment(0, 0, boardSize, 0);
		LineSegment rightWall = new LineSegment(boardSize, 0, boardSize, boardSize);
		LineSegment bottomWall = new LineSegment(0, boardSize, boardSize, boardSize);
		LineSegment leftWall = new LineSegment(0, 0, 0, boardSize);
		walls.add(topWall);
		walls.add(rightWall);
		walls.add(bottomWall);
		walls.add(leftWall);
	}

	private CollisionInfo timeUntilCollision() {
		double lowestColTime = Double.MAX_VALUE;
		Ball collidingBall = null;
		Ball collidingBall2 = null; //this is hard code method, change later plz tyvm xo
		Vect updatedVel = new Vect(0, 0);
		Vect updatedVel2 = null; //only used for ball-to-ball collisions
		AbsorberGizmo absorber = null;
		LineSegment lineHit = null;
		Circle circleHit = null;
		

		for (Ball ball : balls.values()) {
			
			if(ball.paused())
				continue;
			
			Circle circ = ball.getCircle();
			Vect vel = ball.getVelocity();
			double nextTime = 0;
			for (LineSegment line : walls) {
				nextTime = Geometry.timeUntilWallCollision(line, circ, vel);
				if (nextTime < lowestColTime) {
					lowestColTime = nextTime;
					collidingBall = ball;
					updatedVel = Geometry.reflectWall(line, vel, 1);
					absorber = null;
				}
			}
			for (LineSegment line : linesToGizmos.keySet()) {
				nextTime = Geometry.timeUntilWallCollision(line, circ, vel);
				if (nextTime < lowestColTime) {
					lineHit = line;
					circleHit = null;
					lowestColTime = nextTime;
					collidingBall = ball;
					if(linesToGizmos.get(line).isRotatingOnPivot()){
						IGizmo gizmo = linesToGizmos.get(line);
						updatedVel = Geometry.reflectRotatingWall(line, gizmo.getPivotPoint(), gizmo.getAngularVel(), circ, vel, gizmo.getCof()); //DOUBLE CHECK THIS COULD CAUSE ISSUES
					}else
						updatedVel = Geometry.reflectWall(line, vel, linesToGizmos.get(line).getCof());
					absorber = null;
				}
			}
			for (Circle circle : circlesToGizmos.keySet()) {
				nextTime = Geometry.timeUntilCircleCollision(circle, circ, vel);
				if (nextTime < lowestColTime) {
					lineHit = null;
					circleHit = circle;
					lowestColTime = nextTime;
					collidingBall = ball;
					if(circlesToGizmos.get(circle).isRotatingOnPivot()){
						IGizmo gizmo = circlesToGizmos.get(circle);
						updatedVel = Geometry.reflectRotatingCircle(circle, gizmo.getPivotPoint(), gizmo.getAngularVel(), circ, vel, gizmo.getCof());
					}else
						updatedVel = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), vel,
								circlesToGizmos.get(circle).getCof());
					absorber = null;
				}
			}
			for (LineSegment line : linesToAbsorber.keySet()) {
				nextTime = Geometry.timeUntilWallCollision(line, circ, vel);
				if (nextTime < lowestColTime) {
					lineHit = null;
					circleHit = null;
					lowestColTime = nextTime;
					collidingBall = ball;
					absorber = (AbsorberGizmo) linesToAbsorber.get(line);
					updatedVel = absorber.getExitVeloicty();
				}
			}
			for (Circle circle : circlesToAbsorber.keySet()) {
				nextTime = Geometry.timeUntilCircleCollision(circle, circ, vel);
				if (nextTime < lowestColTime) {
					lineHit = null;
					circleHit = null;
					lowestColTime = nextTime;
					collidingBall = ball;
					absorber = (AbsorberGizmo) circlesToAbsorber.get(circle);
					updatedVel = absorber.getExitVeloicty();
				}
			}
			for(Ball ball2 : balls.values()){
				if(ball2.equals(ball))
					continue;
				
				nextTime = Geometry.timeUntilBallBallCollision(ball.getCircle(), ball.getVelocity(), ball2.getCircle(), ball2.getVelocity());
				if (nextTime < lowestColTime) {
					lineHit = null;
					circleHit = null;
					VectPair vectPair = null;
					lowestColTime = nextTime;
					collidingBall = ball;
					collidingBall2 = ball2;
					vectPair = Geometry.reflectBalls(ball.getCenter(), ball.getMass(), ball.getVelocity(),
										  ball2.getCenter(), ball2.getMass(), ball2.getVelocity());
					absorber = null;
					updatedVel = vectPair.v1;
					updatedVel2 = vectPair.v2;
				}
			}

		}

		if (absorber != null && lowestColTime < time) {
			collidingBall.setAbsorbed(!collidingBall.isAbsorbed());
			if (lowestColTime == 0.0) {
				lowestColTime = 0.02;
				collidingBall.setAbsorbed(false);
			}
		}

		if(lineHit != null)
			if(linesToGizmos.get(lineHit).getOutgoingConnection() != null)
				linesToGizmos.get(lineHit).getOutgoingConnection().trigger();
		else if(circleHit != null)
			if(circlesToGizmos.get(circleHit).getOutgoingConnection() != null)
				circlesToGizmos.get(circleHit).getOutgoingConnection().trigger();
		
		return (new CollisionInfo(lowestColTime, collidingBall, updatedVel, collidingBall2, updatedVel2, absorber));

	}

	private void triggerAbsorber() {
		for(IGizmo gizmo : gizmos.values()){
			if(!gizmo.gizmoType().equals("absorber")) return;
			if(gizmo.triggered()){
				AbsorberGizmo abs = (AbsorberGizmo) gizmo;
				for (Ball ball : balls.values()) {
					if (ball.isAbsorbed()) {
						ball.resume();
						ball.setVelocity(abs.getExitVeloicty());
					}
				}
				abs.trigger();//untrigger
			}
		}
	}
	
	@Override
	public void tick(){
		//move balls
		moveBalls();
		triggerFlippers();
		triggerAbsorber();
	}
	
	private void triggerFlippers(){
		for(IGizmo gizmo: gizmos.values()){
			if(gizmo.gizmoType().toLowerCase().equals("leftflipper")){
				LeftFlipperGizmo flipper = (LeftFlipperGizmo) gizmo;
				if(flipper.triggered()){
					//check if already on maxAngle
					//	if not set rotateOnPivot to true
					//get lines and circles, rotate them via pivot
					
					if(flipper.getCurrentAngle() != flipper.getMaxAngle()){ 
						flipper.rotateOnPivot(true);
						
						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();
						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();
						
						int angle = (int) (-1 * flipper.getAngularVel() / (1/time));
						
						if(angle + flipper.getCurrentAngle() > flipper.getMaxAngle())
							angle = flipper.getMaxAngle() - flipper.getCurrentAngle();
						
						Angle newAngle = new Angle(360 - angle);
						
						for(LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for(Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));
						
						flipper.setCurrentAngle(flipper.getCurrentAngle() + angle);
					}else{
						flipper.rotateOnPivot(false);
						continue;
					}
					
					
				}else{//NOT TRIGGERED move back down
					if(flipper.getCurrentAngle() != 0){
						flipper.rotateOnPivot(true);
						
						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();
						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();
						
						int angle = (int) (-1 * flipper.getAngularVel() / (1/time));
						
						if(flipper.getCurrentAngle() - angle < 0)
							angle = flipper.getCurrentAngle();
						
						Angle newAngle = new Angle(angle);
						
						for(LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for(Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));
						
						flipper.setCurrentAngle(flipper.getCurrentAngle() - angle);
					}else{
						flipper.rotateOnPivot(false);
						continue;
					}
				}
				
				
			}else if(gizmo.gizmoType().toLowerCase().equals("rightflipper")){
				RightFlipperGizmo flipper = (RightFlipperGizmo) gizmo;
				
				if(flipper.triggered()){
					//check if already on maxAngle
					//	if not set rotateOnPivot to true
					//get lines and circles, rotate them via pivot
					
					if(flipper.getCurrentAngle() != flipper.getMaxAngle()){ 
						flipper.rotateOnPivot(true);
						
						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();
						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();
						
						int angle = (int) (flipper.getAngularVel() / (1/time));
						
						if(angle + flipper.getCurrentAngle() > flipper.getMaxAngle())
							angle = flipper.getMaxAngle() - flipper.getCurrentAngle();
						
						Angle newAngle = new Angle(angle);
						
						for(LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for(Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));
						
						flipper.setCurrentAngle(flipper.getCurrentAngle() + angle);
					}else{
						flipper.rotateOnPivot(false);
						continue;
					}
					
					
				}else{//NOT TRIGGERED move back down
					if(flipper.getCurrentAngle() != 0){
						flipper.rotateOnPivot(true);
						
						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();
						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();
						
						int angle = (int) (flipper.getAngularVel() / (1/time));
						
						if(flipper.getCurrentAngle() - angle < 0)
							angle = flipper.getCurrentAngle();
						
						Angle newAngle = new Angle(360 - angle);
						
						for(LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for(Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));
						
						flipper.setCurrentAngle(flipper.getCurrentAngle() - angle);
					}else{
						flipper.rotateOnPivot(false);
						continue;
					}
				}
			}
		}
	}
	
	@Override
	public void moveBalls() {
		CollisionInfo colInfo = timeUntilCollision();
		double colTime = colInfo.getColTime();
		Ball colBall = colInfo.getCollidingBall();
		Ball colBall2 = null;
		boolean colbal2check = false;
		
		if (colTime < time) { // collision detected
			String key = colBall.getKey();
			String key2 = null;
			balls.remove(key);
			
			if(colInfo.getCollidingBall2() != null){
				colBall2 = colInfo.getCollidingBall2();
				key2 = colBall2.getKey();
				balls.remove(key2);
				colbal2check = true;
				
				colBall = calculateBallMove(colBall, colTime);
				colBall.setVelocity(colInfo.getUpdatedVel());
				colBall2 = calculateBallMove(colBall2, colTime);
				colBall2.setVelocity(colInfo.getUpdatedVel2());
				
				applyFriction(colBall, colTime);
				applyGravity(colBall, colTime);
				applyFriction(colBall2, colTime);
				applyGravity(colBall2, colTime);
			} else if (colInfo.getAbs() != null) {
				if (colBall.isAbsorbed()) {
					AbsorberGizmo absorber = colInfo.getAbs();
					colBall.setX(absorber.getEndX() - colBall.getRadius());
					colBall.setY(absorber.getEndY() - colBall.getRadius());
					colBall.pause();
					colBall.setVelocity(colInfo.getUpdatedVel());
				} else {
					colBall = calculateBallMove(colBall, colTime);
					applyFriction(colBall, colTime);
					applyGravity(colBall, colTime);
				}
			} else {
				colBall = calculateBallMove(colBall, colTime);
				colBall.setVelocity(colInfo.getUpdatedVel());
				applyFriction(colBall, colTime);
				applyGravity(colBall, colTime);
			}
			
			for (Ball ball : balls.values()) {
				ball = calculateBallMove(ball, colTime);
				applyFriction(ball, colTime);
				applyGravity(ball, colTime);
			}
			balls.put(key, colBall);
			if(colbal2check)
				balls.put(key2, colBall2);
		} else {
			for (Ball ball : balls.values()) {
				if(!ball.paused() && !ball.isAbsorbed()){
					ball = calculateBallMove(ball, time);
					applyFriction(ball, time);
					applyGravity(ball, time);
				}
			}
		}

		this.setChanged();
		this.notifyObservers(); // update board both in gui and model
	}

	private Ball calculateBallMove(Ball ball, double newTime) {
		double vectX = ball.getVelocity().x();
		double vectY = ball.getVelocity().y();
		double x = ball.getX() + (vectX * newTime);
		double y = ball.getY() + (vectY * newTime);
		ball.setX(x);
		ball.setY(y);

		return ball;
	}

	private class CollisionInfo {
		double colTime;
		Ball collidingBall, collidingBall2;
		Vect updatedVel, updatedVel2;
		AbsorberGizmo abs;

		public CollisionInfo(double t, Ball b, Vect v, Ball b2, Vect v2, AbsorberGizmo a) {
			colTime = t;
			collidingBall = b;
			collidingBall2 = b2;
			updatedVel = v;
			updatedVel2 = v2;
			abs = a;
		}

		public double getColTime() {
			return colTime;
		}

		public Ball getCollidingBall() {
			return collidingBall;
		}

		public Ball getCollidingBall2(){
			return collidingBall2;
		}
		
		public Vect getUpdatedVel() {
			return updatedVel;
		}

		public Vect getUpdatedVel2(){
			return updatedVel2;
		}
		
		public AbsorberGizmo getAbs() {
			return abs;
		}

	}

	public void keyPressed(String key){
		List<IGizmo> keyGiz = keylistToGizmos.get(key);
		for (int i=0; i<keylistToGizmos.size();i++){
			System.out.println(keylistToGizmos.keySet());
		}
		if(keyGiz==null)
			return;
		
		for(IGizmo gizmo : keyGiz){
			System.out.println(gizmo);
			gizmo.trigger();
		}
	}
	
	/**
	 * 
	 * 
	 * BUILDING CODE BEYOND THIS POINT
	 * 
	 * 
	 */

	public double[] getFriction(){
		double[] friction= new double[2];
		friction[0]=frictionMU;
		friction[1]=frictionMUTwo;
		return 	friction;
	}
	
	public double getGravity(){
		return 	gravity;
	}
	
	private boolean validatePosition(double sx, double sy, double ex, double ey, IGizmo giz){
		if (sx < 0 || ex > boardSize || sy < 0 || ey > boardSize)
			return false;

		// check if given coordinates overlaps with any other gizmo position
		for (IGizmo gizmo : gizmos.values()) {
			if(!gizmo.equals(giz)){
				if (sx < gizmo.getEndX() && ex > gizmo.getStartX() && sy < gizmo.getEndY() && ey > gizmo.getStartY())
					return false;
			}
		}
		return true;
	}
	
	// check if object can be added (check outside board, position, size, then
	// scale)
	// Params: startx, starty, endx, endy
	private boolean validatePosition(double sx, double sy, double ex, double ey) {
		// check if gizmo is somehow placed outside of board (shouldnt happen)
		if (sx < 0 || ex > boardSize || sy < 0 || ey > boardSize)
			return false;

		// check if given coordinates overlaps with any other gizmo position
		for (IGizmo gizmo : gizmos.values()) {
			if (sx < gizmo.getEndX() && ex > gizmo.getStartX() && sy < gizmo.getEndY() && ey > gizmo.getStartY())
				return false;
		}
		return true;
	}

	@Override
	public boolean addGizmo(String gizmo, int x, int y) {
		String type = String.valueOf(gizmo.charAt(0));
		String xCoord = String.valueOf(x);
		String yCoord = String.valueOf(y);

		if (x < 10)
			xCoord = "0" + xCoord;
		if (y < 10)
			yCoord = "0" + yCoord;

		String uniqueKey = type + xCoord + yCoord;
		return addGizmo(gizmo, uniqueKey, x, y);
	}

	// adds a gizmo given the type of gizmo, a key and coords.
	@Override
	public boolean addGizmo(String gizmo, String key, int x, int y) {
		switch (gizmo.toLowerCase()) {
		case "triangle":
			if (addGizmo(new TriangleGizmo(x, y), key)) {
				System.out.println("Adding triangle at (" + x + "," + y + ")");
				return true;
			}
			break;
		case "circle":
			if (addGizmo(new CircleGizmo(x, y), key)) {
				System.out.println("Adding circle at (" + x + "," + y + ")");
				return true;
			}
			break;
		case "square":
			if (addGizmo(new SquareGizmo(x, y), key)) {
				System.out.println("Adding square at (" + x + "," + y + ")");
				return true;
			}
			break;
		case "rightflipper":
			if (addGizmo(new RightFlipperGizmo(x, y), key)) {
				System.out.println("Adding right flipper , 19, 20, 20at (" + x + "," + y + ")");
				return true;
			}
			break;
		case "leftflipper":
			if (addGizmo(new LeftFlipperGizmo(x, y), key)) {
				System.out.println("Adding left flipper at (" + x + "," + y + ")");
				return true;
			}
			break;
		}

		System.out.println("Cannot add gizmo at (" + x + "," + y + ")");
		return false;
	}

	@Override
	public boolean addGizmo(IGizmo gizmo, String key) {
		if (!validatePosition(gizmo.getStartX(), gizmo.getStartY(), gizmo.getEndX(), gizmo.getEndY()))
			return false;

		gizmo.setKey(key);

		// add gizmo to gizmo list
		gizmos.put(key, gizmo);

		this.setChanged();
		this.notifyObservers(); // call the observer to redraw the added gizmo

		return true;
	}

	public boolean addBall(String key, double x, double y, double velx, double vely) {
		Ball ball = new Ball(x, y, velx, vely);

		double r = ball.getRadius();

		if (!validatePosition(x - r, y - r, x + r, y + r))
			return false;

		ball.setKey(key);

		balls.put(key, ball);

		this.setChanged();
		this.notifyObservers();
		return true;
	}

	public boolean addBall(double x, double y, double velx, double vely) {
		String xCoord = String.valueOf(x);
		String yCoord = String.valueOf(y);

		if (x < 10)
			xCoord = "0" + xCoord;
		if (y < 10)
			yCoord = "0" + yCoord;

		String uniqueKey = "ball" + xCoord + yCoord;
		return addBall(uniqueKey, x, y, velx, vely);
	}

	public boolean addAbsorber(String key, int x, int y, int ex, int ey) {
		if (!validatePosition(x, y, ex, ey))
			return false;

		// add to list of gizmos
		gizmos.put(key, new AbsorberGizmo(x, y, ex, ey));

		// draw absorber

		this.setChanged();
		this.notifyObservers();
		return true;
	}

	public boolean addAbsorber(int x, int y, int ex, int ey) {
		String xCoord = String.valueOf(x);
		String yCoord = String.valueOf(y);

		if (x < 10)
			xCoord = "0" + xCoord;
		if (y < 10)
			yCoord = "0" + yCoord;

		String uniqueKey = "absorber" + xCoord + yCoord;
		return addAbsorber(uniqueKey, x, y, ex, ey);
	}

	//return key for gizmo at (x,y)
	private String findGizmo(int x, int y) {
		int ex = x + 1;
		int ey = y + 1;

		for (String key : gizmos.keySet()) {
			if (x < gizmos.get(key).getEndX() && ex > gizmos.get(key).getStartX() && y < gizmos.get(key).getEndY()
					&& ey > gizmos.get(key).getStartY())
				return key;
		}

		return null;
	}

	@Override
	public boolean rotateGizmo(int x, int y) {
		String key = findGizmo(x, y);
		System.out.println(key);
		if (key == null || key.contains("absorber"))
			return false;

		rotateGizmo(key);
		return true;
	}

	@Override
	public void rotateGizmo(String key) {
		gizmos.get(key).rotate();

		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void connectGizmo(IGizmo gizmo1, IGizmo gizmo2) {
		if (gizmo2.getOutgoingConnection() != null) {
			gizmo2.getOutgoingConnection().removeIncomingConnection(gizmo2);
			gizmo2.clearOutgoingConnection();
		}

		if (!gizmo1.getIncomingConnections().isEmpty()) {
			for (IGizmo incomingCon : gizmo1.getIncomingConnections()) {
				incomingCon.clearOutgoingConnection();
			}
			gizmo1.clearIncomingConnections();
		}

		gizmo1.setOutgoingConnection(gizmo2);
		gizmo2.addIncomingConnection(gizmo1);
		// System.out.println("connection done");
		// System.out.println(gizmo1.getKey());
		// System.out.println(gizmo2.getKey());

	}

	@Override
	public void connectGizmo(int x1, int y1, int x2, int y2){
		String gizmo1 = findGizmo(x1, y1);
		String gizmo2 = findGizmo(x2, y2);
		if(gizmo1!=null && gizmo2!=null){
			 connectGizmo( gizmos.get(gizmo1) , gizmos.get(gizmo2) );
		}
	}
	
	@Override
	public void disconnectGizmo(IGizmo gizmo) {
		if (gizmo.getOutgoingConnection() != null) {
			gizmo.getOutgoingConnection().removeIncomingConnection(gizmo);
			gizmo.clearOutgoingConnection();
		}

		if (!gizmo.getIncomingConnections().isEmpty()) {
			for (IGizmo incomingCon : gizmo.getIncomingConnections()) {
				incomingCon.clearOutgoingConnection();
			}
			gizmo.clearIncomingConnections();
		}
	}
	
	@Override
	public void disconnectGizmo(int x, int y){
		String gizmo= findGizmo(x, y);
		if(gizmo!=null){
			disconnectGizmo( gizmos.get(gizmo));
		}
	}
	
	@Override
	public void keyConnectGizmo(IGizmo gizmo, String k) {
		//gizmo.setKeyboardPress(k);
		List<IGizmo> tempList = keylistToGizmos.get(k);
		
		if(tempList == null)
			tempList = new ArrayList<IGizmo>();
		tempList.add(gizmo);
		keylistToGizmos.put(k, tempList);
	}
	
	@Override
	public void keyConnectGizmo(int x, int y, String k){
		String gizmo= findGizmo(x, y);
		if(gizmo!=null){
			keyConnectGizmo( gizmos.get(gizmo),k);
		}
	}
	
	
	private void removeKeyPress(IGizmo gizmo) {
		gizmo.setKeyboardPress(null);
	}
	
	@Override
	public void removeKeyPress(int x, int y) {
		removeKeyPress(gizmos.get(findGizmo(x, y)));
	}
	
	@Override
	public void deleteGizmo(String key) {
		gizmos.remove(key);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void deleteGizmo(int x, int y) {
		String gizmoKey = findGizmo(x, y);

		if (gizmoKey != null)
			deleteGizmo(gizmoKey);
	}

	@Override
	public void clear() {
		gizmos.clear();
		balls.clear();
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void setFriction(double f, double fTwo) {
		frictionMU = f;
		frictionMUTwo = fTwo;
	}

	public void applyFriction(Ball ball, double time) {
		double mu = frictionMU;
		double mu2 = frictionMUTwo;

		double xVel = ball.getVelocity().x();
		double yVel = ball.getVelocity().y();

		double fValue = (1 - mu * time - ball.getVelocity().length() * mu2 * time);
		ball.setVelocity(ball.getVelocity().times(fValue));
	}

	@Override
	public void setGravity(double g) {
		gravity = g;
	}

	public void applyGravity(Ball ball, double time) {
		Vect currentVel = ball.getVelocity();
		Vect velGravity = new Vect(currentVel.x(), (currentVel.y() + (gravity * time)));
		ball.setVelocity(velGravity);
	}

	private boolean moveGizmo(int x, int y, IGizmo gizmo) {
		if (validatePosition(x, y, x + gizmo.getSize(), y + gizmo.getSize(), gizmo)) {
			gizmo.newPosition(x, y);
			this.setChanged();
			this.notifyObservers();
			return true;
		}

		return false;
	}

	@Override
	public boolean moveGizmo(int gizmoX, int gizmoY, int newX, int newY) {
		String gizmoKey = findGizmo(gizmoX, gizmoY);
		IGizmo gizmo;

		if (gizmoKey == null)
			return false;
		else
			gizmo = gizmos.get(gizmoKey);

		return moveGizmo(newX, newY, gizmo);
	}

	@Override
	public void save(File f) {
		FileWriter writer;

		try {
			writer = new FileWriter(f);

			for (String key : gizmos.keySet()) {
				IGizmo gizmo = gizmos.get(key);

				// Gizmo
				// capitalises first letter
				String type = gizmo.gizmoType().substring(0, 1).toUpperCase()
						+ gizmo.gizmoType().substring(1).toLowerCase();
				String x = String.valueOf(gizmo.getStartX());
				String y = String.valueOf(gizmo.getStartY());
				String gizmoString = type + " " + key + " " + x + " " + y + "\n";
				writer.write(gizmoString);

				// Rotate
				int rot = gizmo.getRotation() / 90;
				for (int i = 0; i < rot; i++) {
					String rotation = "Rotate " + key + "\n";
					writer.write(rotation);
				}

				// KeyConnect keyword
				// DOUBLE CHECK THIS, MIGHT NOT BE CORRECT
				if (gizmo.getKeyboardPress() != null) {
					String keyConnection = "KeyConnect key " + gizmo.getKey() + " " + key + "\n";
					writer.write(keyConnection);
				}

				// Connect keyword
				if (gizmo.getOutgoingConnection() != null) {
					String connect = "Connect " + key + " " + gizmo.getOutgoingConnection().getKey() + "\n";
					writer.write(connect);
				}
			}

			// Ball
			for (Ball ball : balls.values()) {
				String ballString = "Ball " + ball.getKey() + " " + String.valueOf(ball.getX()) + " "
						+ String.valueOf(ball.getY()) + " " + String.valueOf(ball.getVelocity().x()) + " "
						+ String.valueOf(ball.getVelocity().y()) + "\n";
				writer.write(ballString);
			}

			// Gravity
			String grav = "Gravity " + String.valueOf(gravity) + "\n";
			writer.write(grav);
			// Friction
			String fric = "Friction " + String.valueOf(frictionMU) + " " + String.valueOf(frictionMUTwo) + "\n";
			writer.write(fric);

			writer.close();
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}

	@Override
	public boolean load(File f) {
		clear();
		int lineNumber = 0;
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				String[] splitCommand = line.toLowerCase().split(" ");
				switch (splitCommand[0]) {
				case "circle":
				case "triangle":
				case "square":
				case "leftflipper":
				case "rightflipper":
					if (splitCommand.length != 4)
						System.out
								.println("Skipping instruction at line " + lineNumber + " invalid gizmos instruction");
					else if (!addGizmo(splitCommand[0], splitCommand[1], (Integer.parseInt(splitCommand[2])),
							(Integer.parseInt(splitCommand[3]))))
						System.out.println("Skipping instruction at line " + lineNumber + " overlapping gizmo");
					break;
				case "absorber":
					if (splitCommand.length != 6)
						System.out.println(
								"Skipping instruction at line " + lineNumber + " invalid absorber instruction");
					else if (!addAbsorber(splitCommand[1], Integer.parseInt(splitCommand[2]),
							Integer.parseInt(splitCommand[3]), Integer.parseInt(splitCommand[4]),
							Integer.parseInt(splitCommand[5])))
						System.out.println("Skipping instruction at line " + lineNumber + " overlapping gizmo");
					break;
				case "ball":
					if (splitCommand.length != 6)
						System.out.println("Skipping instruction at line " + lineNumber + " invalid ball instruction");
					else if (!addBall(splitCommand[1], Double.parseDouble(splitCommand[2]),
							Double.parseDouble(splitCommand[3]), Double.parseDouble(splitCommand[4]),
							Double.parseDouble(splitCommand[5])))
						System.out.println("Skipping instruction at line " + lineNumber + " overlapping ball");

					break;
				case "rotate":
					if (splitCommand.length != 2)
						System.out
								.println("Skipping instruction at line " + lineNumber + " invalid rotate instruction");
					else
						rotateGizmo(splitCommand[1]);
					break;
				case "delete":
					if (splitCommand.length != 2)
						System.out
								.println("Skipping instruction at line " + lineNumber + " invalid delete instruction");
					else
						deleteGizmo(splitCommand[1]);
					break;
				case "move":
					if (splitCommand.length != 4)
						System.out.println("Skipping instruction at line " + lineNumber + " invalid move instruction");
					else if (!moveGizmo(Integer.parseInt(splitCommand[2]), Integer.parseInt(splitCommand[3]),
							gizmos.get(splitCommand[1])))
						System.out.println("Skipping instruction at line " + lineNumber + " invalid move location");
					break;
				case "keyconnect":
					if (splitCommand.length != 5)
						System.out.println(
								"Skipping instruction at line " + lineNumber + " invalid key connect instruction");
					else {
						keyConnectGizmo(gizmos.get(splitCommand[4]),splitCommand[2]);// connect
											}
					break;
				case "connect":
					if (splitCommand.length != 3)
						System.out
								.println("Skipping instruction at line " + lineNumber + " invalid connect instruction");
					else
						connectGizmo(gizmos.get(splitCommand[1]), gizmos.get(splitCommand[2]));
					break;
				case "friction":
					if (splitCommand.length != 3)
						System.out.println(
								"Skipping instruction at line " + lineNumber + " invalid friction instruction");
					else {
						System.out.println(splitCommand[0] + " " + splitCommand[1] + " " + splitCommand[2]);
						setFriction(Double.parseDouble((splitCommand[1])), Double.parseDouble((splitCommand[2])));
					}
					break;
				case "gravity":
					if (splitCommand.length != 2) {
						System.out
								.println("Skipping instruction at line " + lineNumber + " invalid gravity instruction");
					} else {
						setGravity(Double.parseDouble((splitCommand[1])));
						System.out.println(splitCommand[0] + " " + splitCommand[1]);
					}
					break;
				case "":
					break;
				default:
					return false;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

		this.setChanged();
		this.notifyObservers();

		return true;
	}

	public List<Ball> getBalls() {
		return new ArrayList<Ball>(balls.values());
	}

	public List<IGizmo> getGizmos() {
		List<IGizmo> gizmosList = new ArrayList<IGizmo>();
		for (IGizmo gizmo : gizmos.values()) {
			gizmosList.add(gizmo);
		}

		return gizmosList;
	}

	public List<IDrawableGizmo> drawableGizmos() {
		List<IDrawableGizmo> drawables = new ArrayList<IDrawableGizmo>();

		for (IGizmo gizmo : gizmos.values()) {
			drawables.add(new DrawableGizmo(gizmo));
		}

		return drawables;
	}

	public List<IDrawableBall> drawableBalls() {
		List<IDrawableBall> drawables = new ArrayList<IDrawableBall>();

		for (Ball ball : balls.values()) {
			drawables.add(new DrawableBall(ball));
		}

		return drawables;
	}

	public boolean containsGizmo(int x, int y){
		String key = findGizmo(x, y);
		if (key == null)
			return false;
		return true;
	}




}
