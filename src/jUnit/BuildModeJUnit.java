package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.ErrorMessage;
import model.Model;

public class BuildModeJUnit {

	private Model model;
	private String gizmo;
	private int x, y, ex, ey;
	private double xd, yd, velx, vely;

	@Before
	public void setup() {
		ErrorMessage eM = new ErrorMessage();
		model = new Model(eM);
		gizmo = "square";
		x = 1;
		y = 1;
		xd = 1;
		yd = 1;
		velx = 1;
		vely = 1;
		ex = 2;
		ey = 2;
	}

	@Test
	public void addSquare() {
		assertTrue(model.addGizmo("square", x, y));
	}

	@Test
	public void addCircle() {
		assertTrue(model.addGizmo("circle", x, y));
	}

	@Test
	public void addTriangle() {
		assertTrue(model.addGizmo("triangle", x, y));
	}

	@Test
	public void addRightFlipper() {
		assertTrue(model.addGizmo("rightflipper", x, y));
	}

	@Test
	public void addLeftFlipper() {
		assertTrue(model.addGizmo("leftflipper", x, y));
	}

	@Test
	public void addGizmo() {
		assertTrue(model.addGizmo(gizmo, x, y));
	}

	@Test
	public void addGizmoInvalidName() {
		assertFalse(model.addGizmo("s", x, y));
	}

	@Test
	public void addGizmoInvalidCoordinate() {
		assertFalse(model.addGizmo("s", 30, 30));
	}

	@Test
	public void addGizmoOverlapping() {
		model.addGizmo(gizmo, x, y);
		assertFalse(model.addGizmo(gizmo, x, y));
	}

	@Test
	public void addGizmoOverlappingBall() {
		model.addBall(xd, yd, velx, vely);
		assertFalse(model.addGizmo(gizmo, x, y));
	}

	@Test
	public void addBall() {
		assertTrue(model.addBall(xd, yd, velx, vely));
	}

	@Test
	public void addBallInvalidCoordinate() {
		assertFalse(model.addBall(30, 30, velx, vely));
	}

	@Test
	public void addBallOverLapping() {
		model.addBall(xd, yd, velx, vely);
		assertFalse(model.addBall(xd, yd, velx, vely));
	}

	@Test
	public void addBallOverLappingGizmo() {
		model.addGizmo(gizmo, x, y);
		assertFalse(model.addBall(xd, yd, velx, vely));
	}

	@Test
	public void addAbsorber() {
		assertTrue(model.addAbsorber(x, y, ex, ey));
	}

	@Test
	public void addAbsorberInvalidCoordinate() {
		assertFalse(model.addAbsorber(30, 30, ex, ey));
	}

	@Test
	public void addAbsorberOverlapping() {
		model.addAbsorber(x, y, ex, ey);
		assertFalse(model.addAbsorber(x, y, ex, ey));
	}

	@Test
	public void rotateGizmo() {
		model.addGizmo(gizmo, x, y);
		assertTrue(model.rotateGizmo(x, y));
	}

	@Test
	public void rotateGizmoNoGizmo() {
		assertFalse(model.rotateGizmo(x, y));
	}

	@Test
	public void rotateGizmoAbsorber() {
		model.addAbsorber(x, y, ex, ey);
		assertFalse(model.rotateGizmo(x, y));
	}

	@Test
	public void deleteGizmo() {
		model.addGizmo(gizmo, x, y);
		assertTrue(model.deleteGizmo(x, y));
	}

	@Test
	public void deleteGizmonoGizmo() {
		assertFalse(model.deleteGizmo(x, y));
	}

	@Test
	public void deleteBall() {
		model.addBall(xd, yd, velx, vely);
		assertTrue(model.deleteBall(xd, yd));
	}

	@Test
	public void deleteBallNoBall() {
		assertFalse(model.deleteBall(xd, yd));
	}

	@Test
	public void clear() {
		model.addGizmo(gizmo, x, y);
		model.clear();
		assertFalse(model.containsGizmo(x, y));
	}

	@Test
	public void friction() {
		model.setFriction(.25, .25);
		assertEquals(model.getFriction()[0], 0.25, 0.001);
	}

	@Test
	public void gravity() {
		model.setGravity(9.8);
		assertEquals(model.getGravity(), 9.8, 0.001);
	}

	@Test
	public void moveGizmo() {
		model.addGizmo(gizmo, x, y);
		assertTrue(model.moveGizmo(x, y, ex, ey));
	}

	@Test
	public void moveGizmoNoGizmo() {
		assertFalse(model.moveGizmo(x, y, ex, ey));
	}

	@Test
	public void moveGizmoAbsorber() {
		model.addAbsorber(x, y, ex, ey);
		assertFalse(model.moveGizmo(x, y, ex, ey));
	}

	@Test
	public void moveInvalidCoordinate() {
		model.addGizmo(gizmo, x, y);
		assertFalse(model.moveGizmo(x, y, 30, 30));
	}

	@Test
	public void moveOverlapping() {
		model.addGizmo(gizmo, x, y);
		model.addGizmo(gizmo, ex, ey);
		assertFalse(model.moveGizmo(x, y, ex, ey));
	}

	@Test
	public void moveBall() {
		model.addBall(xd, yd, velx, vely);
		assertTrue(model.moveBall(xd, yd, 10, 10));
	}

	@Test
	public void moveBallNoBall() {
		assertFalse(model.moveBall(xd, yd, 10, 10));
	}

	@Test
	public void moveBallInvalidCoordinate() {
		model.addBall(xd, yd, velx, vely);
		assertFalse(model.moveBall(xd, yd, 100, 100));
	}

	@Test
	public void keyConnect() {
		model.addGizmo(gizmo, x, y);
		assertTrue(model.keyConnectGizmo(x, y, "32"));
	}

	@Test
	public void keyConnectNoGizmo() {
		model.addGizmo(gizmo, x, y);
		assertTrue(model.keyConnectGizmo(x, y, "32"));
	}

	@Test
	public void connect() {
		model.addGizmo(gizmo, x, y);
		model.addGizmo(gizmo, ex, ey);
		assertTrue(model.connectGizmo(x, y, ex, ey));
	}

	@Test
	public void connect2() {
		model.addGizmo(gizmo, x, y);
		model.addGizmo(gizmo, ex, ey);
		model.addGizmo(gizmo, 3, 3);
		model.connectGizmo(x, y, ex, ey);
		model.connectGizmo(x, y, 3, 3);
		assertTrue(model.connectGizmo(3, 3, ex, ey));
	}

	@Test
	public void connectNoGizmo() {
		assertFalse(model.connectGizmo(x, y, ex, ey));
	}

	@Test
	public void disconnectGizmo() {
		model.addGizmo(gizmo, x, y);
		model.addGizmo(gizmo, ex, ey);
		model.connectGizmo(x, y, ex, ey);
		assertTrue(model.disconnectGizmo(x, y));
	}

	@Test
	public void disconnectGizmo2() {
		model.addGizmo(gizmo, x, y);
		model.addGizmo(gizmo, ex, ey);
		model.connectGizmo(ex, ey, x, y);
		assertTrue(model.disconnectGizmo(x, y));
	}

	@Test
	public void disconnectGizmoNoConnection() {
		model.addGizmo(gizmo, x, y);
		model.addGizmo(gizmo, ex, ey);
		assertTrue(model.disconnectGizmo(x, y));
	}

	@Test
	public void resetBall() {
		model.addBall(xd, yd, velx, vely);
		model.resetBalls();
	}
}
