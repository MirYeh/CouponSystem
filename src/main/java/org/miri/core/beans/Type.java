package org.miri.core.beans;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a Type of coupons in the database.
 * @author Miri Yehezkel
 * @see Coupon
 */
@Entity
public class Type implements Serializable {
	private static final long serialVersionUID = -1652543516394126984L;
	
	private long id;
	private String name;
	
	public Type() {}
	
	public Type(String name) {
		this.name = name;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	@Column(unique=true, nullable=false)
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	/**
	 * Generates a hash code for a Type.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	/**
	 * Checks if objects are equal. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj instanceof Type)
			return (this.id == ((Type)obj).id);
		return false;
	}
	
}
