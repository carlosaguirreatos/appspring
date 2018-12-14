package com.udemy.backendninja.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

//import com.udemy.backendninja.entity.User;
import com.udemy.backendninja.entity.UserRole;
import com.udemy.backendninja.respository.UserRepository;
// VIDEO 063
@Service("userService")
public class UserService implements UserDetailsService{ // UserDetailsService es de SPRING para autenticar usuarios

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // éste método llama al repository y su findByUsername que devuelve un User que habrá que pasar a UserDetails
		com.udemy.backendninja.entity.User user = userRepository.findByUsername(username); // NO IMPORTAMOS ESTE USER ENTITY PORQUE CHOCA CON EL USER DE userdetails.
		
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		
		return buildUser(user, authorities );
	}
	
	
	// necesitamos dos métodos para pasar de User a UserDetails. Son los siguientes:
	
	private User buildUser(com.udemy.backendninja.entity.User user, List<GrantedAuthority>  authorities) { // el authorities en realidad es nuestra entidad rol pero interno de Spring Security
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities); //  accountNonExpired, credentialsNonExpired, accountNonLocked lo ponemos todo a true..
		
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){  // el set de la entidad User
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		// recorremos los userRole y por cada vuelta añade en auths el rol correspondiente (es una especie de conversion de roles a "roles de spring")
		
		for(UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
		
	}

}














