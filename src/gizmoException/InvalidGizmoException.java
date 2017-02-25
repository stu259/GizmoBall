package gizmoException;

public class InvalidGizmoException extends Exception {

	private String message;
	
	public InvalidGizmoException(String m) {
		message = m;
	}

	public String getMessager(){
		return message;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
