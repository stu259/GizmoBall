package view;

import model.Model;

public class FlipperPrototype {

	public static void main(String[] args) {
		Model m = new Model();

		m.addGizmo("leftflipper", "lf", 8, 10);
		m.addGizmo("rightflipper", "rf", 10, 10);

//		m.addBall("ball", 5, 5, 5, 5);
		
		TestingGUI tg = new TestingGUI(m);

		m.runMode();
		
		PrototypesTimer pt = new PrototypesTimer(m,tg.getBoard());
		pt.actionPerformed(null);
	}

}
