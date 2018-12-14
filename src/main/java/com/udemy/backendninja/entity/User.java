package com.udemy.backendninja.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Entity
@Table(name = "users")
public class User {
	
	/** The username. */
	@Id
	@Column(name = "username" , unique = true, nullable = false, length = 45)
	private String username;
	
	/** The password. */
	@Column(name = "password" ,  nullable = false, length = 60) // aunque le demos una contraseña de 3 caracteres en bbdd se almacenará en 60
	private String password;
	
	/** The enabled. */
	@Column(name = "enabled" ,  nullable = false)
	private boolean enabled;
	
	/** The user role. */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user") //un usuario puede tener muchos roles. EAGER PARA QUE AL LLAMAR AL USER SE TRAIGA EL SET DE USERROLE
	private Set<UserRole> userRole = new HashSet<UserRole>();
	
	// constructores
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
		
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param enabled the enabled
	 */
	public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param enabled the enabled
	 * @param userRole the user role
	 */
	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}
	
	// getters y setters

	/**
	 * Instantiates a new user.
	 *
	 * @param username2 the username 2
	 * @param password2 the password 2
	 * @param enabled2 the enabled 2
	 * @param b the b
	 * @param c the c
	 * @param d the d
	 * @param authorities the authorities
	 */
	public User(String username2, String password2, boolean enabled2, boolean b, boolean c, boolean d,
			List<GrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
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

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	/**
	 * Sets the user role.
	 *
	 * @param userRole the new user role
	 */
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	
	
	
	
	
}
