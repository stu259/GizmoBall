package model;

import physics.*;
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

public class Model extends Observable implements IModel {
	// (triggers)
	// lines/circles -> gizmos (color change, connections)
	// flippers -> lines/circles

	private Map<String, IGizmo> gizmos;
	private List<Ball> balls;
	private List<LineSegment> lines;
	private List<Circle> circles;

	private double gravity = 25;
	private double frictionMU = 0.025;
	private double frictionMUTwo = 0.025;
	private double time = 0.05;

	// connections for triggering both redrawing of lines and
	private Map<LineSegment, IGizmo> linesToAbsorber;
	private Map<Circle, IGizmo> circlesToAbsorber;
	private Map<Circle, IGizmo> circlesToGizmos;
	private Map<LineSegment, IGizmo> linesToGizmos;
	private Map<IGizmo, List<LineSegment>> flippersToLines;
	private Map<IGizmo, List<Circle>> flippersToCircles;

	private int boardSize;

	public Model() {
		boardSize = 20;

		gizmos = new HashMap<String, IGizmo>();
		balls = new ArrayList<Ball>();
		lines = new ArrayList<LineSegment>();
		circles = new ArrayList<Circle>();

		linesToAbsorber = new HashMap<LineSegment, IGizmo>();
		circlesToAbsorber = new HashMap<Circle, IGizmo>();
		linesToGizmos = new HashMap<LineSegment, IGizmo>();
		circlesToGizmos = new HashMap<Circle, IGizmo>();
		flippersToLines = new HashMap<IGizmo, List<LineSegment>>();
		flippersToCircles = new HashMap<IGizmo, List<Circle>>();
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
		lines.clear();
		circles.clear();
		linesToGizmos.clear();
		circlesToGizmos.clear();
		flippersToLines.clear();
		flippersToCircles.clear();

		makeWalls(boardSize);

		for (String key : gizmos.keySet()) {
			IGizmo gizmo = gizmos.get(key);
			// add method to draw gizmo
			if (gizmo instanceof SquareGizmo)
				makeSquareGizmo(gizmo.copy());

			else if (gizmo instanceof TriangleGizmo)
				makeTriangleGizmo(gizmo.copy());

			else if (gizmo instanceof RightFlipperGizmo)
				makeRightFlipper(gizmo.copy());

			else if (gizmo instanceof LeftFlipperGizmo)
				makeLeftFlipper(gizmo.copy());

			else if (gizmo instanceof CircleGizmo)
				makeCircleGizmo(gizmo.copy());

			else if (gizmo instanceof AbsorberGizmo)
				makeAbsorberGizmo(gizmo.copy());
		}
	}

	private void makeAbsorberGizmo(IGizmo gizmo) {
		// get x and y coordinates of starting and ending points of the gizmo
		int x1 = gizmo.getStartX();
		int x2 = gizmo.getEndX();
		int y1 = gizmo.getStartY();
		int y2 = gizmo.getEndY();

		// create lines and corners
		LineSegment left = new LineSegment(x1, y1, x1, y2);
		LineSegment bottom = new LineSegment(x1, y2, x2, y2);
		LineSegment right = new LineSegment(x2, y2, x2, y1);
		LineSegment top = new LineSegment(x2, y1, x1, y1);
		Circle topLeft = new Circle(x1, y1, 0);
		Circle bottomLeft = new Circle(x1, y2, 0);
		Circle topRight = new Circle(x2, y1, 0);
		Circle bottomRight = new Circle(x2, y2, 0);

		// create connections for the lines -> absorber
		linesToAbsorber.put(left, gizmo);
		linesToAbsorber.put(bottom, gizmo);
		linesToAbsorber.put(right, gizmo);
		linesToAbsorber.put(top, gizmo);
		// create connections for the circles -> absorber
		circlesToAbsorber.put(topLeft, gizmo);
		circlesToAbsorber.put(bottomLeft, gizmo);
		circlesToAbsorber.put(topRight, gizmo);
		circlesToAbsorber.put(bottomRight, gizmo);
	}

