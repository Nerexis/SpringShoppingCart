package controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Configuration
@ComponentScan
public class RootController extends AbstractShopController {

	@GetMapping(value = { "/welcome", "/" }, produces = "text/html")
	public String welcome(Model model) {
		System.out.println("Entered welcome request");
		this.addCartEntries(model);
		return "welcome";
	}

	@RequestMapping(value = "/error")
	public String errorMessage(Model model,
			@RequestParam(value = "message") String message) {
		model.addAttribute("message", message);
		return "error";
	}

}
