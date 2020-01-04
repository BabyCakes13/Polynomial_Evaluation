package gui;

import java.awt.GridLayout;

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
		@SuppressWarnings("unused")
		InputContainer inputContainer = new InputContainer(this.frame, "Coefficients.");
	}
	
}
