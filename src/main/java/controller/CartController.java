package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.CartEntryService;

@Controller
@Configuration
@ComponentScan
public class CartController {
	@Autowired
	private CartEntryService cartEntryService;

	@GetMapping(value = "/cartEntries")
	public String cartEntries(Model model) {
		System.out.println("Requested cartEntries");
		model.addAttribute("cartEntries",
				cartEntryService.list(SecurityContextHolder.getContext()
						.getAuthentication().getName()));
		return "cartEntries";
	}

	@PostMapping(value = "/cartEntryRemove")
	public String cartEntryRemove(Model model,
			@RequestParam(value = "idCartEntry") int cartEntryId) {
		System.out.println("Cart entry remove");
		cartEntryService.remove(cartEntryId);
		return "redirect:/cartEntries";
	}

	@PostMapping(value = "/cartChangeQuantity")
	public ModelAndView cartEntryChangeQuantity(ModelAndView model,
			@RequestParam(value = "idCartEntry") int cartEntryId,
			@RequestParam(value = "newQuantity") int newQuantity) {
		System.out.println("Cart entry change quantity");
		try {
			cartEntryService.changeQuantity(cartEntryId, newQuantity);

		} catch (Exception e) {
			ModelAndView errorMsg = new ModelAndView();
			errorMsg.setViewName("error");
			errorMsg.addObject("message", e.getMessage());
			return errorMsg;
		}

		model.setViewName("redirect:/cartEntries");
		return model;
		// return "redirect:/cartEntries";
	}

	@RequestMapping(value = "/cartEntryAdd")
	public ModelAndView cartEntryAdd(Model model,
			@RequestParam(value = "idProduct") int idProduct,
			@RequestParam(value = "quantity") int quantity) {
		System.out.println("Cart entry add");

		if (!SecurityContextHolder.getContext().getAuthentication()
				.isAuthenticated()) {
			return new ModelAndView("403");
		}

		try {
			cartEntryService.addCartEntryForCurrentUser(idProduct, quantity);
		} catch (Exception e) {
			ModelAndView errorMsg = new ModelAndView();
			errorMsg.setViewName("error");
			errorMsg.addObject("message", e.getMessage());
			return errorMsg;
		}
		return new ModelAndView("redirect:/productList");
	}
}
