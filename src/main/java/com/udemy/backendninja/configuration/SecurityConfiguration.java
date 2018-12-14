package com.udemy.backendninja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// TODO: Auto-generated Javadoc
//VIDEO 064

/**
 * The Class SecurityConfiguration.
 */
@Configuration
@EnableWebSecurity     					// para habilitar la seguridad web
@EnableGlobalMethodSecurity(prePostEnabled = true)  // VIDEO 066 nos permite escribir anotaciones en otras clases (pro ejemplo @PreAuthorized) para controlar el acceso a los métodos según la seguirdad. 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {        //para ayudar a guiarnos por la configuración

	
	/** The user service. */
        @Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	
	/**
	 * Configure global.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	// antes que nada necesitamos inyectar un componente de spring que es:
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{ // nos servirá para añadir el userdetailService que hemos creado con anterioridad
		
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());  // el passwoerdEncoder cifra la password en BBDD
	
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {    // usaremos el objeto http QUE CREO QUE NNNNO
		
		http.authorizeRequests()
			.antMatchers("/css/*", "/imgs/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
			.usernameParameter("username").passwordParameter("password")
			.defaultSuccessUrl("/loginsuccess").permitAll()
			.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
		
		// con ésto tenemos la configuración de seguridad añadida. Habrá que modificar el loginController y la plantilla para tener todo perfect
			
	} 
	
	

}
