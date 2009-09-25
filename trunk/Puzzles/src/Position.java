/**
 * representing the positions of the puzzle to enforce the pieces into a certain
 * structure
 * 
 * properties of a position:
 *  - has got neighbors (either 4 or 3, depending on the shape)
 *  - has got an unique id
 *  
 * 
 * Example:
 * If a triangle shaped position on the board marked 4 has 3 sides ABC and their three 
 * neighbors are position 1 side B, position 11 side C and position 9 side B
 * then the array of neighbors for Position with id = 4 would look like this:
 * neighbors = [1][11][9]
 *			   [B][C] [B]    .. ordered by ABC, with ABC changed to int representation!? 
 * feel free to change, its a bit clumsy but simple
 */

public class Position {
	// stores neighboring sides by piece and side order by side
	private int[][] neighbors; 
	private int id;
	
	/**
	 * constructor for triangles
	 * @param id
	 * @param neighborA
	 * @param aSide
	 * @param neighborB
	 * @param bSide
	 * @param neighborC
	 * @param cSide
	 */
	public Position(int id, int neighborA, int aSide, int neighborB,
					 int bSide, int neighborC, int cSide) {
		this.id = id;
		neighbors = new int[3][2];
		neighbors[0][0] = neighborA;
		neighbors[0][1] = aSide;
		neighbors[1][0] = neighborB;
		neighbors[1][1] = bSide;
		neighbors[2][0] = neighborC;
		neighbors[2][1] = cSide;
	}
	
	/**
	 * constructor for squares
	 * @param id
	 * @param neighborA
	 * @param aSide
	 * @param neighborB
	 * @param bSide
	 * @param neighborC
	 * @param cSide
	 * @param neighborD
	 * @param dSide
	 */
	public Position(int id, int neighborA, int aSide, int neighborB,
					 int bSide, int neighborC, int cSide, int neighborD, int dSide) {
		this.id = id;
		neighbors = new int[4][2];
		neighbors[3][0] = neighborD;
		neighbors[3][1] = dSide;
		neighbors[0][0] = neighborA;
		neighbors[0][1] = aSide;
		neighbors[1][0] = neighborB;
		neighbors[1][1] = bSide;
		neighbors[2][0] = neighborC;
		neighbors[2][1] = cSide;
	}
	
	public int[][] getNeighbors() {
		return neighbors;
	}
	
	public int[] getNeighborA() {
		return neighbors[0];
	}
	
	public int[] getNeighborB() {
		return neighbors[1];
	}
	
	public int[] getNeighborC() {
		return neighbors[2];
	}
	
	public int[] getNeighborD() {
		if(this.neighbors.length == 4) return neighbors[3];
		else {
			int[] ret = new int[2];
			ret[0] = -1;
			ret[1] = -1;
			return ret;
		}
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * return the side of this position, which is connected to the other position
	 * @param position
	 * @return
	 */
	public int getConnectingSide(Position position) {
		for(int i = 0; i < this.neighbors.length; i++) {
			if(this.neighbors[i][0] == position.getId()) return this.neighbors[i][1];
		}
		return -1;
	}
	
	/**
	 * checks whether this position is connected with the other position
	 * @param position
	 * @return
	 */
	public boolean areConnected(Position position) {
		for(int i = 0; i < this.neighbors.length; i++) {
			if(this.neighbors[i][0] == position.getId()) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String str = id + ": " + neighbors[0][0] + "-" + neighbors[0][1] + " " + neighbors[1][0] + "-" + neighbors[1][1] + " " + neighbors[2][0] + "-" + neighbors[2][1];
		if(neighbors.length == 4) {
			str += " " + neighbors[3][0] + "-" + neighbors[3][1];
		}
		return str;
	}
}