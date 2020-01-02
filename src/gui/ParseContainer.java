package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ParseContainer extends Container{
	private JPanel parseContainer;
	private JTextArea parseScreen;
	private DefaultTableModel tableModel;
	private int columnsNo;
	
	public ParseContainer(JFrame frame, int cellCount) {
		super(frame, "Parse.");
		this.parseContainer = this.container;
		this.parseContainer.setLayout(new BorderLayout());
		this.columnsNo = cellCount * 3; // each coeficient has input, output and x;
		// this.parseScreen = this.addParseScreen();
		this.tableModel = this.addTable();
		
		frame.add(this.parseContainer);
		frame.pack();
	}
	
	private DefaultTableModel addTable() {
		DefaultTableModel model = new DefaultTableModel(); 
		JTable table = new JTable(model);
		
		String[] tableHead = new String[this.columnsNo];
		
		for(int i = 0; i < this.columnsNo / 3; ++i) {
			model.addColumn("input"); 
			model.addColumn("x"); 
			model.addColumn("output"); 
			
			tableHead[3*i + 0] = "input";
			tableHead[3*i + 1] = "x";
			tableHead[3*i + 2] = "output";
		}
		
		model.addRow(tableHead);
		
		this.parseContainer.add(table);
		this.parseContainer.revalidate();
		
		return model;
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
	
	public void addDataRow(ArrayList<Float> inputs, ArrayList<Float> outputs, ArrayList<Float> xes) {
		System.out.println("Number of columns: " + this.columnsNo);
		String[] newRow = new String[this.columnsNo];
		
		for(int i = 0; i < this.columnsNo / 3; ++i) {
			newRow[3*i + 0] = Float.toString(inputs.get(i));
			newRow[3*i + 1] = Float.toString(xes.get(i));
			newRow[3*i + 2] = Float.toString(outputs.get(i));
		}
		
		this.tableModel.addRow(newRow);
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
