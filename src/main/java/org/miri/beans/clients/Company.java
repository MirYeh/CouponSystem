package org.miri.beans.clients;

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
 * Represents a Company entity in the database.
 * @author Miri Yehezkel
 * @see Client
 */
@Entity @Component
public class Company extends Client implements Serializable {
	private static final long serialVersionUID = -3916313550659930832L;
	
	/** No-args constructor */
	public Company() {
		super();
	}
	
	/**
	 * Allows to construct a Company entity with user name and password.
	 * @param username Company's user name
	 * @param password Company's password
	 */
	public Company(String username, String password) {
		super(username, password);
	}
	
	/**
	 * Allows to construct a Company entity with user name, password and email.
	 * @param username Company's user name
	 * @param password Company's password
	 * @param email Company's email address
	 */
	public Company(String username, String password, String email) {
		super(username, password, email);
	}
	
	/**
	 * Generates a hash code for a company.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, username);
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
	
	/**
	 * Checks if objects are equal. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj instanceof Company)
			return (this.id == ((Company)obj).id);
		return false;
	}
	
	
	
}
