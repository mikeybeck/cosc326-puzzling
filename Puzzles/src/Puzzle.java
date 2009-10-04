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
import java.util.ArrayList;

public class Puzzle {
	private InOut in;
	private int pieceCount;
	private Piece[] pieces;
	private Position[] positions;
	private ArrayList<int[]> matches;
   
 	public Puzzle(String inputFile) throws IOException {
 		this.in = new InOut(inputFile);
 		this.pieceCount = in.getPieceCountFromUser();
	 	this.pieces = new Piece[pieceCount];
	 	this.positions = new Position[pieceCount];
	 	for(int i = 0; i < pieceCount; i++) {
	 		setPiece(i);
	 	}
	 	setPositions();
	 	this.matches = getAllMatches();
 	}
    
 	/**
 	 * the algorithm
 	 * @return
 	 * @throws NotSolvableException 
 	 * @throws AlreadyPlacedException 
 	 */

 	public int[] solve() throws NotSolvableException, AlreadyPlacedException {
 		// just some vars for easier coding
 		boolean currentShape;
 		// maybe useful variables in the deeper loops of the algorithm
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
 		boolean found = false;
 		for(Position pos : positions) {
 			if(pos.getShape()) {
	 			for(Piece p : pieces) {
					if(p.getPieceShape() && !found) {
						pos.pluginPiece(p, 0);
						placedPieces[0] = p.getID();
						currentPiece = p.getID();
						found = true;
					}
				}
 			}
 		}
 		
 		ArrayList<ArrayList<int[]>> test = new ArrayList<ArrayList<int[]>>();
 		
 		/***************************
 		 ** THE SOLVING-ALGORITHM **
 		 ***************************/
		
		
		
// 		// loop 1: finding/placing piece for position 1 (placedPieces_index = 0)
// 		do { 
// 			// place the piece into the first position and find matching triangles
// 			pieceMatches[placedPieces[0]] = placePiece(placedPieces[0]);
// 			// put first piece into solution_array
// 			sln[0] = placedPieces[0]; 
// 			// rotation of first piece does not matter
// 			slnRotation[0] = 0; 
// 			// because we start with a square
// 			currentShape = true; 
// 			
// 			//TODO ... I haven't got any idea how to code this... :-( (Tobbe)
// 			
// 			// placed piece did not lead to a solution
// 			// so take out all pieces and start from start again
// 			for(int i = 0; i < pieceCount; i++) {
// 				pieces[i].releasePiece();
// 			}
// 			
// 			// take the next piece and start again
// 			placedPieces[0]++;
// 		}
// 		while(placedPieces[0] < 6); // END of loop 1
 		
 		throw new NotSolvableException();
 	}
 	
	public ArrayList<int[]> getAllMatches() {
		ArrayList<int[]> a = new ArrayList<int[]>();
		for(Piece p1 : pieces) {
	      for(Piece p2 : pieces) {
	         if(p1.getID() != p2.getID()) {
	            if(p1.hasMatch(p2)) {
	               ArrayList<int[]> sides = p1.getMatchingSides(p2);
	               for(int[] s : sides) {
	                  int[] matches = new int[4];
	                  matches[0] = p1.getID();
	                  matches[1] = s[0];
	                  matches[2] = p2.getID();
	                  matches[3] = s[1];
	                  a.add(matches);
	               }
	            }
	         }
	      }
	   }
	   return a;
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
 		
 		//TODO not general (8 and 6 refers to triangles/squares)
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
 	
 	/**
 	 * 
 	 * @param piece
 	 * @return
 	 */
 	public ArrayList<int[]> getMatches(Piece piece) {
 		ArrayList<int[]> sln = new ArrayList<int[]>();
 		for(int[] i : matches) {
 			if(i[0] == piece.getID()) sln.add(i);
 		}
 		return sln;
 	}
        
 	public void setPiece(int id) throws IOException {
 		this.pieces[id] = new Piece(id, in.getPieceShapeFromUser(id), in);
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
 	
 	public ArrayList<int[]> giveAllMatches() {
 		return this.matches;
 	}
 	
 	/**
 	 * creates the positions and their connections by reading it from "puzzle.shape"-file
 	 * @throws IOException 
 	 */
 	public void setPositions() throws IOException {
 		//TODO input not working yet
// 		InOut shapeIn = new InOut("puzzle.shape");
// 		for(int i = 0; i < pieceCount; i++) {
// 			this.positions[i] = shapeIn.getPosition(i);
// 		}
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
 	/*
 	 * example of printout:
 	 *          ____
 	 *          \7 /\
 	 *           \/E \
 	 *        ---|\  /
 	 *        |D |6\/__
 	 *        |  | /\8 /
  	 *    ____|__|/  \/
 	 *   /\4 /\5 /\F /
 	 *  /B \/C \/  \/
 	 * |\  /\  /
 	 * |3\/2 \/
 	 * ------|\
 	 *    |  | \
 	 *    |A |1/
 	 *    |__|/
 	 */
 	public void printSln(int[] sln) {
 		System.out.println("The puzzle could be solved and the solutio looks like this:");
 		System.out.println("         ____");
 		System.out.println("         \\" + "7" + " /\\");
 		System.out.println("          \\/" + "E" + " \\");
 		System.out.println("       ---|\\  /");
 		System.out.println("       |" + "D" + " |" + "6" + "\\/__");
 		System.out.println("       |  | /\\" + "8" + " /");
 		System.out.println("   ____|__|/  \\/");
 		System.out.println("  /\\" + "4" + " /\\" + "5" + " /\\" + "F" + " /");
 		System.out.println(" /" + "B" + " \\/" + "C" + " \\/  \\/");
 		System.out.println("|\\  /\\  /");
 		System.out.println("|" + "3" + "\\/" + "2" + " \\/");
 		System.out.println("------|\\");
 		System.out.println("   |  | \\");
 		System.out.println("   |" + "A" + " |" + "1" + "/");
 		System.out.println("   |__|/");
 	}
}