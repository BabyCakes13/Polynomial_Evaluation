package items;

import items.PolynomialEvaluator;

import gui.GUIController;
import gui.GUIInput;

public class Main {

	public static void main(String[] args) {
		PolynomialEvaluator pe = new PolynomialEvaluator();
		GUIInput guiInput = new GUIInput("Coefficients input.");
		guiInput.displayGUI();
		// GUIController guiController = new gui.GUIController("Polynomial evaluation", pe);
		//guiController.displayGUI();
	}

}
