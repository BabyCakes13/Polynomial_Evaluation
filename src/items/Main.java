package items;

import items.PolynomialEvaluation;

import java.util.ArrayList;

import gui.Window;

public class Main {

	public static void main(String[] args) {
		PolynomialEvaluation pe = new PolynomialEvaluation();
		ArrayList<Cell> cells = pe.getCells();
		callGUI(cells);
	}
	
	public static void callEvaluation() {
		PolynomialEvaluation pe = new PolynomialEvaluation();
		pe.startProcessing();
	}
	
	public static void callGUI(ArrayList<Cell> cells) {
		Window w = new Window(cells);
	}

}
