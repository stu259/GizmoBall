package model;
import java.awt.Color;
import physics.*;

public class Ball {
	private Vect vel;
	private double minSpeed, maxSpeed, radius, x, y, mass, startX, startY, 
	               startXVel, startYVel;
	private Color color;
	private boolean paused, absorbed;
	private String key;
	
	/*
	 * Direction must be in radians
	 */
	public Ball(double x, double y, double xVel, double yVel){
		this.x = x;
		this.y = y;
		minSpeed = -200;
		maxSpeed = 200;
		vel = new Vect(xVel, yVel);
		color = Color.CYAN;
		radius = 0.25;
		paused = false;
		absorbed = false;
		key = null;
		mass = 1;
		startX = x;
		startY = y;
		startXVel = xVel;
		startYVel = yVel;
	}

	
	
	public double getMass(){
		return mass;
	}
	
	public void pause(){
		paused = true;
	}
	
	public void resume(){
		paused = false;
	}
	
	public boolean paused(){
		return paused;
	}
	
	/*
	 * Return the X coordinate of the ball
	 */
	public double getX() {
		return x;
	}

	/*
	 * Return the Y coordinate of the ball
	 */
	public double getY() {
		return y;
	}

	/*
	 * Return assigned key
	 */
	public String getKey(){
		return key;
	}
	
	/*
	 * Set the key
	 */
	public void setKey(String key){
		this.key = key;
	}
	
	/*
	 * Returns whether or not the ball is absorbed
	 */
	public boolean isAbsorbed() {
		return absorbed;
	}

	/*
	 * Sets whether or not the ball is absorbed
	 */
	public void setAbsorbed(boolean absorbed) {
		this.absorbed = absorbed;
	}
	
	/*
	 * Returns the velocity of the ball
	 */
	public Vect getVelocity(){
		return vel;
	}
	
	/*
	 * Returns the radius of the ball
	 */
	public double getRadius(){
		return radius;
	}
	
	/*
	 * Return get color of the ball
	 */
	public Color getColor(){
		return color;
	}
	
	/*
	 * Returns a Circle object version of the ball
	 */
	public Circle getCircle(){
		return new Circle(x, y, radius);
	}
	
	/*
	 * Sets the x coordinate of the ball
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/*
	 * Sets the y coordinate of the ball
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/*
	 * returns the center of the ball
	 */
	public Vect getCenter(){
		return new Vect(x,y);
	}
	
	/*
	 * Set the color of the ball
	 */
	public void setColor(Color color){
		this.color = color;
	}
	
	/*
	 * Set the velocity of the ball
	 * Params: xVel, yVel
	 */
	public void setVelocity(double xVel, double yVel){
		//Validation to ensure speed doesn't exceed limits
		if(xVel > maxSpeed)
			xVel = maxSpeed;
		else if(xVel < minSpeed)
			xVel = minSpeed;
		
		if(yVel > maxSpeed)
			yVel = maxSpeed;
		else if(yVel < minSpeed)
			yVel = minSpeed;
		
		
		
		vel = new Vect(xVel, yVel);
	}
	/*
	 * Params: (Vect) vel
	 */
	public void setVelocity(Vect vel){
		this.vel = vel;
	}
	
	public double getStartX(){
		return x-radius;
	}
	
	public double getStartY(){
		return y-radius;
	}
	
	public double getEndX(){
		return x+radius;
	}
	
	public double getEndY(){
		return y+radius;
	}
	
	public void resetBall(){
		x = startX;
		y = startY;
		setVelocity(startXVel, startYVel);
	}
}
