package view;

import model.Model;

public class a {
	
	
	public static void main(String[] args) {
		Model m = new Model();
		
		m.addBall("ball1", 4, 4, 5, 5);
		m.addBall("ball2", 7, 7, 5, 5);
		
		m.addGizmo("square", "s1", 0, 0);
		m.addGizmo("square", "s2", 4, 7);
//		m.addGizmo("square", "s3", 19, 0);
//		m.addGizmo("square", "s4", 19, 0);
//		m.addGizmo("square", "s5", 19, 0);
//		m.addGizmo("square", "s6", 19, 0);
//		m.addGizmo("square", "s7", 19, 0);
		
		m.addGizmo("triangle", "t1", 19, 0);
		m.rotateGizmo("t1");
		
		m.addAbsorber("abs1", 0, 18, 20, 19);
		
		
		TestingGUI tg = new TestingGUI(m);
		
		m.runMode();
		m.resume();
		PrototypesTimer pt = new PrototypesTimer(m);
		pt.actionPerformed(null);
	}


}
