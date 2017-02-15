package view;

import model.Model;

public class LoadDriver {

	public static void main(String[] args) {
		Model m = new Model();
		Display d = new Display(m);
		d.load();

	}

}
