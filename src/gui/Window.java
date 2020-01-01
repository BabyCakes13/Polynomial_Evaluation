package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		// Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("BoxLayout Example X_AXIS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the panel to add buttons
        JPanel panel = new JPanel();
        
     // Set the BoxLayout to be X_AXIS: from left to right
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxlayout);
        
     // Set border for the panel
     panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
     //panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80))); 
     
  // Define new buttons
     this.addButtons(panel);
     
  // Set size for the frame
     //frame.setSize(300, 300);
      
     // Set the window to be visible as the default to be false
     frame.add(panel);
     frame.pack();
     frame.setVisible(true);
	}
	
	public void addButtons(JPanel panel) {
		for(Cell cell: this.cells) {
			System.out.println(Integer.toString(cell.getCoefficient()));
			JButton newButton = new JButton(Integer.toString(cell.getCoefficient()));
			panel.add(newButton);
			buttons.add(newButton);
		}
	}
}
