import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Torbjoern Klatt
 *
 */
public class Addition {

	public static void main(String[] args) throws IOException {
		String input;
		String[] inputSplit;
		int base;
		int[] value1, value2, add;
		
		System.out.println("*** ADDITION ***\n****************");
		System.out.println("Please enter two numbers and their base. (e.g. \"1234 56789 10\")");
		do {
			input = getUserInput();
			inputSplit = input.split(" ");
			if(inputSplit.length != 3) {
				System.out.println("Wrong input. (could not detect three values)");
			}
			else {
				base = Integer.parseInt(inputSplit[2]);
				
//				inputSplit[0] = "4625152";
//				inputSplit[1] = "5356106";
//				base = 7;
				// ==> add: 13314261
				// ==> div: 
				inputSplit[0] = "573916";
				inputSplit[1] = "73897261";
				base = 10;
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
					
					add = add(value1, value2, base);
					System.out.println(intArray2String(value1) + "+" + intArray2String(value2) + " of base " + base + " is " + intArray2String(add));
					
					add = cutOffLeadingNull(add);
					
					devide(add, base);
				}
				else {
					System.out.println("Invalid base. (" + base + ")");
				}
			}
		}
		while(true);
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
	
	/**
	 * is not working properly! is producing wrong results for other bases than 10
	 * @param a
	 * @param base
	 */
	//TODO correct the code
	public static void devide(int[] a, int base) {
		int[] sln = new int[a.length];
		int val, sub, rem = 0;
		for(int i = 0; i < a.length; i++) {
			val = a[i] + (10 * rem);
			if(val > 1) {
				rem = val % 2;
				if(rem == 0) {
					sub = val / 2;
					if(sub < base) {
						sln[i] = sub;
					}
					else { // sub >= base
						sln[i] = sub - base;
						for(int j = 1; j < i+1; j++) {
							sln[i-j]++;
							if(sln[i-j] < base) j = i+1;
							else {
								if(sln[i-j] == base) sln[i-j] = 0;
								else sln[i-j] -= base;
							}
						}
					}
				}
				else { // rem == 1
					sub = (val - 1) / 2;
					if(sub < base) {
						sln[i] = sub;
					}
					else { // sub >= base
						sln[i] = sub - base;
						for(int j = 1; j < i+1; j++) {
							sln[i-j]++;
							if(sln[i-j] < base) j = i+1;
							else {
								if(sln[i-j] == base) sln[i-j] = 0;
								else sln[i-j] -= base;
							}
						}
					}
				}
			}
			else {
				sln[i] = 0;
				rem = val;
			}
		}
		
		// check the solution
		int[] chk = add(sln, sln, base);
		chk = cutOffLeadingNull(chk);
		chk[chk.length-1]++;
		System.out.println(intArray2String(chk) + "\n" + intArray2String(a));
		// end of check
		
		// final output
		System.out.println(intArray2String(a) + " by 2 in base of " + base + " is " + intArray2String(sln) + " and a remainer of " + rem);
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

	public static String getUserInput() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		return in.readLine();
	}
	
	public static String intArray2String(int[] a) {
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
