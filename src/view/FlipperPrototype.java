package view;

import model.Model;

public class FlipperPrototype {

	public static void main(String[] args) {
		Model m = new Model();

		System.out.println(m.addGizmo("leftflipper", "lf", 8, 10));
		System.out.println(m.addGizmo("rightflipper", "rf", 10, 10));

		TestingGUI tg = new TestingGUI(m);

		m.runMode();
		m.resume();
		
		PrototypesTimer pt = new PrototypesTimer(m,tg.getBoard());
		pt.actionPerformed(null);
	}

}
