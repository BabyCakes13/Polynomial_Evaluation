package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlContainer extends Container {
	// TODO add functionality class to press x and flush
	private JPanel controlContainer;
	
	public ControlContainer(JFrame frame) {
		super(frame, "Control");
		this.controlContainer = this.container;
		
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
			 // e.printStackTrace();
			System.out.println("Please enter a number for x.");
			return;
		}
		
		//solveForNewX(x, false);
	}
	
	private void handleFlush() {
		boolean stillRequiresFlush = true;
		
		while (stillRequiresFlush) {
			stillRequiresFlush = false; //solveForNewX(0, true);
		}
	}
	
	/*private boolean solveForNewX(int x, boolean flush) {
		ArrayList<Float> propagatedInputs = this.polynomialEvaluator.getPreviousTimeOutputs();
		boolean b = this.polynomialEvaluator.handlePushX(x, flush);
		ArrayList<Float> propagatedOutputs = this.polynomialEvaluator.getPreviousTimeOutputs();
		float propagatedResult = this.polynomialEvaluator.getPropagatedResult();
		float propagatedX = this.polynomialEvaluator.getPropagatedX();
		
		System.out.println("Result: " + this.polynomialEvaluator.getPropagatedResult() + " for x: " + this.polynomialEvaluator.getPropagatedX());
		System.out.println("Inputs: " + propagatedInputs);
		System.out.println("Outputs: " + propagatedOutputs);

		
		JTable newTable = this.createTable(propagatedInputs, propagatedOutputs, propagatedResult, propagatedX);
		// this.addNewTable(newTable);
		this.addLines(propagatedInputs, this.parseDisplay);
		return b;
	}*/
}
