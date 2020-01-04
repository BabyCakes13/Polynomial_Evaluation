package input_gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.Container;

public class EquationDisplayContainer extends Container{
	private JPanel equationDisplayContainer;
	private JTextArea displayScreen;
	
	public EquationDisplayContainer(JFrame frame, String containerTitle) {
		super(frame, containerTitle);
		this.equationDisplayContainer = this.container;
		this.equationDisplayContainer.setLayout(new BorderLayout());

		this.displayScreen = this.addDisplayScreen();

		frame.add(this.equationDisplayContainer);
		frame.pack();
	}
	
	public JTextArea addDisplayScreen() {
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospace", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.equationDisplayContainer.add(scrollPane);
		this.equationDisplayContainer.revalidate();
		
		return textArea;
	}
	
	private String buildEquation(ArrayList<Float> coefficients) {
		StringBuilder equation = new StringBuilder();
		int power = 0;
		
		for(int i = 0; i < coefficients.size(); ++i) {
			float coefficient = coefficients.get(i);
			equation.append(" + " + coefficient + "*x^" + power);
			power++;
		}
		
		return equation.toString();
	}
	
	public void displayEquation(ArrayList<Float> coefficients) {
		String equation = this.buildEquation(coefficients);
		System.out.println(equation);
		this.displayScreen.append(equation);
		this.displayScreen.append("\n");
		this.equationDisplayContainer.revalidate();
		this.equationDisplayContainer.repaint();
	}
	
	
}
