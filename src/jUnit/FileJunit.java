package jUnit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import controller.ErrorMessage;
import model.Model;
import view.Display;

public class FileJunit {

	private Model model;
	private Display d;
	private String gizmo, ak;
	private int gx, gy, ax, ay, aex, aey;
	private double bx, by, bvx, bvy;

	@Before
	public void setup() {
		ErrorMessage eM = new ErrorMessage();
		eM.setTest(true);
		model = new Model(eM);
		d = new Display(model);
		eM.giveDisplay(d);
		gizmo = "triangle";
		gx = 1;
		gy = 2;
		ax = 3;
		ay = 3;
		aex = 4;
		aey = 4;
		ak = "32 up";
		bx = 1;
		by = 1;
		bvx = 0;
		bvy = 0;
		model.addGizmo(gizmo, gx, gy);
		model.addAbsorber(ax, ay, aex, aey);
		model.addBall(bx, by, bvx, bvy);
		model.keyConnectGizmo(ax, ay, ak);
		model.keyConnectGizmo(ax, ay, ak);
		model.rotateGizmo(gx, gy);
		model.connectGizmo(gx, gy, ax, ay);
	}

	@Test
	public void save() throws NumberFormatException, InvalidLineException {
		model.save(new File("JUnit.txt"));
		model.load(new File("JUnit.txt"));
		model.containsGizmo(gx, gy);
	}

	@Test
	public void load() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/fileformat.txt"));
	}

	@Test(expected = InvalidLineException.class)
	public void loadInvalid2() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/fileformat2.txt"));
	}

	@Test(expected = InvalidLineException.class)
	public void loadInvalid3() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/fileformat3.txt"));
	}

	@Test(expected = InvalidLineException.class)
	public void loadInvalid4() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/fileformat4.txt"));
	}

	@Test(expected = InvalidLineException.class)
	public void loadInvalid5() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/fileformat5.txt"));
	}

	@Test(expected = InvalidLineException.class)
	public void loadInvalid6() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/fileformat6.txt"));
	}
	
	@Test(expected = InvalidLineException.class)
	public void loadInvalid7() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/fileformat7.txt"));
	}
	
	@Test
	public void loadEmpty() throws NumberFormatException, InvalidLineException {
		model.load(new File("TestingTxt/empty.txt"));
	}

}
