package jUnit;

import static org.junit.Assert.*;

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
		model.tick();
	}
	
	@Test
	public void trigger(){
		model.keyPressed(ak);
	}
	
	@Test
	public void GizmoList(){
		assertTrue(containsGizmo(model.drawableGizmos()));
	}
	
	public boolean containsGizmo(List<IDrawableGizmo> g){
		for(IDrawableGizmo gizmo : g){
			if(gizmo.getGizmoType().equals("square")&&gizmo.getStartX()==gx&&gizmo.getStartY()==gy)
				return true;						
		}
		return false;		
	}
	
	@Test
	public void ballList(){
		assertTrue(containsBall(model.drawableBalls()));
	}
	
	public boolean containsBall(List<IDrawableBall> b){
		for(IDrawableBall ball : b){
			if(ball.getX()==bx && ball.getY()==by)
				return true;						
		}
		return false;		
	}
	
}
