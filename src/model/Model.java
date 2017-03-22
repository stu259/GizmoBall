package model;

//import physics.*;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import controller.ErrorMessage;
import jUnit.InvalidLineException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.LineSegment;
import physics.Vect;

public class Model extends Observable implements IModel, IDrawableModel {
	private Map<String, IGizmo> gizmos;

	private double gravity = 25;
	private double frictionMU = 0.025;
	private double frictionMUTwo = 0.025;
	private final double time = 0.01;
	private double currentTick = time;

	private Map<Circle, IGizmo> circlesToGizmos;
	private Map<LineSegment, IGizmo> linesToGizmos;
	private Map<String, Ball> balls;
	private Map<String, List<IGizmo>> keylistToGizmos;
	private Map<IGizmo, Queue<Ball>> absorberToBalls;

	private List<LineSegment> walls;
	private List<IGizmo> rotatingFlippers;

	private int boardSize;

	private ErrorMessage errorMessage;

	public Model(ErrorMessage eM) {
		errorMessage = eM;
		boardSize = 20;

		gizmos = new HashMap<String, IGizmo>();
		balls = new HashMap<String, Ball>();
		linesToGizmos = new HashMap<LineSegment, IGizmo>();
		circlesToGizmos = new HashMap<Circle, IGizmo>();
		keylistToGizmos = new HashMap<String, List<IGizmo>>();
		absorberToBalls = new HashMap<IGizmo, Queue<Ball>>();

		walls = new ArrayList<LineSegment>();
		rotatingFlippers = new ArrayList<IGizmo>();

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
		absorberToBalls.clear();

		resetGizmos();

		Queue<Ball> q;

		for (String key : gizmos.keySet()) {
			if (gizmos.get(key).gizmoType().toLowerCase().equals("absorber")) {
				q = new LinkedList<Ball>();
				absorberToBalls.put(gizmos.get(key), q);
			}
			IGizmo gizmo = gizmos.get(key);
			drawGizmos(gizmo);
		}

		resetBalls();

	}

	private void drawGizmos(IGizmo gizmo) {
		List<LineSegment> lines = gizmo.getLines();
		List<Circle> corners = gizmo.getCorners();

		for (LineSegment line : lines) {
			linesToGizmos.put(line, gizmo);
		}
		for (Circle corner : corners) {
			circlesToGizmos.put(corner, gizmo);
		}
	}

