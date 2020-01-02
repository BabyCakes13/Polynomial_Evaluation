package gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import items.PolynomialEvaluation;

public class ControlContainer extends Container {
	private JPanel controlContainer;
	private ParseContainer parseContainer;
	private PolynomialEvaluation pe;
	
	public ControlContainer(JFrame frame, ParseContainer parseContainer, PolynomialEvaluation pe) {
		super(frame, "Control");
		this.controlContainer = this.container;
		this.parseContainer = parseContainer;
		this.pe = pe;
		
		this.addPushX();
		this.addFlush();
		
		frame.add(this.controlContainer);
		frame.pack();
	}
	
	private void setLayoutConstraints(JComponent object) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		if (object instanceof JTextField) {
			constraints.gridy=1;
			constraints.gridheight=2;
			this.containerLayout.setConstraints(object, constraints);
		} else {
			constraints.gridy=1;
			constraints.gridheight=2;
			this.containerLayout.setConstraints(object, constraints);
		}
	}
	
	private void addPushX() {
		JButton pushXButton = new JButton("PUSH X");
		JLabel pushXLabel = new JLabel();
		JTextField pushXTextField = new JTextField(20);
		
		pushXLabel.setText("Enter the value of the X to be pushed to the flow:");
		
		pushXButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
			      String textFieldValue = pushXTextField.getText();
			      handlePushX(textFieldValue);
			   }
			});
		
		this.setLayoutConstraints(pushXLabel);
		this.setLayoutConstraints(pushXButton);
		this.setLayoutConstraints(pushXTextField);
		
		this.controlContainer.add(pushXLabel);
		this.controlContainer.add(pushXTextField);
		this.controlContainer.add(pushXButton);
	}
	
	private void addFlush() {
		JButton flushButton = new JButton("FLUSH");
		
		flushButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
			      handleFlush();
			   }
			});
		
		this.setLayoutConstraints(flushButton);
		this.controlContainer.add(flushButton);
	}
	
	private void handlePushX(String textFieldValue) {
		int x;
		try {
			x = Integer.parseInt(textFieldValue);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number for x.");
			return;
		}
		
		solveForNewX(x, false);
	}
	
	private void handleFlush() {
		boolean stillRequiresFlush = true;
		
		while (stillRequiresFlush) {
			stillRequiresFlush = solveForNewX(0, true);
		}
	}
	
	public boolean solveForNewX(int x, boolean flush) {
		ArrayList<Float> propagatedInputs = this.pe.getPreviousTimeOutputs();
		boolean b = this.pe.handlePushX(x, flush);
		ArrayList<Float> propagatedOutputs = this.pe.getPreviousTimeOutputs();
		float propagatedResult = this.pe.getPropagatedResult();
		float propagatedX = this.pe.getPropagatedX();
		
		System.out.println("Result: " + propagatedResult + " for x: " + propagatedX);
		System.out.println("Inputs: " + propagatedInputs);
		System.out.println("Outputs: " + propagatedOutputs);

		this.parseContainer.addData(propagatedInputs);
		return b;
	}
}
