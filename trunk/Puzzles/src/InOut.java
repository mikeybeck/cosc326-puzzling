/**
 * reads in the pieces of the puzzle from an input file of a certain format
 * 
 */
import java.io.*;
import java.util.*;

public class InOut {	
    
	private File f;
	private Scanner in;
	private ArrayList<String> arr = new ArrayList<String>();
   
	public InOut(String inFile) {
		try {
			f = new File(inFile);
			in = new Scanner(f);

			while(in.hasNext()) {
				arr.add(in.nextLine());
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("oops");
		}
	}
	
	public Position getPosition(int id) throws IOException {
		String line = arr.get(id).toString().trim();
		String[] lineSplit = line.split(",");
		int[] intArr = new int[lineSplit.length];
		Position pos;
		for(int i = 0; i < lineSplit.length; i++) {
			intArr[i] = Integer.parseInt(lineSplit[i].trim());
		}
		if(intArr.length == 6) { // triangle
			pos = new Position(id, intArr[0], intArr[1], intArr[2], intArr[3], intArr[4], intArr[5]);
		}
		else if(intArr.length == 8) { // square
			pos = new Position(id, intArr[0], intArr[1], intArr[2], intArr[3], intArr[4], intArr[5], intArr[6], intArr[7]);
		}
		else {
			throw new IOException();
		}
		return pos;
	}

	//Piece count
	public int getPieceCountFromUser() throws IOException {
		return arr.size();
	}
   
	//Triangle or square
	public boolean getPieceShapeFromUser(int piece) throws IOException {
		String line = arr.get(piece).toString();
		char c = line.charAt(0);
		if (c == 'T'){
			return false;
		}
		else if (c == 'S'){
			return true;
		}
		else {
			throw new IOException("Wrong input value");
		}
	}

	//Colour
	public int getColourFromUser(int piece, int side) throws IOException {
       String line = arr.get(piece).toString();
       char c = ' ';
        if(side == 1) {
        	c = line.charAt(1);
        }
        else if(side == 2) {
        	c = line.charAt(4);
        }
        else if(side == 3) {
        	c = line.charAt(7);
        }
        else if(side == 4) {
        	c = line.charAt(10);
        }
        if(c == 'R') {
        	return 0;
        }
        else if (c == 'B') {
        	return 1;
        }
        else if(c == 'G') {
        	return 2;
        }
        else if(c == 'Y') {
        	return 3;
        }
        else {
        	throw new IOException("Wrong input value");
        }
	}

	//Heads or tail
	public boolean getShapeFromUser(int piece, int side) throws IOException {
		String line = arr.get(piece).toString();
		
		char c = ' ';
		if(side == 1) {
			c = line.charAt(2);
		}
		else if(side == 2) {
			c = line.charAt(5);
		}
		else if(side == 3) {
			c = line.charAt(8);
		}
		else if(side == 4) {
			c = line.charAt(11);
		}
		if(c == 'H') {
			return true;
		}
		else if (c == 'T') {
			return false;
		}
		else {
			throw new IOException("Wrong input value");
		}   
	}

	//Orientation
	public boolean getOrientationFromUser(int piece, int side) throws IOException {
		String line = arr.get(piece).toString();
		char c = ' ';
		if(side == 1) {
			c = line.charAt(3);
		}
		else if(side == 2) {
			c = line.charAt(6);
		}
		else if(side == 3) {
			c = line.charAt(9);
		}
		else if(side == 4) {
			c = line.charAt(12);
		}
		if(c == 'C') {
			return true;
		}
		else if (c == 'A') {
			return false;
		}
		else {
			throw new IOException("Wrong input value");
		}
	}
}