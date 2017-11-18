package org.miri.api.repositories;

import java.sql.Date;
import java.util.List;

import org.miri.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	List<Coupon> findByOwnerUsernameIgnoreCase(String ownerName);
	
	@Query("SELECT c FROM Coupon c WHERE c.amount > 0 AND c.endDate > CURRENT_DATE AND c.isActive = true")
	List<Coupon> findActiveCoupons();	
	
	@Query("SELECT c FROM Coupon c WHERE c.endDate < CURRENT_DATE AND c.isActive = true")
	List<Coupon> findActiveExpiredCoupons();
	
	List<Coupon> findAllByOwnerIdAndIsActive(long ownerId, boolean activeCoupons);
	List<Coupon> findAllByOwnerId(long ownerId);
	
	@Query("SELECT c FROM Coupon c WHERE c.endDate <= ?1 AND c.isActive = true")
	List<Coupon> findActiveCouponsByEndDate(Date endDate);
	List<Coupon> findByEndDate(Date endDate);
	
	@Query("SELECT c FROM Coupon c WHERE c.price BETWEEN ?1 AND ?2 AND c.isActive = true")
	List<Coupon> findActiveCouponsByPriceRange(double minPrice, double maxPrice);
	
	@Query("SELECT c FROM Coupon c JOIN c.type type "
		+ "WHERE c.price BETWEEN ?1 AND ?2 AND UPPER(type.name) = UPPER(?3) "
		+ "AND c.isActive = true")
	List<Coupon> findActiveCouponsByParams(double minPrice, double maxPrice, String type);
	
	
	
}
