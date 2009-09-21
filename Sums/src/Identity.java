import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Identity {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String inStr = "";
		String[] StringArray = new String[2];
		double f = 0, g = 0;
		while(true) {
			if(in.ready()) {
				inStr = in.readLine();
				StringArray = inStr.split(" ");
				f = Double.parseDouble(StringArray[0]);
				g = Double.parseDouble(StringArray[1]);
				System.out.println("f=" + f + " g=" + g + " -> difference=" + identity(f,g));
			}
			else System.exit(0);
		}
	}
	
	public static double identity(double f, double g) {
		double diff = (f / g - f * g) * g + f * g * g;
		return f - diff;
	}
}
