package application.Exceptions;

public class DbmIOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	public DbmIOException(String path) {
		this.path = path;
	}
	
	public String getBadPath() {
		return this.path;
	}
	
	
	
	
}
