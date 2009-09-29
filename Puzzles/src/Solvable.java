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
		int ytacount = 0;
		int yhccount = 0;
		int ytccount = 0;
		int yhacount = 0;
		int rtacount = 0;
		int rhccount = 0;
		int rtccount = 0;
		int rhacount = 0;
		int gtacount = 0;
		int ghccount = 0;
		int gtccount = 0;
		int ghacount = 0;
		int btacount = 0;
		int bhccount = 0;
		int btccount = 0;
		int bhacount = 0;
		
		Piece[] pieces = puzzle.getPieces();
		int tempcount = 0;
		int tempcount2 = 0;
		for(Piece piece: pieces){
				tempcount ++;
				Side[] sides = piece.getSides();
				for(Side side: sides){
					tempcount2++;
					if(side.equals(yta)){
						
						ytacount ++;
					}
					else if(side.equals(yhc)){
						yhccount ++;
					}
					else if(side.equals(ytc)){
						ytccount ++;
					}
					else if(side.equals(yha)){
						yhacount ++;
					}
					else if(side.equals(rta)){
						rtacount ++;
					}
					else if(side.equals(rhc)){
						rhccount ++;
					}
					else if(side.equals(rtc)){
						rtccount ++;
					}
					else if(side.equals(rha)){
						rhacount ++;
					}
					else if(side.equals(gta)){
						gtacount ++;
					}
					else if(side.equals(ghc)){
						ghccount ++;
					}
					else if(side.equals(gtc)){
						gtccount ++;
					}
					else if(side.equals(gha)){
						ghacount ++;
					}
					else if(side.equals(bta)){
						btacount ++;
					}
					else if(side.equals(bhc)){
						bhccount ++;
					}
					else if(side.equals(btc)){
						btccount ++;
					}
					else if(side.equals(bha)){
						bhacount ++;
					}
				}
		}
		return ((ytacount == yhccount)&&(yhacount == ytccount)&&(rtacount == rhccount)&&(rhacount == rtccount)&&(gtacount == ghccount)&&(ghacount == gtccount)&&(btacount == bhccount)&&(bhacount == btccount));
	}
}