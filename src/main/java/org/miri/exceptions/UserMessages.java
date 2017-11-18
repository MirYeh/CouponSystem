package org.miri.exceptions;

/**
 * Predefined messages to use when throwing an exception.
 * @author Miri Yehezkel
 */
public enum UserMessages {
	//login messages
	INVALID_USERNAME_OR_PASSWORD("Invalid username or password"),
	USER_NOT_LOGGED_IN("User not logged in"),
	OCCUPIED_USERNAME("Username is occupied"),
	EMPTY_USERNMAE("Must enter username"),
	EMPTY_PASSWORD("Must enter password"),
	ACCESS_DENIED("Access denied"),
	
	//coupon messages
	COUPON_INVALID_OWNER("Invalid coupon owner"),
	COUPON_INVALID_TITLE("Invalid coupon title"),
	COUPON_INVALID_START_DATE("Invalid coupon start date"),
	COUPON_INVALID_END_DATE("Invalid coupon end date"),
	COUPON_DATES_CONFLICT("Coupon dates are conflicted"),
	COUPON_INVALID_AMOUNT("Invalid coupon amount"),
	COUPON_INVALID_TYPE("Invalid coupon type"),
	COUPON_INVALID_PRICE("Invalid coupon price"),
	
	//company messages
	COMPANY_REMOVAL_ACTIVE_COUPONS("Unable to delete account: active coupons"),
	COMPANY_REMOVAL_COUPON_PURCHASED("Unable to delete: coupon already purchased"),
	
	//add to user-message
	MAX("(max: %s)")
	;
	private String value;
	
	private UserMessages(String value) {
		this.value = value;
	}
	
	public String getValue() { return value; }
}
