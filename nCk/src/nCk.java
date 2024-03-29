import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Date;

public class nCk {
	public static void main(String[] args) throws IOException {
		System.out.println("*** nCk ***\n***********");
		System.out.println("Please enter 'n' followed by 'k' (e.g.: '5 3' for 5C3)");
		String[] input;
		int n, k, bin = 0, bin2 = 0, bin3 = 0;
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
			if(n > 33 || bin == -1 || bin < 0) {
				bin2 = binCoeff(n-1,k-1);
				bin3 = binCoeff(n-1,k);
				if(bin2 > bin) {
					bin = -1;
				}
				else if(((bin2 + bin3) != bin) || (bin2 == -1) || (bin3 == -1)) {
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
		if((n < k) || (k < 0)) { // binomial coefficient not defined for n<k
			bin = 0;
		}
		else if((k == 0) || (n == k)) { // outer diagonal
			bin = 1;
		}
		else {
			if(n < 13) { // no overflow with factorials of values smaller than 13
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
			calc1 = calc * (n-1);
			if(overflow(calc, n-1, calc1)) return -1;
			return calc1; // n/2 * (n-1)
		}
		else {
			calc = (n - 1) / 2;
			calc1 = calc * n;
			if(overflow(calc, n, calc1)) return -1;
			return calc1; // (n-1)/2 * n
		}
	}
	
	public static int thirdDiag(int n, int k) { // tetrahedral numbers: (n-2)(n-1)n/6
		int calc1, calc2, calc3, calc4;
		if(thirds(n)) {
			calc1 = n / 3;
			if(even(n-1)) {
				calc2 = (n-1) / 2;
				calc3 = calc1 * calc2;
				if(overflow(calc1, calc2, calc3)) return -1;
				calc4 = calc3 * (n-2);
				if(overflow(calc3, (n-2), calc4)) return -1;
				return calc4; // n/3 * (n-1)/2 * (n-2)
			}
			else { // even(n-2) == true 
				calc2 = (n-2) / 2;
				calc3 = calc1 * calc2;
				if(overflow(calc1, calc2, calc3)) return -1;
				calc4 = calc3 * (n-1);
				if(overflow(calc3, (n-1), calc4)) return -1;
				return calc4; // n/3 * (n-2)/2 * (n-1)
			}
		}
		else if(thirds(n-1)) {
			calc1 = (n-1) / 3;
			if(even(n)) {
				calc2 = n / 2;
				calc3 = calc1 * calc2;
				if(overflow(calc1, calc2, calc3)) return -1;
				calc4 = calc3 * (n-2);
				if(overflow(calc3, (n-2), calc4)) return -1;
				return calc4; // (n-1)/3 * n/2 * (n-2)
			}
			else {
				return binCoeff(n-1,k-1) + binCoeff(n-1,k);
			}
		}
		else { // thirds(n-2) == true
			calc1 = (n-2) / 3;
			if(even(n)) {
				calc2 = n / 2;
				calc3 = calc1 * calc2;
				if(overflow(calc1, calc2, calc3)) return -1;
				calc4 = calc3 * (n-1);
				if(overflow(calc3, (n-1), calc4)) return -1;
				return calc4; // (n-2)/3 * n/2 * (n-1)
			}
			else { // even(n-1) == true
				calc2 = (n-1) / 2;
				calc3 = calc1 * calc2;
				if(overflow(calc1, calc2, calc3)) return -1;
				calc4 = calc3 * n;
				if(overflow(calc3, n, calc4)) return -1;
				return calc4; // (n-2)/3 * (n-1)/2 * n
			}
		}
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
	
	public static boolean overflow(int calc1, int calc2, int calc3) {
		if((calc3 / calc1) != calc2) return true;
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
