package wniemiec.web.nforum.services.exceptions;


/**
 * Indicates that an element has not been found.
 */
public class ElementNotFoundException extends Exception {
	
	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public ElementNotFoundException() {
	}
	
	public ElementNotFoundException(String msg) {
		super(msg);
	}
}
