package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.UserService;

@Controller
@Configuration
@ComponentScan
public class UserController {

	@Autowired
	// @Qualifier("userDetailsService")
	private UserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {

		System.out.println("Login action");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Nie udana próba logowania!");
		}

		if (logout != null) {
			model.addObject("msg", "Pomyœlnie zalogowano!");
		}
		model.setViewName("login");

		return model;

	}

	@GetMapping(value = "/register")
	public String register(Model model) {
		return "register";
	}

	@RequestMapping(value = "/registerAction")
	public String register(Model model, @RequestParam String username,
			@RequestParam String password) {

		System.out.println("Registering new user...");
		try {
			userService.registerNewUser(username, password);
		} catch (Exception e) {
			return registerStatus(model, e.getMessage());
		}

		System.out.println("User registered");
		return registerStatus(model, "Rejestracja powiod³a siê!");
	}

	@RequestMapping(value = "/registerStatus")
	public String registerStatus(Model model,
			@RequestParam(value = "message") String message) {
		model.addAttribute("message", message);
		return "registerStatus";
	}
}
