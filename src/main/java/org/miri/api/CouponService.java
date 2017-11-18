package org.miri.api;

import java.util.List;

import org.miri.beans.Coupon;
import org.miri.beans.clients.Company;

/**
 * Defines methods for interacting with coupons.
 * @author Miri Yehezkel
 * @see Coupon
 * @see Company
 */
public interface CouponService {
	
	/**
	 * Retrieves a coupon from the database by its Id.
	 * @param id coupon id
	 * @return the found coupon.
	 */
	Coupon getCoupon(long id);
	
	Company getCompany(long companyId);
	
	//TODO check coupon-service: getAllCouponsByCompany() not used
	/** 
	 * Retrieves coupons from the database of the requested company.
	 * @param ownerId coupon's owner
	 * @param activeCoupons whether to get only active coupons
	 * @return a List of found coupons.
	 */
	List<Coupon> getAllCouponsByCompany(long ownerId, boolean activeCoupons);
	
	/**
	 * Retrieves coupons from the database by selected price range and type.
	 * @param minPrice minimum price
	 * @param maxPrice maximum price
	 * @param typeName name of type
	 * @return a List of found coupons.
	 */
	List<Coupon> getAllCoupons(double minPrice, double maxPrice, String typeName);
	
	
}
