package org.miri.api;

import org.miri.beans.Coupon;
import org.miri.beans.CustomerCoupon;
import org.miri.beans.clients.Customer;

/**
 * Defines methods for a registered customer to interact with the system.
 * @author Miri Yehezkel
 * @see Customer
 * @see Coupon
 */
public interface CustomerService extends ClientService<Customer> {
	
	/**
	 * Allows the associated customer to purchase coupons.
	 * @param couponId coupon id
	 * @param purchaseAmount amount of coupon to purchase
	 * @return a {@link CustomerCoupon} representing the purchase.
	 */
	CustomerCoupon purchaseCoupon(long couponId, int purchaseAmount);
	
	/**
	 * Retrieves a specific purchase.
	 * @param purchaseId requested purchase id
	 * @return a {@link CustomerCoupon} representing the purchase.
	 */
	CustomerCoupon getPurchase(long purchaseId);
}
