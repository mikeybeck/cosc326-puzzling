
public class DontMatchException extends Exception {
	private static final long serialVersionUID = 1L;
	private static String msg = "Pieces don't match.";

	public DontMatchException() {}
	
	private String getMsg() {
		return msg;
	}
}
