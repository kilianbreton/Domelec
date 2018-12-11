package application.Exceptions;

public class SelectException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6933704212172997175L;
	private String type;
	
	public SelectException(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
