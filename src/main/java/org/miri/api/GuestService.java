package org.miri.api;

import java.util.List;

import org.miri.core.beans.clients.Company;

/**
 * Defines methods available for clients and guests.
 * @author Miri Yehezkel
 * @see CouponService
 */
public interface GuestService extends CouponService {
	
	/**
	 * Retrieves all companies from the database.
	 * @return a List of found companies.
	 */
	List<Company> getAllCompanies();
	
	//TODO check guest-service: getAllCompaniesNames() not used
	/**
	 * Retrieves a List of all the companies names.
	 * @return a List of type String.
	 */
	List<String> getAllCompaniesNames();
	
}
