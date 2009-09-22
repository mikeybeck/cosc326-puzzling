import java.io.IOException;

public class Puzzle {
	private int pieceCount;
	private Piece[] pieces;
   
 	public Puzzle(int pieceCount) throws IOException {
 		this.pieceCount = pieceCount;
	 	this.pieces = new Piece[pieceCount];
	 	for(int i = 0; i < pieceCount; i++) {
	 		setPiece(i);
	 	}
 	}
    
 	/**
 	 * the algorithm
 	 * -implemented by Tobbe-
 	 * @return
 	 */
 	public int[] solve() {
 		int[] sln = new int[pieceCount];
 		placePiece(this, 1);
 		//TODO algorithm
 		return sln;
 	}
 	
 	/**
 	 * I'm not sure about the usage (Tobbe)
 	 * -implemented by Nabil-
 	 * @param side
 	 * @return
 	 */
 	public int getMatchingSideCount(Side side) {
 		int count = 0;
 		for(Piece piece : pieces) {
 			count += piece.getMatchingSides(side);
 		}
 		return count;
 	}
        
 	public void setPiece(int id) throws IOException {
 		this.pieces[id] = new Piece(id, InOut.getPieceShapeFromUser(id));
 	}
        
 	public Piece[] getPieces() {
 		return this.pieces;
 	}
        
 	public Piece getPiece(int id) {
 		return this.pieces[id];
 	}
        
 	public int getPieceCount() {
 		return this.pieceCount;
 	}
        
 	public void printSln(int[] sln) {
 		//TODO print out
 	}
 	
 	/** Places a piece then returns the id of all the pieces in the puzzle 
 	 *  that can be matched to that piece.
 	 */
 	public int[] placePiece(Puzzle a, int index){
 		int[] matches;
 		int count =0;
 		Piece p = a.getPiece(index);
 		if(p.getPieceShape()) {
 			matches = new int[4];
 		}
 		else {
 			matches = new int[3];
 		}
 		for(int i =1; i<=14; i++){
 			// TODO: review this for-loop and its if-checks
 			if(i == index){ // ???
 			}
 			else { // ???
 				if(p.hasMatch(a.getPiece(i))) { // ???
 					matches[i] = i;
 					count++;
 				}
 			}
 		}
 		//couldn't match this piece to enough other pieces
 		if(count < matches.length) {
 			System.out.println("This puzzle is unsolvable");
        	//perhaps some sort of exception to be thrown?
 		}
 		return matches;
 	}
}