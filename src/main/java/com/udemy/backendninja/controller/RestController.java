package com.udemy.backendninja.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udemy.backendninja.model.ContactModel;


// video 70
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest") //  por aquí vendrán todas las peticiones que empiecen por /rest
public class RestController {
	
	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest(){    //devolverá un json con datos y la vista del cliente (Angular) ya monta su template
		
		ContactModel cm = new ContactModel(2, "Pololo", "garcia", "456456", "La orotava");
		
		// return new ResponseEntity<String>("OK!", HttpStatus.OK); metido a pelo
		return new ResponseEntity<ContactModel>(cm, HttpStatus.OK); 
		
	}
}
