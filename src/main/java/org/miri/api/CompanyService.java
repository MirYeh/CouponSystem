package org.miri.api;

import org.miri.beans.Coupon;
import org.miri.beans.clients.Company;

/**
 * Defines methods for a registered company to interact with the system.
 * @author Miri Yehezkel
 * @see Company
 * @see Coupon
 */
public interface CompanyService extends ClientService<Company> {
	
	/**
	 * Creates a coupon in the database.
	 * @param coupon coupon to add
	 * @return the created coupon.
	 */
	Coupon createCoupon (Coupon coupon);
	
	/**
	 * Updates a coupon in the database.
	 * @param couponId coupon id
	 * @param coupon data to update
	 * @return an updated coupon.
	 */
	Coupon updateCoupon (long couponId, Coupon coupon);
	
	/**
	 * Removes a coupon from the database.
	 * @param couponId coupon id
	 */
	void removeCoupon (long couponId);

}