	private void makeLeftFlipper(IGizmo gizmo) {
		int x1 = gizmo.getStartX();
		int x2 = gizmo.getEndX();
		int y1 = gizmo.getStartY();
		int y2 = gizmo.getEndY();
		double radius = 0.25;

		// drawing circles .25 radius
		Circle topCircle = new Circle(x1 + (radius), y1 + (radius), radius);
		Circle botCircle = new Circle(x1 + (radius), y2 - (radius), radius);
		List<Circle> tempCirc = new ArrayList<Circle>();
		tempCirc.add(topCircle);
		tempCirc.add(botCircle);
		circles.addAll(tempCirc);

		// drawing lines
		LineSegment right = new LineSegment(x1 + (2 * radius), y1 + (radius),
				x1 + (2 * radius), y2 - (radius));
		LineSegment left = new LineSegment(x1, y1 + (radius), x1, y2 - (radius));
		List<LineSegment> tempLines = new ArrayList<LineSegment>();
		tempLines.add(right);
		tempLines.add(left);
		lines.addAll(tempLines);

		// connect lines/corners to flipper gizmo
		linesToGizmos.put(right, gizmo);
		linesToGizmos.put(left, gizmo);
		circlesToGizmos.put(topCircle, gizmo);
		circlesToGizmos.put(botCircle, gizmo);

		// connect flipper to lines and circle (used for rotation)
		flippersToLines.put(gizmo, tempLines);
		flippersToCircles.put(gizmo, tempCirc);
	}

	private void makeRightFlipper(IGizmo gizmo) {
		int x1 = gizmo.getStartX();
		int x2 = gizmo.getEndX();
		int y1 = gizmo.getStartY();
		int y2 = gizmo.getEndY();
		double radius = 0.25;

		// drawing circles .25 radius
		Circle topCircle = new Circle(x2 - (radius), y1 + (radius), radius);
		Circle botCircle = new Circle(x2 - (radius), y2 - (radius), radius);
		List<Circle> tempCirc = new ArrayList<Circle>();
		tempCirc.add(topCircle);
		tempCirc.add(botCircle);
		circles.addAll(tempCirc);

		// drawing lines
		LineSegment right = new LineSegment(x2, y1 + (radius), x2, y2 - (radius));
		LineSegment left = new LineSegment(x2 - (2 * radius), y1 + (radius),
				x2 - (2 * radius), y2 - (radius));
		List<LineSegment> tempLines = new ArrayList<LineSegment>();
		tempLines.add(right);
		tempLines.add(left);
		lines.addAll(tempLines);

		// connect lines/corners to flipper gizmo
		linesToGizmos.put(right, gizmo);
		linesToGizmos.put(left, gizmo);
		circlesToGizmos.put(topCircle, gizmo);
		circlesToGizmos.put(botCircle, gizmo);

		// connect flipper to lines and circle (used for rotation)
		flippersToLines.put(gizmo, tempLines);
		flippersToCircles.put(gizmo, tempCirc);
	}

	private void makeCircleGizmo(IGizmo gizmo) {
		int x1 = gizmo.getStartX();
		int x2 = gizmo.getEndX();
		int y1 = gizmo.getStartY();
		int y2 = gizmo.getEndY();

		double radius = gizmo.getSize() / 2;
		// get midpoints
		Circle circle = new Circle((x1 + x2) / 2, (y1 + y2) / 2, radius);
		circlesToGizmos.put(circle, gizmo);
		circles.add(circle);
	}

