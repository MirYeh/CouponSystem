package org.miri.core.system;

import java.util.List;

import javax.annotation.PreDestroy;

import org.miri.api.repositories.CouponRepository;
import org.miri.core.beans.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class DailyCouponTask implements Runnable {
	private boolean quit;
	//convert milliseconds to 24 hours
	private static final int TWENTY_FOUR_HOURS =	1000 * 60 * 60 * 24;
	
	@Autowired
	CouponRepository couponRepository;
	
	
	/**No-arguments Constructor */
	DailyCouponTask() {
		quit = false;
	}
	
	public void run() {
		while (! quit) {
			checkExpiredCoupons();
			try {
				Thread.sleep(TWENTY_FOUR_HOURS);
			} catch (InterruptedException exc) {
				stopTask();
			}
		}	
	}
	
	/**
	 * Stops Daily Coupon Task
	 * @see DailyCouponTask#run()
	 */ 
	public void stopTask () {
		this.quit = true;
	}
	
	public void startTask() {
		quit = false;
	}
	
	private void checkExpiredCoupons() {
		List<Coupon> expiredCoupons = couponRepository.findActiveExpiredCoupons();

		for (Coupon curr : expiredCoupons) {
			curr.setIsActive(false);
			couponRepository.save(curr);
		}
		
		expiredCoupons = couponRepository.findActiveExpiredCoupons();
		
	}
	
	@PreDestroy
	public void destroy() {
		stopTask();
	}
	
	
}
