package model;
import java.awt.Color;
import physics.*;

public class Ball {
	private Vect vel;
	private double minSpeed, maxSpeed, radius, x, y;
	private Color color;
	
	/*
	 * Direction must be in radians
	 */
	public Ball(double x, double y, double xVel, double yVel){
		this.x = x;
		this.y = y;
		minSpeed = 0.01;
		maxSpeed = 200;
		vel = new Vect(xVel, yVel);
		color = Color.CYAN;
		radius = 0.25;
	}
	
	/*
	 * return the position of the ball
	 */
	public double[] getPosition(){
		double[] pos = {x,y};
		return pos;
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
}
