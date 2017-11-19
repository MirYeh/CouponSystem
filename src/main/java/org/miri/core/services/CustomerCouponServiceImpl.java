package org.miri.core.services;

import java.util.List;

import org.miri.api.CustomerCouponService;
import org.miri.api.repositories.CouponRepository;
import org.miri.api.repositories.CustomerCouponRepository;
import org.miri.core.beans.Coupon;
import org.miri.core.beans.CustomerCoupon;
import org.miri.core.beans.clients.Customer;
import org.miri.core.exceptions.UserInputException;
import org.miri.core.exceptions.UserMessages;
import org.miri.core.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCouponServiceImpl implements CustomerCouponService {
	
	@Autowired
	CustomerCouponRepository ccRepository;
	
	@Autowired
	CouponRepository couponRepository;
	
	
	/**
	 * Allows customers to purchase the requested amount of coupons.<br>
	 * Method verifies there is a sufficient amount of the requested coupon to purchase,
	 *  and registers an income for the system.
	 * @param customer purchasing customer
	 * @param coupon coupon to purchase
	 * @param purchaseAmount amount of coupon to purchase
	 * @return the CustomerCoupon object created and saved to the system.
	 */
	@Override
	public CustomerCoupon purchase(Customer customer, Coupon coupon, int purchaseAmount) {
		verifyValidPurchaseAmount(coupon, purchaseAmount);
		CustomerCoupon customerCoupon = ccRepository.save(new CustomerCoupon(customer, coupon, purchaseAmount));
		updateCouponAmount(coupon, purchaseAmount);
		return customerCoupon;
	}
	
	/** Updates amount of coupon after purchase. */
	private void updateCouponAmount(Coupon coupon, int purchaseAmount) {
		int finalAmount = coupon.getAmount() - purchaseAmount;
		coupon.setAmount(finalAmount);
		if (finalAmount == 0)
			coupon.setIsActive(false);
		couponRepository.save(coupon);
	}

	/**
	 * Retrieves customer's purchased coupons.
	 * @param customer requested customer's coupons
	 * @return a List of purchases by the customer.
	 */
	@Override
	public List<Coupon> getCustomerPurchases(Customer customer) {
		return Validations.verifyNotNull(
				ccRepository.findCustomerCoupons(customer)
				);
	}
	
	/**
	 * Retrieves requested coupon's purchases.
	 * @param coupon coupon to retrieve purchases
	 * @return a List of {@link CustomerCoupon}.
	 */
	public List<CustomerCoupon> getCouponPurchases(Coupon coupon) {
		return ccRepository.findByCoupon(coupon);
	}
	
	
	public CustomerCoupon getPurchase(long purchaseId) {
		return Validations.verifyNotNull(ccRepository.findOne(purchaseId));
	}

	/** Verifies amount of purchase is valid. Otherwise throws UserInputException with respective message */
	void verifyValidPurchaseAmount(Coupon coupon, int purchaseAmount) {
		if (purchaseAmount < 1)
			throw new UserInputException(UserMessages.COUPON_INVALID_AMOUNT);
		int maxAmount = coupon.getAmount();
		if (maxAmount < purchaseAmount)
			throw UserInputException.build(UserMessages.COUPON_INVALID_AMOUNT.getValue(),
					UserMessages.MAX.getValue().replaceFirst("%s", maxAmount + ""));
	}
	
}
