package org.miri.core.beans.clients;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

/**
 * Represents a Customer entity in the database.
 * @author Miri Yehezkel
 * @see Client
 */
@Entity @Component
public class Customer extends Client implements Serializable {
	private static final long serialVersionUID = -1023373016696394491L;
	
	private String personalName;
	
	/** No-args constructor */
	public Customer() {
		super();
	}
	
	/**
	 * Allows to construct a Customer entity with user name and password.
	 * @param username Customer's user name
	 * @param password Customer's password
	 */
	public Customer(String username, String password) {
		super(username, password);
	}
	
	/**
	 * Allows to construct a Customer entity with user name, password and email.
	 * @param username Customer's user name
	 * @param password Customer's password
	 * @param email Customer's email address
	 * @param personalName Customer's personal name
	 */
	public Customer(String username, String password, String email, String personalName) {
		super(username, password, email);
		this.personalName = personalName;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	@Column(unique=true, nullable=false)
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	@Column(nullable=false)
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	@Column(updatable=false)
	public Date getJoined() { return joined; }
	public void setJoined(Date joined) { 
		if (this.joined == null)
			this.joined = joined;
	}
	
	public String getPersonalName() { return personalName; }
	public void setPersonalName(String personalName) { this.personalName = personalName; }
	
	/**
	 * Generates a hash code for a customer.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, username);
	}
	
	/**
	 * Checks if objects are equal. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj instanceof Customer)
			return (this.id == ((Customer)obj).id);
		return false;
	}
	
}
