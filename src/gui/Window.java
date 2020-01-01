package gui;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		 	JFrame frame = new JFrame("Alignment Example");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    Container panel1 = this.createCells();
		    Container panel2 = layoutComponents("Center", Component.CENTER_ALIGNMENT);
		    Container panel3 = layoutComponents("Bottom", Component.BOTTOM_ALIGNMENT);

		    frame.setLayout(new GridLayout(3, 1));
		    frame.add(panel1);
		    frame.add(panel2);
		    frame.add(panel3);

		    frame.setSize(500, 500);
		    frame.setVisible(true);
	}
	
	public void addButtons(JPanel container, float alignment) {
		for(Cell cell: this.cells) {
			JButton newButton = new JButton(Integer.toString(cell.getCoefficient()));
			container.add(newButton);
			newButton.setAlignmentY(alignment);
			buttons.add(newButton);
		}
	}
	
	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c, JPanel container) {
		Button button = new Button(name);
		gridbag.setConstraints(button, c);
		container.add(button);
}
	
	public Container createCells() {
		JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder("Cells"));
	    
	    // start creating the grid
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
	    container.setLayout(gridbag);
        
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1.0;
	    
	    for(Cell cell: this.cells) {
			this.makebutton(Integer.toString(cell.getCoefficient()), gridbag, c, container);
		}
	    
	    return container;
	}
	
	private Container layoutComponents(String title, float alignment) {
		// create container basic
	    JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder(title));
	    BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
	    container.setLayout(layout);
	    
	    // add buttons
	    this.addButtons(container, alignment);
	    
	    return container;
	  }
}
