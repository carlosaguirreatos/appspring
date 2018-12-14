package com.udemy.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.backendninja.constant.ViewConstant;
// TODO: Auto-generated Javadoc
//import com.udemy.backendninja.model.UserCredential;

/**
 * The Class LoginController.
 */
//VIDEO 50
@Controller
public class LoginController {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	/*  
	@GetMapping("/")
	public String redirectToLogin() {
		LOG.info("METHOD: redirectToLogin()");
		return "redirect:/login";
	}
	*/
	
	/**
	 * Show login form.
	 *
	 * @param model the model
	 * @param error the error
	 * @param logout the logout
	 * @return the string
	 */
	@GetMapping("/login")
	public String showLoginForm(Model model,
								@RequestParam(name="error",  required = false) String error,
								@RequestParam(name="logout", required = false) String logout) {
		LOG.info("METHOD: showLoginForm() -- PARAMS: error=" + error + "; logout=" + logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		// model.addAttribute("userCredentials", new UserCredential());ANTES DE IMPLEMENTAR EL SPRING SECURITY..
		LOG.info("Returning to login view");
		return ViewConstant.LOGIN;
	}
	
	/* ANTES DE IMPLEMENTAR EL SPRING SECURITY...
	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential) {
		LOG.info("METHOD: loginCheck() -- PARAMS: userCredentials=" + userCredential.toString());
		
		if(userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {
			LOG.info("Returning to contacts view");
			// return ViewConstant.CONTACTS; // aunque la anotación sea logincheck al final la vista es contacts, pero en el navegador aparecerá como logincheck ¡¡OJO!!
			
			return "redirect:/contacts/showcontacts";
		}
		LOG.info("Redirect to login?error");
		return "redirect:/login?error";  // como redireccionamos a /login que es el método showLoginForm y le enviamos un parámetro (error) pues aquel debe recibirlo con el @RequestParam
	}
	*/
	
	/**
	 * Login check.
	 *
	 * @return the string
	 */
	@GetMapping({"/loginsuccess", "/"})   // tanto el /loginsuccess como / entrarán por aquí
	public String loginCheck() {
		LOG.info("METHOD: loginCheck()");	
		LOG.info("Returning to contacts view");			
		return "redirect:/contacts/showcontacts";
	}
}






















