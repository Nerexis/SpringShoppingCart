package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.OrderService;

@Controller
@Configuration
@ComponentScan
public class OrderController extends AbstractShopController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/makeOrder")
	public String makeOrder(Model model) {
		this.addCartEntries(model);
		return "makeOrder";
	}

	@PostMapping(value = "/makeOrderAction")
	public ModelAndView makeOrderAction(ModelAndView model,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "surname") String surname,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "phone") String phone) {

		orderService.makeOrderForCurrentUser(name, surname, address, phone);

		model.setViewName("message");
		model.addObject("message", "Pomyœlnie z³o¿ono zamówienie! :)");
		return model;
	}
}
