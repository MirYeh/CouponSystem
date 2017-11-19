package org.miri.core.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.miri.core.beans.clients.Customer;
import org.springframework.stereotype.Component;

@Entity @Component
public class CustomerCoupon implements Serializable {
	private static final long serialVersionUID = -2625770243484681945L;
	
	private long id;
	private Customer customer;
	private Coupon coupon;
	private Date date;
	private int amount;
	private double totalPrice;
	
	CustomerCoupon() {
		date = new Date(new java.util.Date().getTime());
	}
	
	public CustomerCoupon(Customer customer, Coupon coupon, int amount) {
		this();
		this.customer = customer;
		this.coupon = coupon;
		this.amount = amount;
		totalPrice = coupon.getPrice() * amount;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE, optional=false)
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE, optional=false)
	public Coupon getCoupon() { return coupon; }
	public void setCoupon(Coupon coupon) { this.coupon = coupon; }
	
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	
	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount; }
	
	public double getTotalPrice() { return totalPrice; }
	public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
	
	
	/**
	 * Generates a hash code for a CustomerCoupon.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, totalPrice);
	}
	
	/**
	 * Checks if objects are equal. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj instanceof CustomerCoupon)
			return (this.id == ((CustomerCoupon)obj).id);
		return false;
	}
	
}
