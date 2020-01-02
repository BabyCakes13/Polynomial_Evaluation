package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import items.Cell;

public class ResultContainer extends Container{
	private JPanel resultContainer;
	private JTextArea parseScreen;
	
	public ResultContainer(JFrame frame) {
		super(frame, "Result.");
		this.resultContainer = this.container;
		this.resultContainer.setLayout(new BorderLayout());
		
		this.parseScreen = this.addParseScreen();
		
		frame.add(this.resultContainer);
		frame.pack();
	}
	
	private JTextArea addParseScreen() {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospace", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.resultContainer.add(scrollPane);
		this.resultContainer.revalidate();
		
		return textArea;
	}
	
	public void addResult(float result, float x, ArrayList<Cell> cells) {
		String bigEquation = this.createEquation(result, x, cells).toString();
		this.parseScreen.append(bigEquation);
		this.parseScreen.append("\n");
		this.resultContainer.revalidate();
		this.resultContainer.repaint();
	}
	
	public StringBuilder createEquation(float result, float x, ArrayList<Cell> cells) {
		StringBuilder bigEquation = new StringBuilder("0*x + "  + cells.get(0).getCoefficient() + ")");
		for(int i = 1; i < cells.size(); i++) {
			bigEquation.insert(0, "(");
			String tinyEquation = "*x + " + cells.get(i).getCoefficient() + ")";
			bigEquation.append(tinyEquation);
		}
		bigEquation.append(" = " + result + " (x = " + x + ")");
		
		return bigEquation;
	}
}
