package org.miri.core.exceptions;

/**
 * RuntimeException thrown when a given input is not valid and should be changed by the user.
 * @author Miri Yehezkel
 * @see RuntimeException
 */
public class UserInputException extends RuntimeException {
	private static final long serialVersionUID 		= 	8924684710306184537L;
	
	/** 
	 * Constructs a new runtime exception using a predefined UserMessages message.
	 * @see UserMessages
	 */
	public UserInputException(UserMessages message) {
		super(message.getValue());
	}
	
	private UserInputException(String message) {
		super(message);
	}
	
	public static UserInputException build(String... messages) {
		StringBuilder result = new StringBuilder();
		for (String curr : messages)
			result.append(curr);
		return new UserInputException(result.toString());
	}
	
	
}//end of class