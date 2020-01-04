package items;

import items.PolynomialEvaluator;

import evaluation_gui.GUIController;

public class Main {

	public static void main(String[] args) {
		PolynomialEvaluator pe = new PolynomialEvaluator();
		GUIController guiController = new GUIController("Polynomial evaluation", pe);
		guiController.displayGUI();
	}

}
