/**
 * reads in the pieces of the puzzle from an input file of a certain format
 * 
 */
import java.io.*;
import java.util.*;

public class InOut {	
    
	private static File f;
	private static Scanner in;
	private static ArrayList<String> arr = new ArrayList<String>();
   
	public InOut() {  
		try {
			f = new File("file");
			in = new Scanner(f);
		}
		catch(FileNotFoundException e) {
			System.out.println("oops");
		}

		while(in.hasNext()) {
			arr.add(in.nextLine());
		}
	}

	//Piece count
	public static int getPieceCountFromUser() throws IOException {
		return arr.size();
	}
   
	//Triangle or square
	public static boolean getPieceShapeFromUser(int piece) throws IOException {
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
	public static int getColourFromUser(int piece, int side) throws IOException {
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
	public static boolean getShapeFromUser(int piece, int side) throws IOException {
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
	public static boolean getOrientationFromUser(int piece, int side) throws IOException {
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