	private void makeTriangleGizmo(IGizmo gizmo) {
		// the right angled corner should be in the top-left position by default
		// THIS IS A MUST!!!! DO NOT CHANGE
		int x1 = gizmo.getStartX();
		int x2 = gizmo.getEndX();
		int y1 = gizmo.getStartY();
		int y2 = gizmo.getEndY();
		int direction = gizmo.getRotation();

		// keeps track of the triangle rotation for edge and corner placement
		switch (direction) {
		case 0: // right-angle is top-left (this is by default)
			LineSegment line1 = new LineSegment(x1, y1, x1, y2); // left edge
			LineSegment line2 = new LineSegment(x1, y2, x2, y1); // hypotenuse
																	// edge
			LineSegment line3 = new LineSegment(x2, y1, x1, y1); // top edge
			Circle circle1 = new Circle(x1, y1, 0);
			Circle circle2 = new Circle(x2, y1, 0);
			Circle circle3 = new Circle(x1, y2, 0);
			// add lines to line list
			lines.add(line1);
			lines.add(line2);
			lines.add(line3);
			// add lines to hashmap
			linesToGizmos.put(line1, gizmo);
			linesToGizmos.put(line2, gizmo);
			linesToGizmos.put(line3, gizmo);
			// add circles to circle list
			circles.add(circle1);
			circles.add(circle2);
			circles.add(circle3);
			// add circles to hashmap
			circlesToGizmos.put(circle1, gizmo);
			circlesToGizmos.put(circle2, gizmo);
			circlesToGizmos.put(circle3, gizmo);
			break;
		case 90: // right-angle is top right
			LineSegment line4 = new LineSegment(x2, y1, x1, y1); // top edge
			LineSegment line5 = new LineSegment(x1, y1, x2, y2); // hypotenuse
																	// edge
			LineSegment line6 = new LineSegment(x2, y2, x2, y1); // right edge
			Circle circle4 = new Circle(x1, y1, 0);
			Circle circle5 = new Circle(x2, y1, 0);
			Circle circle6 = new Circle(x2, y2, 0);
			// add lines to line list
			lines.add(line4);
			lines.add(line5);
			lines.add(line6);
			// add lines to hashmap
			linesToGizmos.put(line4, gizmo);
			linesToGizmos.put(line5, gizmo);
			linesToGizmos.put(line6, gizmo);
			// add circles to circle list
			circles.add(circle4);
			circles.add(circle5);
			circles.add(circle6);
			// add circles to hashmap
			circlesToGizmos.put(circle4, gizmo);
			circlesToGizmos.put(circle5, gizmo);
			circlesToGizmos.put(circle6, gizmo);
			break;
		case 180: // right-angle is bottom right
			LineSegment line7 = new LineSegment(x2, y2, x1, y2); // bottom edge
			LineSegment line8 = new LineSegment(x1, y2, x2, y1); // hypotenuse
																	// edge
			LineSegment line9 = new LineSegment(x2, y1, x2, y2); // right edge
			Circle circle7 = new Circle(x2, y2, 0);
			Circle circle8 = new Circle(x2, y1, 0);
			Circle circle9 = new Circle(x1, y2, 0);
			// add lines to line list
			lines.add(line7);
			lines.add(line8);
			lines.add(line9);
			// add lines to hashmap
			linesToGizmos.put(line7, gizmo);
			linesToGizmos.put(line8, gizmo);
			linesToGizmos.put(line9, gizmo);
			// add circles to circle list
			circles.add(circle7);
			circles.add(circle8);
			circles.add(circle9);
			// add circles to hashmap
			circlesToGizmos.put(circle7, gizmo);
			circlesToGizmos.put(circle8, gizmo);
			circlesToGizmos.put(circle9, gizmo);
			break;
		case 270: // right-angle is bottom left
			LineSegment line10 = new LineSegment(x1, y2, x2, y2); // bottom edge
			LineSegment line11 = new LineSegment(x1, y1, x2, y2); // hypotenuse
																	// edge
			LineSegment line12 = new LineSegment(x1, y1, x1, y2); // left edge
			Circle circle10 = new Circle(x1, y1, 0);
			Circle circle11 = new Circle(x1, y2, 0);
			Circle circle12 = new Circle(x2, y2, 0);
			// add lines to line list
			lines.add(line10);
			lines.add(line11);
			lines.add(line12);
			// add lines to hashmap
			linesToGizmos.put(line10, gizmo);
			linesToGizmos.put(line11, gizmo);
			linesToGizmos.put(line12, gizmo);
			// add circles to circle list
			circles.add(circle10);
			circles.add(circle11);
			circles.add(circle12);
			// add circles to hashmap
			circlesToGizmos.put(circle10, gizmo);
			circlesToGizmos.put(circle11, gizmo);
			circlesToGizmos.put(circle12, gizmo);
			break;
		}
	}

	private void makeSquareGizmo(IGizmo gizmo) {
		// get x and y coordinates of starting and ending points of the gizmo
		int x1 = gizmo.getStartX();
		int x2 = gizmo.getEndX();
		int y1 = gizmo.getStartY();
		int y2 = gizmo.getEndY();

		// create lines and corners
		LineSegment left = new LineSegment(x1, y1, x1, y2);
		LineSegment bottom = new LineSegment(x1, y2, x2, y2);
		LineSegment right = new LineSegment(x2, y2, x2, y1);
		LineSegment top = new LineSegment(x2, y1, x1, y1);
		Circle topLeft = new Circle(x1, y1, 0);
		Circle bottomLeft = new Circle(x1, y2, 0);
		Circle topRight = new Circle(x2, y1, 0);
		Circle bottomRight = new Circle(x2, y2, 0);

		// create connections for the lines -> gizmo
		linesToGizmos.put(left, gizmo);
		linesToGizmos.put(bottom, gizmo);
		linesToGizmos.put(right, gizmo);
		linesToGizmos.put(top, gizmo);
		// create connections for the circles -> gizmo
		circlesToGizmos.put(topLeft, gizmo);
		circlesToGizmos.put(bottomLeft, gizmo);
		circlesToGizmos.put(topRight, gizmo);
		circlesToGizmos.put(bottomRight, gizmo);
		// add lines to list of alllines
		lines.add(left);
		lines.add(bottom);
		lines.add(right);
		lines.add(top);
		// adds little circles to ensure that corners have collision
		circles.add(topLeft);
		circles.add(bottomLeft);
		circles.add(topRight);
		circles.add(bottomRight);
	}

