
public class Side {
	private boolean shape;
	private int colour;
	private boolean orientation;
	
	public Side() {
	}
	
	public Side(boolean shape, int colour, boolean orientation) {
		this.shape = shape;
		this.colour = colour;
		this.orientation = orientation;
	}
	
	// two sides match if colour is same and shape and orientation is flipped
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
	
	public Side clone() {
		return null;
		//TODO maybe we need it later - maybe not
	}
}
