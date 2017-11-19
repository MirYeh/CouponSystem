package org.miri.api.repositories;

import java.util.List;

import org.miri.core.beans.Coupon;
import org.miri.core.beans.CustomerCoupon;
import org.miri.core.beans.clients.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, Long> {
	
	List<CustomerCoupon> findByCustomer(Customer customer);
	
	List<CustomerCoupon> findByCoupon(Coupon coupon);
	
	@Query("SELECT coup FROM CustomerCoupon custCoup JOIN custCoup.coupon coup WHERE custCoup.customer = ?1")
	List<Coupon> findCustomerCoupons(Customer customer);
	
}
