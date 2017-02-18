package view;

import model.Model;

public class FlipperPrototype {

	public static void main(String[] args) {
		Model m = new Model();

		m.addGizmo("leftflipper", "lf", 9, 10);
		m.addGizmo("rightflipper", "rf", 11, 10);

		TestingGUI tg = new TestingGUI(m);

		m.runMode();
		m.resume();
		
		PrototypesTimer pt = new PrototypesTimer(m,tg.getBoard());
		pt.actionPerformed(null);
	}

}
