package org.miri.core.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.miri.core.beans.clients.Company;


/**
 * Represents a Coupon entity in the database.
 * @author Miri Yehezkel
 * @see Type
 */
@Entity
public class Coupon implements Serializable {
	private static final long serialVersionUID = -7740435785066598340L;
	
	protected long id;
	protected Company owner;
	protected String title;
	protected Date startDate;
	protected Date endDate;
	protected int amount;
	protected Type type;
	protected double price;
	protected boolean isActive;
	protected String message;
	protected String image;
	
	/**	No-args constructor */
	public Coupon() {}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false,cascade={CascadeType.MERGE, CascadeType.REMOVE})
	public Company getOwner() { return owner; }
	public void setOwner(Company owner) { this.owner = owner; }
	
	@Column(nullable=false)
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	@Column(nullable=false, updatable=false)
	public Date getStartDate() { return startDate; }
	public void setStartDate(Date startDate) {
		if (this.startDate == null)
			this.startDate = startDate;
	}
	
	@Column(nullable=false, updatable=false)
	public Date getEndDate() { return endDate; }
	public void setEndDate(Date endDate) {
		if (this.endDate == null)
			this.endDate = endDate;
	}
	
	@Column(nullable=false)
	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount; }
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE, optional=false)
	public Type getType() { return type; }
	public void setType(Type type) { this.type = type; }
	
	@Column(nullable=false)
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	
	@Column(nullable=false)
	public boolean getIsActive() { return isActive; }
	public void setIsActive(boolean isActive) { this.isActive = isActive; }
	
	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }
	
	@Lob
	public String getImage() { return image; }
	public void setImage(String image) { this.image = image; }
	
	/**
	 * Generates a hash code for a company.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, owner, title);
	}
	
	/**
	 * Checks if objects are equal. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj instanceof Coupon)
			return (this.id == ((Coupon)obj).id);
		return false;
	}
	
	
}
