package gui;

import items.PolynomialEvaluation;

import java.awt.GridLayout;

import javax.swing.JFrame;

import gui.ControlContainer;

public class Window {
	private PolynomialEvaluation polynomialEvaluator;
	@SuppressWarnings("unused")
	private ControlContainer controlContainer;
	@SuppressWarnings("unused")
	private CellContainer cellContainer;
	private ParseContainer parseContainer;

	public Window(PolynomialEvaluation polyEval) {
		this.polynomialEvaluator = polyEval;
		this.setUpFrame();
	}
	
	public void setUpFrame() {
		JFrame frame = new JFrame("Polynomial Evaluation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 1));

		this.cellContainer = new CellContainer(frame, this.polynomialEvaluator.getCells());
		this.parseContainer = new ParseContainer(frame);
		this.controlContainer = new ControlContainer(frame, this.parseContainer, this.polynomialEvaluator);

		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
}
