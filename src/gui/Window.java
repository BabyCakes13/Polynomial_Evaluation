package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private PolynomialEvaluation polyEval;
	
	public Window(PolynomialEvaluation polyEval) {
		this.polyEval = polyEval;
		this.setUp();
	}

	public void setUp() {
		JFrame frame = this.setFrame();

		Container controlContainer = this.createControlContainer();
		Container cellContainer = this.createCellsContainer();
		Container parsingContainer = this.createParsingContainer();

		frame.add(controlContainer);
		frame.add(cellContainer);
		frame.add(parsingContainer);

		frame.setSize(1000, 1000);
		frame.setVisible(true);

	}

	public JFrame setFrame() {
		JFrame frame = new JFrame("Polynomial Evaluation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 1));

		return frame;
	}

	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c, JPanel container) {
		Button button = new Button(name);
		gridbag.setConstraints(button, c);
		container.add(button);
	}
	
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
		pushXLabel.setText("Enter the value of the X to be pushed to the flow:");
		pushXLabel.setBounds(10, 10, 100, 100);
		
		JTextField pushXTextField = new JTextField();
		pushXTextField.setBounds(110, 50, 130, 30);
		
		pushXButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
			      String textFieldValue = pushXTextField.getText();
			      triggerPressX(textFieldValue);
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
	
	private boolean triggerPressX(String textFieldValue) {
		int x = 0;
		try {
			x = Integer.parseInt(textFieldValue);
		} catch (NumberFormatException e) {
			 // e.printStackTrace();
			System.out.println("Please enter a number for x.");
			return false;
		}
		System.out.println(x);
		// TODO call polyEval function here
		this.polyEval.startProcessingWithGUI(x);
		return true;
	}
	
	public Container createCellsContainer() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Cells"));

		// start creating the grid
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		container.setLayout(gridbag);

		// make each cell a box
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		for (Cell cell : this.polyEval.getCells()) {
			this.makebutton(Integer.toString(cell.getCoefficient()), gridbag, c, container);
		}

		return container;
	}

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

	private JTable createTable() {
		String[] columnNames = { "Time", "Cell", "Input", "Output", "X" };

		Object[][] data = { { "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		JTable table = new JTable(data, columnNames);
		return table;
	}
}
