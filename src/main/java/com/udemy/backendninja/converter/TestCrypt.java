package com.udemy.backendninja.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// TODO: Auto-generated Javadoc
/**
 * The Class TestCrypt.
 */
// VIDEO 065
public class TestCrypt {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("user")); // para testear y ver cómo se codifica la contraseña. Boton derecho RUN as java application y copiamos la contraseña. Luego la metemos a lo burro en BBDD.
		
		// salió ésto: $2a$10$FohyePdp1Qlz5tCekpQv6ePBcyzGKU8OI25/K5FILBkIPRxcs.ELa
		
		// lo metemos a mano
		
		// INSERT INTO users ( username, password, enabled) values ( 'user',true, '$2a$10$FohyePdp1Qlz5tCekpQv6ePBcyzGKU8OI25/K5FILBkIPRxcs.ELa')
		// INSERT INTO user_roles (user_role_id, role, username) values (1,'ROLE_USER', 'user')


	}

}