	private void makeWalls(int boardSize) {
		LineSegment topWall = new LineSegment(0, 0, boardSize, 0);
		LineSegment rightWall = new LineSegment(boardSize, 0, boardSize, boardSize);
		LineSegment bottomWall = new LineSegment(0, boardSize, boardSize, boardSize);
		LineSegment leftWall = new LineSegment(0, 0, 0, boardSize);
		lines.add(topWall);
		lines.add(rightWall);
		lines.add(bottomWall);
		lines.add(leftWall);
	}

	private CollisionInfo timeUntilCollision() {
		double lowestColTime = Double.MAX_VALUE;
		Ball collidingBall = null;
		Vect updatedVel = new Vect(0, 0);
		AbsorberGizmo absorber = null;

		for (Ball ball : balls) {
			Circle circ = ball.getCircle();
			Vect vel = ball.getVelocity();
			double nextTime = 0;
			for (LineSegment line : lines) {
				nextTime = Geometry.timeUntilWallCollision(line, circ, vel);
				if (nextTime < lowestColTime) {
					lowestColTime = nextTime;
					collidingBall = ball;
					if(linesToGizmos.get(line) == null)
						updatedVel = Geometry.reflectWall(line, vel, 1);
					else
						updatedVel = Geometry.reflectWall(line, vel, linesToGizmos.get(line).getCof());
					absorber = null;
				}
			}
			for (Circle circle : circles) {
				nextTime = Geometry.timeUntilCircleCollision(circle, circ, vel);
				if (nextTime < lowestColTime) {
					lowestColTime = nextTime;
					collidingBall = ball;
					updatedVel = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), vel,
							circlesToGizmos.get(circle).getCof());
					absorber = null;
				}
			}
			for (LineSegment line : linesToAbsorber.keySet()) {
				nextTime = Geometry.timeUntilWallCollision(line, circ, vel);
				if (nextTime < lowestColTime) {
					lowestColTime = nextTime;
					collidingBall = ball;
					absorber = (AbsorberGizmo) linesToAbsorber.get(line);
					updatedVel = absorber.getExitVeloicty();
				}
			}
			for (Circle circle : circlesToAbsorber.keySet()) {
				nextTime = Geometry.timeUntilCircleCollision(circle, circ, vel);
				if (nextTime < lowestColTime) {
					lowestColTime = nextTime;
					collidingBall = ball;
					absorber = (AbsorberGizmo) circlesToAbsorber.get(circle);
					updatedVel = absorber.getExitVeloicty();
				}
			}

		}
		return (new CollisionInfo(lowestColTime, collidingBall, updatedVel, absorber));

	}

	@Override
	public void moveBalls() {
		CollisionInfo colInfo = timeUntilCollision();
		double colTime = colInfo.getColTime();
		Ball colBall = colInfo.getCollidingBall();
		if (colTime < time) {  //collision detected
			balls.remove(colBall);
			if (colInfo.getAbs() != null) {
				AbsorberGizmo absorber = colInfo.getAbs();
				colBall.setX(absorber.getEndX() - colBall.getRadius());
				colBall.setY(absorber.getStartY() - colBall.getRadius());
				colBall.setVelocity(colInfo.getUpdatedVel());
			} else {
				colBall = calculateBallMove(colBall, colTime);
				colBall.setVelocity(colInfo.getUpdatedVel());
				applyFriction(colBall, colTime);
				applyGravity(colBall, colTime);
			}
			for (Ball ball : balls) {
				ball = calculateBallMove(ball, colTime);
				applyFriction(ball, colTime);
				applyGravity(ball, colTime);
			}
			balls.add(colBall);
		} else {
			for (Ball ball : balls) {
				ball = calculateBallMove(ball, time);
				applyFriction(ball, time);
				applyGravity(ball, time);
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

	@Override
	public void resume() {
		for (Ball ball : balls)
			ball.setPaused(false);
	}

	@Override
	// pauses all the balls and therefore pauses the game
	public void pause() {
		for (Ball ball : balls)
			ball.setPaused(true);
	}

	private class CollisionInfo {
		double colTime;
		Ball collidingBall;
		Vect updatedVel;
		AbsorberGizmo abs;

		public CollisionInfo(double t, Ball b, Vect v, AbsorberGizmo a) {
			colTime = t;
			collidingBall = b;
			updatedVel = v;
			abs = a;
		}

		public double getColTime() {
			return colTime;
		}

		public Ball getCollidingBall() {
			return collidingBall;
		}

		public Vect getUpdatedVel() {
			return updatedVel;
		}

		public AbsorberGizmo getAbs() {
			return abs;
		}

	}

	/**
	 * 
	 * 
	 * BUILDING CODE BEYOND THIS POINT
	 * 
	 * 
	 */

	// check if object can be added (check outside board, position, size, then
	// scale)
	// Params: startx, starty, endx, endy
	private boolean validatePosition(double sx, double sy, double ex, double ey) {
		// check if gizmo is somehow placed outside of board (shouldnt happen)
		if (sx < 0 || ex > boardSize || sy < 0 || ey > boardSize)
			return false;

		// check if given coordinates overlaps with any other gizmo position
		for (String key : gizmos.keySet()) {
			IGizmo gizmo = gizmos.get(key);
			if (sx < gizmo.getEndX() && ex > gizmo.getStartX() && sy < gizmo.getEndY() && ey > gizmo.getEndY())
				return false;
		}
		return true;
	}

	// adds a gizmo given the type of gizmo, a key and coords.
	@Override
	public boolean addGizmo(String gizmo, String key, int x, int y) {
		switch (gizmo.toLowerCase()) {
		case "triangle":
			return addGizmo(new TriangleGizmo(x, y), key);
		case "circle":
			return addGizmo(new CircleGizmo(x, y), key);
		case "square":
			return addGizmo(new SquareGizmo(x, y), key);
		case "rightflipper":
			return addGizmo(new RightFlipperGizmo(x, y), key);
		case "leftflipper":
			return addGizmo(new LeftFlipperGizmo(x, y), key);
		}
		return false;
	}

	@Override
	public boolean addGizmo(IGizmo gizmo, String key) {
		if (!validatePosition(gizmo.getStartX(), gizmo.getStartY(), gizmo.getEndX(), gizmo.getEndY()))
			return false;

		// TODO CHECK FOR ABSORBER OVERLAPPING AS WELL

		// add gizmo to gizmo list
		gizmos.put(key, gizmo);

		this.setChanged();
		this.notifyObservers(); // call the observer to redraw the added gizmo

		return true;
	}

	// Adds param ball to the list of balls on the board
	public void addBall(Ball ball) {
		this.setChanged();
		this.notifyObservers();
		balls.add(ball);
	}

	public boolean addBall(String key, double x, double y, double velx, double vely) {
		Ball ball = new Ball(x, y, velx, vely);

		double r = ball.getRadius();

		if (!validatePosition(x - r, y - r, x + r, y + r))
			return false;

		balls.add(ball);
		this.setChanged();
		this.notifyObservers();
		return true;
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

	@Override
	public void rotateGizmo(String key) {
		gizmos.get(key).rotate();
		
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public boolean connectGizmo(IGizmo gizmo1, IGizmo gizmo2) {
		if (gizmo2.getConnectedGizmo() != null) {
			gizmo1.setConnectedGizmo(gizmo2);
			gizmo2.setConnectedGizmo(null);
			gizmo2.addGizmoConnected(gizmo1);
			return true;
		}
		return false;
	}

	@Override
	public boolean disconnectGizmo(IGizmo gizmo) {
		boolean removed = false;
		if (gizmo.getConnectedGizmo() != null) {
			gizmo.getConnectedGizmo().removeGizmoConnected(gizmo);
			gizmo.setConnectedGizmo(null);
			removed = true;
		}
		if (gizmo.getGizmosConnected().size() > 0) {
			for (IGizmo giz : gizmo.getGizmosConnected()) {
				giz.setConnectedGizmo(null);
			}
			gizmo.clearGizmoConnected();
			removed = true;
		}
		return removed;
	}

	@Override
	public void keyConnectGizmo(IGizmo gizmo, String k) {
		gizmo.setKey(k);
	}

	@Override
	public void removeKey(IGizmo gizmo) {
		gizmo.setKey("");
	}

	/* TODO REWRITE TO REMOVE FROM THE MAPS AND LISTS */
	@Override
	public void deleteGizmo(String key) {
		gizmos.remove(key);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void clear() {
		gizmos.clear();
		balls.clear();
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

		double newX = xVel * (1 - (mu * time) - (mu2 * xVel) * time);
		double newY = yVel * (1 - (mu * time) - (mu2 * yVel) * time);

		Vect newV = new Vect(newX, newY);
		ball.setVelocity(newV);
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

	@Override
	public boolean moveGizmo(int x, int y, IGizmo gizmo) {
		if (validatePosition(x, y, x + gizmo.getSize(), y + gizmo.getSize())) {
			gizmo.newPosition(x, y);
			this.setChanged();
			this.notifyObservers();
			return true;
		}

		return false;
	}

	@Override
	public void save(File f) {
		FileWriter writer;
		try {
			writer = new FileWriter(f);
			writer.close();
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
	}

	@Override
	public boolean load(File f) {
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while ((line = reader.readLine()) != null) {
				String[] splitCommand = line.toLowerCase().split(" ");
				switch (splitCommand[0]) {
				case "circle":
				case "triangle":
				case "square":
				case "leftflipper":
				case "rightflipper":
					if (splitCommand.length != 4)
						return false;
					else if (!addGizmo(splitCommand[0], splitCommand[1], (Integer.parseInt(splitCommand[2])),
							(Integer.parseInt(splitCommand[3]))))
						return false;
					break;
				case "absorber":
					if (splitCommand.length != 6)
						return false;
					else
						addAbsorber(splitCommand[1], Integer.parseInt(splitCommand[2]),
								Integer.parseInt(splitCommand[3]), Integer.parseInt(splitCommand[4]),
								Integer.parseInt(splitCommand[5]));
					break;
				case "ball":
					if (splitCommand.length != 6)
						return false;
					else
						addBall(splitCommand[1], Double.parseDouble(splitCommand[2]),
								Double.parseDouble(splitCommand[3]), Double.parseDouble(splitCommand[4]),
								Double.parseDouble(splitCommand[5]));
					break;
				case "rotate":
					if (splitCommand.length != 2)
						return false;
					else
						gizmos.get(splitCommand[1]).rotate();
					break;
				case "delete":
					if (splitCommand.length != 2)
						return false;
					else
						deleteGizmo(splitCommand[1]);
					break;
				case "move":
					if (splitCommand.length != 4)
						return false;
					else
						gizmos.get(splitCommand[1]).newPosition(Integer.parseInt(splitCommand[2]),
								Integer.parseInt(splitCommand[3]));
					break;
				case "keyconnect":
					if (splitCommand.length != 5)
						return false;
					else {
						gizmos.get(splitCommand[4]);// connect
						System.out.println(splitCommand[0] + " " + splitCommand[1] + " " + splitCommand[2] + " "
								+ splitCommand[3] + " " + splitCommand[4]);
					}
					break;
				case "connect":
					if (splitCommand.length != 3)
						return false;
					else
						System.out.println(splitCommand[0] + " " + splitCommand[1] + " " + splitCommand[2]);
					// gizmos.get(splitCommand[1]).addGizmoConnected(gizmos.get(splitCommand[2]));
					break;
				case "friction":
					if (splitCommand.length != 3)
						return false;
					else {
						System.out.println(splitCommand[0] + " " + splitCommand[1] + " " + splitCommand[2]);
						setFriction(Double.parseDouble((splitCommand[1])), Double.parseDouble((splitCommand[2])));
					}
					break;
				case "gravity":
					if (splitCommand.length != 3)
						return false;
					else {
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

	public List<Ball> getBalls(){
		return balls;
	}
	
	public List<IGizmo> getGizmos(){
		List<IGizmo> gizmosList = new ArrayList<IGizmo>();
		for(IGizmo gizmo : gizmos.values()){
			gizmosList.add(gizmo);
		}
		
		return gizmosList;
	}
	public void moveFlipper(IGizmo gizmo){
		if (gizmo instanceof LeftFlipperGizmo){	
			
		}
		if (gizmo instanceof RightFlipperGizmo){
			
		}
		this.setChanged();
		this.notifyObservers();
	}
}
