package org.miri.exceptions;

/**
 * RuntimeException thrown when a Company delete operation on coupons or self can't be executed.
 * @author Miri Yehezkel
 * @see RuntimeException
 */
public class CompanyRemovalException extends RuntimeException {
	private static final long serialVersionUID = 1970155377535461976L;
	
	public CompanyRemovalException(UserMessages message) {
		super(message.getValue());
	}
	
	
	
}
