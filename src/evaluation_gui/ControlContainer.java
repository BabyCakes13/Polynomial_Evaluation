package evaluation_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Container;
import items.PolynomialEvaluator;

public class ControlContainer extends Container {
	private JPanel controlContainer;
	private ParseContainer parseContainer;
	private ResultContainer resultContainer;
	
	private PolynomialEvaluator pe;
	
	public ControlContainer(JFrame frame, ParseContainer parseContainer, ResultContainer resultContainer, PolynomialEvaluator pe) {
		super(frame, "Control");
		this.controlContainer = this.container;
		this.parseContainer = parseContainer;
		this.resultContainer = resultContainer;
		this.pe = pe;
		
		this.addPushX();
		this.addFlush();
		
		frame.add(this.controlContainer);
		frame.pack();
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
		
		this.setInputLayoutConstraints(pushXLabel);
		this.setInputLayoutConstraints(pushXButton);
		this.setInputLayoutConstraints(pushXTextField);
		
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
		
		this.setInputLayoutConstraints(flushButton);
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
		if (b) {
			ArrayList<Float> propagatedOutputs = this.pe.getCurrentOutputs();
			ArrayList<Float> propagatedXes = this.pe.getCurrentXes();
			float propagatedResult = this.pe.getPropagatedResult();
			float propagatedX = this.pe.getPropagatedX();
			
			System.out.println("Result: " + propagatedResult + " for x: " + propagatedX);
			System.out.println("Inputs: " + propagatedInputs);
			System.out.println("Outputs: " + propagatedOutputs);

			// this.parseContainer.addData(propagatedInputs, propagatedOutputs, propagatedResult, propagatedX);
			this.parseContainer.addDataRow(propagatedInputs, propagatedOutputs, propagatedXes);
			if (!(Double.isNaN(propagatedResult))) {
				System.out.println("PR: " + propagatedResult);
				this.resultContainer.addResult(propagatedResult, propagatedX, this.pe.getCells());
			}
		}
		
		return b;
	}
}
