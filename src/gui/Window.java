package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import items.Cell;

public class Window {
	private ArrayList<Cell> cells;

	private ArrayList<JButton> buttons;

	public Window(ArrayList<Cell> cells) {
		this.cells = cells;
		this.buttons = new ArrayList<JButton>();

		this.setUp();
	}

	public void setUp() {
		JFrame frame = this.setFrame();

		Container panel1 = this.createCellsContainer();
		Container panel2 = this.createParsingContainer();

		frame.add(panel1);
		frame.add(panel2);

		frame.setSize(500, 500);
		frame.setVisible(true);

	}

	public JFrame setFrame() {
		JFrame frame = new JFrame("Polynomial Evaluation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 1));

		return frame;
	}

	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c, JPanel container) {
		Button button = new Button(name);
		gridbag.setConstraints(button, c);
		container.add(button);
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

		for (Cell cell : this.cells) {
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
