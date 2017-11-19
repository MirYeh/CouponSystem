package org.miri.core.services;

import java.util.List;

import org.miri.api.CustomerCouponService;
import org.miri.api.GuestService;
import org.miri.api.repositories.CompanyRepository;
import org.miri.api.repositories.CouponRepository;
import org.miri.core.beans.Coupon;
import org.miri.core.beans.clients.Company;
import org.miri.core.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Defines methods allowed for all clients and guests.
 * @author Miri Yehezkel
 */
@Service @Primary
public class GuestServiceImpl implements GuestService {

	@Autowired
	protected CouponRepository couponRepository;
	@Autowired
	protected CustomerCouponService ccService;
	@Autowired
	private CompanyRepository companyRepository;

	/**
	 * Retrieves all companies from the database.
	 * @return a List of found companies.
	 */
	public List<Company> getAllCompanies() {
		return Validations.VerifyNotEmpty(companyRepository.findAll());
	}

	/**
	 * Retrieves a List of all the companies names.
	 * @return a List of type String.
	 */
	public List<String> getAllCompaniesNames() {
		return Validations.VerifyNotEmpty(companyRepository.findAllUsernames());
	}
	
	/** 
	 * Retrieves a coupon from the database by its Id.
	 * @param id coupon id
	 * @return the found coupon.
	 */
	public Coupon getCoupon(long id) {
		return Validations.verifyNotNull(couponRepository.findOne(id));
	}

	public Company getCompany(long companyId) {
		return Validations.verifyNotNull(companyRepository.findOne(companyId));
		
	}
	
	/**
	 * Retrieves coupons from the database of the requested company.
	 * @param ownerId coupon's owner
	 * @param activeCoupons whether to get only active coupons
	 * @return a List of found coupons.
	 */
	public List<Coupon> getAllCouponsByCompany(long ownerId, boolean activeCoupons) {
		List<Coupon> companyCoupons = 
				couponRepository.findAllByOwnerIdAndIsActive(ownerId, activeCoupons);
		return Validations.verifyNotNull(companyCoupons);
	}

	/**
	 * Retrieves coupons from the database of the requested company's name.
	 * @param companyName name of requested company
	 * @return a List of found coupons.
	 */
	public List<Coupon> getAllCouponsByCompanyName(String companyName) {
		return Validations.VerifyNotEmpty(
				couponRepository.findByOwnerUsernameIgnoreCase(companyName));
	}
	
	/**
	 * Retrieves coupons from the database by selected price range and type.
	 * @param minPrice minimum price
	 * @param maxPrice maximum price
	 * @param typeName name of type
	 * @return a List of found coupons.
	 */
	public List<Coupon> getAllCoupons(double minPrice, double maxPrice, String typeName) {
		List<Coupon> coupons;
		if (typeName != null && !typeName.trim().isEmpty())
			coupons = couponRepository.findActiveCouponsByParams(minPrice, maxPrice, typeName);
		else
			coupons = couponRepository.findActiveCouponsByPriceRange(minPrice, maxPrice);
		return Validations.VerifyNotEmpty(coupons);
	}
	
	
}
