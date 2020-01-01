package items;

public class Cell {
	private int coefficient;
	
	public Cell(int coefficient) {
		this.coefficient = coefficient;
	}
	
	public int getCoefficient() {
		return this.coefficient;
	}
	
	public int computeCoefficient(int input, int x) {
		int output = input * x + this.coefficient;
		System.out.println(input + "*" + x + " + " + this.coefficient + " = " + output);
		return output;
	}
	
	@Override
	public String toString() {
		return "Cell[" + this.coefficient + "]";
	}
}
