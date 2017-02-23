package model;

public class DrawableBall implements IDrawableBall{

	private Ball ball;
	
	public DrawableBall(Ball ball){
		this.ball = ball;
	}
	
	public double getX() {
		return ball.getX();
	}

	public double getY() {
		return ball.getY();
	}

	public double getRadius(){
		return ball.getRadius();
	}
}
