package org.miri.beans.clients;

import java.sql.Date;

/**
 * Represents a Client object.
 * @author Miri Yehezkel
 * @see Company
 * @see Customer
 */
public abstract class Client {
	
	protected long id; 			//auto
	protected String username;		//must input
	protected String password;		//must input
	protected String email;
	protected Date joined;
	
	/** No-args constructor */
	public Client() {
		joined = new Date(new java.util.Date().getTime());
	}
	
	/**
	 * Allows to construct a Client with user name and password.
	 * @param username Client's user name
	 * @param password Client's password
	 */
	public Client(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Allows to construct a Client with user name, password and email.
	 * @param username Client's user name
	 * @param password Client's password
	 * @param email Client's email address
	 */
	public Client(String username, String password, String email) {
		this(username, password);
		this.email = email;
	}
	
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public Date getJoined() { return joined; }
	public void setJoined(Date joined) { this.joined = joined; }
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object obj);
	
}
