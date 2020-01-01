package items;

public class Cell {
	private int coefficient;
	
	public Cell(int coefficient) {
		this.coefficient = coefficient;
	}
	
	public int computeCoefficient(int input, int x) {
		int output = input * x + this.coefficient;
		
		System.out.println("output= " + output);
		System.out.println("");
		
		return output;
	}
	
	@Override
	public String toString() {
		return "Cell[" + this.coefficient + "]";
	}
}
