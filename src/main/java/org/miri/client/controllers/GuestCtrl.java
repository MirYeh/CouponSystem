package org.miri.client.controllers;

import java.util.List;

import org.miri.api.GuestService;
import org.miri.beans.Coupon;
import org.miri.beans.clients.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RestController that exposes the guest controller.<br>
 * Mapped to the application's base path.
 * @author Miri Yehezkel
 * @see RestController
 */
@Scope("session")
@RestController
@RequestMapping("/")
public class GuestCtrl {
	
	@Autowired
	private GuestService service;
	
	
	public GuestCtrl(){}
	
	@GetMapping("/coupons/{couponId}")
	public @ResponseBody Coupon getCoupon(@PathVariable("couponId") long couponId) {
		return service.getCoupon(couponId);
	}
	
	@GetMapping("/coupons")
	public @ResponseBody List<Coupon> getAllCoupons(
					@RequestParam(name="min", defaultValue="0") double minPrice,
					@RequestParam(name="max", defaultValue=Double.MAX_VALUE+"") double maxPrice,
					@RequestParam(name="type", defaultValue="") String type) {
		return service.getAllCoupons(minPrice, maxPrice, type);
	}
	
	
	@GetMapping("/companies/{companyId}/coupons")
	public @ResponseBody List<Coupon> getAllCouponsByCompany(long companyId) {
		return service.getAllCouponsByCompany(companyId, true);
	}
	
	@GetMapping("/companies/{companyId}")
	public @ResponseBody Company getCompany(@PathVariable("companyId") long companyId) {
		return service.getCompany(companyId);
	}
	
	@GetMapping("/companies")
	public @ResponseBody List<Company> getAllCompanies() {
		return service.getAllCompanies();
	}
	
}
