package org.miri.client.controllers;

import java.net.URI;
import java.util.List;

import org.miri.api.CompanyService;
import org.miri.core.beans.Coupon;
import org.miri.core.beans.clients.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A RestController that exposes the company controller.<br>
 * Mapped to {@code appBasePath}:/cp.
 * @author Miri Yehezkel
 * @see RestController
 */
@Scope("session")
@RestController
@RequestMapping("/cp")
public class CompanyCtrl {
	private static String COMPANY_INSTANCE_PATH = "/cp/";
	
	@Autowired
	private CompanyService service;
	

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Company> login(@RequestBody Company company) {
		company = service.login(company);
		return ResponseEntity.ok()
				.location(buildClientUri(company))
				.build();
	}
	
	@PostMapping("/register")
	public @ResponseBody ResponseEntity<Void> register(@RequestBody Company company) {
		company = service.register(company);
		return ResponseEntity
				.created(buildClientUri(company))
				.build();
	}
	
	@GetMapping("/logout/{id}")
	public @ResponseBody ResponseEntity<Void> logout(@PathVariable("id") long id) {
		service.logout(id);
		return ResponseEntity
				.noContent()
				.location(Constants.buildBaseUri())
				.build();
	}
	
	
	
	@GetMapping("/{id}")
	public @ResponseBody Company viewClient(@PathVariable("id") long id) {
		return service.viewClient(id);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody Company update(@PathVariable("id") long id,
			 @RequestBody Company company) {
		return service.update(id, company);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Void> remove(@PathVariable("id") long id) {
		service.remove(id);
		return ResponseEntity
				.noContent()
				.location(Constants.buildBaseUri())
				.build();
	}
	
	
	
	@GetMapping("/coupons")
	public @ResponseBody List<Coupon> getCoupons() {
		return service.getCoupons();
	}
	
	@PostMapping("/coupons")
	public @ResponseBody ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
		coupon = service.createCoupon(coupon);
		return ResponseEntity
				.created(Constants.buildCouponUri(coupon))
				.build();
	}
	
	@GetMapping("/coupons/{couponId}")
	public @ResponseBody Coupon getCoupon(@PathVariable("couponId") long couponId) {
		return service.getCoupon(couponId);
	}
	
	@PutMapping("/coupons/{couponId}")
	public @ResponseBody ResponseEntity<Coupon> updateCoupon(@PathVariable("couponId") long couponId,
			 @RequestBody Coupon coupon) {
		coupon = service.updateCoupon(couponId, coupon);
		return ResponseEntity
				.ok()
				.location(Constants.buildCouponUri(coupon))
				.build();
	}
	
	@DeleteMapping("/coupons/{couponId}")
	public @ResponseBody ResponseEntity<Void> removeCoupon(@PathVariable("couponId") long couponId) {
		service.removeCoupon(couponId);
		return ResponseEntity
				.noContent()
				.location(Constants.buildBaseUri())
				.build();
	}
	
	
	
	
	/** Returns a URI for given company. */
	private URI buildClientUri(Company company) {
		return Constants.buildUri(COMPANY_INSTANCE_PATH + company.getId());
	}
	
}
