package evaluation_gui;

import items.GUIBase;
import items.PolynomialEvaluator;

import java.awt.GridLayout;

import evaluation_gui.ControlContainer;

public class GUIController extends GUIBase{
	private PolynomialEvaluator polynomialEvaluator;

	public GUIController(String title, PolynomialEvaluator polyEval) {
		super(title);
		this.frame.setLayout(new GridLayout(4, 1));
		this.polynomialEvaluator = polyEval;
	}
	
	public void displayGUI() {
		this.setUpContainers();
		this.displayFrame();
	}
	
	private void setUpContainers() {
		CellContainer cellContainer = new CellContainer(this.frame, this.polynomialEvaluator.getCells());
		ParseContainer parseContainer = new ParseContainer(this.frame, this.polynomialEvaluator.getCells().size());
		ResultContainer resultContainer = new ResultContainer(this.frame);
		ControlContainer controlContainer = new ControlContainer(this.frame, parseContainer, resultContainer, this.polynomialEvaluator);
	}
	
}
