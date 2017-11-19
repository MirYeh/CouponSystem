package org.miri.core.services;

import java.util.List;

import org.miri.api.CustomerService;
import org.miri.api.repositories.CustomerRepository;
import org.miri.core.beans.Coupon;
import org.miri.core.beans.CustomerCoupon;
import org.miri.core.beans.clients.Customer;
import org.miri.core.exceptions.UserInputException;
import org.miri.core.exceptions.UserMessages;
import org.miri.core.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles access of customers to the database.<br>
 * Customer can access account-related methods only after logging in or registering.
 * @author Miri Yehezkel
 * @see CustomerService
 */
@Service
public class CustomerServiceImpl extends GuestServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private Customer instance;
	
	
	/**
	 * Updates customer's data in the database.<br>
	 * Throws UserInputException with corresponding message
	 *  if required fields are missing or if user-name is occupied.
	 * @param customer customer data to update
	 * @return an updated customer instance.
	 * @throws UserInputException if required fields are missing or if user-name is occupied.
	 */
	public Customer update(long id, Customer customer) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		Validations.verifyNotEmpty(customer);
		customer.setId(id);
		if (! instance.getUsername().equals(customer.getUsername()))
			VerifyUniqueUsername(customer);
		customer.setId(id);
		instance = repository.save(customer);
		return instance;
	}

	/**
	 * Removes customer from database.<br>
	 * Verifies customer is logged in to perform removal.
	 * @throws UserInputException if customer isn't logged in.
	 */
	public void remove(long id) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		repository.delete(instance);
		instance.setId(0);
	}

	/**
	 * Allows to perform a login operation for a registered customer.
	 * @param customer customer to log in
	 * @return A customer instance.
	 * @throws UserInputException with respective message if login data is missing or
	 *  isn't registered.
	 */
	public Customer login(Customer customer) {
		Validations.verifyNotEmpty(customer);
		instance = repository.findByUsernameAndPassword(customer.getUsername(),
				 customer.getPassword());
		if (instance == null)
			throw new UserInputException(UserMessages.INVALID_USERNAME_OR_PASSWORD);
		return instance;
	}

	/**
	 * Registers a new client to the database.
	 * @param customer customer to register
	 * @return a customer instance.
	 * @throws UserInputException with respective message if login data is invalid.
	 */
	public Customer register(Customer customer) {
		customer.setId(0);
		Validations.verifyNotEmpty(customer);
		VerifyUniqueUsername(customer);
		instance = repository.save(customer);
		return instance;
	}
	
	/** Allows client to log-out. */
	public void logout(long id) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		instance.setId(0);
	}
	
	/** Retrieves the associated client. */
	public Customer viewClient(long id) {
		Validations.verifyLoggedIn(instance);
		Validations.verifyAccessAllowed(instance, id);
		return instance;
	}
	
	/** Retrieves the associated customer's coupons. */
	public List<Coupon> getCoupons() {
		Validations.verifyLoggedIn(instance);
		return ccService.getCustomerPurchases(instance);
	}
	
	/** Allows a registered customer to purchase a coupon. */
	public CustomerCoupon purchaseCoupon(long couponId, int purchaseAmount) {
		Validations.verifyLoggedIn(instance);
		Coupon coupon = couponRepository.findOne(couponId);
		Validations.verifyNotNull(coupon);
		return ccService.purchase(instance, coupon, purchaseAmount);
	}
	
	/** Retrieves a specific purchase. */
	public CustomerCoupon getPurchase(long purchaseId) {
		Validations.verifyLoggedIn(instance);
		return ccService.getPurchase(purchaseId);
	}
	
	
	
	/**
	 * Verifies the given user-name is unique.<br>
	 * If user-name is occupied, a runtimeException with a corresponding message is thrown.
	 * @param customer customer to verify
	 * @throws UserInputException if user-name is occupied.
	 */
	private void VerifyUniqueUsername(Customer customer) {
		customer = repository.findByUsernameIgnoreCaseAndIdNot(customer.getUsername(), customer.getId());
		if (customer != null) { //not unique
			throw new UserInputException(UserMessages.OCCUPIED_USERNAME);
		}
	}
}
