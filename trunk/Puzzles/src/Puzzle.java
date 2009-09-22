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
	
	public int[] solve() {
		int[] sln = new int[pieceCount];
		//TODO algorithm
		return sln;
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
}
