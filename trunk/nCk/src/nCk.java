import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Date;

public class nCk {
	public static void main(String[] args) throws IOException {
		System.out.println("*** nCk ***\n***********");
		System.out.println("Please enter 'n' followed by 'k' (e.g.: '5 3' for 5C3)");
		String[] input;
		int n, k, bin, bin2;
//		Date start, stop;
		System.out.println(Integer.MAX_VALUE + " is the biggest possible integer value.");
		while(true) {
			input = input().split(" ");
			n = Integer.parseInt(input[0]);
			k = Integer.parseInt(input[1]);
//			start = new Date();
			bin = binCoeff(n, k);
//			stop = new Date();
//			System.out.println((stop.getTime() - start.getTime()) + " milsec");
			if((((n > 33) && ((k > 4) && ((n - k) > 4))) || (bin < 0))) {
				bin2 = binCoeff((n-1),k);
				if(((bin2 - bin) < 0) || (bin2 < 0)) {
					bin = -1;
				}
			}
			if(bin != -1) {
				printOut(n,k,bin);
			}
			else {
				System.out.println(n + "C" + k + " could not be calculated as the result would overflow integer max value.");
			}
		}
	}
	
	public static int binCoeff(int n, int k) {
		int bin = 0;
		int kNew = 0;
		if((n < k) || (k < 0)) { // binomial coefficient not defined for n<k
			bin = 0;
		}
		else if((k == 0) || (n == k)) { // outer diagonal
			bin = 1;
		}
		else {
			if(n < 13) { // no overflow with factorials smaller than 13
				bin = directCalc(n, k);
			}
			else if((n >= 2) && ((n - k) == 1 || (k == 1))) { // 1st diagonal
				bin = firstDiag(n, k);
			}
			else if((n >= 4) && ((k == 2) || ((n - k) == 2))) { // triangular numbers (2nd diagonal)
				bin = secondDiag(n, k);
			}
			else if((n >= 6) && ((k == 3) || ((n - k) == 3))) { // tetrahedral numbers (3rd diagonal)
				bin = thirdDiag(n, k);
			}
			else if((n >= 8) && ((k == 4) || ((n - k) == 4))) { // pentatope numbers (4th diagonal)
				bin = fourthDiag(n, k);
			}
			else  { // direct calculation (recursive)
				if(k > (n/2)) { // use symmetry of Pascal's triangle
					k = n - k;
				}
				// using nCk=(n-1)C(k-1) + (n-1)Ck
				bin = binCoeff((n - 1), (k - 1)) + binCoeff((n - 1), k);
			}
		}
		return bin;
	}
	
	public static int firstDiag(int n, int k) {
		return n;
	}
	
	public static int secondDiag(int n, int k) { // triangular numbers: (n-1)*n/2
		int calc, calc1;
		if(even(n)) {
			calc = n / 2;
		}
		else {
			calc = (n - 1) / 2;
		}
		if((calc * 2) != n) {
			return binCoeff((n - 1), (k - 1)) + binCoeff((n - 1), k);
		}
		else {
			if(even(n)) {
				calc1 = calc * (n - 1);
			}
			else {
				calc1 = calc * n;
			}
			if((calc1 / (n - 1)) != calc) {
				return binCoeff((n - 1), (k - 1)) + binCoeff((n - 1), k);
			}
			else {
				return calc1;
			}
		}
	}
	
	public static int thirdDiag(int n, int k) { // tetrahedral numbers: (n-2)(n-1)n/6
		int calc1, calc2, calc3, calc4;
		if(!even(n) && thirds(n)) {
			calc1 = n / 3;
			calc2 = (n - 1) / 2;
			if(((calc1 * 3) == n) || ((calc2 * 2) == (n - 1))) {
				calc3 = calc1 * calc2;
				if((calc3 / calc2) == calc1) {
					calc4 = calc3 * (n - 2);
					if((calc4 / (n - 2)) == calc3) {
						return calc4;
					}
				}
			}
		}
		else if(!even(n - 1) && thirds(n - 1)) {
			calc1 = (n - 1) / 3;
			calc2 = n / 2;
			if(((calc1 * 3) == (n - 1)) || ((calc2 * 2) == n)) {
				calc3 = calc1 * calc2;
				if((calc3 / calc2) == calc1) {
					calc4 = calc3 * (n - 2);
					if((calc4 / (n - 2)) == calc3) {
						return calc4;
					}
				}
			}
		}
		else if(!even(n - 2) && thirds(n - 2)) {
			calc1 = (n - 2) / 3;
			calc2 = (n - 1) / 2;
			if(((calc1 * 3) == (n - 2)) || ((calc2 * 2) == (n - 1))) {
				calc3 = calc1 * calc2;
				if((calc3 / calc2) == calc1) {
					calc4 = calc3 * n;
					if((calc4 / n) == calc3) {
						return calc4;
					}
				}
			}
		}
		return -1;
	}
	
	public static int fourthDiag(int n, int k) { // pentatope numbers: (n-3)(n-2)(n-1)n/24
		@SuppressWarnings("unused")
		int calc, calc1, calc2, calc3, calc4;
		if(thirds(n)) {
			calc = n / 3;
			calc1 = (n - 1) * (n - 3);
			calc2 = calc1 / 4;
			if((calc2 * 4) == calc1) {
				calc3 = (n - 2) / 2;
				calc4 = calc3 * calc2;
				if((calc4 / calc3) == calc2) {
					return calc4;
				}
			}
		}
		else if(thirds(n-1)) {
			calc = (n - 1) / 3;
			calc1 = (n - 2) * n;
			calc2 = calc1 / 4;
			if((calc2 * 4) == calc1) {
				calc3 = (n - 3) / 2;
				calc4 = calc3 * calc2;
				if((calc4 / calc3) == calc2) {
					return calc4;
				}
			}
		}
		else { // thirds(n-2)
			calc = (n - 2) / 3;
			calc1 = (n - 1) * (n - 3);
			calc2 = calc1 / 4;
			if((calc2 * 4) == calc1) {
				calc3 = n / 2;
				calc4 = calc3 * calc2;
				if((calc4 / calc3) == calc2) {
					return calc4;
				}
			}
		}
		return -1;
	}
	
	public static int directCalc(int n, int k) { // n<13 -> no overflow
		return factorial(n) / (factorial(k) * factorial(n - k));
	}
	
	public static int factorial(int n) { // WARNING! overflow with n>12
		if(n == 0) return -1;
		int fac = 1;
		for(int i = 1; i < n+1; i++) {
			fac *= i;
		}
		return fac;
	}
	
	public static boolean even(int n) {
		if((n % 2) == 0) return true;
		else return false;
	}
	
	public static boolean thirds(int n) {
		if((n % 3) == 0) return true;
		else return false;
	}
	
	public static String input() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		return in.readLine();
	}
	
	public static void printOut(int n, int k, int r) {
		System.out.println(n + "C" + k + " is equal " + r);
	}
}
