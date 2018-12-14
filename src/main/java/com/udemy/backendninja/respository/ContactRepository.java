package com.udemy.backendninja.respository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.backendninja.entity.Contact;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContactRepository.
 */
@Repository("contactRepository")  // nos creamos el repositorio para poder añadir un contacto nuevo. Extiende JpaRepository para tener toda la funcionalidad y los métodos de JPA sobre la entidad Contact
public interface ContactRepository extends JpaRepository<Contact, Serializable> {
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the contact
	 */
	public abstract Contact findById(int id); // se lo tuvimos que añadir para los borrados de contactos (video 057)

}
