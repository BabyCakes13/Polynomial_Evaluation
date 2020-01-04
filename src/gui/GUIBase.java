package gui; 

import javax.swing.JFrame;

public class GUIBase {
	protected JFrame frame;
	
	public GUIBase(String title) {
		this.frame = new JFrame(title);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void displayFrame() {
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
}