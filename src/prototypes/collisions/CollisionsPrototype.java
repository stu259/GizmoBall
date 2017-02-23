package prototypes.collisions;

import java.io.File;

import model.Model;
import view.Display;

public class CollisionsPrototype {
	
	public static void main(String[] args) {
		Model m = new Model();
		
		m.runMode();
//		
////		m.addGizmo("square", "s0", 7, 10);
//		m.addGizmo("square", "s1", 8, 10);
////		m.addGizmo("square", "s2", 10, 10);
////		m.addGizmo("square", "s3", 19, 19);
//		
//		m.addGizmo("circle", "c1", 6, 7);
//		
//		m.addGizmo("triangle", "t1", 2, 7);
//		
//		m.addBall("b1", 5, 5, 5, 5);
		
		m.load(new File("fileformat.txt"));
		
		Display d = new Display(m);
		
	}
}
