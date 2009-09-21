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
	
	public void setPiece(int id) throws IOException {
		this.pieces[id] = new Piece(id, InOut.getPieceShapeFromUser(id));
	}
	
	public Piece[] getPieces() {
		return this.pieces;
	}
	
	public int getPieceCount() {
		return this.pieceCount;
	}
}
