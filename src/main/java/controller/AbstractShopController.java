package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import service.CartEntryService;

public abstract class AbstractShopController {
	@Autowired
	CartEntryService cartEntryService;

	void addCartEntries(Model model) {

		model.addAttribute("cartEntries",
				cartEntryService.listForNonOrderedForCurrentUser());
	}
}
