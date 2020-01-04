package input_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Container;

public class InputContainer extends Container{
	private JPanel inputContainer;
	
	public InputContainer(JFrame frame, String containerTitle) {
		super(frame, "Equation input.");
		this.inputContainer = this.container;
		
		this.addCoefficients();
		
		frame.add(this.inputContainer);
		frame.pack();
	}
	
	private void addCoefficients() {
		JLabel label = new JLabel();
		label.setText("Enter the coefficients to the polynomial equation.");
		JTextField inputField = new JTextField(20);
		JButton addButton = new JButton("ADD COEFFICIENTS");
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputValue = inputField.getText();
				System.out.println(inputValue);
			}
		});
		
		this.setInputLayoutConstraints(label);
		this.setInputLayoutConstraints(inputField);
		this.setInputLayoutConstraints(addButton);
		
		this.inputContainer.add(label);
		this.inputContainer.add(inputField);
		this.inputContainer.add(addButton);
	}
}
