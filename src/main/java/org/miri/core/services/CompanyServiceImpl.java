package org.miri.core.services;

import java.sql.Date;
import java.util.List;

import org.miri.api.CompanyService;
import org.miri.api.repositories.CompanyRepository;
import org.miri.core.beans.Coupon;
import org.miri.core.beans.clients.Company;
import org.miri.core.exceptions.*;
import org.miri.core.util.TypeHandler;
import org.miri.core.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles access of companies to the database.
 * Company can access account-related methods only after logging in or registering.
 * @author Miri Yehezkel
 * @see CompanyService
 */
@Service
public class CompanyServiceImpl extends GuestServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository repository;
	@Autowired
	private TypeHandler typeHandler;
	
	/** Represents the company instance of the service when client is logged-in. */
	@Autowired
	private Company instance;
	
	
	/**
	 * Updates company's data in the database.<br>
	 * Throws UserInputException with corresponding message
	 *  if required fields are missing or if user-name is occupied.
	 * @param company company data to update
	 * @throws UserInputException if required fields are missing or if user-name is occupied.
	 */
	public Company update(long id, Company company) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		Validations.verifyNotEmpty(company);
		company.setId(id);
		if (! instance.getUsername().equals(company.getUsername()))
			VerifyUniqueUsername(company);
		instance = repository.save(company);
		return instance;
	}

	/**
	 * Removes company from database.<br>
	 * Verifies company is logged in to perform removal and if it doesn't own any active coupons.
	 * @throws UserInputException if company isn't logged in.
	 * @throws CompanyRemovalException if company has active coupons.
	 */
	public void remove(long id) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		VerifyCouponsExpired();
		repository.delete(instance);
		instance.setId(0);
	}

	/**
	 * Allows to perform a login operation for a registered company.
	 * @param company company to log in
	 * @throws UserInputException with respective message if login data is missing or
	 *  isn't registered.
	 */
	public Company login(Company company) {
		Validations.verifyNotEmpty(company);
		instance = repository.findByUsernameAndPassword(company.getUsername(), 
				company.getPassword());
		if (instance == null)
			throw new UserInputException(UserMessages.INVALID_USERNAME_OR_PASSWORD);
		return instance;
	}
	
	/**
	 * Registers a new client to the database.
	 * @param company company to register
	 * @throws UserInputException with respective message if login data is invalid.
	 */
	public Company register(Company company) {
		company.setId(0);
		Validations.verifyNotEmpty(company);
		VerifyUniqueUsername(company);
		instance = repository.save(company);
		return instance;
	}
	
	/** Allows client to log-out. */
	public void logout(long id) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		instance.setId(0);
	}
	
	/** Retrieves the associated client. */
	public Company viewClient(long id) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		return instance;
	}


	/**	Retrieves coupons of the associated customer from the database. */
	public List<Coupon> getCoupons() {
		Validations.verifyLoggedIn(instance);
		return couponRepository.findAllByOwnerId(instance.getId());
	}
	
	@Override
	public Coupon getCoupon(long id) {
		Validations.verifyLoggedIn(instance);
		Coupon coupon = super.getCoupon(id);
		verifyAccessAllowed(coupon);
		return coupon;
	}
	
	/** Creates a coupon in the database. */
	public Coupon createCoupon(Coupon coupon) {
		Validations.verifyLoggedIn(instance);
		coupon.setId(0);
		setCouponFields(coupon);
		verifyValidCoupon(coupon);
		return couponRepository.save(coupon);
	}

	/** Updates a coupon in the database. */
	public Coupon updateCoupon(long couponId, Coupon coupon) {
		Validations.verifyLoggedIn(instance);
		coupon.setId(couponId);
		setCouponFields(coupon);
		verifyAccessAllowed(coupon);
		verifyValidCoupon(coupon);
		return couponRepository.save(coupon);
	}
	
	/** Removes a coupon from the database. */
	public void removeCoupon(long couponId) {
		Validations.verifyLoggedIn(instance);
		Coupon coupon = couponRepository.findOne(couponId);
		verifyAccessAllowed(coupon);
		if (! Validations.isEmpty(ccService.getCouponPurchases(coupon)))
			throw new CompanyRemovalException(UserMessages.COMPANY_REMOVAL_COUPON_PURCHASED);
		couponRepository.delete(couponId);
	}
	
	
	
	/**
	 * Verifies company's coupons have expired.
	 * @throws CompanyRemovalException with respective message if company has active coupons.
	 */
	private List<Coupon> VerifyCouponsExpired() throws CompanyRemovalException {
		List<Coupon> coupons = super.getAllCouponsByCompany(instance.getId(), true);
		if (! Validations.isEmpty(coupons)) //has active coupons
			throw new CompanyRemovalException(UserMessages.COMPANY_REMOVAL_ACTIVE_COUPONS);
		return coupons;
	}
	
	/**
	 * Verifies the given user-name is unique.<br>
	 * If user-name is occupied, a runtimeException with a corresponding message is thrown.
	 * @param company company to verify
	 * @throws UserInputException if user-name is occupied.
	 */
	private void VerifyUniqueUsername(Company company) {
		company = repository.findByUsernameIgnoreCaseAndIdNot(company.getUsername(), company.getId());
		if (company != null) //not unique
			throw new UserInputException(UserMessages.OCCUPIED_USERNAME);
	}
	
	/**
	 * Verifies coupon is valid and has valid fields (ignores owner).
	 * Otherwise throws UserInputException with respective message.
	 */
	public static void verifyValidCoupon(Coupon coupon) {
		if (coupon == null || coupon.getTitle() == null || coupon.getTitle().trim().isEmpty())
			throw new UserInputException(UserMessages.COUPON_INVALID_TITLE);
		if (coupon.getAmount() <= 0)
			throw new UserInputException(UserMessages.COUPON_INVALID_AMOUNT);
		if (coupon.getType() == null)
			throw new UserInputException(UserMessages.COUPON_INVALID_TYPE);
		if (coupon.getPrice() <= 0)
			throw new UserInputException(UserMessages.COUPON_INVALID_PRICE);
		
		Date startDate = coupon.getStartDate();
		Date endDate = coupon.getEndDate();
		
		verifyValidStartDate(startDate);
		verifyValidEndDate(endDate);
		if (startDate.after(endDate))
			throw new UserInputException(UserMessages.COUPON_DATES_CONFLICT);
	}
	
	/** 
	 * Verifies start-date is not before the current date. 
	 * Otherwise throws UserInputException with respective message.
	 */
	public static void verifyValidStartDate(Date startDate) {
		int TWENTY_FOUR_HOURS = 86400000;
		Date current = new Date(new java.util.Date().getTime() - TWENTY_FOUR_HOURS);
		if (startDate == null || current.after(startDate))
			throw new UserInputException(UserMessages.COUPON_INVALID_START_DATE);
	}
	
	/** 
	 * Verifies end-date is not before the current date. 
	 *  Otherwise throws UserInputException with respective message.
	 */
	public static void verifyValidEndDate(Date endDate) {
		Date current = new Date(new java.util.Date().getTime());
		if (endDate == null || endDate.before(current))
			throw new UserInputException(UserMessages.COUPON_INVALID_END_DATE);
	}
	
	/** Sets coupon's owner to {@link CompanyServiceImpl#instance} and sets type. */
	private void setCouponFields(Coupon coupon) {
		coupon.setOwner(instance);
		coupon.setType(typeHandler.getType(coupon));
	}
	
	/** Verifies company owns the coupon. */
	private void verifyAccessAllowed(Coupon coupon) {
		if (! coupon.getOwner().equals(instance))
			throw new UserInputException(UserMessages.ACCESS_DENIED);
	}
	
}
