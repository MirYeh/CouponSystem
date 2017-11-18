package org.miri;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.miri.system.DailyCouponTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CouponSystemApp {
	
	@Autowired
	DailyCouponTask dailyCouponTask;
	
	public static void main(String[] args) {
		SpringApplication.run(CouponSystemApp.class, args);
	}
	
	@PostConstruct
	public void init() {
		Thread dailyTask = new Thread(dailyCouponTask);
		dailyTask.start();
	}
	
	@PreDestroy
	public void destroy() {
		dailyCouponTask.stopTask();
	}
}
