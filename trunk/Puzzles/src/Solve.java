/**
 * Puzzling
 *  written by Nabil Boag, Scott Henderson, Nick Frandsen, Torbjoern Klatt
 *  
 * Solving a puzzle with given size, structure and pieces
 */

import java.io.IOException;

public class Solve {

	public static void main(String[] args) throws IOException {
		// input all pieces
		Puzzle puzzle = new Puzzle(InOut.getPieceCountFromUser());
		int n = puzzle.getPieceCount();
		
		// solution[2] = 3 --> piece.id 3 goes into position 2
		int[] solution = new int[n];
		try {
			solution = puzzle.solve();
			puzzle.printSln(solution);
		}
		catch (NotSolvableException nse) {
			System.out.println(nse.getMessage());
		}
	}
}
