public class AlreadyPlacedException extends Exception {
	private static final long serialVersionUID = 1L;
	private static String msg = "Piece already placed.";

	public AlreadyPlacedException() {}
	
	private String getMsg() {
		return msg;
	}
}
