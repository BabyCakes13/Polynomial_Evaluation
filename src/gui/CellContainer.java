package gui;

import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import items.Cell;

public class CellContainer extends Container{
	private JPanel cellContainer;
	private ArrayList<Cell> cells;
	
	public CellContainer(JFrame frame, ArrayList<Cell> cells) {
		super(frame, "Processors.");
		this.cellContainer = this.container;
		this.cells = cells;
		
		this.populateCells();
		
		frame.add(this.cellContainer);
		frame.pack();
	}
	
	private GridBagConstraints setLayoutConstraints(JComponent object) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		this.containerLayout.setConstraints(object, constraints);
		
		return constraints;
		
	}
	
	private void populateCells() {
		for (Cell cell : cells) {
			this.makebutton(Float.toString(cell.getCoefficient()));
		}
	}
	
	private void makebutton(String name) {
		JButton newButton = new JButton(name);
		this.setLayoutConstraints(newButton);
		container.add(newButton);
	}
	
}
