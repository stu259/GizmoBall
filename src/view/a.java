package view;

import model.Model;

public class a {

	public static void main(String[] args) {
		Model m = new Model();

		m.addGizmo("leftflipper", "lf", 0, 0);
		m.addGizmo("rightflipper", "rf", 3, 0);
//
//
//		m.addBall("ball1", 4, 4, 5, 5);
//		m.addBall("ball2", 7, 7, 5, 5);
//		
//		m.addGizmo("square", "s1", 0, 0);
//		m.addGizmo("square", "s2", 0, 1);
//		
//		
//		for(Integer i = 3; i < 20; i++){
//		String key = "s" + i.toString();
//		m.addGizmo("square", key, 0, i-1);
//		}
//		
//		
//		m.addGizmo("triangle", "t1", 10, 0);
//		m.rotateGizmo("t1");
//
//		m.addAbsorber("abs1", 0, 19, 20, 20);

		TestingGUI tg = new TestingGUI(m);

		m.runMode();
		m.resume();
		
		PrototypesTimer pt = new PrototypesTimer(m,tg.getBoard());
		pt.actionPerformed(null);
	}

}
