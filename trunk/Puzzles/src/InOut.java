import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InOut {	
	public static int getPieceCountFromUser() throws IOException {
		System.out.println("Number of pieces: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		return Integer.parseInt(in.readLine());
	}
	
	public static boolean getPieceShapeFromUser(int piece) throws IOException {
		System.out.println("Piece " + piece + ": Square? Y/N:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if(in.readLine().equals("Y")) return true;
		else if(in.readLine().equals("N")) return false;
		else throw new IOException("Wrong input value");
	}
	
	public static boolean getShapeFromUser(int side) throws IOException {
		System.out.println("Side " + side + ": Head? Y/N:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if(in.readLine().equals("Y")) return true;
		else if(in.readLine().equals("N")) return false;
		else throw new IOException("Wrong input value");
	}
	
	public static int getColourFromUser(int side) throws IOException {
		System.out.println("Side " + side + ": Which colour? red/blue/green/yellow:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if(in.readLine().equals("red")) return 0;
		else if(in.readLine().equals("blue")) return 1;
		else if(in.readLine().equals("green")) return 2;
		else if(in.readLine().equals("yellow")) return 3;
		else throw new IOException("Wrong input value");
	}
	
	public static boolean getOrientationFromUser(int side) throws IOException {
		System.out.println("Side " + side + ": Clockwise? Y/N:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if(in.readLine().equals("Y")) return true;
		else if(in.readLine().equals("N")) return false;
		else throw new IOException("Wrong input value");		
	}
}
