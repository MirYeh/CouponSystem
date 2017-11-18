package org.miri.exceptions;

/**
 * RuntimeException thrown when requested data is not found.
 * @author Miri Yehezkel
 * @see RuntimeException
 */
public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4424181328380519350L;
	
	/** Constructs a new runtime exception with a specific message. */
	public DataNotFoundException(Class<? extends Object> ofclass) {
		super("Requested " + ofclass.getSimpleName().toLowerCase() + " not found");
	}
	
	/** Constructs a new runtime exception with the general <i>not found</i> message. */
	public DataNotFoundException() {
		super("Requested data not found");
	}
}
