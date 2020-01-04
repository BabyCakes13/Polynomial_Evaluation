package gui; 

import javax.swing.JFrame;

public class GUIBase {
	protected JFrame frame;
	
	public GUIBase(String title) {
		this.frame = new JFrame(title);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void displayFrame(int x, int y) {
		frame.setSize(x, y);
		frame.setVisible(true);
	}
}