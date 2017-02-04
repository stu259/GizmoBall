package model;
import physics.Circle;
import physics.LineSegment;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Model implements IModel{
	//			   (triggers)
	//lines/circles    ->    gizmos (color change, connections)
	//	   flippers    ->    lines/circles
	
	private List<IGizmo> gizmos;
	private List<Ball> balls;
	private List<Absorber> absorber;
	private List<LineSegment> lines;
	private List<Circle> circles;
	
	private HashMap<Circle, IGizmo> circlesToGizmos;
	private HashMap<LineSegment, IGizmo> linesToGizmos;
	private HashMap<IGizmo , LineSegment> flippersToLines;
	private HashMap<IGizmo , Circle> flippersToCircles;
	
	
	
	private int boardSize, boardScale;
	
	
	public Model(){
		boardSize = 2000; //this is 20x20*L; L=100
		boardScale = boardSize/20;
		makeWalls(boardSize);
		
		gizmos = new ArrayList<IGizmo>();
		balls = new ArrayList<Ball>();
		absorber = new ArrayList<Absorber>();
		lines = new ArrayList<LineSegment>();
		circles = new ArrayList<Circle>();
		
		linesToGizmos = new HashMap<LineSegment , IGizmo>();
		circlesToGizmos = new HashMap<Circle , IGizmo>();
		flippersToLines = new HashMap<IGizmo , LineSegment>();
		flippersToCircles = new HashMap<IGizmo , Circle>();
	}
	
	/*
	 * Called when all data from build mode is to be translated into the run mode
	 * Creates all line segments and circles to be processed for the physics engine
	 */
	@Override
	public void runMode(){
		for(IGizmo gizmo : gizmos){
			//add method to draw gizmo
			if(gizmo instanceof SquareGizmo)
				makeSquare(gizmo.copy());
			
			else if(gizmo instanceof TriangleGizmo)
				makeTriangle(gizmo.copy());
			
//			else if(gizmo instanceof RightFlipperGizmo)
//				makeRightFlipper(gizmo.copy());
//			
//			else if(gizmo instanceof LeftFlipperGizmo)
//				makeLeftFlipper(gizmo.copy());
			
			else if(gizmo instanceof CircleGizmo)
				makeCircleGizmo(gizmo.copy());
		}
	}
	
	private void makeCircleGizmo(IGizmo gizmo) {
		int x1 = gizmo.getStartX()*boardScale;
		int x2 = gizmo.getEndX()*boardScale;
		int y1 = gizmo.getStartY()*boardScale;
		int y2 = gizmo.getEndY()*boardScale;
		
		double radius = gizmo.getSize()/2;
		//get midpoints
		Circle circle = new Circle((x1+x2)/2, (y1+y2)/2, radius);
		circlesToGizmos.put(circle, gizmo);
		circles.add(circle);
	}
	
	private void makeTriangle(IGizmo gizmo) {
		//the right angled corner should be in the top-left position by default
		//THIS IS A MUST!!!! DO NOT CHANGE
		int x1 = gizmo.getStartX()*boardScale;
		int x2 = gizmo.getEndX()*boardScale;
		int y1 = gizmo.getStartY()*boardScale;
		int y2 = gizmo.getEndY()*boardScale;
		int direction = gizmo.getRotation();
		
		//keeps track of the triangle rotation for edge and corner placement
		switch(direction){
		case 0: //right-angle is top-left (this is by default)
			LineSegment line1 = new LineSegment(x1, y1, x1, y2); //left edge
			LineSegment line2 = new LineSegment(x1, y2, x2, y1); //hypotenuse edge
			LineSegment line3 = new LineSegment(x2, y1, x1, y1); //top edge
			Circle circle1 = new Circle(x1, y1, 0);
			Circle circle2 = new Circle(x2, y1, 0);
			Circle circle3 = new Circle(x1, y2, 0);
			//add lines to line list
			lines.add(line1);
			lines.add(line2);
			lines.add(line3);
			//add lines to hashmap
			linesToGizmos.put(line1, gizmo);
			linesToGizmos.put(line2, gizmo);
			linesToGizmos.put(line3, gizmo);
			//add circles to circle list
			circles.add(circle1);
			circles.add(circle2);
			circles.add(circle3);
			//add circles to hashmap
			circlesToGizmos.put(circle1, gizmo);
			circlesToGizmos.put(circle2, gizmo);
			circlesToGizmos.put(circle3, gizmo);
			break;
		case 90: //right-angle is top right
			LineSegment line4 = new LineSegment(x2, y1, x1, y1); //top edge
			LineSegment line5 = new LineSegment(x1, y1, x2, y2); //hypotenuse edge
			LineSegment line6 = new LineSegment(x2, y2, x2, y1); //right edge
			Circle circle4 = new Circle(x1, y1, 0);
			Circle circle5 = new Circle(x2, y1, 0);
			Circle circle6 = new Circle(x2, y2, 0);
			//add lines to line list
			lines.add(line4);
			lines.add(line5);
			lines.add(line6);
			//add lines to hashmap
			linesToGizmos.put(line4, gizmo);
			linesToGizmos.put(line5, gizmo);
			linesToGizmos.put(line6, gizmo);
			//add circles to circle list
			circles.add(circle4);
			circles.add(circle5);
			circles.add(circle6);
			//add circles to hashmap
			circlesToGizmos.put(circle4, gizmo);
			circlesToGizmos.put(circle5, gizmo);
			circlesToGizmos.put(circle6, gizmo);
			break;
		case 180: // right-angle is bottom right
			LineSegment line7 = new LineSegment(x2, y2, x1, y2); //bottom edge
			LineSegment line8 = new LineSegment(x1, y2, x2, y1); //hypotenuse edge
			LineSegment line9 = new LineSegment(x2, y1, x2, y2); //right edge
			Circle circle7 = new Circle(x2, y2, 0);
			Circle circle8 = new Circle(x2, y1, 0);
			Circle circle9 = new Circle(x1, y2, 0);
			//add lines to line list
			lines.add(line7);
			lines.add(line8);
			lines.add(line9);
			//add lines to hashmap
			linesToGizmos.put(line7, gizmo);
			linesToGizmos.put(line8, gizmo);
			linesToGizmos.put(line9, gizmo);
			//add circles to circle list
			circles.add(circle7);
			circles.add(circle8);
			circles.add(circle9);
			//add circles to hashmap
			circlesToGizmos.put(circle7, gizmo);
			circlesToGizmos.put(circle8, gizmo);
			circlesToGizmos.put(circle9, gizmo);
			break;
		case 270: //right-angle is bottom left
			LineSegment line10 = new LineSegment(x1, y2, x2, y2); //bottom edge
			LineSegment line11 = new LineSegment(x1, y1, x2, y2); //hypotenuse edge
			LineSegment line12 = new LineSegment(x1, y1, x1, y2); //left edge
			Circle circle10 = new Circle(x1, y1, 0);
			Circle circle11 = new Circle(x1, y2, 0);
			Circle circle12 = new Circle(x2, y2, 0);
			//add lines to line list
			lines.add(line10);
			lines.add(line11);
			lines.add(line12);
			//add lines to hashmap
			linesToGizmos.put(line10, gizmo);
			linesToGizmos.put(line11, gizmo);
			linesToGizmos.put(line12, gizmo);
			//add circles to circle list
			circles.add(circle10);
			circles.add(circle11);
			circles.add(circle12);
			//add circles to hashmap
			circlesToGizmos.put(circle10, gizmo);
			circlesToGizmos.put(circle11, gizmo);
			circlesToGizmos.put(circle12, gizmo);
			break;
		}
	}

	private void makeSquare(IGizmo gizmo) {
		//get x and y coordinates of starting and ending points of the gizmo
		int x1 = gizmo.getStartX()*boardScale;
		int x2 = gizmo.getEndX()*boardScale;
		int y1 = gizmo.getStartY()*boardScale;
		int y2 = gizmo.getEndY()*boardScale;
		
		
		//create lines and corners
		LineSegment left = new LineSegment(x1, y1, x1, y2);
		LineSegment bottom = new LineSegment(x1, y2, x2, y2);
		LineSegment right = new LineSegment(x2, y2, x2, y1);
		LineSegment top = new LineSegment(x2, y1, x1, y1);
		Circle topLeft = new Circle(x1, y1, 0);
		Circle bottomLeft = new Circle(x1, y2, 0);
		Circle topRight = new Circle(x2, y1, 0);
		Circle bottomRight = new Circle(x2, y2, 0);
		
		
		//create connections for the lines -> gizmo
		linesToGizmos.put(left, gizmo);
		linesToGizmos.put(bottom, gizmo);
		linesToGizmos.put(right, gizmo);
		linesToGizmos.put(top, gizmo);
		//create connections for the circles -> gizmo
		circlesToGizmos.put(topLeft, gizmo);
		circlesToGizmos.put(bottomLeft, gizmo);
		circlesToGizmos.put(topRight, gizmo);
		circlesToGizmos.put(bottomRight, gizmo);
		//add lines to list of alllines
		lines.add(left);
		lines.add(bottom);
		lines.add(right);
		lines.add(top);
		//adds little circles to ensure that corners have collision
		circles.add(topLeft);
		circles.add(bottomLeft);
		circles.add(topRight);
		circles.add(bottomRight);
	}
	

	@Override
	public boolean addGizmo(IGizmo gizmo) {
		//check if gizmo can be added (check outside board, position, size, then scale)
		if(gizmo.getStartX() < 0 || gizmo.getEndX() > boardSize 
				|| gizmo.getStartY() < 0 || gizmo.getEndY() > boardSize)
			return false;
		
		for(IGizmo giz : gizmos)
			if((giz.getStartX() < gizmo.getStartX() && gizmo.getStartX() < giz.getEndX())
					&& (giz.getStartY() < gizmo.getStartY() && gizmo.getStartY() < giz.getEndY()))
				return false;
		
		//TODO CHECK FOR ABSORBER OVERLAPPING AS WELL
		
		//add gizmo to gizmo list
		gizmos.add(gizmo);
		
		//TODO DRAW ABSORBERS HERE (MAYBE)
		
		return true;
	}
	
	//Adds param ball to the list of balls on the board
	public void addBall(Ball ball){
		balls.add(ball);
	}

	public void addAbsorber() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotateGizmo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectGizmo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnectGizmo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyConnectGizmo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteGizmo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFriction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGravity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveGizmo() {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Makes the walls for the outer edges of the board
	 */
	private void makeWalls(int boardSize){
		LineSegment topWall = new LineSegment(0, 0, boardSize, 0);
		LineSegment rightWall = new LineSegment(boardSize, 0, boardSize, boardSize);
		LineSegment bottomWall = new LineSegment(0, boardSize, boardSize, boardSize);
		LineSegment leftWall = new LineSegment(0, 0, 0, boardSize);
		lines.add(topWall);
		lines.add(rightWall);
		lines.add(bottomWall);
		lines.add(leftWall);
	}
}
