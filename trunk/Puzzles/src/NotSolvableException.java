/**
 * is thrown when a puzzle can not be solved, because of different reasons
 * 
 */
public class NotSolvableException extends Exception {
	private static final long serialVersionUID = 1L;
	private static String msg = "Puzzle not solvable.";

	public NotSolvableException() {}
	
	private String getMsg() {
		return msg;
	}
}
