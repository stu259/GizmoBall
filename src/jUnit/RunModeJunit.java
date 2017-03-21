package jUnit;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ErrorMessage;
import model.IDrawableBall;
import model.IDrawableGizmo;
import model.IModel;
import model.Model;

public class RunModeJunit {

	private IModel model;
	private String gizmo, ak;
	private int gx, gy, ax, ay, aex, aey;
	private double bx, by, bvx, bvy;

	@Before
	public void setup() {
		ErrorMessage eM = new ErrorMessage();
		model = new Model(eM);
		gizmo = "square";
		gx = 1;
		gy = 2;
		ax = 3;
		ay = 3;
		aex = 4;
		aey = 4;
		ak="32";
		bx = 1;
		by = 1;
		bvx = 0;
		bvy = 0;
		model.addGizmo(gizmo, gx, gy);
		model.addAbsorber(ax, ay, aex, aey);
		model.addBall(bx, by, bvx, bvy);
		model.keyConnectGizmo(ax, ay, ak);
	}

	@Test
	public void runMode() {
		model.runMode();
	}
	

	@Test
	public void tick() {
		model.runMode();
		model.tick();
	}
	
	@Test
	public void trigger(){
		model.runMode();
		model.keyPressed(ak);
	}
	
	@Test
	public void GizmoList(){
		model.runMode();
		assertTrue(containsGizmo(model.drawableGizmos()));
	}
	
	public boolean containsGizmo(List<IDrawableGizmo> g){
		model.runMode();
		for(IDrawableGizmo gizmo : g){
			if(gizmo.getGizmoType().equals("square")&&gizmo.getStartX()==gx&&gizmo.getStartY()==gy)
				return true;						
		}
		return false;		
	}
	
	@Test
	public void ballList(){
		model.runMode();
		assertTrue(containsBall(model.drawableBalls()));
	}
	
	public boolean containsBall(List<IDrawableBall> b){
		model.runMode();
		for(IDrawableBall ball : b){
			if(ball.getX()==bx && ball.getY()==by)
				return true;						
		}
		return false;		
	}
	
	@Test
	public void fullTest() throws InvalidLineException{
		model.load(new File("TestingTxt/fileformat.txt"));
		model.runMode();
		for(int i=0;i<100000;i++){
			model.tick();
			if(i%1000 ==0){
				model.keyPressed("65 down");
				model.keyPressed("65 up");
				model.keyPressed("32 down");
				model.keyPressed("32 up");
				model.keyPressed("81 down");
				model.keyPressed("81 up");
				model.keyPressed("87 down");
				model.keyPressed("87 up");
			}
		}

	}

	
}
