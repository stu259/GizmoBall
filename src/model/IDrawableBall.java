package model;

/*
 * This interface is used between the model and view
 * in order to ensure that the view does not have
 * more information about the ball than is required
 * e.g. the view does not need to know about the ball's velocity
 */
public interface IDrawableBall {
	public double getX();
	public double getY();
	public double getRadius();
}
