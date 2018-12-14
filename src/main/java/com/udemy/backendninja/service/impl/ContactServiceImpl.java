package com.udemy.backendninja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.udemy.backendninja.component.ContactConverter;
import com.udemy.backendninja.controller.LoginController;
import com.udemy.backendninja.entity.Contact;
import com.udemy.backendninja.model.ContactModel;
import com.udemy.backendninja.respository.ContactRepository;
import com.udemy.backendninja.service.ContactService;


@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{
	
	private static final Log LOG = LogFactory.getLog(ContactServiceImpl.class);
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	
	//@PreAuthorize("permitAll()")
	@Override
	public ContactModel addContact(ContactModel contactModel) {    // como el repository.save trabaja con entidades y aquí tenemos ContactModel debemos crear un converter que lo pase de ContactModel a entidad contact
															// para eso usamos el component.ContactConverter.java	
		
		Contact contact =  contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
		 // una vez trabajado el .save que devuelve una entidad debemos otra vez pasar a modelo que es lo que trabaja el controlador. Por eso usamos el contactConverter otra vez en el return		
		return contactConverter.convertContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		LOG.info("Entrando en listAllContacts()");
		List<Contact> contacts = contactRepository.findAll(); // findAll devuelve entities
		
		List<ContactModel> contactsModel = new ArrayList<ContactModel>();		
		
		// convertimos todas las entities que devuelve el findAll en models que es lo que maneja el controller
		for(Contact contact : contacts) {
			LOG.info("Contacto actual: " + contact.toString());
			LOG.info("ContactsModel actual: " + contactConverter.convertContact2ContactModel(contact).toString());
			contactsModel.add(contactConverter.convertContact2ContactModel(contact));
			
		}
		return contactsModel;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContact2ContactModel(findContactById(id));
		
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if(contact != null) {
			contactRepository.delete(contact);
		}
	}
	
	
	
	

}
