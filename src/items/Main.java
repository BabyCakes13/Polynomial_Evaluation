package items;

import items.PolynomialEvaluator;

import java.util.ArrayList;

import gui.GUIController;

public class Main {

	public static void main(String[] args) {
		PolynomialEvaluator pe = new PolynomialEvaluator();
		GUIController GUIcontroller = new GUIController(pe);
	}

}
