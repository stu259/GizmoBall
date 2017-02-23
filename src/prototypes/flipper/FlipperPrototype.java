package prototypes.flipper;

import model.Model;
import prototypes.PrototypesTimer;

public class FlipperPrototype {

	public static void main(String[] args) {
		Model m = new Model();

		m.addGizmo("leftflipper", "lf", 8, 10);
		m.addGizmo("rightflipper", "rf", 10, 10);

//		m.addBall("ball", 5, 5, 5, 5);
		
		FlipperGui fg = new FlipperGui(m);

		m.runMode();
		
		PrototypesTimer pt = new PrototypesTimer(m,fg.getBoard());
		pt.actionPerformed(null);
	}

}
