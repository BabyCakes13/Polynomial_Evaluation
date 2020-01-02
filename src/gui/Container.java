package gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Container {
	protected JFrame frame;
	
	public Container(JFrame frame) {
		this.frame = frame;
	}
	
	public JPanel createBasicContainer() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Parsing"));
		container.setLayout(new BorderLayout());
		
		return container;
	}
}
