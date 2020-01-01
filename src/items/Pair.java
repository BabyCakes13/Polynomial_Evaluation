package items;

public class Pair {
	private int result;
	private int x;

	public Pair(int result, int x) {
		this.result = result;
		this.x = x;
	}
	
	public int getCoefficient() {
		return this.result;
	}
	
	public int getX() {
		return this.x;
	}
}
