package gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import input_gui.EquationDisplayContainer;
import input_gui.InputContainer;

public class GUIInput extends GUIBase{
	public GUIInput(String title) {
		super(title);
		this.frame.setLayout(new GridLayout(2, 1));
	}
	
	public void displayGUI() {
		this.setUpContainers();
		this.displayFrame(1000, 200);
	}
	
	private void setUpContainers() {
		EquationDisplayContainer equationContainer = new EquationDisplayContainer(this.frame, "Equation to be solved.");
		@SuppressWarnings("unused")
		InputContainer inputContainer = new InputContainer(equationContainer, this.frame, "Coefficients.");
	}
	
}
