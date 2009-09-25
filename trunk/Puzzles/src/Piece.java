/**
 * representing a Piece of the puzzle
 * 
 * properties of a piece:
 *  - has got a shape (square or triangle)
 *  - has got a certain amount of sides (4 if square; 3 if triangle)
 *  - has got an unique id
 *  - can be placed into a position
 *  
 * a piece is a part of a puzzle
 * 
 */

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
					}
				}
			}
		}
		if(empty) throw new DontMatchException(); // just to check more easily whether two pieces don't match
		return matches;
	}
   
	/**
	 * is there any match between 2 pieces
	 * -implemented by Scott-
	 * @param p
	 * @return boolean
	 */
	public boolean hasMatch(Piece p) {
		if((this.square && !(p.getPieceShape()))
				|| (!(this.square) && p.getPieceShape())) {
			for(int i = 0; i < this.sideArray.length; i++) {
				for(int j = 0; j < p.getSides().length; j++) {
					if(this.sideArray[i].match(p.getSides()[j])) return true;
				}
			}
		}
		else return false;
		return false;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public int[] getMatchingSides(Piece p) {
		int[] result;
		int count = 0;
		for(Side s : this.sideArray) {
			for(int i = 0; i < 3; i++) {
				if(s.match(p.getSides()[i])) count++;
			}
		}
		result = new int[count];
		
		for(int j = 0; j < this.sideArray.length; j++) {
			for(int i = 0; i < p.getSides().length; i++) {
				if(this.sideArray[j].match(p.getSides()[i])) {
					//TODO ... returning pairs of matching sides (ids)
				}
			}
		}
		
		return result;
	}
	
	/**
	 * I'm not sure about the usage (Tobbe)
	 * -implemented by Nabil-
	 */
	public int getMatchingSidesCount(Side side) {
		int count = 0;
		for(Side s: sideArray) {
			if(s.equals(side)) {
				count++;
			}
		}
		return count;
	}
	
	public void setSides() throws IOException {
		for(int i = 0; i < sideArray.length; i++) {
			this.sideArray[i] = new Side();
			this.sideArray[i].setShape(InOut.getShapeFromUser(id, i));
			this.sideArray[i].setColour(InOut.getColourFromUser(id, i));
			this.sideArray[i].setOrientation(InOut.getOrientationFromUser(id, i));
		}
	}
    
	/**
	 * marks the piece as placed/used
	 * - alternative method-name: setPlace()
	 * @throws AlreadyPlacedException
	 */
	public void placePiece() throws AlreadyPlacedException {
		if(!(isPlaced())) {
			throw new AlreadyPlacedException();
		}
		else {
			placed = true;
		}
	}
	
	/**
	 * takes a placed piece out again, so it can be placed again
	 */
	public void releasePiece() {
		this.placed = false;
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
}