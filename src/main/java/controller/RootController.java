package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.Authority;
import model.CartEntry;
import model.Product;
import model.User;
import service.CartEntryService;
import service.ProductService;
import service.UserService;

@Controller
@Configuration
@ComponentScan
public class RootController {
	@Autowired
	private ProductService productService;

	@Autowired
	//@Qualifier("userDetailsService")
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CartEntryService cartEntryService;

	@GetMapping(value = {"/welcome","/"}, produces="text/html")
	public String welcome(Model model) {
		System.out.println("Entered welcome request");
		return "welcome";
	}

	@GetMapping(value = "/productList")
	public String productList(Model model) {
		System.out.println("Requested productList");
		model.addAttribute("products", productService.list());
		return "productList";
	}
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		System.out.println("Login action");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Nie udana pr�ba logowania!");
		}

		if (logout != null) {
			model.addObject("msg", "Pomy�lnie zalogowano!");
		}
		model.setViewName("login");

		return model;

	}
	@RequestMapping(value = "/productAdd")
	public String productAdd() {
		return "productAdd";
	}

	@PostMapping(value = "/productAddAction")
	public ModelAndView productAddAction(@RequestParam(value="name") String name,
			@RequestParam(value = "description") String description,
			@RequestParam(value="price") double price) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		try {
			productService.save(product);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return new ModelAndView("redirect:/productList");
	}
	@GetMapping(value="/register")
	public String register(Model model) {
		return "register";
	}
	@RequestMapping(value="/productRemove")
	public ModelAndView productRemove(@RequestParam(value="id_product") int idProduct) {
		productService.remove(idProduct);
		return new ModelAndView("redirect:/productList");
	}
	@RequestMapping(value = "/registerAction")
	public String register(Model model, @RequestParam String username,
			@RequestParam String password) {

		System.out.println("Registering new user...");

		User user = new User();

		Authority authority = new Authority("ROLE_ADMIN"); // debug
		authority.setUser(user);

		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEnabled(1);
		user.getAuthorities().add(authority);
		try {
			userService.save(user);
		} catch(Exception e) {
			return registerStatus(model,e.getMessage());
		}

		System.out.println("User registered");
		return registerStatus(model,"Rejestracja powiod�a si�!");
	}
	@RequestMapping(value = "/registerStatus")
	public String registerStatus(Model model, @RequestParam(value="message") String message) {
		model.addAttribute("message", message);
		return "registerStatus";
	}
	@RequestMapping(value = "/error")
	public String errorMessage(Model model, @RequestParam(value="message") String message) {
		model.addAttribute("message", message);
		return "error";
	}

	@RequestMapping(value = "/cartEntryAdd")
	public ModelAndView cartEntryAdd(Model model, @RequestParam(value="id_product") int idProduct,
			@RequestParam(value="quantity") int quantity) {
		System.out.println("Cart entry add");

		if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			return new ModelAndView("403");
		}

		try {
			System.out.println("Adding cart entry...");
			CartEntry cartEntry = new CartEntry();
			cartEntry.setProduct(productService.getProductById(idProduct));
			cartEntry.setQuantity(quantity);
			cartEntry.setUser(userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
			cartEntryService.save(cartEntry);
		} catch(Exception e) {
			return new ModelAndView("error", "", model);
		}
		return new ModelAndView("redirect:/productList");
	}

}
