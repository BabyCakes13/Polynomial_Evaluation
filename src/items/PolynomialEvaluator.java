package items;

import java.util.ArrayList;
import java.util.Arrays;

public class PolynomialEvaluator {
	private ArrayList<Float> coefficients = new ArrayList<Float>(Arrays.asList(2f, -6f, 2f, -1f));
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Float> previousTimeOutputs = new ArrayList<Float>();
	private ArrayList<Float> previousTimeXes = new ArrayList<Float>();
	private ArrayList<Float> currentOutputs = new ArrayList<Float>();
	private ArrayList<Float> currentXes = new ArrayList<Float>();
	
	private float result;
	private float x;
	
	private float FLUSH_VALUE = Float.NaN;
	
	private int runtime;
	private int time;
	
	public PolynomialEvaluator() {
		this.setItemsUp();
		this.runtime = coefficients.size() - 1; // time taken to process the data
	}
	
	public ArrayList<Cell> getCells() {
		return this.cells;
	}
	
	public ArrayList<Float> getPreviousTimeOutputs() {
		return this.previousTimeOutputs;
	}
	
	public ArrayList<Float> getCurrentOutputs() {
		return this.currentOutputs;
	}
	
	private void setItemsUp() {
		for(float coefficient : this.coefficients) {
			// create the cells, previous time outputs and previous time x'es
			Cell cell = new Cell(coefficient);
			this.previousTimeOutputs.add(FLUSH_VALUE);
			this.previousTimeXes.add(FLUSH_VALUE);
			this.cells.add(cell);
		}
	}
	
	public boolean handlePushX(float x, boolean flush) {
		if (!flush) {
			this.runtime++;
		}
		if (this.time > this.runtime) {
			return false;
		} else {			
			System.out.print ("Cell Inputs:  ");
			this.displayParsing(this.previousTimeOutputs);
			
			this.parseCells(); // parse and update outputs and x'es through cells
			
			System.out.print ("Cell Outputs: ");
			this.displayParsing(this.previousTimeOutputs);
			System.out.println("");
			
			this.propagate(x);
			this.time++;
			System.out.println("------------------------------------------------------------------------------------");
			return true;
		}
		
	}
	
	public float getPropagatedResult() {
		return this.result;
	}
	
	public float getPropagatedX() {
		return this.x;
	}
	
	public void propagate(float x) {
		this.previousTimeXes.add(0, x); 
		this.previousTimeOutputs.add(0, 0f); // add 0*x for the first ax + b
		
		// get the results:
		System.out.print("Result is " + this.previousTimeOutputs.get(this.previousTimeOutputs.size() -1));
		System.out.println(" for x " + this.previousTimeXes.get(this.previousTimeXes.size() -1));
		
		this.result = this.previousTimeOutputs.get(this.previousTimeOutputs.size() -1);
		this.x = this.previousTimeXes.get(this.previousTimeXes.size() -1);
	}
	
	public void parseCells() {
		currentOutputs = new ArrayList<Float>();
		currentXes = new ArrayList<Float>();
		
		for(int i = 0 ; i < cells.size(); ++i) {
			Cell cell = cells.get(i);
			
			float input = previousTimeOutputs.get(i); // get the input from the last output
			float x = previousTimeXes.get(i); // get the x from the previous x
			
			float output = cell.computeCoefficient(input, x); // calculate the new output based on the cell data
			currentOutputs.add(output);
			currentXes.add(x);
			
			// TODO send data here to the GUI
		}
		
		this.previousTimeOutputs = currentOutputs;
		this.previousTimeXes = currentXes;
	}
	
	public Object[] sendData(int time, float input, float output, float x) {
		Object[] dataLine = {time, input, output, x};
		return dataLine;
	}
	
	private void displayParsing(ArrayList<Float> al) {
		System.out.println("");
		for(Float output: al) {
			System.out.print(output + ", ");
		}
		System.out.println("");
	}
}
