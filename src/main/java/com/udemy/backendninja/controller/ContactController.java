package com.udemy.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.backendninja.constant.ViewConstant;
import com.udemy.backendninja.entity.Contact;
import com.udemy.backendninja.model.ContactModel;
import com.udemy.backendninja.service.ContactService;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactController.
 */
@Controller
//   @PreAuthorize("permitAll()")
@RequestMapping("/contacts")
public class ContactController {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	
	/** The contact service. */
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	/**
	 * Cancel.
	 *
	 * @return the string
	 */
	@GetMapping("/cancel") // como viene de un simple link no es un formulario luego viene por get...
	public String cancel() {
		return "redirect:showcontacts";
	}
	
	
	// ver video 066 el fichero SecurityConfiguration. Le podemos meter expresiones de spring como vemos. Si entramos con otro rol nos manda al 403.html (te lo dice la propia plantilla que no la encuentra...)
	// también puede metérsele OR..    	@PreAuthorize("hasRole('ROLE_USER') or/and hasRole('ROLE_ADMIN')")     @PreAuthorize("permitAll()")
	/**
	 * Redirect contact form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	// también las podemos meter a nivel de clase e incluso en los services
	@PreAuthorize("hasRole('ROLE_USER')")   
	@GetMapping("/contactform")  // como el método va a gestionar un modelo se lo añadimos. USAMOS ESTO PARA AÑADIR Y ACTUALIZAR CONTACTOS ya que llama al save en el servicio
	public String redirectContactForm(@RequestParam(name="id", required=false) int id,
			Model model) { 
		
		ContactModel contact = new ContactModel();
		
		if(id != 0) {
			contact = contactService.findContactByIdModel(id);
		}
		
		model.addAttribute("contactModel", contact);
		return ViewConstant.CONTACT_FORM;   // LAS DEFINICIONES DE LAS VISTAS ESTÁN EN ViewConstanta.java del package constant
	}
	
	/**
	 * Adds the contact.
	 *
	 * @param contactModel the contact model
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/addcontacts")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			Model model) { // recibe un modelAttribute con un name correspondiente al objeto del formulario y en java es una clase contactModel. También recibe un model para el mensaje de EXITO
		
		LOG.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
		
		if(contactService.addContact(contactModel) != null) {
			model.addAttribute("result", 1);
		
		}
		else {
			model.addAttribute("result", 0);
		}

		//return ViewConstant.CONTACTS;
		return "redirect:showcontacts";
	}
	
	/**
	 * Show contacts.
	 *
	 * @return the model and view
	 */
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		
		// video 067 PARA PILLAR EL USUARIO AUTENTICADO EN EL CONTROLADOR
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // OJO EL USER ES DE USERDETAILS!!
		mav.addObject("username", user.getUsername());
		
		mav.addObject("contacts", contactService.listAllContacts());
		
		LOG.info("Awita con el mav que es: " + mav.toString());
		
		return mav;	
		
	}
	
	/**
	 * Removes the contact.
	 *
	 * @param id the id
	 * @return the model and view
	 */
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		return showContacts();   // y así devuelve un ModelAndView
	}
}



















