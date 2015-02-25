/**
 * This class holds the x and y position for stone and calculate the distance
 * between them.
 * 
 * Group Project: Mancala. CS-151.
 * 
 * @author Team Alpha: Nirav Patel, Shubhangi Rankhode, Vaishali Shah.
 */
public class Stone {
	private double x;
	private double y;

	public Stone(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Calculate distance between two stones.
	 * 
	 * @param stone
	 *            the stone
	 * @param width
	 *            the width the stone
	 * @param height
	 *            the height of stone
	 * @return the distance
	 */
	public double calculateDistance(Stone stone, int width, int height) {
		double dx = 1.0 * (this.x - stone.x) * width;
		double dy = 1.0 * (this.y - stone.y) * height;
		return Math.sqrt(dx * dx + dy * dy);
	}


}
