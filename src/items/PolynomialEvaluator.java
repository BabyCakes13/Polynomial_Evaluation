package items;

import java.util.ArrayList;

public class PolynomialEvaluator {
	private ArrayList<Float> coefficients;
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Float> previousTimeOutputs = new ArrayList<Float>();
	private ArrayList<Float> previousTimeXes = new ArrayList<Float>();
	private ArrayList<Float> currentOutputs = new ArrayList<Float>();
	private ArrayList<Float> currentXes = new ArrayList<Float>();
	
	private float result;
	private float x;
	
	private float FLUSH_VALUE = Float.NaN;
	
	private int totalTimeToRun;
	private int time;
	
	public PolynomialEvaluator(ArrayList<Float> coefficients) {
		this.coefficients = coefficients;
		this.setItemsUp();
		this.totalTimeToRun = coefficients.size() - 1; // time taken to process the data
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
	
	public ArrayList<Float> getCurrentXes() {
		return this.currentXes;
	}
	
	private void setItemsUp() {
		for(float coefficient : this.coefficients) {
			Cell cell = new Cell(coefficient);
			this.previousTimeOutputs.add(FLUSH_VALUE);
			this.previousTimeXes.add(FLUSH_VALUE);
			this.cells.add(cell);
		}
	}
	
	public boolean handlePushX(float x, boolean flush) {
		if (!flush) {
			int pipelineLength =  coefficients.size();
			// each time we add a new x, we have to recalculate the time needed to flush the pipeline.
			this.totalTimeToRun = this.time + pipelineLength - 1;
		} else {
			x = Float.NaN;
		}
		if (this.time > this.totalTimeToRun) {
			return false;
		} else {			
			this.traverseCells(x);
			
			this.previousTimeOutputs = currentOutputs;
			this.previousTimeXes = currentXes;
			this.result = this.previousTimeOutputs.get(this.previousTimeOutputs.size() -1);
			this.x = this.previousTimeXes.get(this.previousTimeXes.size() -1);
			
			this.time++;
			return true;
		}
		
	}
	
	public float getPropagatedResult() {
		return this.result;
	}
	
	public float getPropagatedX() {
		return this.x;
	}
	
	public void traverseCells(float newX) {
		currentOutputs = new ArrayList<Float>();
		currentXes = new ArrayList<Float>();
		
		this.previousTimeOutputs.add(0, 0f); // add 0*x for the first ax + b
		this.previousTimeXes.add(0, newX); 
		
		for(int i = 0 ; i < cells.size(); ++i) {
			Cell cell = cells.get(i);
			
			float input = previousTimeOutputs.get(i); // get the input from the last output
			float propagatedX = previousTimeXes.get(i); // get the x from the previous x
			
			float output = cell.computeCoefficient(input, propagatedX); // calculate the new output based on the cell data
			currentOutputs.add(output);
			currentXes.add(propagatedX);

		}	
	}
	
	public Object[] sendData(int time, float input, float output, float x) {
		Object[] dataLine = {time, input, output, x};
		return dataLine;
	}
}
