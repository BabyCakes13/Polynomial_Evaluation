package items;

import items.PolynomialEvaluator;

import gui.GUIController;

public class Main {

	public static void main(String[] args) {
		PolynomialEvaluator pe = new PolynomialEvaluator();
		GUIController guiController = new gui.GUIController("Polynomial evaluation", pe);
		guiController.displayGUI();
	}

}
