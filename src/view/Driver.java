package view;

import model.Model;

public class Driver {
	public static void main(String[] args) {
		Model m = new Model();
		Display d = new Display(m);
	}
}