

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInput {
	private File f;
	private Scanner in;
	private ArrayList<String> arr = new ArrayList<String>();

	public FileInput(String fileName) {
		try {
			f = new File(fileName);
			in = new Scanner(f);
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("File " + fileName + " not found.");
		}
		
		while(in.hasNext()) {
			arr.add(in.nextLine());
		}
	}
	
	/**
	 * returns the requested line trimmed
	 * @param line
	 * @return requested trimmed line
	 */
	public String getString(int line) {
		return arr.get(line).trim();
	}
	
	/**
	 * returns the amount of lines
	 * @return total amount of lines
	 */
	public int getLineCount() {
		return arr.size();
	}
}
