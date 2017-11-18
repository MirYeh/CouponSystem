package org.miri.client.controllers;

import java.net.URI;
import java.util.List;

import org.miri.api.CustomerService;
import org.miri.beans.Coupon;
import org.miri.beans.CustomerCoupon;
import org.miri.beans.clients.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A RestController that exposes the customer controller.<br>
 * Mapped to {@code appBasePath}:/u.
 * @author Miri Yehezkel
 * @see RestController
 */
@Scope("session")
@RestController
@RequestMapping("/u")
public class CustomerCtrl {
	private static String CUSTOMER_INSTANCE_PATH = "/u/";
	
	@Autowired
	private CustomerService service;
	
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Void> login(@RequestBody Customer customer) {
		customer = service.login(customer);
		return ResponseEntity.
				ok()
				.location(buildClientUri(customer))
				.build();
	}
	
	@PostMapping("/register")
	public @ResponseBody ResponseEntity<Void> register(@RequestBody Customer customer) {
		service.register(customer);
		return ResponseEntity
				.created(buildClientUri(customer))
				.build();
	}
	
	@GetMapping("/logout/{id}")
	public @ResponseBody ResponseEntity<Void> logout(@PathVariable("id") long couponId) {
		service.logout(couponId);
		return ResponseEntity
				.noContent()
				.location(Constants.buildBaseUri())
				.build();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Customer viewClient(@PathVariable("id") long id) {
		return service.viewClient(id);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Void> update(@PathVariable("id") long id,
			 @RequestBody Customer customer) {
		customer = service.update(id, customer);
		return ResponseEntity
				.ok()
				.location(buildClientUri(customer))
				.build();
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Void> remove(@PathVariable("id") long id) {
		service.remove(id);
		return ResponseEntity
				.noContent()
				.location(Constants.buildBaseUri())
				.build();
	}
	
	
	@GetMapping("/coupon/all")
	public @ResponseBody List<Coupon> getCoupons() {
		return service.getCoupons();
	}
	
	@PostMapping("/coupon")
	public @ResponseBody ResponseEntity<Void> purchaseCoupon(
			@RequestBody CustomerCoupon customerCoupon) {
		customerCoupon = service.purchaseCoupon(
				customerCoupon.getCoupon().getId(), customerCoupon.getAmount());
		return ResponseEntity
				.ok()
				.location(Constants.buildCouponUri(customerCoupon.getCoupon()))
				.build();
	}
	
	@GetMapping("/coupon/{purchaseId}")
	public @ResponseBody ResponseEntity<CustomerCoupon> getPurchase(@PathVariable("purchaseId") long purchaseId) {
		CustomerCoupon purchase = service.getPurchase(purchaseId);
		return ResponseEntity
				.ok()
				.location(Constants.buildCouponUri(purchase.getCoupon()))
				.body(purchase);
	}
	
	
	
	
	/** Returns a URI for given company. */
	private URI buildClientUri(Customer customer) {
		return Constants.buildUri(CUSTOMER_INSTANCE_PATH + customer.getId());
	}
	
}
