package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ParseContainer extends Container{
	private JPanel parseContainer;
	private JTextArea parseScreen;
	
	public ParseContainer(JFrame frame) {
		super(frame, "Parse.");
		this.parseContainer = this.container;
		this.parseContainer.setLayout(new BorderLayout());
		
		this.parseScreen = this.addParseScreen();
		
		frame.add(this.parseContainer);
		frame.pack();
	}
	
	private JTextArea addParseScreen() {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.parseContainer.add(scrollPane);
		this.parseContainer.revalidate();
		
		return textArea;
	}
	
	public void addData(ArrayList<Float> parseData) {
		this.parseScreen.append(Float.toString(parseData.get(0)));
		this.parseContainer.revalidate();
		this.parseContainer.repaint();
	}
}
