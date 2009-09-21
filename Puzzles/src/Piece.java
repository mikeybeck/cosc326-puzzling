import java.io.IOException;

public class Piece {
	private Side[] sideArray;
	private boolean square;
	private int id;
	private boolean placed = false;
	
	public Piece(int id, boolean square) throws IOException {
		this.id = id;
		this.square = square;
		if(square) this.sideArray = new Side[4];
		else this.sideArray = new Side[3];
		setSides();
	}
	
	/**
	 * returns a matrix of all possible matching
	 * sides, which match have true entries, false otherwise
	 * e.g. piece 1 square, piece 2 triangle
	 * matrix:
	 * 11 12 13      false true  false false
	 * 21 22 23 --\  true  false false false
	 * 31 32 33 --/  true  false false true
	 * 41 42 43      false false true  false
	 * @param piece
	 * @return boolean[][]
	 * @throws DontMatchException
	 */
	public boolean[][] match(Piece piece) throws DontMatchException {
		int c = 0;
		boolean[][] matches = new boolean[0][0];
		boolean empty = true;
		if((this.square && !(piece.getPieceShape()))
				|| (!(this.square) && piece.getPieceShape())) {
			matches = new boolean[this.sideArray.length][piece.getSides().length];
			for(int i = 0; i < this.sideArray.length; i++) {
				for(int j = 0; j < piece.getSides().length; j++) {
					if(this.sideArray[i].match(piece.getSides()[j])) {
						matches[i][j] = true;
						empty = false;
						c++;
					}
				}
			}
		}
		if(empty) throw new DontMatchException(); // just to check more easily whether two pieces don't match
		return matches;
	}
	
	public void setSides() throws IOException {
		for(int i = 0; i < sideArray.length; i++) {
			this.sideArray[i] = new Side();
			this.sideArray[i].setShape(InOut.getShapeFromUser(id, i));
			this.sideArray[i].setColour(InOut.getColourFromUser(id, i));
			this.sideArray[i].setOrientation(InOut.getOrientationFromUser(id, i));
		}
	}
	
	public void placePiece() throws AlreadyPlacedException {
		if(!(isPlaced())) {
			throw new AlreadyPlacedException();
		}
		else {
			placed = true;
		}
	}
	
	public int getID() {
		return id;
	}
	
	public Side[] getSides() {
		return sideArray;
	}
	
	public boolean getPieceShape() {
		return this.square;
	}
	
	public boolean isPlaced() {
		return this.placed;
	}
	
	public Piece clone() {
		return null;
		//TODO maybe we need it later - maybe not
	}
}
