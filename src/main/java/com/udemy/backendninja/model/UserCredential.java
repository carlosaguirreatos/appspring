package com.udemy.backendninja.model;

// TODO: Auto-generated Javadoc
/**
 * The Class UserCredential.
 */
public class UserCredential {

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;

	// constructores

	/**
	 * Instantiates a new user credential.
	 */
	public UserCredential() {
	}

	/**
	 * Instantiates a new user credential.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public UserCredential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	// Getters y Setters
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	// ToString
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserCredential [username=" + username + ", password=" + password + "]";
	}
	
	

}
