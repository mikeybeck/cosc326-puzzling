//
//  Solvable.java
//  
//  Created by nabil boag on 9/22/09.
//  Create this object and call the canBesolved method on a puzzle to check if it can be solved
//

public class Solvable {
	private Side yta;
	private Side yhc;
	private Side ytc;
	private Side yha;
	private Side rta;
	private Side rhc;
	private Side rtc;
	private Side rha; 
	private Side gta;
	private Side ghc;
	private Side gtc;
	private Side gha;
	private Side bta;
	private Side bhc;
	private Side btc;
	private Side bha;
	
	// Constuctor initializes the sides
	public Solvable(){
		yta = new Side(false, 3, false);
		yhc = new Side(true, 3, true);
		ytc = new Side(false, 3, true);
		yha = new Side(true, 3, false);
		rta = new Side(false, 0, false);
		rhc = new Side(true, 0, true);
		rtc = new Side(false, 0, true);
		rha = new Side(true, 0, false);
		gta = new Side(false, 2, false);
		ghc = new Side(true, 2, true);
		gtc = new Side(false, 2, true);
		gha = new Side(true, 2, false);
		bta = new Side(false, 1, false);
		bhc = new Side(true, 1, true);
		btc = new Side(false, 1, true);
		bha = new Side(true, 1, false);
	}
	
	// Checks to see if there are any sides that don't have a match, if they don't, the puzzle can not be solved. 
	public boolean canBeSolved(Puzzle puzzle){
		if((puzzle.getMatchingSideCount(yta) == puzzle.getMatchingSideCount(yhc))
				&& (puzzle.getMatchingSideCount(ytc) == puzzle.getMatchingSideCount(yha))
				&& (puzzle.getMatchingSideCount(rta) == puzzle.getMatchingSideCount(rhc))
				&& (puzzle.getMatchingSideCount(rtc) == puzzle.getMatchingSideCount(rha))
				&& (puzzle.getMatchingSideCount(gta) == puzzle.getMatchingSideCount(ghc))
				&& (puzzle.getMatchingSideCount(gtc) == puzzle.getMatchingSideCount(gha))
				&& (puzzle.getMatchingSideCount(bta) == puzzle.getMatchingSideCount(bhc))
				&& (puzzle.getMatchingSideCount(btc) == puzzle.getMatchingSideCount(bha))) {
			return true;
		}
		else return false;
	}
}