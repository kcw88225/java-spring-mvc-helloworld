package com.in28minutes.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.jee.LoginService;

@Controller
public class LoginController {
	
	//TODO: DI
	private LoginService service = new LoginService();
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name, 
			@RequestParam String password,
			ModelMap model) {
		if(!service.validateUser(name, password)){
			model.put("errorMessage", "not valid");
			return "login";
			
		}
		
		model.put("name", name);
		model.put("password", password);
		
		return "welcome";
	}
}
