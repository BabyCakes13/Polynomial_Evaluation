package items;

public class Cell {
	private float coefficient;
	
	public Cell(float coefficient) {
		this.coefficient = coefficient;
	}
	
	public float getCoefficient() {
		return this.coefficient;
	}
	
	public float computeCoefficient(float input, float x) {
		float output = input * x + this.coefficient;
		System.out.println(input + "*" + x + " + " + this.coefficient + " = " + output);
		return output;
	}
	
	@Override
	public String toString() {
		return "Cell[" + this.coefficient + "]";
	}
}
