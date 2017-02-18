package view;

import model.Model;

public class a {

	public static void main(String[] args) {
		Model m = new Model();

		m.addGizmo("leftflipper", "lf", 0, 0);
		m.addGizmo("rightflipper", "rf", 2, 0);
		m.addGizmo("square", "s0", 3, 0);

		m.addBall("ball1", 4, 4, 5, 5);
		m.addBall("ball2", 7, 7, 5, 5);
		
		m.addGizmo("square", "s1", 0, 0);
		m.addGizmo("square", "s2", 0, 1);
		
		m.addGizmo("circle", "c1", 6, 10);
		m.addGizmo("square", "s1", 7, 10);

		TestingGUI tg = new TestingGUI(m);

		m.runMode();
		m.resume();
		
		PrototypesTimer pt = new PrototypesTimer(m,tg.getBoard());
		pt.actionPerformed(null);
	}

}
