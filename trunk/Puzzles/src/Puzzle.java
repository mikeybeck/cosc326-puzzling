/**
 * representing a puzzle
 * 
 * properties of a puzzle:
 *  - has got a certain size
 *  - has got the same amount of pieces (of different shapes)
 *  - has got a certain structure (positions)
 *  - can be solved
 */
import java.io.IOException;

public class Puzzle {
	private int pieceCount;
	private Piece[] pieces;
	private Position[] positions;
   
 	public Puzzle(int pieceCount) throws IOException {
 		this.pieceCount = pieceCount;
	 	this.pieces = new Piece[pieceCount];
	 	this.positions = new Position[pieceCount];
	 	for(int i = 0; i < pieceCount; i++) {
	 		setPiece(i);
	 	}
	 	setPositions();
 	}
    
 	/**
 	 * the algorithm
 	 * @return
 	 * @throws NotSolvableException 
 	 */

 	public int[] solve() throws NotSolvableException {
 		// just some vars for easier coding
 		boolean currentShape;
 		// maybe useful variables in the deeper loops of the algorithm
 		boolean[][] currentMatches;
 		int[][] currentNeighbors;
 		int[] nA, nB, nC, nD;
 		int currentPiece;
 		
 		// stores the final solution of puzzle
 		// (array_index -> position_id)
 		int[] sln = new int[pieceCount];
 		// (array_index -> rotation)
 		// rotation = 0 --> side A -> piece.side_array_index = 0
 		// rotation = 1 --> side B -> piece.side_array_index = 0
 		// rotation = 2 --> side C -> piece.side_array_index = 0
 		// rotation = 3 --> side D -> piece.side_array_index = 0
 		int[] slnRotation = new int[pieceCount];
 		// stores matching pieces (ids) for particular piece
 		// (1st-dim: array_index -> piece_id; 2nd-dim: array of matching piece_ids)
 		int[][] pieceMatches = new int[pieceCount][];
 		// collection of loop_indizes
 		// (array_index -> loop number)
 		int[] placedPieces = new int[pieceCount];
 		
 		// find first piece, that fits into position_0 (only criteria is shape)
 		for(Piece pc : pieces) {
 			if(pc.getPieceShape()) {
 				placedPieces[0] = pc.getID();
 			}
 		}
 		
 		/***************************
 		 ** THE SOLVING-ALGORITHM **
 		 ***************************/
 		// loop 1: finding/placing piece for position 1 (placedPieces_index = 0)
 		do { 
 			// place the piece into the first position and find matching triangles
 			pieceMatches[placedPieces[0]] = placePiece(placedPieces[0]);
 			// put first piece into solution_array
 			sln[0] = placedPieces[0]; 
 			// rotation of first piece does not matter
 			slnRotation[0] = 0; 
 			// because we start with a square
 			currentShape = true; 
 			
 			//TODO ... I haven't got any idea how to code this... :-( (Tobbe)
 			
 			// placed piece did not lead to a solution
 			// so take out all pieces and start from start again
 			for(int i = 0; i < pieceCount; i++) {
 				pieces[i].releasePiece();
 			}
 			
 			// take the next piece and start again
 			placedPieces[0]++;
 		}
 		while(placedPieces[0] < 6); // END of loop 1
 		
 		throw new NotSolvableException();
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
 			count += piece.getMatchingSidesCount(side);
 		}
 		return count;
 	}
 	
 	/**
 	 * Places a piece then returns the id of all the pieces in the puzzle 
 	 * that can be matched to that piece.
 	 * @throws NotSolvableException 
 	 */
 	public int[] placePiece(int index) throws NotSolvableException{
 		int[] matches;
 		int count = 0;
 		Piece piec = this.getPiece(index);
 		
 		if(piec.getPieceShape()) {
 			matches = new int[8];
 		}
 		else {
 			matches = new int[6];
 		}
 		
 		// get matching pieces
 		for(int i = 0; i < pieceCount; i++){
 			if(i != index) { // don't match the piece with itself
 				if(piec.hasMatch(this.getPiece(i))) {
 					matches[count] = i;
 					count++;
 				}
 			}
 		}
 		
 		// clean up the array - don't return NULL-values
 		int[] matchesNew = new int[count];
 		for(int i = 0; i < count; i++) {
 			matchesNew[i] = matches[i];
 		}
 		
 		//couldn't match this piece to enough other pieces
 		if((count < 4 && piec.getPieceShape()) || (count < 3 && !(piec.getPieceShape()))) {
 			throw new NotSolvableException();
 		}
 		
 		return matches;
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
 	
 	/**
 	 * creates the positions and their connections
 	 */
 	public void setPositions() {
 		positions[0] = new Position(0, 12, 0, 13, 0, 7, 0, 6, 0);
 		positions[1] = new Position(1, 12, 1, 6, 2, 8, 0, 10, 0);
 		positions[2] = new Position(2, 6, 1, 7, 2, 9, 2, 8, 1);
 		positions[3] = new Position(3, 7, 1, 13, 1, 11, 0, 9, 0);
 		positions[4] = new Position(4, 8, 2, 9, 1, 11, 1, 10, 1);
 		positions[5] = new Position(5, 10, 2, 11, 2, 13, 2, 12, 2);
 		positions[6] = new Position(6, 0, 3, 2, 0, 1, 1);
 		positions[7] = new Position(7, 0, 2, 3, 0, 2, 1);
 		positions[8] = new Position(8, 1, 2, 2, 3, 4, 0);
 		positions[9] = new Position(9, 3, 3, 4, 1, 2, 2);
 		positions[10] = new Position(10, 1, 3, 4, 3, 5, 0);
 		positions[11] = new Position(11, 3, 2, 4, 2, 5, 1);
 		positions[12] = new Position(12, 0, 0, 1, 0, 5, 3);
 		positions[13] = new Position(13, 0, 1, 3, 1, 5, 2);
 	}
    
 	/**
 	 * prints out the solution of the puzzle in an appropriate way
 	 * @param sln
 	 */
 	public void printSln(int[] sln) {
 		//TODO ??? in which way shall we print out the solution ???
 	}
}