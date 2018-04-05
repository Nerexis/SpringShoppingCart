package service;

import java.util.List;

import model.CartEntry;
import model.Product;
import model.User;

public interface CartEntryService {
	void save(CartEntry cartEntry);

	void update(CartEntry cartEntry);

	List<CartEntry> list(String username);

	List<CartEntry> listForNonOrderedForCurrentUser();

	CartEntry createCartEntryForUser(User user, Product product, int quantity);

	void addCartEntryForCurrentUser(int idProduct, int quantity);

	void remove(int cartEntryId);

	void changeQuantity(int cartEntryId, int newQuantity);

}
