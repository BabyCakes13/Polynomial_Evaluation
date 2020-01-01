package items;
import java.util.ArrayList;
import java.util.Arrays;

import items.Cell;
import items.PolynomialEvaluation;

public class Main {

	public static void main(String[] args) {
		PolynomialEvaluation pe = new PolynomialEvaluation();
		pe.startProcessing();
		/*ArrayList<Integer> coefficients = new ArrayList<Integer>(Arrays.asList(2, -6, 2, -1));
		ArrayList<Integer> xFeed = new ArrayList<Integer>(Arrays.asList(3, 4, 5));
		ArrayList<Cell> cells = new ArrayList<Cell>();
		ArrayList<Integer> previousTimeOutputs = new ArrayList<Integer>();
		ArrayList<Integer> previousTimeXes = new ArrayList<Integer>();
		
		for(int coefficient : coefficients) {
			// create the cells, previous time outputs and previous time x'es
			Cell cell = new Cell(coefficient);
			previousTimeOutputs.add(0);
			previousTimeXes.add(0);
			cells.add(cell);
		}
		
		int timeToRun = 
		
		
		for(int time = 0; time < timeToRun; time++) {
			if(time < xFeed.size()) {
			  // as long as there are x'es, we feed them one by one (time) to the cells
			  previousTimeXes.add(0, xFeed.get(time)); 
			}
			else {
				// if there are no more x'es, we will feed other fake data to the processors
				previousTimeXes.add(0, 999999);
				previousTimeOutputs.add(0, 0);
			}
			
			// prepare the outputs and x'es
			ArrayList<Integer> currentOutputs = new ArrayList<Integer>();
			ArrayList<Integer> currentXes = new ArrayList<Integer>();
			
			// parse all the cells and based on the inputs, generate the outputs. 
			// after all cells are traversed, apply the changes
			for(int i = 0 ; i < cells.size(); ++i) {
				Cell cell = cells.get(i);
				
				int input = previousTimeOutputs.get(i);
				int currentX = previousTimeXes.get(i);

				int newOutput = cell.computeCoefficient(input, currentX);
				currentOutputs.add(newOutput);
				currentXes.add(currentX);
			}
	
			previousTimeOutputs = currentOutputs;
			previousTimeXes = currentXes;

			System.out.println("");
			for(Integer output: previousTimeOutputs) {
				System.out.print(output + " ");
			}
			System.out.println("");
		}*/
	}

}
