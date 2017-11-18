package org.miri.api;

import java.util.List;

import org.miri.beans.Coupon;
import org.miri.beans.clients.Client;

/**
 * Defines methods that allow clients to access the system.
 * @author Miri Yehezkel
 */
public interface ClientService<C extends Client> extends GuestService {
	
	/**
	 * Updates the associated client in the database.
	 * @param id client id
	 * @param client client data to update
	 * @return an updated client.
	 */
	C update(long id, C client);
	
	/**
	 * Removes the associated client from the database.
	 * @param id client id
	 */
	void remove(long id);
	
	/**
	 * Allows client to log-in.
	 * @param client client to log in
	 * @return an associated client.
	 */
	C login(C client);
	
	/**
	 * Registers a new client to the database.
	 * @param client client to register
	 * @return an associated client.
	 */
	C register(C client);
	
	/**
	 * Allows client to log-out.
	 * @param id client id
	 */
	void logout(long id);

	/**
	 * Retrieves the associated client.
	 * @param id client id
	 * @return the associated client.
	 */
	C viewClient(long id);
	
	/**
	 * Retrieves coupons by the associated client from the database.
	 * @return a List of coupons.
	 */
	List<Coupon> getCoupons();
	
	
}
