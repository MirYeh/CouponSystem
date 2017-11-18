package org.miri.api;

import java.util.List;

import org.miri.beans.Coupon;
import org.miri.beans.CustomerCoupon;
import org.miri.beans.clients.Customer;

/**
 * Defines methods for interacting with purchases.
 * @author Miri Yehezkel
 * @see CustomerCoupon
 * @see Coupon
 */
public interface CustomerCouponService {
	
	/**
	 * Allows customers to purchase the requested amount of coupons.<br>
	 * Method verifies there is a sufficient amount of the requested coupon to purchase,
	 *  and registers an income for the system.
	 * @param customer purchasing customer
	 * @param coupon coupon to purchase
	 * @param purchaseAmount amount of coupon to purchase
	 * @return the CustomerCoupon object created and saved to the system.
	 */
	CustomerCoupon purchase(Customer customer, Coupon coupon, int purchaseAmount);
	
	/**
	 * Retrieves customer's purchased coupons.
	 * @param customer requested customer purchases
	 * @return a List of coupons.
	 */
	List<Coupon> getCustomerPurchases(Customer customer);
	
	/**
	 * Retrieves coupon purchases.
	 * @param coupon requested coupon's purchases
	 * @return a List of {@link CustomerCoupon}.
	 */
	List<CustomerCoupon> getCouponPurchases(Coupon coupon);
	
	/**
	 * Retrieves purchase data by purchaseId.
	 * @param purchaseId requested purchase id
	 * @return a CustomerCoupon representing the requested purchase.
	 */
	CustomerCoupon getPurchase(long purchaseId);
	
	
	
}
