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
			lines.add(new LineSegment(x1, y1, x1, y2)); //left edge
			lines.add(new LineSegment(x1, y2, x2, y1)); //hypotenuse edge
			lines.add(new LineSegment(x2, y1, x1, y1)); //top edge
			circles.add(new Circle(x1, y1, 0));
			circles.add(new Circle(x2, y1, 0));
			circles.add(new Circle(x1, y2, 0));
			break;
		case 90: //right-angle is top right
			lines.add(new LineSegment(x2, y1, x1, y1)); //top edge
			lines.add(new LineSegment(x1, y1, x2, y2)); //hypotenuse edge
			lines.add(new LineSegment(x2, y2, x2, y1)); //right edge
			circles.add(new Circle(x1, y1, 0));
			circles.add(new Circle(x2, y1, 0));
			circles.add(new Circle(x2, y2, 0));
			break;
		case 180: // right-angle is bottom right
			lines.add(new LineSegment(x2, y2, x1, y2)); //bottom edge
			lines.add(new LineSegment(x1, y2, x2, y1)); //hypotenuse edge
			lines.add(new LineSegment(x2, y1, x2, y2)); //right edge
			circles.add(new Circle(x2, y2, 0));
			circles.add(new Circle(x2, y1, 0));
			circles.add(new Circle(x1, y2, 0));
			break;
		case 270: //right-angle is bottom left
			lines.add(new LineSegment(x1, y2, x2, y2)); //bottom edge
			lines.add(new LineSegment(x1, y1, x2, y2)); //hypotenuse edge
			lines.add(new LineSegment(x1, y1, x1, y2)); //left edge
			circles.add(new Circle(x1, y1, 0));
			circles.add(new Circle(x1, y2, 0));
			circles.add(new Circle(x2, y2, 0));	
		}
	}

	private void makeSquare(IGizmo gizmo) {
		int x1 = gizmo.getStartX()*boardScale;
		int x2 = gizmo.getEndX()*boardScale;
		int y1 = gizmo.getStartY()*boardScale;
		int y2 = gizmo.getEndY()*boardScale;
		
		lines.add(new LineSegment(x1, y1, x1, y2));
		lines.add(new LineSegment(x1, y2, x2, y2));
		lines.add(new LineSegment(x2, y2, x2, y1));
		lines.add(new LineSegment(x2, y1, x1, y1));
		//adds little circles to ensure that corners have collision
		circles.add(new Circle(x1, y1, 0));
		circles.add(new Circle(x1, y2, 0));
		circles.add(new Circle(x2, y1, 0));
		circles.add(new Circle(x2, y2, 0));
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
