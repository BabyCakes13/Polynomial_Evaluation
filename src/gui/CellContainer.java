package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CellContainer extends Container{
	private JPanel cellContainer;
	
	public CellContainer(JFrame frame) {
		super(frame);
		this.cellContainer = super.createBasicContainer();
		
		// TODO add elements.
		
		frame.add(this.cellContainer);
		frame.pack();
	}
}
