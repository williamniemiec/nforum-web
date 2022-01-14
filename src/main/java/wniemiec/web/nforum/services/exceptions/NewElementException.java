package wniemiec.web.nforum.services.exceptions;


/**
 * Indicates a new element could not be insert.
 */
public class NewElementException extends Exception {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public NewElementException() {
	}
	
	public NewElementException(String msg) {
		super(msg);
	}
}
