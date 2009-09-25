/**
 * representing a side of a piece of a puzzle
 * 
 * properties of a side:
 *  - has got a shape (head or tale)
 *  - has got a color (represented as a int)
 *  - has got an orientation (clockwise or not)
 *  
 * a side is part of a piece
 * 
 */

public class Side {
	private boolean shape;
	private int colour;
	private boolean orientation;
	
	public Side() {
		this.shape = true;
		this.colour = 0;
		this.orientation = true;
	}
	
	/**
	 * probably we do not need this constructor
	 * may be deleted in later versions
	 */
	public Side(boolean shape, int colour, boolean orientation) {
		this.shape = shape;
		this.colour = colour;
		this.orientation = orientation;
	}
	
	/**
	 * the equals-function (from the other point of view)
	 * two sides match if color is same and shape and orientation is vice versa
	 */
	public boolean match(Side side) {
		if((this.colour == side.getColour())
				&& (this.shape != side.getShape())
				&& (this.orientation != side.getOrientation())) {
			return true;
		}
		else return false;
	}
	
	public boolean getShape() {
		return shape;
	}
	
	public void setShape(boolean shape) {
		this.shape = shape;
	}
	
	public int getColour() {
		return colour;
	}
	
	public void setColour(int colour) {
		this.colour = colour;
	}
	
	public boolean getOrientation() {
		return orientation;
	}
	
	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}
}