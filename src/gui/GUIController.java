package gui;

import items.PolynomialEvaluator;

import java.awt.GridLayout;

import javax.swing.JFrame;

import gui.ControlContainer;

public class GUIController {
	private PolynomialEvaluator polynomialEvaluator;

	public GUIController(PolynomialEvaluator polyEval) {
		this.polynomialEvaluator = polyEval;
		this.setUpFrame();
	}
	
	public void setUpFrame() {
		JFrame frame = new JFrame("Polynomial Evaluation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 1));

		CellContainer cellContainer = new CellContainer(frame, this.polynomialEvaluator.getCells());
		ParseContainer parseContainer = new ParseContainer(frame);
		ResultContainer resultContainer = new ResultContainer(frame);
		ControlContainer controlContainer = new ControlContainer(frame, parseContainer, resultContainer, this.polynomialEvaluator);

		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
}
