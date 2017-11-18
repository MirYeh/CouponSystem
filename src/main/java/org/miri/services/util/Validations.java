package org.miri.services.util;

import java.util.List;

import org.miri.beans.clients.Client;
import org.miri.exceptions.DataNotFoundException;
import org.miri.exceptions.UserInputException;
import org.miri.exceptions.UserMessages;

/**
 * Groups validation and verification methods that are used by more than one service.<br>
 * Validation methods return a boolean value while verification methods throw an exception.
 * @author Miri Yehezkel
 */
public class Validations {
	
	/** Checks if object is not {@code null}. Returns a boolean value of {@code true}
	 *  if object is null and {@code false} otherwise.
	 */
	public static <T extends Object> boolean isNull(T t) {
		if (t == null)
			return true;
		return false;
	}
	
	/** Checks if list is not {@code null} or empty. Returns a boolean value of
	 *  {@code true} if list is empty and {@code false} otherwise.
	 */
	public static <T extends Object> boolean isEmpty(List<T> list) {
		if (list == null || list.size() == 0)
			return true;
		return false;
	}
	
	/**
	 * Verifies client is not null and has a user-name and a password.
	 * @param client to  verify
	 * @throws UserInputException with respective message if client is null or missing a field.
	 */
	public static void verifyNotEmpty(Client client) {
		if (client == null)
			throw new UserInputException(UserMessages.EMPTY_USERNMAE);
		String username = client.getUsername();
		if (username == null || username.trim().isEmpty())
			throw new UserInputException(UserMessages.EMPTY_USERNMAE);
		String password = client.getPassword();
		if (password == null || password.trim().isEmpty())
			throw new UserInputException(UserMessages.EMPTY_PASSWORD);
	}
	
	/** Verifies client is logged in. Otherwise throws UserInputException with respective message. */
	public static <T extends Client> T verifyLoggedIn(T client) {
		if (client == null || client.getId() == 0)
			throw new UserInputException(UserMessages.USER_NOT_LOGGED_IN);
		return client;
	}
	
	/** Verifies given client has an equal id to the requested id. */
	public static <T extends Client> void verifyAccessAllowed(T client, long id) {
		if (client.getId() != id)
			throw new UserInputException(UserMessages.ACCESS_DENIED);
	}
	

	/** Verifies Object isn't null. Otherwise throws DataNotFoundException. */
	public static <T extends Object> T verifyNotNull(T t) throws DataNotFoundException {
		if (t == null)
			throw new DataNotFoundException();
		return t;
	}

	/** Verifies List isn't null or empty. Otherwise throws DataNotFoundException. */
	public static <T extends Object> List<T> VerifyNotEmpty(List<T> list) {
		if (list == null || list.size() == 0)
			throw new DataNotFoundException();
		return list;
	}
	
	
	
}
