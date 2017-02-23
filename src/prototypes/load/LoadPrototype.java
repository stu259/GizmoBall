package prototypes.load;

import model.Model;
import view.Display;

public class LoadPrototype {

	public static void main(String[] args) {
		Model m = new Model();
		Display d = new Display(m);
		d.load();
	}

}
