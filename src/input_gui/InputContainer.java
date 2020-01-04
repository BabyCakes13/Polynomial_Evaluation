package input_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Container;
import gui.GUIController;
import items.PolynomialEvaluator;

public class InputContainer extends Container{
	private JPanel inputContainer;
	private ArrayList<Float> coefficients;
	
	public InputContainer(EquationDisplayContainer equationContainer, JFrame frame, String containerTitle) {
		super(frame, containerTitle);
		this.inputContainer = this.container;
		
		this.addCoefficients(equationContainer);
		
		frame.add(this.inputContainer);
		frame.pack();
	}
	
	public ArrayList<Float> getCoefficients() {
		return this.coefficients;
	}
	
	private void addCoefficients(EquationDisplayContainer equationContainer) {
		JLabel coefficientsLabel = new JLabel();
		coefficientsLabel.setText("Enter the coefficients to the polynomial equation.");
		JTextField inputField = new JTextField(20);
		JButton addButton = new JButton("ADD COEFFICIENTS");
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputValue = inputField.getText();
				coefficients = addCoefficients(inputValue, coefficientsLabel);

				if(coefficients != null) {
					equationContainer.displayEquation(coefficients);
					Collections.reverse(coefficients);
					PolynomialEvaluator evaluator = new PolynomialEvaluator(coefficients);
					GUIController guiController = new gui.GUIController("Polynomial evaluation", evaluator);
					guiController.displayGUI();
				}
			}
		});
		
		this.setInputLayoutConstraints(coefficientsLabel);
		this.setInputLayoutConstraints(inputField);
		this.setInputLayoutConstraints(addButton);
		
		this.inputContainer.add(coefficientsLabel);
		this.inputContainer.add(inputField);
		this.inputContainer.add(addButton);
	}
	
	private ArrayList<Float> addCoefficients(String input, JLabel label) {
		String[] stringCoefficients = input.split(",");
		ArrayList<Float> coefficients = new ArrayList<Float>();
		
		for(String coefficient:stringCoefficients) {
			if (checkNumber(coefficient)) {
				coefficients.add(Float.parseFloat(coefficient));
			} else {
				label.setText("The coefficients must be numbers.");
				return null;
			}
		}
		
		label.setText("Good job, human. We shall proceed to the next step...");
		return coefficients;
	}
	
	private boolean checkNumber(String input) {
		try {
			Float.parseFloat(input);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number for x.");
			return false;
		}
		return true;
	}
}