	private void makeWalls(int boardSize) {
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
		boolean skip = false;
		VectPair vectPair = null;
		Vect updatedVel = new Vect(0, 0);
		LineSegment lineHit = null;
		Circle circleHit = null;
		Ball collidingBall2 = null;
		Vect updatedVel2 = null;
		Geometry.setForesight(time * 1.2);

		for (Ball ball : balls.values()) {
			skip = false;
			
			if (ball.paused()) continue;

			for (IGizmo gizmo : absorberToBalls.keySet())
				if (gizmo.gizmoType().toLowerCase().equals("absorber"))
					if (detectInsideAbsorber(ball, gizmo)) {
						AbsorberGizmo abs = (AbsorberGizmo) gizmo;
						addBallToAbsorber(abs, ball);
						skip = true;
					}

			if (skip) continue;

			Circle circ = ball.getCircle();
			Vect vel = ball.getVelocity();
			double nextTime = 0;
			for (LineSegment line : walls) {
				nextTime = Geometry.timeUntilWallCollision(line, circ, vel);
				if(nextTime == 0){ //ball has stopped bouncing
					Vect noBounceVel = Geometry.reflectWall(line, vel, 1);
					ball.setVelocity(new Vect(noBounceVel.x(), 0));
					continue;
				}
				if (nextTime < lowestColTime) {
					lineHit = null;
					circleHit = null;
					lowestColTime = nextTime;
					collidingBall = ball;
					collidingBall2 = null;
					if(ball.getVelocity().y() > -1 && ball.getVelocity().y() < 1){ //if ball is so slow that it should stop
						Vect noBounceVel = Geometry.reflectWall(line, vel, 1);
						updatedVel = new Vect(noBounceVel.x(), 0);
					}
					else
						updatedVel = Geometry.reflectWall(line, vel, 1);
				}
			}
			for (LineSegment line : linesToGizmos.keySet()) {
				nextTime = Geometry.timeUntilWallCollision(line, circ, vel);
				if(nextTime == 0){ //ball has stopped bouncing
					Vect noBounceVel = Geometry.reflectWall(line, vel, linesToGizmos.get(line).getCoef());
					ball.setVelocity(new Vect(noBounceVel.x(), 0));
					continue;
				}
				if (nextTime < lowestColTime) {
					lineHit = line;
					circleHit = null;
					lowestColTime = nextTime;
					collidingBall = ball;
					collidingBall2 = null;
					
					if(ball.getVelocity().y() > -1 && ball.getVelocity().y() < 1){
						Vect noBounceVel = Geometry.reflectWall(line, vel, linesToGizmos.get(line).getCoef());
						updatedVel = new Vect(noBounceVel.x(), 0);
					}
					else
						updatedVel = Geometry.reflectWall(line, vel, linesToGizmos.get(line).getCoef());
				}
			}
			for (Circle circle : circlesToGizmos.keySet()) {
				nextTime = Geometry.timeUntilCircleCollision(circle, circ, vel);
				if (nextTime < lowestColTime) {
					lineHit = null;
					circleHit = circle;
					lowestColTime = nextTime;
					collidingBall = ball;
					collidingBall2 = null;
					updatedVel = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), vel,
							circlesToGizmos.get(circle).getCoef());
				}
			}
			//rotating flippers
			for (IGizmo flipper : rotatingFlippers) {
				double angVel = flipper.getAngularVel()*(Math.PI/180);
				if(!flipper.triggered())
					angVel = angVel * -1;
				for (LineSegment line : flipper.getLines()) {
					nextTime = Geometry.timeUntilRotatingWallCollision(line, flipper.getPivotPoint(),
					angVel, ball.getCircle(), vel);
					if (nextTime < lowestColTime) { // collision
						lineHit = null;
						circleHit = null;
						lowestColTime = nextTime;
						collidingBall = ball;
						collidingBall2 = null;
						updatedVel = Geometry.reflectRotatingWall(line, flipper.getPivotPoint(),
								angVel, circ, vel, flipper.getCoef());
					}
				}
				for (Circle circle : flipper.getCorners()) {
					nextTime = Geometry.timeUntilRotatingCircleCollision(circle, flipper.getPivotPoint(),
							angVel, ball.getCircle(), vel);
					if (nextTime < lowestColTime) {
						lineHit = null;
						circleHit = null;
						collidingBall2 = null;
						lowestColTime = nextTime;
						collidingBall = ball;
						updatedVel = Geometry.reflectRotatingCircle(circle, flipper.getPivotPoint(),
								angVel, circ, vel, flipper.getCoef());
					}
				}
			}
			for (Ball ball2 : balls.values()) {
				if (ball2.equals(ball) || ball2.paused())
					continue;
				nextTime = Geometry.timeUntilBallBallCollision(ball.getCircle(), ball.getVelocity(), ball2.getCircle(),
						ball2.getVelocity());
				if (nextTime < lowestColTime) {
					lineHit = null;
					circleHit = null;
					lowestColTime = nextTime;
					collidingBall = ball;
					collidingBall2 = ball2;
					vectPair = Geometry.reflectBalls(ball.getCenter(), ball.getMass(), ball.getVelocity(),
							ball2.getCenter(), ball2.getMass(), ball2.getVelocity());
					updatedVel = vectPair.v1;
					updatedVel2 = vectPair.v2;
				}
			}
		}
		if (lowestColTime < time) {
			// line collision
			if (lineHit != null) {
				// absorber collision
				if (linesToGizmos.get(lineHit).gizmoType().equals("absorber")) {
					AbsorberGizmo abs = (AbsorberGizmo) linesToGizmos.get(lineHit);

					addBallToAbsorber(abs, collidingBall);
				}
				// all other gizmos collisions
				else {
					linesToGizmos.get(lineHit).setHit((int)(3 * 1/time)); // 120 = 3 seconds
															// (40ticks/second)
					if (linesToGizmos.get(lineHit).getOutgoingConnections() != null) {
						for (IGizmo outgoingGizmo : linesToGizmos.get(lineHit).getOutgoingConnections())
							outgoingGizmo.trigger();
					}
				}
			}
			// circle collision
			else if (circleHit != null) {
				// absorber collision
				if (circlesToGizmos.get(circleHit).gizmoType().equals("absorber")) {
					AbsorberGizmo abs = (AbsorberGizmo) circlesToGizmos.get(circleHit);

					addBallToAbsorber(abs, collidingBall);
				}
				// all other gizmo collisions
				else {
					circlesToGizmos.get(circleHit).setHit((int)(3 * 1/time)); // 120 = 3
																// seconds
																// (40ticks/second)
					if (circlesToGizmos.get(circleHit).getOutgoingConnections() != null)
						for (IGizmo outgoingGizmo : circlesToGizmos.get(circleHit).getOutgoingConnections())
							outgoingGizmo.trigger();
				}
			}
		}

		return (new CollisionInfo(lowestColTime, collidingBall, updatedVel, collidingBall2, updatedVel2));

	}

	private boolean detectInsideAbsorber(Ball ball, IGizmo abs) {
		int aStartX = abs.getStartX();
		int aStartY = abs.getStartY();
		int aEndX = abs.getEndX();
		int aEndY = abs.getEndY();
		double bCenterX = ball.getCenter().x();
		double bCenterY = ball.getCenter().y();
		double r = ball.getRadius();

		return ((bCenterX + r > aStartX) && (bCenterX - r < aEndX) && (bCenterY + r > aStartY)
				&& (bCenterY - r < aEndY));
	}

	@Override
	public void tick() {
		currentTick = time;
		loopGizmos();
		moveBalls();
		this.setChanged();
		this.notifyObservers();
	}

	private void fireAbsorber(AbsorberGizmo absorber) {
		if (absorber.triggered() && findGizmo(absorber.getEndX()-1, absorber.getEndY()-2) == null) {
			if (!absorberToBalls.keySet().contains(absorber)) {
				absorber.trigger();
				return;
			}
			if (!absorberToBalls.get(absorber).isEmpty()) {
				Ball ballToFire = absorberToBalls.get(absorber).remove();
				ballToFire.setY(absorber.getStartY() - ballToFire.getRadius());
				ballToFire.resume();
				ballToFire.setAbsorbed(false);
				moveBallsInAbsorber(absorber);
			}
			absorber.trigger();//untrigger
		}
	}

	private void addBallToAbsorber(AbsorberGizmo abs, Ball ball) {
		// pause ball
		ball.pause();
		ball.setAbsorbed(true);

		// add ball to absorbers queue
		ball.setVelocity(abs.getExitVeloicty());
		Queue<Ball> temp = new LinkedList<Ball>();
		if (absorberToBalls.get(abs) != null)
			temp.addAll(absorberToBalls.get(abs));
		temp.add(ball);
		absorberToBalls.put(abs, temp);

		List<Ball> q = new ArrayList<Ball>(temp);

		// move ball to top right of absorber
		if (temp.size() == 1) {
			ball.setX((double) abs.getEndX() - ball.getRadius());
			ball.setY((double) abs.getStartY() + ball.getRadius());
			return;
		}

		Ball scndLast = q.get(q.size() - 2);
		ball.setX(scndLast.getCenter().x() - scndLast.getRadius() * 2);
		ball.setY(scndLast.getCenter().y());

		// when row is full
		if (ball.getCenter().x() - ball.getRadius() < abs.getStartX()) {
			ball.setX(abs.getEndX() - ball.getRadius());
			ball.setY(ball.getCenter().y() + 2 * ball.getRadius());
		}
		if (ball.getCenter().y() > abs.getEndY()) {
			ball.setX(q.get(q.size() - 2).getCenter().x());
			ball.setY(q.get(q.size() - 2).getCenter().y());
		}
	}

	private void moveBallsInAbsorber(AbsorberGizmo abs) {
		List<Ball> q = new ArrayList<Ball>(absorberToBalls.get(abs));

		for (Ball b : q) {
			if (b.getCenter().x() - b.getRadius() == abs.getStartX()
					&& abs.getEndY() - b.getRadius() == b.getCenter().y()) {
				b.setX(b.getX() + b.getRadius() * 2);
				return;
			}

			b.setX(b.getX() + b.getRadius() * 2);

			if (b.getX() - b.getRadius() >= abs.getEndX()) {
				b.setX(abs.getStartX() + b.getRadius());
				b.setY(b.getY() - 2 * b.getRadius());
			}
		}
	}

	private void resetGizmos() {
		for (IGizmo gizmo : gizmos.values()) {
			// make sure all gizmos aren't hit and triggered
			gizmo.setHit(0);
			if (gizmo.triggered()) {
				gizmo.trigger();
			}

			// moves flippers back down
			if (gizmo.gizmoType().toLowerCase().equals("leftflipper")) {
				LeftFlipperGizmo flipper = (LeftFlipperGizmo) gizmo;
				flipper.setCurrentAngle(0);
				flipper.rotateOnPivot(false);
				flipper.redraw();
			} else if (gizmo.gizmoType().toLowerCase().equals("rightflipper")) {
				RightFlipperGizmo flipper = (RightFlipperGizmo) gizmo;
				flipper.setCurrentAngle(0);
				flipper.rotateOnPivot(false);
				flipper.redraw();
			}
		}
	}

	private void loopGizmos() {
		rotatingFlippers.clear();

		for (IGizmo gizmo : gizmos.values()) {

			gizmo.cooldownHit();

			if (gizmo.gizmoType().toLowerCase().equals("leftflipper")) {
				LeftFlipperGizmo flipper = (LeftFlipperGizmo) gizmo;
				
				if (flipper.triggered()) {
					// check if already on maxAngle
					// if not set rotateOnPivot to true
					// get lines and circles, rotate them via pivot
					
					if (flipper.getCurrentAngle() != flipper.getMaxAngle()) {
						flipper.rotateOnPivot(true);
						rotatingFlippers.add(flipper); // add to the list of
														// rotating flippers
						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();
						
						// remove from global list
						for (LineSegment l : lines)
							linesToGizmos.remove(l);
						for (Circle c : corners)
							circlesToGizmos.remove(c);

						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();

						double angle = (double) (-1 * flipper.getAngularVel() / (1 / currentTick));

						if (angle + flipper.getCurrentAngle() > flipper.getMaxAngle())
							angle = flipper.getMaxAngle() - flipper.getCurrentAngle();

						Angle newAngle = new Angle((360 - angle)*(Math.PI/180));
						
						for (LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for (Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));

						flipper.setCorners(newCorners);
						flipper.setLines(newLines);

						flipper.setCurrentAngle(flipper.getCurrentAngle() + angle);

						// add back to global list
						if (flipper.getCurrentAngle() == flipper.getMaxAngle() || flipper.getCurrentAngle() == 0.0) {
							for (LineSegment l : newLines)
								linesToGizmos.put(l, gizmo);
							for (Circle c : corners)
								circlesToGizmos.put(c, gizmo);
						}
					} else {
						flipper.rotateOnPivot(false);
						rotatingFlippers.remove(flipper);
						continue;
					}

				} else {// NOT TRIGGERED move back down
					if (flipper.getCurrentAngle() != 0) {
						flipper.rotateOnPivot(true);
						rotatingFlippers.add(flipper);

						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();

						// remove from global list
						for (LineSegment l : lines)
							linesToGizmos.remove(l);
						for (Circle c : corners)
							circlesToGizmos.remove(c);

						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();

						double angle = (double) (-1 * flipper.getAngularVel() / (1 / currentTick));

						if (flipper.getCurrentAngle() - angle < 0)
							angle = flipper.getCurrentAngle();

						Angle newAngle = new Angle(angle*(Math.PI/180));

						for (LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for (Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));

						flipper.setCorners(newCorners);
						flipper.setLines(newLines);

						flipper.setCurrentAngle(flipper.getCurrentAngle() - angle);

						// add back to global list
						if (flipper.getCurrentAngle() == 0) {
							for (LineSegment l : newLines)
								linesToGizmos.put(l, gizmo);
							for (Circle c : corners)
								circlesToGizmos.put(c, gizmo);
						}
					} else {
						flipper.rotateOnPivot(false);
						rotatingFlippers.remove(flipper);
						continue;
					}
				}

			} else if (gizmo.gizmoType().toLowerCase().equals("rightflipper")) {
				RightFlipperGizmo flipper = (RightFlipperGizmo) gizmo;

				if (flipper.triggered()) {
					// check if already on maxAngle
					// if not set rotateOnPivot to true
					// get lines and circles, rotate them via pivot
					if (flipper.getCurrentAngle() != flipper.getMaxAngle()) {
						flipper.rotateOnPivot(true);
						rotatingFlippers.add(flipper);

						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();

						// remove from global list
						for (LineSegment l : lines)
							linesToGizmos.remove(l);
						for (Circle c : corners)
							circlesToGizmos.remove(c);

						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();

						double angle = (double) (flipper.getAngularVel() / (1 / currentTick));

						if (angle + flipper.getCurrentAngle() > flipper.getMaxAngle())
							angle = flipper.getMaxAngle() - flipper.getCurrentAngle();

						Angle newAngle = new Angle(angle*(Math.PI/180));

						for (LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for (Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));

						flipper.setCorners(newCorners);
						flipper.setLines(newLines);

						flipper.setCurrentAngle(flipper.getCurrentAngle() + angle);

						// add back to global list
						if (flipper.getCurrentAngle() == flipper.getMaxAngle() || flipper.getCurrentAngle() == 0.0) {
							for (LineSegment l : newLines)
								linesToGizmos.put(l, gizmo);
							for (Circle c : corners)
								circlesToGizmos.put(c, gizmo);
						}
					} else {
						flipper.rotateOnPivot(false);
						rotatingFlippers.remove(flipper);
						continue;
					}

				} else {// NOT TRIGGERED move back down
					if (flipper.getCurrentAngle() != 0) {
						flipper.rotateOnPivot(true);
						rotatingFlippers.add(flipper);

						List<LineSegment> lines = flipper.getLines();
						List<Circle> corners = flipper.getCorners();

						// remove from global list
						for (LineSegment l : lines) {
							linesToGizmos.remove(l);
						}
						for (Circle c : corners) {
							circlesToGizmos.remove(c);
						}

						List<LineSegment> newLines = new ArrayList<LineSegment>();
						List<Circle> newCorners = new ArrayList<Circle>();
						Vect pivot = corners.get(0).getCenter();

						double angle = (double) (flipper.getAngularVel() / (1 / currentTick));

						if (flipper.getCurrentAngle() - angle < 0)
							angle = flipper.getCurrentAngle();

						Angle newAngle = new Angle((360 - angle)*(Math.PI/180));

						for (LineSegment line : lines)
							newLines.add(Geometry.rotateAround(line, pivot, newAngle));
						for (Circle corner : corners)
							newCorners.add(Geometry.rotateAround(corner, pivot, newAngle));

						flipper.setCorners(newCorners);
						flipper.setLines(newLines);

						flipper.setCurrentAngle(flipper.getCurrentAngle() - angle);

						// add back to global list
						if (flipper.getCurrentAngle() == 0) {
							for (LineSegment l : newLines)
								linesToGizmos.put(l, gizmo);
							for (Circle c : corners)
								circlesToGizmos.put(c, gizmo);
						}
					} else {
						flipper.rotateOnPivot(false);
						rotatingFlippers.remove(flipper);
						continue;
					}
				}
			} else if (gizmo.gizmoType().equals("absorber")) {
				AbsorberGizmo abs = (AbsorberGizmo) gizmo;
				fireAbsorber(abs);
			}
		}
	}

	private void moveBalls() {
		CollisionInfo colInfo = timeUntilCollision();
		double colTime = colInfo.getColTime();
		if(colTime < time){
			currentTick = colTime;
		}
		Ball colBall = colInfo.getCollidingBall();
		Ball colBall2 = colInfo.getCollidingBall2();

		// collisions
		if (colTime < time) {
			// move colliding ball
			if (colBall != null) {
				if (!(colBall.isAbsorbed() || colBall.paused())) {
					calculateBallMove(colBall, colTime);
					colBall.setVelocity(colInfo.getUpdatedVel());
					applyFriction(colBall, colTime);
					applyGravity(colBall, colTime);
				}
			}
			// move colliding ball2
			if (colBall2 != null) {
				if (!(colBall2.isAbsorbed() || colBall2.paused())) {
					calculateBallMove(colBall2, colTime);
					colBall2.setVelocity(colInfo.getUpdatedVel2());
					applyFriction(colBall2, colTime);
					applyGravity(colBall2, colTime);
				}
			}
			// move rest of balls
			for (Ball ball : balls.values()) {
				if (ball.equals(colBall) || ball.equals(colBall2) || (ball.isAbsorbed() || ball.paused()))
					continue;
				calculateBallMove(ball, colTime);
				applyFriction(ball, colTime);
				applyGravity(ball, colTime);
			}
		}
		// no collision. normal movement
		else {
			for (Ball ball : balls.values()) {
				if (ball.isAbsorbed() || ball.paused())
					continue;
				calculateBallMove(ball, currentTick);
				applyFriction(ball, currentTick);
				applyGravity(ball, currentTick);
			}
		}
		this.setChanged();
		this.notifyObservers(); // update board both in gui and model
	}

	private void calculateBallMove(Ball ball, double newTime) {
		double x = ball.getX() + (ball.getVelocity().x() * newTime);
		double y = ball.getY() + (ball.getVelocity().y() * newTime);
		ball.setX(x);
		ball.setY(y);
	}

	private class CollisionInfo {
		double colTime;
		Ball collidingBall;
		Ball collidingBall2;
		Vect updatedVel;
		Vect updatedVel2;

		public Ball getCollidingBall2() {
			return collidingBall2;
		}

		public Vect getUpdatedVel2() {
			return updatedVel2;
		}

		public CollisionInfo(double t, Ball b, Vect v, Ball b2, Vect v2) {
			colTime = t;
			collidingBall = b;
			updatedVel = v;
			collidingBall2 = b2;
			updatedVel2 = v2;
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

	}

	public void keyPressed(String key) {
		List<IGizmo> keyGiz = keylistToGizmos.get(key);
		if (keyGiz == null)
			return;

		for (IGizmo gizmo : keyGiz) {
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

	public double[] getFriction() {
		double[] friction = new double[2];
		friction[0] = frictionMU;
		friction[1] = frictionMUTwo;
		return friction;
	}

	public double getGravity() {
		return gravity;
	}

	private boolean validatePosition(double sx, double sy, double ex, double ey, IGizmo giz) {
		if (sx < 0 || ex > boardSize || sy < 0 || ey > boardSize)
			return false;

		// check if given coordinates overlaps with any other gizmo position
		for (IGizmo gizmo : gizmos.values()) {
			if (!gizmo.equals(giz)) {
				if (sx < gizmo.getEndX() && ex > gizmo.getStartX() && sy < gizmo.getEndY() && ey > gizmo.getStartY())
					return false;
			}
		}

		for (Ball b : balls.values()) {
			if (sx < b.getEndX() && ex > b.getStartX() && sy < b.getEndY() && ey > b.getStartY())
				return false;
		}

		return true;
	}

	private boolean validatePosition(double sx, double sy, double ex, double ey, Ball ball) {
		if (sx < 0 || ex > boardSize || sy < 0 || ey > boardSize)
			return false;

		// check if given coordinates overlaps with any other gizmo position
		for (IGizmo gizmo : gizmos.values()) {
			if (sx < gizmo.getEndX() && ex > gizmo.getStartX() && sy < gizmo.getEndY() && ey > gizmo.getStartY())
				return false;
		}

		for (Ball b : balls.values()) {
			if (!b.equals(ball)) {
				if (sx < b.getEndX() && ex > b.getStartX() && sy < b.getEndY() && ey > b.getStartY())
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

		for (Ball b : balls.values()) {
			if (sx < b.getEndX() && ex > b.getStartX() && sy < b.getEndY() && ey > b.getStartY())
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
	private boolean addGizmo(String gizmo, String key, int x, int y) {
		switch (gizmo.toLowerCase()) {
		case "triangle":
			if (addGizmo(new TriangleGizmo(x, y), key))
				return true;
			break;
		case "circle":
			if (addGizmo(new CircleGizmo(x, y), key))
				return true;
			break;
		case "square":
			if (addGizmo(new SquareGizmo(x, y), key))
				return true;
			break;
		case "rightflipper":
			if (addGizmo(new RightFlipperGizmo(x, y), key))
				return true;
			
			break;
		case "leftflipper":
			if (addGizmo(new LeftFlipperGizmo(x, y), key))
				return true;
			break;
		}

		System.out.println("Cannot add gizmo at (" + x + "," + y + ")");
		return false;
	}

	private boolean addGizmo(IGizmo gizmo, String key) {
		if (!validatePosition(gizmo.getStartX(), gizmo.getStartY(), gizmo.getEndX(), gizmo.getEndY()))
			return false;

		gizmo.setKey(key);

		// add gizmo to gizmo list
		gizmos.put(key, gizmo);

		this.setChanged();
		this.notifyObservers(); // call the observer to redraw the added gizmo

		return true;
	}

	private boolean addBall(String key, double x, double y, double velx, double vely) {
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

	private boolean addAbsorber(String key, int x, int y, int ex, int ey) {
		if (!validatePosition(x, y, ex, ey))
			return false;

		Gizmo gizmo = new AbsorberGizmo(x, y, ex, ey);
		// add to list of gizmos
		gizmos.put(key, gizmo);
		gizmo.setKey(key);
		// draw absorber
		Queue<Ball> q = new LinkedList<Ball>();
		absorberToBalls.put(gizmo, q); // adds fresh keys to the hashmap

		this.setChanged();
		this.notifyObservers();
		return true;
	}

	public boolean addAbsorber(int x, int y, int ex, int ey) {
		if (x > ex) {
			int temp = x;
			x = ex;
			ex = temp;
		}

		if (y > ey) {
			int temp = y;
			y = ey;
			ey = temp;
		}

		String xCoord = String.valueOf(x);
		String yCoord = String.valueOf(y);

		if (x < 10)
			xCoord = "0" + xCoord;
		if (y < 10)
			yCoord = "0" + yCoord;

		String uniqueKey = "absorber" + xCoord + yCoord;
		return addAbsorber(uniqueKey, x, y, ex, ey);
	}

	// return key for gizmo at (x,y)
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
		if (key == null)
			return false;
		else if (gizmos.get(key).gizmoType().toLowerCase().equals("absorber"))
			return false;


		rotateGizmo(key);
		return true;
	}

	private void rotateGizmo(String key) {
		gizmos.get(key).rotate();

		this.setChanged();
		this.notifyObservers();
	}

	private void connectGizmo(IGizmo gizmo1, IGizmo gizmo2) {
		if (gizmo2.getOutgoingConnections() != null)
			for (IGizmo outgoingGizmo : gizmo2.getOutgoingConnections()) {
				outgoingGizmo.removeIncomingConnection(gizmo2);
				outgoingGizmo.clearOutgoingConnections();
			}

		if (!gizmo1.getIncomingConnections().isEmpty()) {
			for (IGizmo incomingCon : gizmo1.getIncomingConnections())
				incomingCon.clearOutgoingConnections();
			gizmo1.clearIncomingConnections();
		}

		gizmo1.addOutgoingConnection(gizmo2);
		gizmo2.addIncomingConnection(gizmo1);
	}

	@Override
	public boolean connectGizmo(int x1, int y1, int x2, int y2) {
		String gizmo1 = findGizmo(x1, y1);
		String gizmo2 = findGizmo(x2, y2);
		if (gizmo1 != null && gizmo2 != null) {
			connectGizmo(gizmos.get(gizmo1), gizmos.get(gizmo2));
			return true;
		}
		return false;
	}

	private void disconnectGizmo(IGizmo gizmo) {

		if (!gizmo.getOutgoingConnections().isEmpty()) {
			for (IGizmo outgoingGizmo : gizmo.getOutgoingConnections()) {
				outgoingGizmo.removeIncomingConnection(gizmo);
				outgoingGizmo.clearOutgoingConnections();
			}
			gizmo.clearOutgoingConnections();
		}

		if (!gizmo.getIncomingConnections().isEmpty()) {
			for (IGizmo incomingCon : gizmo.getIncomingConnections()) {
				incomingCon.clearOutgoingConnections();
			}
			gizmo.clearIncomingConnections();
		}
		removeKeyPress(gizmo);
	}

	@Override
	public boolean disconnectGizmo(int x, int y) {
		String gizmo = findGizmo(x, y);
		if (gizmo != null) {
			disconnectGizmo(gizmos.get(gizmo));
			return true;
		}
		return false;
	}

	private void keyConnectGizmo(IGizmo gizmo, String k) {
		// gizmo.setKeyboardPress(k);
		List<IGizmo> tempList = keylistToGizmos.get(k);

		if (tempList == null)
			tempList = new ArrayList<IGizmo>();
		tempList.add(gizmo);
		keylistToGizmos.put(k, tempList);
	}

	@Override
	public boolean keyConnectGizmo(int x, int y, String k) {
		String gizmo = findGizmo(x, y);
		if (gizmo != null) {
			if (gizmos.get(gizmo).gizmoType().equals("absorber") && k.contains("up")) {
				return false;
			}
			keyConnectGizmo(gizmos.get(gizmo), k);
			return true;
		}
		return false;
	}

	private void removeKeyPress(IGizmo gizmo) {
		for (String key : keylistToGizmos.keySet()) {
			keylistToGizmos.get(key).remove(gizmo);
		}
	}

	private void deleteGizmo(String key) {
		gizmos.remove(key);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public boolean deleteGizmo(int x, int y) {
		String gizmoKey = findGizmo(x, y);

		if (gizmoKey != null) {
			deleteGizmo(gizmoKey);
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		gizmos.clear();
		balls.clear();
		keylistToGizmos.clear();
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void setFriction(double f, double fTwo) {
		frictionMU = f;
		frictionMUTwo = fTwo;
	}

	private void applyFriction(Ball ball, double t) {
		double mu = frictionMU;
		double mu2 = frictionMUTwo;
		
		double fValue = (1 - mu * t - ball.getVelocity().length() * mu2 * t);
		ball.setVelocity(ball.getVelocity().times(fValue));
	}

	@Override
	public void setGravity(double g) {
		gravity = g;
	}

	private void applyGravity(Ball ball, double t) {
		Vect currentVel = ball.getVelocity();
		Vect velGravity = new Vect(currentVel.x(), (currentVel.y() + (gravity * t)));
		ball.setVelocity(velGravity);
	}

	private boolean moveGizmo(int x, int y, IGizmo gizmo, String key) {
		if (validatePosition(x, y, x + gizmo.getSize(), y + gizmo.getSize(), gizmo)) {
			gizmo.newPosition(x, y);

			// change the key when moving a gizmo
			String type = String.valueOf(gizmo.gizmoType().charAt(0));
			String xCoord = String.valueOf(x);
			String yCoord = String.valueOf(y);

			if (x < 10)
				xCoord = "0" + xCoord;
			if (y < 10)
				yCoord = "0" + yCoord;

			String uniqueKey = type + xCoord + yCoord;

			gizmos.remove(key);
			gizmos.put(uniqueKey, gizmo);

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
		else if (gizmos.get(gizmoKey).gizmoType().toLowerCase().equals("absorber"))
			return false;
		else
			gizmo = gizmos.get(gizmoKey);

		return moveGizmo(newX, newY, gizmo, gizmoKey);
	}

	@Override
	public void save(File f) {
		FileWriter writer;
		resetBalls();

		try {
			writer = new FileWriter(f);

			for (String key : gizmos.keySet()) {
				IGizmo gizmo = gizmos.get(key);

				// Gizmo
				// capitalises first letter
				if (gizmo.gizmoType() != "absorber") {
					String type = gizmo.gizmoType().substring(0, 1).toUpperCase()
							+ gizmo.gizmoType().substring(1).toLowerCase();
					String x = String.valueOf(gizmo.getStartX());
					String y = String.valueOf(gizmo.getStartY());
					String gizmoString = type + " " + key + " " + x + " " + y + "\n";
					writer.write(gizmoString);
				} else {
					String type = gizmo.gizmoType().substring(0, 1).toUpperCase()
							+ gizmo.gizmoType().substring(1).toLowerCase();
					String x = String.valueOf(gizmo.getStartX());
					String y = String.valueOf(gizmo.getStartY());
					String x2 = String.valueOf(gizmo.getEndX());
					String y2 = String.valueOf(gizmo.getEndY());
					String gizmoString = type + " " + key + " " + x + " " + y + " " + x2 + " " + y2 + "\n";
					writer.write(gizmoString);
				}

				// Rotate
				int rot = gizmo.getRotation() / 90;
				for (int i = 0; i < rot; i++) {
					String rotation = "Rotate " + key + "\n";
					writer.write(rotation);
				}

			}

			// connect
			for (String key : gizmos.keySet()) {
				IGizmo gizmo = gizmos.get(key);
				// Connect keyword
				if (gizmo.getOutgoingConnections() != null) {
					for (IGizmo outgoingGizmo : gizmo.getOutgoingConnections()) {
						String connect = "Connect " + key + " " + outgoingGizmo.getKey() + "\n";
						writer.write(connect);
					}
				}
			}

			// Keys
			for (String key : keylistToGizmos.keySet()) {
				List<IGizmo> g = keylistToGizmos.get(key);
				for (int i = 0; i < g.size(); i++) {
					String keyConnection = "KeyConnect key " + key + " " + g.get(i).getKey() + "\n";
					writer.write(keyConnection);
				}
			}

			// Ball
			for (Ball ball : balls.values()) {
				String ballString = "Ball " + ball.getKey() + " " + String.valueOf(ball.getHomeX()) + " "
						+ String.valueOf(ball.getHomeY()) + " " + String.valueOf(ball.getStartXVel()) + " "
						+ String.valueOf(ball.getStartYVel()) + "\n";
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
	public void load(File f) throws InvalidLineException, NumberFormatException {
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
						errorMessage
								.error("Skipping instruction at line " + lineNumber + " invalid gizmos instruction");
					else if (!addGizmo(splitCommand[0], splitCommand[1], (Integer.parseInt(splitCommand[2])),
							(Integer.parseInt(splitCommand[3]))))
						errorMessage.error("Skipping instruction at line " + lineNumber + " overlapping gizmo");
					break;
				case "absorber":
					if (splitCommand.length != 6)
						errorMessage
								.error("Skipping instruction at line " + lineNumber + " invalid absorber instruction");
					else if (!addAbsorber(splitCommand[1], Integer.parseInt(splitCommand[2]),
							Integer.parseInt(splitCommand[3]), Integer.parseInt(splitCommand[4]),
							Integer.parseInt(splitCommand[5])))
						errorMessage.error("Skipping instruction at line " + lineNumber + " overlapping gizmo");
					break;
				case "ball":
					if (splitCommand.length != 6)
						errorMessage.error("Skipping instruction at line " + lineNumber + " invalid ball instruction");
					else if (!addBall(splitCommand[1], Double.parseDouble(splitCommand[2]),
							Double.parseDouble(splitCommand[3]), Double.parseDouble(splitCommand[4]),
							Double.parseDouble(splitCommand[5])))
						errorMessage.error("Skipping instruction at line " + lineNumber + " overlapping ball");

					break;
				case "rotate":
					if (splitCommand.length != 2)
						errorMessage
								.error("Skipping instruction at line " + lineNumber + " invalid rotate instruction");
					else
						rotateGizmo(splitCommand[1]);
					break;
				case "delete":
					if (splitCommand.length != 2)
						errorMessage
								.error("Skipping instruction at line " + lineNumber + " invalid delete instruction");
					else
						deleteGizmo(splitCommand[1]);
					break;
				case "move":
					if (splitCommand.length != 4)
						errorMessage.error("Skipping instruction at line " + lineNumber + " invalid move instruction");
					else if (!moveGizmo(Integer.parseInt(splitCommand[2]), Integer.parseInt(splitCommand[3]),
							gizmos.get(splitCommand[1]), splitCommand[1]))
						errorMessage.error("Skipping instruction at line " + lineNumber + " invalid move location");
					break;
				case "keyconnect":
					if (splitCommand.length != 5)
						errorMessage.error(
								"Skipping instruction at line " + lineNumber + " invalid key connect instruction");
					else {
						keyConnectGizmo(gizmos.get(splitCommand[4]), splitCommand[2] + " " + splitCommand[3]);
					}
					break;
				case "connect":
					if (splitCommand.length != 3)
						errorMessage
								.error("Skipping instruction at line " + lineNumber + " invalid connect instruction");
					else
						connectGizmo(gizmos.get(splitCommand[1]), gizmos.get(splitCommand[2]));
					break;
				case "friction":
					if (splitCommand.length != 3)
						errorMessage
								.error("Skipping instruction at line " + lineNumber + " invalid friction instruction");
					else {
						setFriction(Double.parseDouble((splitCommand[1])), Double.parseDouble((splitCommand[2])));
					}
					break;
				case "gravity":
					if (splitCommand.length != 2) {
						errorMessage
								.error("Skipping instruction at line " + lineNumber + " invalid gravity instruction");
					} else {
						setGravity(Double.parseDouble((splitCommand[1])));
					}
					break;
				case "":
					break;
				default:
					errorMessage.error("Invalid Line Ignoring");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			errorMessage.error("No file found");
		} catch (IOException e) {
			errorMessage.error("IO Exception");
		}

		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public List<IDrawableGizmo> drawableGizmos() {
		List<IDrawableGizmo> drawables = new ArrayList<IDrawableGizmo>();

		for (IGizmo gizmo : gizmos.values()) {
			drawables.add(new DrawableGizmo(gizmo));
		}

		return drawables;
	}

	@Override
	public List<IDrawableBall> drawableBalls() {
		List<IDrawableBall> drawables = new ArrayList<IDrawableBall>();

		for (Ball ball : balls.values()) {
			drawables.add(new DrawableBall(ball));
		}

		return drawables;
	}

	@Override
	public String containsGizmo(double x, double y) {
		String key = findGizmo((int) x, (int) y);
		if (key != null)
			return "g";
		key = findBall(x, y);
		if (key == null)
			return null;
		return "b";
	}

	@Override
	public boolean deleteBall(double x, double y) {
		String ballKey = findBall(x, y);
		if (ballKey != null) {
			deleteBall(ballKey);
			return true;
		}
		return false;
	}

	private void deleteBall(String key) {
		balls.remove(key);
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public boolean moveBall(double x, double y, double newX, double newY) {
		String ballKey = findBall(x, y);

		if (ballKey != null) {
			return moveBall(ballKey, newX, newY);
		}
		return false;
	}

	private boolean moveBall(String key, double x, double y) {
		Ball b = balls.get(key);
		if (validatePosition(x - b.getRadius(), y - b.getRadius(), x + b.getRadius(), y + b.getRadius(), b)) {
			b.setNewX(x);
			b.setNewY(y);
			b.setX(x);
			b.setY(y);
			this.setChanged();
			this.notifyObservers();
			return true;
		}

		return false;
	}

	private String findBall(double x, double y) {

		for (String key : balls.keySet()) {
			if ((x > balls.get(key).getStartX()) && (x < balls.get(key).getEndX()) && (y > balls.get(key).getStartY())
					&& (y < balls.get(key).getEndY()))
				return key;
		}

		return null;
	}

	@Override
	public void resetBalls() {
		for (Ball b : balls.values()) {
			b.resetBall();
			b.resume();
			b.setAbsorbed(false);
		}
	}


}
