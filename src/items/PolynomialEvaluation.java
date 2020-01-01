package items;

import java.util.ArrayList;
import java.util.Arrays;

public class PolynomialEvaluation {
	private ArrayList<Integer> coefficients = new ArrayList<Integer>(Arrays.asList(2, -6, 2, -1));
	private ArrayList<Integer> xFeed = new ArrayList<Integer>(Arrays.asList(3, 4, 5));
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Integer> previousTimeOutputs = new ArrayList<Integer>();
	private ArrayList<Integer> previousTimeXes = new ArrayList<Integer>();
	private int runtime;
	
	public PolynomialEvaluation() {
		this.setItemsUp();
		this.runtime = coefficients.size() + xFeed.size() - 1; // time taken to process the data
	}
	
	private void setItemsUp() {
		for(int coefficient : this.coefficients) {
			// create the cells, previous time outputs and previous time x'es
			Cell cell = new Cell(coefficient);
			this.previousTimeOutputs.add(0);
			this.previousTimeXes.add(0);
			this.cells.add(cell);
		}
	}
	
	public void startProcessing() {
		for(int time = 0; time < this.runtime; time++) {
			this.feedData(time);
			
			// set up new cells for the current time phase
			ArrayList<Integer> currentOutputs = new ArrayList<Integer>();
			ArrayList<Integer> currentXes = new ArrayList<Integer>();
			
			this.parseCells(currentOutputs, currentXes); // parse and update outputs and x'es through cells
			
			this.displayParsing();
		}
	}
	
	private void feedData(int time) {
		if(time < xFeed.size()) {
			  // we feed one x each time, as long as there are x values left.
			  this.previousTimeXes.add(0, xFeed.get(time)); 
		} else {
			// if there are no more x left to be fed, we feed fake data until the time is done.
			this.previousTimeXes.add(0, 999999);
			this.previousTimeOutputs.add(0, 0);
		}
	}
	
	private void parseCells(ArrayList<Integer> currentOutputs, ArrayList<Integer> currentXes) {
		for(int i = 0 ; i < cells.size(); ++i) {
			Cell cell = cells.get(i);
			
			int input = previousTimeOutputs.get(i); // get the input from the last output
			int x = previousTimeXes.get(i); // get the x from the previous x
			
			int output = cell.computeCoefficient(input, x); // calculate the new output based on the cell data
			currentOutputs.add(output);
			currentXes.add(x);
		}
		
		this.previousTimeOutputs = currentOutputs;
		this.previousTimeXes = currentXes;
	}
	
	private void displayParsing() {
		System.out.println("");
		for(Integer output: previousTimeOutputs) {
			System.out.print(output + ", ");
		}
		System.out.println("");
	}
}
