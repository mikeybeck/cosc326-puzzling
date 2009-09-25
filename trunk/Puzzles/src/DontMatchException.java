/**
 * is thrown, when two pieces have not got any matching sides or are of the same shape
 * 
 */
public class DontMatchException extends Exception {
	private static final long serialVersionUID = 1L;
	private static String msg = "Pieces don't match.";

	public DontMatchException() {}
	
	private String getMsg() {
		return msg;
	}
}
