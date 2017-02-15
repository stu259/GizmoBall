package view;

import model.Model;

public class a {
	
	
	public static void main(String[] args) {
		Model m = new Model();
		
		m.addBall("ball1", 4, 4, 1, 1);
		m.addGizmo("square", "s1", 0, 0);
		
		
		TestingGUI tg = new TestingGUI(m);
		
		m.runMode();
		m.resume();
		m.moveBalls();
		m.moveBalls();
		m.moveBalls();
		m.moveBalls();
		//PrototypesTimer pt = new PrototypesTimer(m);
		//pt.actionPerformed(null);
	}


}
