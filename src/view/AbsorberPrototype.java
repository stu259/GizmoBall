package view;

import model.Model;

public class AbsorberPrototype {

	public static void main(String[] args) {
		Model m = new Model();

		m.addBall("ball", 5, 5, 5, 5);
		m.addAbsorber("a1", 0, 19, 20, 20);
		
		TestingGUI tg = new TestingGUI(m);

		m.runMode();
		
		PrototypesTimer pt = new PrototypesTimer(m,tg.getBoard());
		pt.actionPerformed(null);
	}

}
