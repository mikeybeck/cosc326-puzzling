import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Torbjoern Klatt, Nabil Boag
 */
public class Addition {
	static int remainder;

	public static void main(String[] args) throws IOException {
		String input;
		String[] inputSplit;
		int base;
		int[] value1, value2, add, dev;
		
		System.out.println("*** ADDITION ***\n****************");
		System.out.println("Please enter two numbers and their base. (e.g. \"1234 56789 10\" for first two numbers in base 10)");
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			input = in.nextLine().trim();
			inputSplit = input.split(" ");
			if(inputSplit.length != 3) {
				System.out.println("Wrong input. (could not detect three values)");
			}
			else {
				base = Integer.parseInt(inputSplit[2]);
				
				// some test-data
//				inputSplit[0] = "1000111";
//				inputSplit[1] = "111000";
//				base = 2;
				// ==> add: 1111111
				// ==> div: 1111110
//				inputSplit[0] = "4625152";
//				inputSplit[1] = "5356106";
//				base = 7;
				// ==> add: 13314261
				// ==> div: 5142130 + 1
//				inputSplit[0] = "573916";
//				inputSplit[1] = "73897261";
//				base = 10;
				// ==> add: 74471177
				// ==> div: 37235588 + 1
				
				if((0 < base) && (base < 11)) {
					value1 = new int[inputSplit[0].length()];
					value2 = new int[inputSplit[1].length()];
					
					// parse values into the int-arrays
					for(int i = 0; i < value1.length; i++) {
						value1[i] = Integer.parseInt(inputSplit[0].substring(i, i+1));
					}
					for(int i = 0; i < value2.length; i++) {
						value2[i] = Integer.parseInt(inputSplit[1].substring(i, i+1));
					}
					
					// calculations
					add = cutOffLeadingNull(add(value1, value2, base));
					dev = cutOffLeadingNull(devide(add, base));
					
					// output
					System.out.println("\n*** (base of " + base + ")");
					System.out.println(toString(value1) + "\n" + toString(value2));
					System.out.println("Addition: " + toString(add));
					System.out.println("Division: " + toString(dev) + " + remainder of " + remainder);
				}
				else {
					System.out.println("Invalid base. (" + base + ")");
				}
			}
		}
	}
	
	public static int[] add(int[] a, int[] b, int base) {
		int aLength = a.length, bLength = b.length, max = Math.max(aLength, bLength);
		int posS, posA, posB, valA, valB, remain = 0, sum;
		int[] sln = new int[max+1];
		
		for(int i = 0; i < max+1; i++) {
			posS = (max - i);
			posA = (aLength - i) - 1;
			posB = (bLength - i) - 1;
			if(posA >= 0) valA = a[posA];
			else valA = 0;
			
			if(posB >= 0) valB = b[posB];
			else valB = 0;
			
			sum = valA + valB;
			
			if(remain == 0) {
				if(sum < base) {
					sln[posS] = sum;
				}
				else { // sum >= base
					sln[posS] = sum - base;
					remain = 1;
				}
			}
			else { // remain > 0
				if((sum + remain) < base) {
					sln[posS] = sum + remain;
					remain = 0;
				}
				else { // (sum + remain) => base
					sum += remain;
					sln[posS] = sum % base;
					remain = (sum - (sum % base)) / base;
				}
			}
		}
		
		return sln;
	}
	
	/*
	 * methodology of dividing by 2
	 * (base 7)
	 * 1345 : 2 = 554 + 1
	 * 13>--------/||   |
	 * --||        ||   |
	 *  04|        ||   |
	 *  04>--------/|   |
	 *  --|         |   |
	 *   05         |   |
	 *   04>--------/   |
	 *   --             |
	 *    1>------------/
	 */
	public static int[] devide(int[] a, int base) {
		int[] sln = new int[a.length];
		int val, rem = 0;
		
		for(int i = 0; i < a.length; i++) {
			val = (base * rem) + a[i];
			if(val > 1) {
				rem = val % 2;
				if(rem == 0) {
					sln[i] = val / 2;
				}
				else { // rem == 1
					sln[i] = (val - 1) / 2;
				}
			}
			else { // a[i] < 2
				rem = a[i];
				sln[i] = 0;
			}
		}
		
		remainder = rem;
		return sln;
	}
	
	public static int[] cutOffLeadingNull(int[] a) {
		int i = 0, count = 0;
		int[] aN;
		while((a[i] == 0) && (i < a.length)) {
			count++;
			i++;
		}
		
		if(count > 0) {
			aN = new int[a.length - count];
			for(int j = 0; j < aN.length; j++) {
				aN[j] = a[j+count];
			}
			return aN;
		}
		else return a;
	}

	public static String input() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		return in.readLine();
	}
	
	public static String toString(int[] a) {
		String out = "";
		int i = 0;
		if(a[i] == 0) i++;
		while(i < a.length) {
			out += a[i];
			i++;
		}
		return out;
	}
}
