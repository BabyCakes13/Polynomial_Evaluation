package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import items.Cell;
import items.PolynomialEvaluation;

public class Window {
	private PolynomialEvaluation polynomialEvaluator;
	private ArrayList<Float> propagatedInputs;
	private ArrayList<Float> propagatedOutputs;
	private Float propagatedResult;
	private Float propagatedX;
	
	public Window(PolynomialEvaluation polyEval) {
		this.polynomialEvaluator = polyEval;
		this.setUpFrame();
	}

	/**
	 * Method which sets up the main frame for the GUI.
	 */
	public void setUpFrame() {
		JFrame frame = new JFrame("Polynomial Evaluation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 1));

		Container controlContainer = this.createControlContainer();
		Container cellContainer = this.createCellsContainer();
		Container parsingContainer = this.createParsingContainer();

		frame.add(controlContainer);
		frame.add(cellContainer);
		frame.add(parsingContainer);

		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
	/**
	 * Method which creates the control container which handles the polynomial evaluation controls.
	 * @return Container: The container responsible for the polynomial evaluation.
	 */
	public Container createControlContainer() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Control"));
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraint1 = new GridBagConstraints();
		GridBagConstraints constraint2 = new GridBagConstraints();

		container.setLayout(gridbag);
		constraint1.fill = GridBagConstraints.BOTH;
		constraint2.fill = GridBagConstraints.BOTH;
		
		JButton pushXButton = new JButton("PUSH X");
		JButton flushButton = new JButton("FLUSH");
		JLabel pushXLabel = new JLabel();
		JTextField pushXTextField = new JTextField();
		
		pushXLabel.setText("Enter the value of the X to be pushed to the flow:");

		pushXButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
			      String textFieldValue = pushXTextField.getText();
			      handlePushX(textFieldValue);
			   }
			});
		
		flushButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
			      handleFlush();
			   }
			});
		
		gridbag.setConstraints(pushXLabel, constraint1);
		gridbag.setConstraints(pushXButton, constraint1);
		gridbag.setConstraints(flushButton, constraint1);
		constraint2.weightx = 0.5;
		gridbag.setConstraints(pushXTextField, constraint2);
		
		container.add(pushXLabel);
		container.add(pushXTextField);
		container.add(pushXButton);
		container.add(flushButton);
		
		return container;
	}
	
	/**
	 * Method which creates the cells container displaying the constant data of the cells.
	 * @return Container: The created container holding the cells in the systolic array.
	 */
	public Container createCellsContainer() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Cells"));

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		container.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		for (Cell cell : this.polynomialEvaluator.getCells()) {
			// create a button for each constant of the polynomial equation, simulating what the processor would hold as a constant value used for calculations.
			this.makebutton(Float.toString(cell.getCoefficient()), gridbag, c, container);
		}

		return container;
	}
	
	/**
	 * Method which creates the parsing container for displaying information about what values are handled during each parse of the cells.
	 * @return Container: The container responsible for displaying the information.
	 */
	private Container createParsingContainer() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Parsing"));

		JTable table = this.createTable();
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		container.setLayout(new BorderLayout());
		container.add(table.getTableHeader(), BorderLayout.PAGE_START);
		container.add(table, BorderLayout.CENTER);

		return container;
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
		
		callForNewX(x, false);
	}
	
	private void handleFlush() {
		boolean stillRequiresFlush = true;
		
		while (stillRequiresFlush) {
			stillRequiresFlush = callForNewX(0, true);
		}
	}
	
	private JTable createTable() {
		// create the head of the table representing each cell
		String[] cells = new String[this.polynomialEvaluator.getCells().size()];
		for(int i = 0; i < cells.length; i++) {
			float cellCoefficient = this.polynomialEvaluator.getCells().get(i).getCoefficient();
			cells[i] = Float.toString(cellCoefficient);
			// System.out.println(i + " " + cells[i]);
		}

		Object[][] data = { { "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		JTable table = new JTable(data, cells);
		return table;
	}
	
	/**
	 * Method which creates a new button placed in the GridBadLayout based on the input parameters.
	 * @param name: Name of the button.
	 * @param gridbag: The layout working on.
	 * @param c: Grid constraints for button placement.
	 * @param container: The container which will hold the new button.
	 */
	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c, JPanel container) {
		Button newButton = new Button(name);
		gridbag.setConstraints(newButton, c);
		container.add(newButton);
	}
	
	private boolean callForNewX(int x, boolean flush) {
		boolean b;
		// System.out.println(x);
		ArrayList<Float> inputs = this.polynomialEvaluator.getPreviousTimeOutputs();
		b = this.polynomialEvaluator.handlePushX(x, flush);
		ArrayList<Float> outputs = this.polynomialEvaluator.getPreviousTimeOutputs();
		System.out.println("Result: " + this.polynomialEvaluator.getPropagatedResult() + " for x: " + this.polynomialEvaluator.getPropagatedX());
		
		System.out.println("Inputs: " + inputs);
		System.out.println("Outputs: " + outputs);
		
		this.propagatedInputs = inputs;
		this.propagatedOutputs = outputs;
		this.propagatedResult = this.polynomialEvaluator.getPropagatedResult();
		this.propagatedX = this.polynomialEvaluator.getPropagatedX();
		
		this.fillTable();
		return b;
	}
	
	private void fillTable() {
		System.out.println(this.propagatedInputs);
		System.out.println(this.propagatedOutputs);
		System.out.println(this.propagatedResult);
		System.out.println(this.propagatedX);
		// TODO fill the table with the inputs, outputs, result and x.
	}
}
