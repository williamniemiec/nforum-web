package wniemiec.app.nforum.services.exceptions;

public class ElementNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ElementNotFoundException() {
	}
	
	public ElementNotFoundException(String msg) {
		super(msg);
	}
}
