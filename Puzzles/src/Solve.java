import java.io.IOException;


public class Solve {

	public static void main(String[] args) throws IOException {
		// input all pieces
		Puzzle puzzle = new Puzzle(InOut.getPieceCountFromUser());
		int n = puzzle.getPieceCount();
		// solution[2] = 3 --> piece.id 3 goes into position 2
		int[] solution = new int[n];
	}
}
