package gui;

import java.awt.Component;
import java.awt.Container;
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

		    Container panel1 = layoutComponents("Top", Component.TOP_ALIGNMENT);
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
	
	private Container layoutComponents(String title, float alignment) {
	    JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder(title));
	    BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
	    container.setLayout(layout);
	    
	    this.addButtons(container, alignment);
	    
	    return container;
	  }
}
