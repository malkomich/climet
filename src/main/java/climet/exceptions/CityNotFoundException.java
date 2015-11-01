package climet.exceptions;

public class CityNotFoundException extends Exception {

	private static final long serialVersionUID = 5657953512926370109L;
	
	private String message;
	
	/**
	 * Creates a new instance of
	 * <code>CityNotFoundException</code> without detail message.
	 */
	public CityNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs an instance of
	 * <code>CityNotFoundException</code> with the specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public CityNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
	    return message;
	}
	
}