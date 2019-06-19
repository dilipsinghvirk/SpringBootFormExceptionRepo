package com.formsub.exam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService service;

	public HttpSession session=null;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String name, 
			@RequestParam String password,HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("isValid", "True");
		boolean isValidUser = service.validateUser(name, password);
		if (!isValidUser && session!=null) {
			model.put(ErrorMessageConstant.ERROR_MSG, ErrorMessageConstant.INVALID_CREDENTIAL);
			return "login";
		}
		model.put("name", name);
		model.put("password", password);
		return "welcome";
	}
	
	@GetMapping("/logout")
	public String logout(ModelMap model) {
		try {
		if(session!=null ) {
			session.invalidate();
		    session.setAttribute("isValid", null);
		}
		}catch(Exception e) {
			model.put(ErrorMessageConstant.ERROR_MSG,ErrorMessageConstant.SESSION_INVALIDATE);
			System.out.println(e.getMessage());
		}
		return "redirect:/login";
	}

}
