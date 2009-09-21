/* Example:
 * If a triangle shaped position on the board marked 4 has 3 sides ABC and their three 
 * neighbors are position 1 side B, position 11 side C and position 9 side B
 * then the array of neigbors for Position with id = 4 would look like this:
 * neighbors = [1][11][9]
 *			   [B][C] [B]    .. ordered by ABC, with ABC changed to int representation!? 
 * feel free to change, its a bit clumsy but simple
 */

public class Position {
	
	int[][] neighbors;//stores neighboring sides by piece and side order by side.
	int id;
	
	public Position(int id, boolean square, int neighborA, int aSide, int neighborB,
					 int bSide, int neighborC, int cSide) {
		int neighborD = -1;
		int dSide = -1;
		if(square) {
			neighbors = new int[4][2];
			neighbors[3][0] = neighborD;
			neighbors[3][1] = dSide;
		}
		else {
			neighbors = new int[3][2];
		}
		neighbors[0][0] = neighborA;
		neighbors[0][1] = aSide;
		neighbors[1][0] = neighborB;
		neighbors[1][1] = bSide;
		neighbors[2][0] = neighborC;
		neighbors[2][1] = cSide;
	}
	
	int[][] getNeighbors(){
		return neighbors;
	}
	
	int getId(){
		return id;
	}

}