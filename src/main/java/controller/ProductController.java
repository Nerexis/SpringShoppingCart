package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.ProductService;

@Controller
@Configuration
@ComponentScan
public class ProductController extends AbstractShopController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/productAdd")
	public String productAdd(Model model) {
		this.addCartEntries(model);
		return "productAdd";
	}

	@RequestMapping(value = "/productRemove")
	public ModelAndView productRemove(
			@RequestParam(value = "idProduct") int idProduct) {
		productService.remove(idProduct);
		return new ModelAndView("redirect:/productList");
	}

	@PostMapping(value = "/productAddAction")
	public ModelAndView productAddAction(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "price") double price) {
		try {
			productService.addNewProduct(name, description, price);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return new ModelAndView("redirect:/productList");
	}

	@GetMapping(value = "/productList")
	public String productList(Model model) {
		System.out.println("Requested productList");

		this.addCartEntries(model);

		model.addAttribute("products", productService.list());
		return "productList";
	}
}
