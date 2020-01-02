package gui;

import java.awt.BorderLayout;
import java.awt.Font;
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
		textArea.setFont(new Font("Monospace", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.parseContainer.add(scrollPane);
		this.parseContainer.revalidate();
		
		return textArea;
	}
	
	public void addData(ArrayList<Float> inputs, ArrayList<Float> outputs, float result, float x) {
		this.parseScreen.append("\n");
		for(int i = 0; i < inputs.size(); i++){
		  float input = inputs.get(i);
		  float output = outputs.get(i);
		  
		  String equation = this.createEquation(input, output, result, x);
		  this.parseScreen.append(equation);
		}

		this.parseContainer.revalidate();
		this.parseContainer.repaint();
	}
	
	public String createEquation(float input, float output, float result, float x) {
		String equation = "(" + input + "*" + x + " + " +  "coefficient)(" + output + ")   ";
		return equation;
	}
}
